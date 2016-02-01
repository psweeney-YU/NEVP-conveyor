using System;

namespace CanonCameraAppLib
{
    public delegate void ShutdownEventHandler(Camera sender, ShutdownEventArgs e);

    public class ShutdownEventArgs : CameraEventArgs
    {
    }
}