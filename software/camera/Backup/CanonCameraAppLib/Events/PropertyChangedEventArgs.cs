using System;

namespace CanonCameraAppLib
{
    public delegate void PropertyChangedEventHandler(Camera sender, PropertyChangedEventArgs e);

    public class PropertyChangedEventArgs : CameraEventArgs
    {
    }
}