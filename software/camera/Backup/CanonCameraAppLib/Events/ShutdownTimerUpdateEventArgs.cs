using System;

namespace CanonCameraAppLib
{
    public delegate void ShutdownTimerUpdateEventHandler(Camera sender, ShutdownTimerUpdateEventArgs e);

    public class ShutdownTimerUpdateEventArgs : CameraEventArgs
    {
        int secondsRemaining;

        public ShutdownTimerUpdateEventArgs(int secondsRemaining)
        {
            this.SecondsRemaining = secondsRemaining;
        }

        public int SecondsRemaining
        {
            get { return this.secondsRemaining; }
            set { this.secondsRemaining = value; }
        }
    }
}