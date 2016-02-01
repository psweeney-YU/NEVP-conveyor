using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;

namespace CanonCameraAppLib.Remote
{
    [Serializable()]
    public enum RemoteCommand
    {
        TakePhoto,
        DepressButton
    }
}