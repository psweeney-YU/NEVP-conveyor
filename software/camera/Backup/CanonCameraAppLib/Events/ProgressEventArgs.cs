using System;

namespace CanonCameraAppLib
{
    public delegate void ProgressEventHandler(Camera sender, ProgressEventArgs e);

    public class ProgressEventArgs : CameraEventArgs
    {
        private int percentComplete;

        public ProgressEventArgs(int percentComplete)
        {
            this.PercentComplete = percentComplete;
        }

        public int PercentComplete
        {
            get { return this.percentComplete; }
            set { this.percentComplete = value; }
        }
    }
}