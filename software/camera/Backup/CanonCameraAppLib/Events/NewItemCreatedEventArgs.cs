using System;
using System.Collections.Generic;
using System.Text;

namespace CanonCameraAppLib.Events
{
    public delegate void NewItemCreatedEventHandler(Camera sender, NewItemCreatedEventArgs e);

    public class NewItemCreatedEventArgs : CameraEventArgs
    {
        private CapturedItem item;

        public NewItemCreatedEventArgs(CapturedItem item)
        {
            this.Item = item;
        }

        public CanonCameraAppLib.CapturedItem Item
        {
            get { return this.item; }
            set { this.item = value; }
        }
    }
}