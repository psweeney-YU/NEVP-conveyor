using System;

namespace CanonCameraAppLib
{
    public class PropertyUnavailableException : CameraException
    {
        public PropertyUnavailableException() : base()
        {
        }

        public PropertyUnavailableException(String message) : base(message)
        {
        }

        public PropertyUnavailableException(String message, Exception innerException) : base(message, innerException)
        {   
        }

        public PropertyUnavailableException(String message, uint errorCode) : base(message, errorCode)
        {   
        }

        public PropertyUnavailableException(String message, Exception innerException, uint errorCode) : base(message, innerException, errorCode)
        {   
        }
    }
}