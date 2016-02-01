using System;
using System.Collections;
using System.Collections.Generic;
using System.Text;

using EDSDKLib;

namespace CanonCameraAppLib
{
    /// <summary>
    /// Convenience class for manipulating camera properties.
    /// </summary>
    internal class PropertyManager
    {
        /// <summary>
        /// Set a series of properties
        /// </summary>
        /// <param name="camera">Camera device</param>
        /// <param name="properties">Key value pair of a PropertyType and int value</param>
        /// <returns>Collection of PropertyType/error code pairs from the camera</returns>
        internal static Dictionary<PropertyType, uint> SetProperty(Camera camera, Dictionary<PropertyType, uint> properties)
        {
            LockUI(camera);

            Dictionary<PropertyType, uint> returnCodes = new Dictionary<PropertyType, uint>(properties.Count);

            foreach (KeyValuePair<PropertyType, uint> property in properties)
            {
                uint error = SetProperty(camera, new Property(property.Key, property.Value));
                returnCodes.Add((PropertyType)property.Key, error);
            }

            UnlockUI(camera);

            return returnCodes;
        }

        /// <summary>
        /// Set a property
        /// </summary>
        /// <param name="camera">Camera device</param>
        /// <param name="property">Property to change</param>
        /// <param name="value">Value of the property to change</param>
        /// <returns>Error code from the camera</returns>
        internal static uint SetProperty(Camera camera, Property property)
        {
            return EDSDK.EdsSetPropertyData(camera.CameraDevice, (uint)property.PropertyType, 0, 0, property.UintValue);
        }

        internal static Property getProperty(Camera camera, PropertyType propertyType)
        {
            //EDSDK.EdsGetPropertyData(
            throw new NotImplementedException();
        }

        /// <summary>
        /// Locks the UI of the camera
        /// </summary>
        /// <param name="camera">The Camera whose UI to lock</param>
        internal static void LockUI(Camera camera)
        {
            uint error = EDSDK.EdsSendStatusCommand(camera.CameraDevice, EDSDK.CameraState_UILock, 0);
            if (EDSDK.EDS_ERR_OK != error)
            {
                throw new CameraUILockException("Unable to lock the UI!", error);
            }
        }

        /// <summary>
        /// Unlocks the UI of the camera
        /// </summary>
        /// <param name="camera">The camera whose UI to unlock</param>
        internal static void UnlockUI(Camera camera)
        {
            uint error = EDSDK.EdsSendStatusCommand(camera.CameraDevice, EDSDK.CameraState_UIUnLock, 0);
            if (EDSDK.EDS_ERR_OK != error)
            {
                throw new CameraUILockException("Unable to unlock the UI!", error);
            }
        }
    }
}