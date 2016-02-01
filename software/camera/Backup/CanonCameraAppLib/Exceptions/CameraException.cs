using System;

using com.waynehartman.util.enums;

namespace CanonCameraAppLib
{
    public class CameraException : System.Exception
    {
        public CameraException() : base()
        {
        }

        public CameraException(String message) : base(message)
        {
        }

        public CameraException(String message, Exception innerException) : base(message, innerException)
        {            
        }

        public CameraException(String message, uint errorCode) : base(message + createErrorInfo(errorCode))
        {            
        }

        public CameraException(String message, Exception innerException, uint errorCode) : base(message + createErrorInfo(errorCode), innerException)
        {            
        }

        private static String createErrorInfo(uint errorCode)
        {
            return "  (code " + errorCode.ToString("X") + " - " + EnumUtils.stringValueOf(errorCode, typeof(ErrorCodes)) + ")";
        }
    }
}