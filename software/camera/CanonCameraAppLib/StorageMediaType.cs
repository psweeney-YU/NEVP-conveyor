using System;
using System.ComponentModel;
using System.Reflection;

namespace CanonCameraAppLib
{
    public enum StorageMediaType
    {
        [DescriptionAttribute("No storage media present.")] None = 0,
        [DescriptionAttribute("Compact Flash")] CompactFlash = 1,
        [DescriptionAttribute("Secure Digital")] SecureDigital = 2
    }
}