using System;
using System.Collections.Generic;
using System.Runtime.InteropServices;
using System.Text;

using CanonCameraAppLib.Events;
using CanonCameraAppLib.Exceptions;

using EDSDKLib;

namespace CanonCameraAppLib
{
    /// <summary>
    /// Object representing the operations and attributes of a camera
    /// </summary>
    public sealed class Camera
    {
        #region Constructor, Constants, Private Variables

            private CameraAPI cameraApi;
            private String name;
            private String portName;
            private IntPtr cameraDevice;
            private StorageMedia storageMedia;
            private String serialNumber;
            private String firmwareVersion;
            private EDSDK.EdsStateEventHandler edsStateEventHandler;
            private EDSDK.EdsPropertyEventHandler edsPropertyEventHandler;
            private EDSDK.EdsObjectEventHandler edsObjectEventHandler;
            private EDSDK.EdsProgressCallback edsProgressCallbackHandler;

            /// <summary>
            /// Makes it so that code outside this assembly cannot instantiate a Camera class.
            /// </summary>
            /// <param name="cameraDevice">Pointer to the instance of the camera device.</param>
            internal Camera(IntPtr cameraDevice)
            {
                this.cameraApi = CameraAPI.Instance;
                this.cameraDevice = cameraDevice;
                this.registerEvents();
            }

        #endregion

        #region Registered Events and Callbacks

            /// <summary>
            /// Registers API events
            /// </summary>
            private void registerEvents()
            {
                uint error;

                //  Register STATE events
                edsStateEventHandler = new EDSDK.EdsStateEventHandler(stateEventHandler);
                error = EDSDK.EdsSetCameraStateEventHandler(this.CameraDevice, EDSDK.StateEvent_All, this.edsStateEventHandler, cameraDevice);
                if (EDSDK.EDS_ERR_OK != error)
                {
                    throw new CameraEventRegistrationException("Unable to register state events with the camera!", error);
                }

                //  Register PROPERTY events
                edsPropertyEventHandler = new EDSDK.EdsPropertyEventHandler(propertyEventHandler);
                error = EDSDK.EdsSetPropertyEventHandler(this.CameraDevice, EDSDK.PropertyEvent_All, edsPropertyEventHandler, cameraDevice);
                if (EDSDK.EDS_ERR_OK != error)
                {
                    throw new CameraEventRegistrationException("Unable to register property events with the camera!", error);
                }

                //  Register OBJECT events
                edsObjectEventHandler = new EDSDK.EdsObjectEventHandler(objectEventHandler);
                error = EDSDK.EdsSetObjectEventHandler(this.CameraDevice, EDSDK.ObjectEvent_All, edsObjectEventHandler, IntPtr.Zero);
                if (EDSDK.EDS_ERR_OK != error)
                {
                    throw new CameraEventRegistrationException("Unable to register object events with the camera!", error);
                }

                // Register PROGRESS events
                edsProgressCallbackHandler = new EDSDK.EdsProgressCallback(progressEventHandler);
                error = EDSDK.EdsSetProgressCallback(this.cameraDevice, edsProgressCallbackHandler, EDSDK.EdsProgressOption.Periodically, IntPtr.Zero);
                if (EDSDK.EDS_ERR_OK != error)
                {
                    //throw new CameraEventRegistrationException("Unable to register progress callback events with the camera!", error);
                }
            }

