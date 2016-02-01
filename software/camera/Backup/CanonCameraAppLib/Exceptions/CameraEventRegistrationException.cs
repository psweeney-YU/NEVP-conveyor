using System;

namespace CanonCameraAppLib
{
    public class CameraEventRegistrationException : CameraException
    {
        public CameraEventRegistrationException() : base()
        {
        }

        public CameraEventRegistrationException(String message) : base(message)
        {
        }

        public CameraEventRegistrationException(String message, Exception innerException) : base(message, innerException)
        {
        }

        public CameraEventRegistrationException(String message, uint errorCode) : base(message, errorCode)
        {
        }

        public CameraEventRegistrationException(String message, Exception innerException, uint errorCode) : base(message, innerException, errorCode)
        {
        }
    }
}