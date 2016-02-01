using System;
using System.ComponentModel;
using System.Reflection;

namespace CanonCameraAppLib
{
    public enum ErrorCodes : uint
    {
        /*-----------------------------------------------------------------------
           ED-SDK Error Code Masks
        ------------------------------------------------------------------------*/
        ISSPECIFIC_MASK =                                 0x80000000,
        COMPONENTID_MASK =                                0x7F000000,
        RESERVED_MASK =                                   0x00FF0000,
        ERRORID_MASK =                                    0x0000FFFF,

        /*-----------------------------------------------------------------------
           ED-SDK Base Component IDs
        ------------------------------------------------------------------------*/
        CMP_ID_CLIENT_COMPONENTID =                       0x01000000,
        CMP_ID_LLSDK_COMPONENTID =                        0x02000000,
        CMP_ID_HLSDK_COMPONENTID =                        0x03000000,

        /*-----------------------------------------------------------------------
           ED-SDK Functin Success Code
        ------------------------------------------------------------------------*/
        OK =                                          0x00000000,

        /*-----------------------------------------------------------------------
           ED-SDK Generic Error IDs
        ------------------------------------------------------------------------*/
        /* Miscellaneous errors */
        [DescriptionAttribute("not implemented")]
        UNIMPLEMENTED =                               0x00000001,
        [DescriptionAttribute("internal error")]
        INTERNAL_ERROR =                              0x00000002,
        [DescriptionAttribute("memory allocation failed")]
        MEM_ALLOC_FAILED =                            0x00000003,
        MEM_FREE_FAILED =                             0x00000004,
        [DescriptionAttribute("operation cancelled")]
        OPERATION_CANCELLED =                         0x00000005,
        [DescriptionAttribute("incompatible version")]
        INCOMPATIBLE_VERSION =                        0x00000006,
        [DescriptionAttribute("operation not supported")]
        NOT_SUPPORTED =                               0x00000007,
        [DescriptionAttribute("unexpected exception")]
        UNEXPECTED_EXCEPTION =                        0x00000008,
        [DescriptionAttribute("protection violation")]
        PROTECTION_VIOLATION =                        0x00000009,
        MISSING_SUBCOMPONENT =                        0x0000000A,
        SELECTION_UNAVAILABLE =                       0x0000000B,

        /* File errors */
        [DescriptionAttribute("file IO error")]
        FILE_IO_ERROR =                               0x00000020,
        [DescriptionAttribute("too many files open")]
        FILE_TOO_MANY_OPEN =                          0x00000021,
        [DescriptionAttribute("file not found")]
        FILE_NOT_FOUND =                              0x00000022,
        [DescriptionAttribute("file open error")]
        FILE_OPEN_ERROR =                             0x00000023,
        [DescriptionAttribute("file close error")]
        FILE_CLOSE_ERROR =                            0x00000024,
        [DescriptionAttribute("file seek error")]
        FILE_SEEK_ERROR =                             0x00000025,
        [DescriptionAttribute("file tell error")]
        FILE_TELL_ERROR =                             0x00000026,
        [DescriptionAttribute("file read error")]
        FILE_READ_ERROR =                             0x00000027,
        [DescriptionAttribute("file write error")]
        FILE_WRITE_ERROR =                            0x00000028,
        [DescriptionAttribute("file permission error")]
        FILE_PERMISSION_ERROR =                       0x00000029,
        [DescriptionAttribute("disk full")]
        FILE_DISK_FULL_ERROR =                        0x0000002A,
        [DescriptionAttribute("file already exists")]
        FILE_ALREADY_EXISTS =                         0x0000002B,
        [DescriptionAttribute("unrecognized file format")]
        FILE_FORMAT_UNRECOGNIZED =                    0x0000002C,
        [DescriptionAttribute("file data corrupt")]
        FILE_DATA_CORRUPT =                           0x0000002D,
        [DescriptionAttribute("file naming error")]
        FILE_NAMING_NA =                              0x0000002E,

