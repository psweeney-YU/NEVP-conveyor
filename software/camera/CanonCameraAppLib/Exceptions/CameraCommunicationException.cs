using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CanonCameraAppLib
{
    public class CameraCommunicationException : CameraException
    {
        public CameraCommunicationException() : base()
        {
        }

        public CameraCommunicationException(String message) : base(message)
        {
        }

        public CameraCommunicationException(String message, Exception innerException) : base(message, innerException)
        {
        }

        public CameraCommunicationException(String message, uint errorCode) : base(message, errorCode)
        {
        }

        public CameraCommunicationException(String message, Exception innerException, uint errorCode) : base(message , innerException, errorCode)
        {
        }
    }
}