using System;

namespace CanonCameraAppLib
{
    public delegate void ShutdownEminentEventHandler(Camera sender, ShutdownEminentEventArgs e);

    public class ShutdownEminentEventArgs : CameraEventArgs
    {
        int secondsRemaining;

        public ShutdownEminentEventArgs(int secondsRemaining)
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