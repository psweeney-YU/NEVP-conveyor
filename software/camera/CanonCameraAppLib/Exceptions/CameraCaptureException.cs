using System;

namespace CanonCameraAppLib
{
    public class CameraCaptureException : CameraException
    {
        public CameraCaptureException() : base()
        {
        }

        public CameraCaptureException(String message) : base(message)
        {
        }

        public CameraCaptureException(String message, Exception innerException) : base(message, innerException)
        {
        }

        public CameraCaptureException(String message, uint errorCode) : base(message, errorCode)
        {            
        }

        public CameraCaptureException(String message, Exception innerException, uint errorCode) : base(message, innerException, errorCode)
        {
        }
    }
}