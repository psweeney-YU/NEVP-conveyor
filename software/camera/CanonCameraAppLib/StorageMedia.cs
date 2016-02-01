using System;

namespace CanonCameraAppLib
{
    public sealed class StorageMedia
    {
        private StorageMediaType storageMediaType;
        private long capacity;
        private long freeSpace;
        private String label;

        /// <summary>
        /// Internal constructor because we don't want code external to the assembly to be able to instantiate the class.
        /// </summary>
        internal StorageMedia()
        {
            //  Do Nothing
        }

        /// <summary>
        /// Internal constructor because we don't want code external to the assembly to be able to instantiate the class.
        /// </summary>
        internal StorageMedia(String label, long capacity, long freeSpace, StorageMediaType storageMediaType)
        {
            this.StorageMediaType = storageMediaType;
            this.FreeSpace = freeSpace;
            this.Capacity = capacity;
            this.label = label;
        }

        #region Public Methods

            /// <summary>
            /// Format the storage media, removing all data on it
            /// </summary>
            public void formatMedia()
            {
                throw new NotImplementedException();
            }
            
        #endregion

        #region Public Properties

            /// <summary>
            /// State of the battery
            /// </summary>
            public CanonCameraAppLib.StorageMediaType StorageMediaType
            {
                get { return this.storageMediaType; }
                internal set { this.storageMediaType = value; }
            }

            /// <summary>
            /// Capacity (in bytes) of the storage media
            /// </summary>
            public long Capacity
            {
                get { return this.capacity; }
                internal set { this.capacity = value; }
            }

            /// <summary>
            /// Free space (in bytes) of the storage media
            /// </summary>
            public long FreeSpace
            {
                get { return this.freeSpace; }
                internal set { this.freeSpace = value; }
            }

            /// <summary>
            /// Volume label of the storage media
            /// </summary>
            public string Label
            {
                get { return this.label; }
                set { throw new NotImplementedException(); }
            }

        #endregion
    }
}