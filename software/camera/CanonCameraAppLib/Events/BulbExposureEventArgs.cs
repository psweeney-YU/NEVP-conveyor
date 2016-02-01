using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CanonCameraAppLib
{
    public delegate void BulbExposureEventHandler(Camera sender, BulbExposureEventArgs e);

    public class BulbExposureEventArgs : CameraEventArgs
    {
        private int exposureTime;

        public BulbExposureEventArgs(int exposureTime)
        {
            this.ExposureTime = exposureTime;
        }

        public int ExposureTime
        {
            get { return this.exposureTime; }
            set { this.exposureTime = value; }
        }
    }
}