using System;

namespace CanonCameraAppLib
{
    public delegate void VolumeInfoChangedEventHandler(Camera sender, VolumeInfoChangedEventArgs e);

    public class VolumeInfoChangedEventArgs : CameraEventArgs
    {
    }
}