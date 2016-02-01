using System;
using System.ComponentModel;
using System.Reflection;

namespace CanonCameraAppLib
{
    public enum ShutterState
    {
        [DescriptionAttribute("Released")]
        Released = 0x00000000,
        [DescriptionAttribute("Half way")]
        Halfway = 0x00000001,
        [DescriptionAttribute("Complete")]
        Complete = 0x00000003,
        [DescriptionAttribute("Half way with no auto focus")]
        Halfway_No_AutoFocus = 0x00010001,
        [DescriptionAttribute("Complete with no auto focus")]
        Completely_No_AutoFocus = 0x00010003
    }
}