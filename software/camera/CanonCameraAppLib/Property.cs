using System;

namespace CanonCameraAppLib
{
    internal class Property
    {
        private String stringValue;
        private uint? uintValue;
        private IntPtr? intPtrValue;

        private PropertyType propertyType;

        public Property(PropertyType propertyType, uint value)
        {
            assignValues();

            this.propertyType = propertyType;
            this.UintValue = value;
        }

        public Property(PropertyType propertyType, String value)
        {
            assignValues();

            this.propertyType = propertyType;
            this.StringValue = value;
        }

        public Property(PropertyType propertyType, IntPtr value)
        {
            assignValues();

            this.propertyType = propertyType;
            this.IntPtrValue = value;
        }

        public CanonCameraAppLib.PropertyType PropertyType
        {
            get { return this.propertyType; }
            set { this.propertyType = value; }
        }

        private void assignValues()
        {
            this.stringValue = null;
            this.uintValue = null;
            this.intPtrValue = null;
        }

        public string StringValue
        {
            get { return this.stringValue; }
            set { this.stringValue = value; }
        }

        public uint? UintValue
        {
            get { return this.uintValue; }
            set { this.uintValue = value; }
        }

        public System.IntPtr? IntPtrValue
        {
            get { return this.intPtrValue; }
            set { this.intPtrValue = value; }
        }
    }
}