        /* Directory errors */
        [DescriptionAttribute("directory not found")]
        DIR_NOT_FOUND =                               0x00000040,
        [DescriptionAttribute("directory IO error")]
        DIR_IO_ERROR =                                0x00000041,
        [DescriptionAttribute("directory entry not found")]
        DIR_ENTRY_NOT_FOUND =                         0x00000042,
        [DescriptionAttribute("directory entry exists")]
        DIR_ENTRY_EXISTS =                            0x00000043,
        [DescriptionAttribute("directory not empty")]
        DIR_NOT_EMPTY =                               0x00000044,

        /* Property errors */
        [DescriptionAttribute("property unavailable")]
        PROPERTIES_UNAVAILABLE =                      0x00000050,
        [DescriptionAttribute("property mismatch")]
        PROPERTIES_MISMATCH =                         0x00000051,
        [DescriptionAttribute("property not loaded")]
        PROPERTIES_NOT_LOADED =                       0x00000053,

        /* Function Parameter errors */
        INVALID_PARAMETER =                           0x00000060,
        INVALID_HANDLE =                              0x00000061,
        INVALID_POINTER =                             0x00000062,
        INVALID_INDEX =                               0x00000063,
        INVALID_LENGTH =                              0x00000064,
        INVALID_FN_POINTER =                          0x00000065,
        INVALID_SORT_FN =                             0x00000066,

        /* Device errors */
        [DescriptionAttribute("device not found")]
        DEVICE_NOT_FOUND =                            0x00000080,
        
        [DescriptionAttribute("device is busy")] 
        DEVICE_BUSY =                                 0x00000081,
        [DescriptionAttribute("invalid device")]
        DEVICE_INVALID =                              0x00000082,
        DEVICE_EMERGENCY =                            0x00000083,
        DEVICE_MEMORY_FULL =                          0x00000084,
        DEVICE_INTERNAL_ERROR =                       0x00000085,
        DEVICE_INVALID_PARAMETER =                    0x00000086,
        DEVICE_NO_DISK =                              0x00000087,
        DEVICE_DISK_ERROR =                           0x00000088,
        DEVICE_CF_GATE_CHANGED =                      0x00000089,
        DEVICE_DIAL_CHANGED =                         0x0000008A,
        DEVICE_NOT_INSTALLED =                        0x0000008B,
        DEVICE_STAY_AWAKE =                           0x0000008C,
        DEVICE_NOT_RELEASED =                         0x0000008D,

        /* Stream errors */
        [DescriptionAttribute("IO error")]
        STREAM_IO_ERROR =                             0x000000A0,
        [DescriptionAttribute("stream not open")]
        STREAM_NOT_OPEN =                             0x000000A1,
        [DescriptionAttribute("stream already open")]
        STREAM_ALREADY_OPEN =                         0x000000A2,
        [DescriptionAttribute("stream open error")]
        STREAM_OPEN_ERROR =                           0x000000A3,
        [DescriptionAttribute("stream close error")]
        STREAM_CLOSE_ERROR =                          0x000000A4,
        [DescriptionAttribute("stream seek error")]
        STREAM_SEEK_ERROR =                           0x000000A5,
        [DescriptionAttribute("steam tell error")]
        STREAM_TELL_ERROR =                           0x000000A6,
        [DescriptionAttribute("read error")]
        STREAM_READ_ERROR =                           0x000000A7,
        [DescriptionAttribute("write error")]
        STREAM_WRITE_ERROR =                          0x000000A8,
        [DescriptionAttribute("permission error")]
        STREAM_PERMISSION_ERROR =                     0x000000A9,
        [DescriptionAttribute("couldn't begin thread")]
        STREAM_COULDNT_BEGIN_THREAD =                 0x000000AA,
        [DescriptionAttribute("bad options")]
        STREAM_BAD_OPTIONS =                          0x000000AB,
        [DescriptionAttribute("end of stream")]
        STREAM_END_OF_STREAM =                        0x000000AC,

