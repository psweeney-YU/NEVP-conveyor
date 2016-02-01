using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

using EDSDKLib;

namespace CanonCameraAppLib
{
    public sealed class CameraAPI
    {
        #region Private Variables

            private EDSDK.EdsCameraAddedHandler edsCameraAddedHandler;

        #endregion

        #region Registered Events

            /// <summary>
            /// Registers camera added events
            /// </summary>
            private void registerEvents()
            {
                edsCameraAddedHandler = new EDSDK.EdsCameraAddedHandler(cameraAddedHandler);

                uint error = EDSDK.EdsSetCameraAddedHandler(edsCameraAddedHandler, IntPtr.Zero);
                
                if (EDSDK.EDS_ERR_OK != error)
                {
                    throw new CameraEventRegistrationException("Unable to register cameraAddedEventHandler!", error);
                }
            }
            
            /// <summary>
            /// Handler for when a camera is connected
            /// </summary>
            /// <param name="inContext">Unmodified IntPtr passed in at event registration.</param>
            /// <returns></returns>
            public uint cameraAddedHandler(IntPtr inContext)
            {
                fireCameraAddedEvent();
                return 0x0;
            }

        #endregion

        #region Public Events

            /// <summary>
            /// Occurs when a camera is connected to the host
            /// </summary>
            public static event CameraAddedEventHandler OnCameraAdded;

        #endregion

        #region Event Invokers

            /// <summary>
            /// Invokes the OnCameraAdded event
            /// </summary>
            private static void fireCameraAddedEvent()
            {
                if (OnCameraAdded != null)
                {
                    OnCameraAdded(new CameraAddedEventArgs());
                }
            }

        #endregion

        #region Instance Methods and Properties

            /// <summary>
            /// Get the list of Camera objects attached to the host
            /// </summary>
            /// <exception cref="CameraException"></exception>    
            /// <exception cref="CameraNotFoundException"></exception>
            /// <exception cref="CameraCommunicationException"></exception>            
            public List<Camera> Cameras
            {
                get
                {
                    List<Camera> cameras = new List<Camera>();

                    IntPtr cameraList = new IntPtr();

                    uint error = EDSDK.EdsGetCameraList(out cameraList);
                    if (EDSDK.EDS_ERR_OK != error)
                    {
                        throw new CameraException("Unable to get camera list!", error);
                    }
                    else
                    {
                        int cameraCount = 0;
                         error = EDSDK.EdsGetChildCount(cameraList, out cameraCount);
                        
                        if (EDSDK.EDS_ERR_OK != error)
                        {
                            throw new CameraException("Unable to get camera count!", error);
                        }
                        else
                        {
                            if (cameraCount <= 0)
                            {
                                throw new CameraNotFoundException("No camera was detected to be connected to the host.");
                            }

                            for (int i = 0; i < cameraCount; i++)
                            {
                                IntPtr cameraDev = new IntPtr(i);

                                 error = EDSDK.EdsGetChildAtIndex(cameraList, i, out cameraDev);
                                if (EDSDK.EDS_ERR_OK !=  error)
                                {
                                    throw new CameraException("Unable to get camera at index (" + i +")", error);
                                }

                                EDSDK.EdsDeviceInfo deviceInfo;
                                 error = EDSDK.EdsGetDeviceInfo(cameraDev, out deviceInfo);

                                if (EDSDK.EDS_ERR_OK != error)
                                {
                                    throw new CameraException("Unable to get device information at index (" + i + ")", error);
                                }

                                 error = EDSDK.EdsOpenSession(cameraDev);
                                if (EDSDK.EDS_ERR_OK !=  error)
                                {
                                    System.Console.WriteLine(error);
                                    switch ( error)
                                    {
                                        case EDSDK.EDS_ERR_DEVICE_NOT_FOUND:
                                            throw new CameraCommunicationException("Unable to open session with camera at index (" + i + ") [" + deviceInfo.szDeviceDescription + "] because it was not found!");

                                        default:
                                            throw new CameraCommunicationException("Unable to open session with camera at index (" + i + ") [" + deviceInfo.szDeviceDescription + "]!",  error);
                                    }
                                }


                                Camera camera = new Camera(cameraDev);

                                camera.Name = deviceInfo.szDeviceDescription;
                                camera.PortName = deviceInfo.szPortName;

                                //  Firmware Version
                                EDSDK.EdsTime firmwareDate;
                                error = EDSDK.EdsGetPropertyData(cameraDev, EDSDK.PropID_FirmwareVersion, 0, out firmwareDate);
                                if (EDSDK.EDS_ERR_OK == error)
                                {
                                    camera.FirmwareVersion = firmwareDate.ToString();
                                }
                                else
                                {
                                    camera.FirmwareVersion = CameraConstants.PROPERTY_UNAVAILABLE;
                                }
                                
                                //  Serial Number
                                uint serialNumber;
                                error = EDSDK.EdsGetPropertyData(cameraDev, EDSDK.PropID_BodyID, 0, out serialNumber);
                                if (EDSDK.EDS_ERR_OK ==  error)
                                {
                                    camera.SerialNumber = serialNumber.ToString();
                                }
                                else
                                {
                                    camera.SerialNumber = CameraConstants.PROPERTY_UNAVAILABLE;
                                }

                                cameras.Add(camera);                                
                            }
                        }
                    }

                    return cameras;
                }
            }

        #endregion

        #region Singleton

            private static readonly CameraAPI cameraApi = new CameraAPI();

            /// <summary>
            /// Get instance of a CameraAPI object
            /// </summary>
            public static CameraAPI Instance
            {
                get { return cameraApi; }
            }

            /// <summary>
            /// Constructor that initializes the EDSDK SDK and registers events.
            /// </summary>
            private CameraAPI()
            {
                uint error = EDSDK.EdsInitializeSDK();
                if (EDSDK.EDS_ERR_OK !=  error)
                {
                    throw new CameraException("Unable to initialize SDK.", error);
                }

                registerEvents();
            }

        #endregion

        #region Destructor
            /// <summary>
            /// Explicit destructor for unregistering unmanaged code events and objects
            /// </summary>
            /// <exception cref="CameraException"></exception>
            ~ CameraAPI()
            {
                //  Unhook events
                this.edsCameraAddedHandler = null;

                //  Terminate anything open to the SDK
                uint error = EDSDK.EdsTerminateSDK();
                if (EDSDK.EDS_ERR_OK !=  error)
                {
                    throw new CameraException("Unable to terminate the SDK.", error);
                }
            }
        #endregion
    }
}