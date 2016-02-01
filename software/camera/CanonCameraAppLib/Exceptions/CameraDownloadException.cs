using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CanonCameraAppLib.Exceptions
{
    public class CameraDownloadException : CameraException
    {
        public CameraDownloadException() : base()
        {
        }

        public CameraDownloadException(String message) : base(message)
        {
        }

        public CameraDownloadException(String message, Exception innerException) : base(message, innerException)
        {
        }

        public CameraDownloadException(String message, uint errorCode) : base(message, errorCode)
        {
        }

        public CameraDownloadException(String message, Exception innerException, uint errorCode) : base(message, innerException, errorCode)
        {
        }
    }
}
