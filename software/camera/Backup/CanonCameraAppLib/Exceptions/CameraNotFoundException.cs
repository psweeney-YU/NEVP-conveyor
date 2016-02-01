using System;

namespace CanonCameraAppLib
{
    public class CameraNotFoundException : CameraException
    {
        public CameraNotFoundException() : base()
        {
        }

        public CameraNotFoundException(String message) : base(message)
        {
        }

        public CameraNotFoundException(String message, Exception innerException): base(message, innerException)
        {
        }
    }
}