        /* Communications errors */
        COMM_PORT_IS_IN_USE =                         0x000000C0,
        COMM_DISCONNECTED =                           0x000000C1,
        COMM_DEVICE_INCOMPATIBLE =                    0x000000C2,
        COMM_BUFFER_FULL =                            0x000000C3,
        COMM_USB_BUS_ERR =                            0x000000C4,

        /* Lock/Unlock */
        [DescriptionAttribute("device lock error")]
        USB_DEVICE_LOCK_ERROR =                       0x000000D0,
        [DescriptionAttribute("device unlock error")]
        USB_DEVICE_UNLOCK_ERROR =                     0x000000D1,

        /* STI/WIA */
        STI_UNKNOWN_ERROR =                           0x000000E0,
        STI_INTERNAL_ERROR =                          0x000000E1,
        STI_DEVICE_CREATE_ERROR =                     0x000000E2,
        STI_DEVICE_RELEASE_ERROR =                    0x000000E3,
        DEVICE_NOT_LAUNCHED =                         0x000000E4,
    
        ENUM_NA =                                     0x000000F0,
        INVALID_FN_CALL =                             0x000000F1,
        HANDLE_NOT_FOUND =                            0x000000F2,
        INVALID_ID =                                  0x000000F3,
        WAIT_TIMEOUT_ERROR =                          0x000000F4,

        /* PTP */
        SESSION_NOT_OPEN =                              0x00002003,
        INVALID_TRANSACTIONID =                         0x00002004,
        INCOMPLETE_TRANSFER =                           0x00002007,
        INVALID_STRAGEID =                              0x00002008,
        DEVICEPROP_NOT_SUPPORTED =                      0x0000200A,
        INVALID_OBJECTFORMATCODE =                      0x0000200B,
        SELF_TEST_FAILED =                              0x00002011,
        PARTIAL_DELETION =                              0x00002012,
        SPECIFICATION_BY_FORMAT_UNSUPPORTED =           0x00002014,
        NO_VALID_OBJECTINFO =                           0x00002015,
        INVALID_CODE_FORMAT =                           0x00002016,
        UNKNOWN_VENDER_CODE =                           0x00002017,
        CAPTURE_ALREADY_TERMINATED =                    0x00002018,
        INVALID_PARENTOBJECT =                          0x0000201A,
        INVALID_DEVICEPROP_FORMAT =                     0x0000201B,
        INVALID_DEVICEPROP_VALUE =                      0x0000201C,
        [DescriptionAttribute("session already open")]
        SESSION_ALREADY_OPEN =                          0x0000201E,
        TRANSACTION_CANCELLED =                         0x0000201F,
        SPECIFICATION_OF_DESTINATION_UNSUPPORTED =      0x00002020,
        UNKNOWN_COMMAND =                               0x0000A001,
        OPERATION_REFUSED =                             0x0000A005,
        LENS_COVER_CLOSE =                              0x0000A006,
        [DescriptionAttribute("low battery")]
		LOW_BATTERY =								    0x0000A101,
		OBJECT_NOTREADY =							    0x0000A102,

		/* Capture Error */
        [DescriptionAttribute("unable to auto focus")]
		TAKE_PICTURE_AF_NG =						    0x00008D01,
		TAKE_PICTURE_RESERVED =						    0x00008D02,
		TAKE_PICTURE_MIRROR_UP_NG =					    0x00008D03,
		TAKE_PICTURE_SENSOR_CLEANING_NG =			    0x00008D04,
		TAKE_PICTURE_SILENCE_NG =						0x00008D05,
		TAKE_PICTURE_NO_CARD_NG =						0x00008D06,
		TAKE_PICTURE_CARD_NG =						    0x00008D07,
		TAKE_PICTURE_CARD_PROTECT_NG =				    0x00008D08,


        LAST_GENERIC_ERROR_PLUS_ONE =                   0x000000F5,
    }
}