            /// <summary>
            /// Catch all state event handler.  All events will come in here and a switch determines what the event was and the associated data.
            /// </summary>
            /// <param name="inEvent">The ID of the event</param>
            /// <param name="inParameter">Message of the event</param>
            /// <param name="inContext">Object containing the applicable data.</param>
            /// <returns>0x0 for OK</returns>
            private uint stateEventHandler(uint inEvent, uint inParameter, IntPtr inContext)
            {
                switch (inEvent)
                {
                    case EDSDK.StateEvent_WillSoonShutDown:
                        invokeShutdownEminentEvent(new ShutdownEminentEventArgs((int)inParameter));
                        break;

                    case EDSDK.StateEvent_Shutdown:
                        invokeShutdownEvent(new ShutdownEventArgs());
                        break;

                    case EDSDK.StateEvent_ShutDownTimerUpdate:
                        invokeShutdownTimerUpdatedEvent(new ShutdownTimerUpdateEventArgs((int)inParameter));
                        break;

                    case EDSDK.StateEvent_CaptureError:
                        //throw new CameraCaptureException("Camera unable to capture.", inParameter);

                    case EDSDK.StateEvent_InternalError:
                        invokeInternalErrorEvent(new InternalErrorEventArgs());
                        break;

                    case EDSDK.StateEvent_BulbExposureTime:
                        invokeBulbExposureElapsedEvent(new BulbExposureEventArgs((int)inParameter));
                        break;

                    case EDSDK.StateEvent_JobStatusChanged:
                        Console.WriteLine(String.Format("StateEventHandler: event {0}, parameter {1}", inEvent, inParameter));
                        break;

                    default:
                        Console.WriteLine(String.Format("StateEventHandler: event {0}, parameter {1}", inEvent, inParameter));
                        break;
                }
                return 0x0;
            }

            /// <summary>
            /// Registers a callback function for receiving status change notification events for property states on a camera.
            /// </summary>
            /// <param name="inEvent">Indicate the event type supplemented. Designate one of the event types subject to supplementation, as designated by EdsSetPropertyEventHandler. Events that occur can be determined based on the event type.</param>
            /// <param name="inPropertyID">Returns the property ID created by the event.</param>
            /// <param name="inParameter">Used to identify information created by the event for custom function (CF) properties or other properties that have multiple items of information.</param>
            /// <param name="inContext">Passes inContext without modification, as designated as an EdsSetPropertyEventHandler argument.</param>
            /// <returns>0x0 (executed OK)</returns>
            private uint propertyEventHandler(uint inEvent, uint inPropertyID, uint inParameter, IntPtr inContext)
            {
                switch (inEvent)
                {
                    //case EDSDK.PropertyEvent_PropertyChanged:

                    //    break;

                    //case EDSDK.PropertyEvent_PropertyDescChanged:
                    //    break;
                    default:
                        Console.WriteLine(String.Format("PropertyEventHandler: event {0}, property {1}, parameter {2}, ", 
                            inEvent.ToString("X"), inPropertyID.ToString("X"), inParameter.ToString("X")));                        
                        break;
                }

                return 0x0;
            }

            /// <summary>
            /// Registered callback function for recieving object events
            /// </summary>
            /// <param name="inEvent">Indicate the event type supplemented.</param>
            /// <param name="inRef">Returns a reference to objects created by the event.</param>
            /// <param name="inContext">Passes inContext without modification</param>
            /// <returns>Status 0 (OK)</returns>
            private uint objectEventHandler(uint inEvent, IntPtr inRef, IntPtr inContext)
            {
                switch (inEvent)
                {
                    case EDSDK.ObjectEvent_DirItemCreated:
                        this.invokeNewItemCreatedEvent(new NewItemCreatedEventArgs(getCapturedItem(inRef)));
                        Console.WriteLine("Directory Item Created");
                        break;
                    case EDSDK.ObjectEvent_DirItemRequestTransfer:
                        Console.WriteLine("Directory Item Requested Transfer");
                        break;
                    default:
                        Console.WriteLine(String.Format("ObjectEventHandler: event {0}, ref {1}", inEvent.ToString("X"), inRef.ToString()));
                        break;
                }

                return 0x0;
            }

            /// <summary>
            /// Registered callback function for progress events
            /// </summary>
            /// <param name="inPercent">Progress in a range of 0 –100%</param>
            /// <param name="inContext">The application information designated by EdsSetProgressCallback</param>
            /// <param name="outCancel">To cancel processing in progress, set this variable to TRUE. For example, if this argument is set to TRUE during file transfer from the camera, the EDSDK notifies the camera that file transfer has been canceled, and transfer of those files is canceled.</param>
            /// <returns>Status 0 (OK)</returns>
            private uint progressEventHandler(uint inPercent, IntPtr inContext, ref bool outCancel)
            {
                invokeProgressEvent(new ProgressEventArgs((int)inPercent));
                return 0x0;
            }

