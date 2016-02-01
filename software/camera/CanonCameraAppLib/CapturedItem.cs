using System;
using System.Collections.Generic;
using System.IO;
using System.Linq;
using System.Runtime.InteropServices;
using System.Text;

using CanonCameraAppLib.Exceptions;
using com.waynehartman.util.graphics;

using EDSDKLib;

namespace CanonCameraAppLib
{
    public class CapturedItem
    {
        private long size;
        private String name;
        private bool isFolder;
        private byte[] image;
        private IntPtr ptr;
        private Dimension dimensions;

        internal CapturedItem()
        {
            //  Do Nothing
        }

        internal CapturedItem(String name, long size, bool isFolder, byte[] image)
        {
            this.Image = image;
            this.Name = name;
            this.Size = size;
            this.IsFolder = isFolder;
        }

        public long Size
        {
            get { return this.size; }
            set { this.size = value; }
        }

        public IntPtr Ptr 
        {
            get { return this.ptr;}
            set { this.ptr = value; }
        
        }

        public string Name
        {
            get { return this.name; }
            set { this.name = value; }
        }

        public bool IsFolder
        {
            get { return this.isFolder; }
            set { this.isFolder = value; }
        }

        public byte[] Image
        {
            get { return this.image; }
            set { this.image = value; }
        }

        public com.waynehartman.util.graphics.Dimension Dimensions
        {
            get { return this.dimensions; }
            set { this.dimensions = value; }
        }
    }
}