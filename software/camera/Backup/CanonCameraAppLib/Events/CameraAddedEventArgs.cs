using System;

namespace CanonCameraAppLib
{
    public delegate void CameraAddedEventHandler(CameraAddedEventArgs e);

    public class CameraAddedEventArgs : CameraEventArgs
    {
    }
}