        #endregion

        #region Public Events

            /// <summary>
            /// Occurs when a property changes on the device
            /// </summary>
            public event PropertyChangedEventHandler OnPropertyChanged;
            /// <summary>
            /// Occurs when the device shuts down
            /// </summary>
            public event ShutdownEventHandler OnShutdown;
            /// <summary>
            /// Occurs when the camera is about to shutdown
            /// </summary>
            public event ShutdownEminentEventHandler OnShutdownEminent;
            /// <summary>
            /// Occurs when the shutdown timer is updated
            /// </summary>
            public event ShutdownTimerUpdateEventHandler OnShutdownTimerUpdated;
            /// <summary>
            /// Occurs when an unrecoverable error happens
            /// </summary>
            public event InternalErrorEventHandler OnInternalError;
            /// <summary>
            /// Occurs every given period during a bulb shot
            /// </summary>
            public event BulbExposureEventHandler OnBulbExposureElapse;
            /// <summary>
            /// Occurs during a progress action, such as a file transfer
            /// </summary>
            public event ProgressEventHandler OnProgressMade;
            /// <summary>
            /// Occurs when an action is performed on the camera but cannot because it is busy
            /// </summary>
            public event CamerBusyEventHandler OnCameraBusy;
            /// <summary>
            /// Created when a new directory, image, video, or other object is created by the camera.
            /// </summary>
            public event NewItemCreatedEventHandler OnNewItemCreated;

        #endregion

        #region Public Properties

            /// <summary>
            /// Device name of the camera
            /// </summary>
            public String Name
            {
                get { return this.name; }
                internal set { this.name = value; }
            }

            /// <summary>
            /// Storage media properties
            /// </summary>
            public StorageMedia StorageMedia
            {
                get { return this.storageMedia; }
                internal set { this.storageMedia = value; }
            }
            
            /// <summary>
            /// State of the battery
            /// </summary>
            public BatteryState BatteryState
            {
                get
                {
                    uint batteryLevel;
                    uint error;
                    error = EDSDK.EdsGetPropertyData(this.CameraDevice, EDSDK.PropID_BatteryLevel, 0, out batteryLevel);
                    if (EDSDK.EDS_ERR_OK == error)
                    {
                        switch (batteryLevel)
                        {
                            case EDSDK.BatteryLevel_AC:
                                return BatteryState.AC;
                            case EDSDK.BatteryLevel_Empty:
                                return BatteryState.Empty;
                            case EDSDK.BatteryLevel_Half:
                                return BatteryState.Half;
                            case EDSDK.BatteryLevel_Low:
                                return BatteryState.Low;
                            case EDSDK.BatteryLevel_Normal:
                                return BatteryState.Full;
                            default:
                                throw new PropertyUnavailableException("Unable to get battery level.", error);
                        }
                    }
                    else
                    {
                        throw new PropertyUnavailableException("Unable to get battery level.", error);
                    }
                }
            }

            /// <summary>
            /// Body ID/Serial Number of the camera
            /// </summary>
            public String SerialNumber
            {
                get { return this.serialNumber; }
                internal set { this.serialNumber = value; }
            }

            /// <summary>
            /// Firmware version
            /// Just a date...
            /// </summary>
            public String FirmwareVersion
            {
                get { return this.firmwareVersion; }
                internal set { this.firmwareVersion = value; }
            }

        #endregion

        #region Internal Properties

            /// <summary>
            /// Port name on which the camera is operating
            /// </summary>
            internal String PortName
            {
                get { return this.portName; }
                set { this.portName = value; }
            }

            /// <summary>
            /// Canon SDK pointer to the camera device
            /// </summary>
            internal IntPtr CameraDevice
            {
                get { return this.cameraDevice; }
                set { this.cameraDevice = value; }
            }

        #endregion

        #region Public Methods

