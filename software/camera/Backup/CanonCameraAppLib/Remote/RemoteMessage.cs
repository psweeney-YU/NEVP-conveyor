using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CanonCameraAppLib.Remote
{
    [Serializable()]
    public class RemoteMessage
    {
        private int delay;
        private RemoteCommand remoteCommand;

        public RemoteMessage()
        {
            //  Do Nothing
        }

        public RemoteMessage(int delay, RemoteCommand remoteCommand)
        {
            this.Delay = delay;
            this.RemoteCommand = remoteCommand;
        }

        public int Delay
        {
            get { return this.delay; }
            set { this.delay = value; }
        }

        public CanonCameraAppLib.Remote.RemoteCommand RemoteCommand
        {
            get { return this.remoteCommand; }
            set { this.remoteCommand = value; }
        }
    }
}