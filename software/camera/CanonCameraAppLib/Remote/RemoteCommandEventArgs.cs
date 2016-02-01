using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CanonCameraAppLib.Remote
{
    public class RemoteCommandEventArgs
    {
        int delay;

        public RemoteCommandEventArgs(int delay)
        {
            this.Delay = delay;
        }

        public int Delay
        {
            get { return this.delay; }
            set { this.delay = value; }
        }
    }
}