            /// <summary>
            /// Get/set shutter button state
            /// </summary>
            public ShutterState ShutterButtonState
            {
                get
                { 
                    throw new NotImplementedException();
                }
                set
                {
                    uint error = EDSDK.EdsSendStatusCommand(this.cameraDevice, Convert.ToUInt32(value), 0);
                    switch (error)
                    {
                        case EDSDK.EDS_ERR_OK:
                            Console.WriteLine("Pressed shutter button...");
                            break;
                        case EDSDK.EDS_ERR_DEVICE_BUSY:
                            Console.WriteLine("Device busy, unable to set state...");
                            break;
                        default:
                            throw new CameraException("Unable to press shutter!", error);
                    }
                }
            }

            /// <summary>
            /// Extends the time remaining until the camera shuts down
            /// </summary>
            public void extendShutdownTimer()
            {
                uint error = EDSDK.EdsSendCommand(this.CameraDevice, EDSDK.CameraCommand_ExtendShutDownTimer, 0);

                switch (error)
                {
                    case EDSDK.EDS_ERR_OK:
                        Console.WriteLine("Extended the shutdown timer...");
                        break;
                    case EDSDK.EDS_ERR_DEVICE_BUSY:
                        Console.WriteLine("Device busy, sleeping for " + CameraConstants.SLEEP_PERIOD + "ms...");
                        System.Threading.Thread.Sleep(CameraConstants.SLEEP_PERIOD);
                        extendShutdownTimer();
                        break;
                    default:
                        throw new CameraException("Unable to extend shutdown timer!", error);
                }
            }

            /// <summary>
            /// Opens the shutter of the camera
            /// </summary>
            /// <seealso cref="http://en.wikipedia.org/wiki/Bulb_(photography)"/>
            public void bulbStart()
            {
                PropertyManager.LockUI(this);

                uint error = EDSDK.EdsSendCommand(this.CameraDevice, EDSDK.CameraCommand_BulbStart, 0);

                switch (error)
                {
                    case EDSDK.EDS_ERR_OK:
                        Console.WriteLine("Started bulb shot...");
                        break;
                    case EDSDK.EDS_ERR_DEVICE_BUSY:
                        Console.WriteLine("Device busy, sleeping for " + CameraConstants.SLEEP_PERIOD + "ms...");
                        System.Threading.Thread.Sleep(CameraConstants.SLEEP_PERIOD);
                        bulbStart();
                        break;
                    default:
                        throw new CameraException("Unable to start bulb shot!", error);
                }

                PropertyManager.UnlockUI(this);
            }

            /// <summary>
            /// Closes the shutter of the camera
            /// </summary>
            /// <seealso cref="http://en.wikipedia.org/wiki/Bulb_(photography)"/>
            public void bulbEnd()
            {
                PropertyManager.LockUI(this);

                uint error = EDSDK.EdsSendCommand(this.CameraDevice, EDSDK.CameraCommand_BulbEnd, 0);

                switch (error)
                {
                    case EDSDK.EDS_ERR_OK:
                        Console.WriteLine("Finished bulb shot...");
                        break;
                    case EDSDK.EDS_ERR_DEVICE_BUSY:
                        invokeCameraBusyEvent(new CameraBusyEventArgs(CameraConstants.SLEEP_PERIOD));
                        Console.WriteLine("Device busy, sleeping for " + CameraConstants.SLEEP_PERIOD + "ms...");
                        System.Threading.Thread.Sleep(CameraConstants.SLEEP_PERIOD);
                        bulbEnd();
                        break;
                    default:
                        throw new CameraException("Unable to finish bulb shot!", error);
                }

                PropertyManager.UnlockUI(this);
            }

            /// <summary>
            /// Take a complete bulb shot for the duration specified
            /// </summary>
            /// <param name="duration">Duration, in milliseconds, of the bulb shot</param>
            public void bulb(int duration)
            {
                bulbStart();
                System.Threading.Thread.Sleep(duration);
                bulbEnd();
            }

