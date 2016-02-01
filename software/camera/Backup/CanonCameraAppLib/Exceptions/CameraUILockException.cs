using System;

namespace CanonCameraAppLib
{
    public class CameraUILockException : CameraException
    {
        public CameraUILockException() : base()
        {
        }

        public CameraUILockException(String message) : base(message)
        {
        }

        public CameraUILockException(String message, Exception innerException) : base(message, innerException)
        {   
        }

        public CameraUILockException(String message, uint errorCode) : base(message, errorCode)
        {   
        }

        public CameraUILockException(String message, Exception innerException, uint errorCode) : base(message, innerException, errorCode)
        {   
        }
    }
}