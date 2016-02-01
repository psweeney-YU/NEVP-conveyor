using System;

namespace CanonCameraAppLib
{
    public delegate void CamerBusyEventHandler(Camera sender, CameraBusyEventArgs e);

    public class CameraBusyEventArgs : CameraEventArgs
    {
        private int delay;

        public CameraBusyEventArgs()
        {
            //  Do Nothing
        }

        public CameraBusyEventArgs(int delay)
        {
            this.Delay = delay;
        }

        /// <summary>
        /// Get or set the delay.  Amount of time in milliseconds that a thread should sleep.
        /// </summary>
        public int Delay
        {
            get { return this.delay; }
            set { this.delay = value; }
        }
    }
}