            /// <summary>
            /// Instructs the camera to take a photograph
            /// </summary>
            public void takePhotograph()
            {
                uint error = EDSDK.EdsSendCommand(this.CameraDevice, EDSDK.CameraCommand_TakePicture, 0);

                switch (error)
                {
                    case EDSDK.EDS_ERR_OK:
                        Console.WriteLine("Took photograph...");
                        break;
                    case EDSDK.EDS_ERR_DEVICE_BUSY:
                        Console.WriteLine("Device busy, sleeping for " + CameraConstants.SLEEP_PERIOD + "ms...");
                        System.Threading.Thread.Sleep(CameraConstants.SLEEP_PERIOD);
                        takePhotograph();
                        break;
                    default:
                        throw new CameraException("Unable to take photograph!", error);
                }
            }

            /// <summary>
            /// Instructs the camera to take a photograph with the (int) delay in milliseconds
            /// NOTE: There will be hardware limitations in the number of photographs that the camera can taken within a given period of time.
            /// </summary>
            /// <param name="delay">Delay in milliseconds</param>
            public void takePhotograph(int delay)
            {                
                System.Threading.Thread.Sleep(delay);
                this.takePhotograph();
            }

            /// <summary>
            /// Instructs the camera to take n number of photographs with the (int) delay in milliseconds.  UI will be locked during the operation.
            /// </summary>
            /// <param name="delay">Delay in milliseconds</param>
            /// <param name="successiveShots">Number of photographs to take</param>
            public void takePhotograph(int delay, int successiveShots)
            {
                PropertyManager.LockUI(this);

                for (int i = 0; i < successiveShots; i++)
                {
                    this.takePhotograph(delay);
                }

                PropertyManager.UnlockUI(this);
            }

        #endregion

        #region Private Methods

            /// <summary>
            /// Gets a photo or video clip from the camera
            /// </summary>
            /// <param name="directoryItem">Reference to the item that the camera captured.</param>
            /// <returns></returns>
            private CapturedItem getCapturedItem(IntPtr directoryItem)
            {
                uint err = EDSDK.EDS_ERR_OK;
                IntPtr stream = IntPtr.Zero;

                EDSDK.EdsDirectoryItemInfo dirItemInfo;

                err = EDSDK.EdsGetDirectoryItemInfo(directoryItem, out dirItemInfo);

                if (err != EDSDK.EDS_ERR_OK)
                {
                    throw new CameraException("Unable to get captured item info!", err);
                }

                //  Fill the stream with the resulting image
                if (err == EDSDK.EDS_ERR_OK)
                {
                    err = EDSDK.EdsCreateMemoryStream((uint)dirItemInfo.Size, out stream);
                }

                //  Copy the stream to a byte[] and 
                if (err == EDSDK.EDS_ERR_OK)
                {
                    err = EDSDK.EdsDownload(directoryItem, (uint)dirItemInfo.Size, stream);
                }

                //  Create the returned item
                CapturedItem item = new CapturedItem();

                if (err == EDSDK.EDS_ERR_OK)
                {
                    IntPtr imageRef = IntPtr.Zero;

                    err = EDSDK.EdsCreateImageRef(stream, out imageRef);

                    if (err == EDSDK.EDS_ERR_OK)
                    {
                        EDSDK.EdsImageInfo info;
                        err = EDSDK.EdsGetImageInfo(imageRef, EDSDK.EdsImageSource.FullView, out info);

                        if (err == EDSDK.EDS_ERR_OK)
                        {
                            item.Dimensions = new com.waynehartman.util.graphics.Dimension((int)info.Width, (int)info.Height);

                            EDSDK.EdsRelease(imageRef);
                        }
                    }
                }

                if (err == EDSDK.EDS_ERR_OK)
                {
                    byte[] buffer = new byte[(int)dirItemInfo.Size];

                    GCHandle gcHandle = GCHandle.Alloc(buffer, GCHandleType.Pinned);

                    IntPtr address = gcHandle.AddrOfPinnedObject();

                    IntPtr streamPtr = IntPtr.Zero;

                    err = EDSDK.EdsGetPointer(stream, out streamPtr);

                    if (err != EDSDK.EDS_ERR_OK)
                    {
                        throw new CameraDownloadException("Unable to get resultant image.", err);
                    }

                    try
                    {
                        Marshal.Copy(streamPtr, buffer, 0, (int)dirItemInfo.Size);

                        item.Image = buffer;
                        item.Name = dirItemInfo.szFileName;
                        item.Size = (long)dirItemInfo.Size;
                        item.IsFolder = Convert.ToBoolean(dirItemInfo.isFolder);

                        return item;
                    }
                    catch (AccessViolationException ave)
                    {
                        throw new CameraDownloadException("Error copying unmanaged stream to managed byte[].", ave);
                    }
                    finally
                    {
                        gcHandle.Free();
                        EDSDK.EdsRelease(stream);
                        EDSDK.EdsRelease(streamPtr);
                    }
                }
                else
                {
                    throw new CameraDownloadException("Unable to get resultant image.", err);
                }
            }

