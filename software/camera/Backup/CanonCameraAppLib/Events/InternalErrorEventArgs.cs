using System;

namespace CanonCameraAppLib
{
    public delegate void InternalErrorEventHandler(Camera sender, InternalErrorEventArgs e);

    public class InternalErrorEventArgs : CameraEventArgs
    {
    }
}