        #endregion

        #region Event Invokers

            /// <summary>
            /// Invokes the event associated with the device shutting down
            /// </summary>
            /// <param name="e"></param>
            private void invokeShutdownEvent(ShutdownEventArgs e)
            {
                if (OnShutdown != null)
                {
                    OnShutdown(this, e);
                }
            }

            /// <summary>
            /// Invokes the event associated with the device shutting down soon
            /// </summary>
            /// <param name="e"></param>
            private void invokeShutdownEminentEvent(ShutdownEminentEventArgs e)
            {
                if (OnShutdownEminent != null)
                {
                    OnShutdownEminent(this, e);
                }
            }

            /// <summary>
            /// Invokes the event associated with the device updating its shutdown timer
            /// </summary>
            /// <param name="e"></param>
            private void invokeShutdownTimerUpdatedEvent(ShutdownTimerUpdateEventArgs e)
            {
                if (OnShutdownTimerUpdated != null)
                {
                    OnShutdownTimerUpdated(this, e);
                }
            }

            /// <summary>
            /// Invokes the event associated with properties changing on the device or storage media
            /// </summary>
            /// <param name="e"></param>
            private void invokePropertyChangedEvent(PropertyChangedEventArgs e)
            {
                if (OnPropertyChanged != null)
                {
                    OnPropertyChanged(this, e);
                }
            }

            /// <summary>
            /// Invokes the event associated with the device experiencing an unrecoverable error
            /// </summary>
            /// <param name="e"></param>
            private void invokeInternalErrorEvent(InternalErrorEventArgs e)
            {
                if (OnInternalError != null)
                {
                    OnInternalError(this, e);
                }
            }

            /// <summary>
            /// Invokes the event associated with the bulb shots
            /// </summary>
            /// <param name="e"></param>
            private void invokeBulbExposureElapsedEvent(BulbExposureEventArgs e)
            {
                if (OnBulbExposureElapse != null)
                {
                    OnBulbExposureElapse(this, e);
                }
            }

            /// <summary>
            /// Invokes the event associated with progress being made during a camera operation
            /// </summary>
            /// <param name="e"></param>
            private void invokeProgressEvent(ProgressEventArgs e)
            {
                if (OnProgressMade != null)
                {
                    OnProgressMade(this, e);
                }
            }

            /// <summary>
            /// Invokes the event associated with the device being busy, preventing it from performing further work
            /// </summary>
            /// <param name="e"></param>
            private void invokeCameraBusyEvent(CameraBusyEventArgs e)
            {
                if (OnCameraBusy != null)
                {
                    OnCameraBusy(this, e);
                }
            }
            
            /// <summary>
            /// Invokes the event associated with the creation of a new directory, image, movie, etc.
            /// </summary>
            /// <param name="e"></param>
            private void invokeNewItemCreatedEvent(NewItemCreatedEventArgs e)
            {
                if (OnNewItemCreated != null)
                {
                    OnNewItemCreated(this, e);
                }
            }

        #endregion

        #region Destructor
            
            /// <summary>
            /// Explicit destructor for reclaiming memory fronm unmanaged events and objects.
            /// </summary>
            ~ Camera()
            {
                //  Unhook our event handlers
                this.edsObjectEventHandler = null;
                this.edsPropertyEventHandler = null;
                this.edsStateEventHandler = null;
                this.edsProgressCallbackHandler = null;
            }

        #endregion
    }
}