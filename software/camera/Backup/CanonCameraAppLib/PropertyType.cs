using System;
using System.ComponentModel;
using System.Reflection;

namespace CanonCameraAppLib
{
    internal enum PropertyType : uint
    {
        /*----------------------------------
         Camera Setting Properties
        ----------------------------------*/
        [DescriptionAttribute("unknown property")]
        Unknown              = 0x0000ffff,
        [DescriptionAttribute("product name")]
        ProductName          = 0x00000002,
        [DescriptionAttribute("body id")]
        BodyID               = 0x00000003,
        [DescriptionAttribute("owner name")]
        OwnerName            = 0x00000004,
        [DescriptionAttribute("maker name")]
        MakerName            = 0x00000005,
        [DescriptionAttribute("date time")]
        DateTime             = 0x00000006,
        [DescriptionAttribute("firmware version")]
        FirmwareVersion      = 0x00000007,
        [DescriptionAttribute("battery level")]
        BatteryLevel         = 0x00000008,
        CFn                  = 0x00000009,
        [DescriptionAttribute("save to")]
        SaveTo               = 0x0000000b,

        [DescriptionAttribute("battery quality")]
		BatteryQuality         = 0x00000010,

        [DescriptionAttribute("HD directory structure")]
		HDDirectoryStructure   = 0x00000020,

        [DescriptionAttribute("quick review time")]
        QuickReviewTime        = 0x0000000f,

        /*----------------------------------
         Image Properties
        ----------------------------------*/
        [DescriptionAttribute("image quality")]
        Image_ImageQuality         = 0x00000100,
        [DescriptionAttribute("JPEG quality")]
        Image_JpegQuality          = 0x00000101,
        [DescriptionAttribute("orientation")]
        Image_Orientation          = 0x00000102,
        [DescriptionAttribute("ICC profile")]
        Image_ICCProfile           = 0x00000103,
        [DescriptionAttribute("focus info")]
        Image_FocusInfo            = 0x00000104,
        [DescriptionAttribute("digital exposure")]
        Image_DigitalExposure      = 0x00000105,
        [DescriptionAttribute("white balance")]
        Image_WhiteBalance         = 0x00000106,
        [DescriptionAttribute("color temperature")]
        Image_ColorTemperature     = 0x00000107,
        [DescriptionAttribute("white balance shift")]
        Image_WhiteBalanceShift    = 0x00000108,
        [DescriptionAttribute("contrast")]
        Image_Contrast             = 0x00000109,
        [DescriptionAttribute("color saturation")]
        Image_ColorSaturation      = 0x0000010a,
        [DescriptionAttribute("color tone")]
        Image_ColorTone            = 0x0000010b,
        [DescriptionAttribute("sharpness")]
        Image_Sharpness            = 0x0000010c,
        [DescriptionAttribute("color space")]
        Image_ColorSpace           = 0x0000010d,
        [DescriptionAttribute("tone curve")]
        Image_ToneCurve            = 0x0000010e,
        [DescriptionAttribute("photo effect")]
        Image_PhotoEffect          = 0x0000010f,
        [DescriptionAttribute("filter effect")]
        Image_FilterEffect         = 0x00000110,
        [DescriptionAttribute("toning effect")]
        Image_ToningEffect         = 0x00000111,
        [DescriptionAttribute("parameter set")]
        Image_ParameterSet         = 0x00000112,
        [DescriptionAttribute("color matrix")]
        Image_ColorMatrix          = 0x00000113,
        [DescriptionAttribute("picture style")]
        Image_PictureStyle         = 0x00000114,
        [DescriptionAttribute("picture style description")]
        Image_PictureStyleDesc     = 0x00000115,
        [DescriptionAttribute("picture style caption")]
        Image_PictureStyleCaption  = 0x00000200,

        /*----------------------------------
         Image Processing Properties
        ----------------------------------*/
        [DescriptionAttribute("linear processing")]
        Linear               = 0x00000300,
        [DescriptionAttribute("click WB point")]
        ClickWBPoint         = 0x00000301,
        [DescriptionAttribute("WB coefficents")]
        WBCoeffs             = 0x00000302,

        /*----------------------------------
         Property Mask
        ----------------------------------*/
        AtCapture_Flag       = 0x80000000,

        /*----------------------------------
         Capture Properties
        ----------------------------------*/
        [DescriptionAttribute("AE mode")]
        Capture_AEMode               = 0x00000400,
        [DescriptionAttribute("drive mode")]
        Capture_DriveMode            = 0x00000401,
        [DescriptionAttribute("ISO speed")]
        Capture_ISOSpeed             = 0x00000402,
        [DescriptionAttribute("metering mode")]
        Capture_MeteringMode         = 0x00000403,
        [DescriptionAttribute("auto focus mode")]
        Capture_AFMode               = 0x00000404,
        [DescriptionAttribute("aperture priority")]
        Capture_Av                   = 0x00000405,
        [DescriptionAttribute("shutter priority")]
        Capture_Tv                   = 0x00000406,
        [DescriptionAttribute("exposure compensation")]
        Capture_ExposureCompensation = 0x00000407,
        [DescriptionAttribute("flash compensation")]
        Capture_FlashCompensation    = 0x00000408,
        [DescriptionAttribute("focal length")]
        Capture_FocalLength          = 0x00000409,
        [DescriptionAttribute("available shots")]
        Capture_AvailableShots       = 0x0000040a,
        [DescriptionAttribute("bracket")]
        Capture_Bracket              = 0x0000040b,
        [DescriptionAttribute("white balance bracket")]
        Capture_WhiteBalanceBracket  = 0x0000040c,
        [DescriptionAttribute("lens name")]
        Capture_LensName             = 0x0000040d,
        [DescriptionAttribute("AE bracket")]
        Capture_AEBracket            = 0x0000040e,
        [DescriptionAttribute("FE bracket")]
        Capture_FEBracket            = 0x0000040f,
        [DescriptionAttribute("ISO bracket")]
        Capture_ISOBracket           = 0x00000410,
        [DescriptionAttribute("noise reduction")]
        Capture_NoiseReduction       = 0x00000411,
        [DescriptionAttribute("flash on")]
        Capture_FlashOn              = 0x00000412,
        [DescriptionAttribute("red eye")]
        Capture_RedEye               = 0x00000413,
        [DescriptionAttribute("flash mode")]
        Capture_FlashMode            = 0x00000414,
        [DescriptionAttribute("lens status")]
        Capture_LensStatus           = 0x00000416,

        #region Masked Capture Properties

            [DescriptionAttribute("AE mode at capture")]
            Capture_AEMode_At_Capture = 0x80000400,
            [DescriptionAttribute("drive mode at capture")]
            Capture_DriveMode_At_Capture = 0x80000401,
            [DescriptionAttribute("ISO speed at capture")]
            Capture_ISOSpeed_At_Capture = 0x80000402,
            [DescriptionAttribute("metering mode at capture")]
            Capture_MeteringMode_At_Capture = 0x80000403,
            [DescriptionAttribute("auto focus mode at capture")]
            Capture_AFMode_At_Capture = 0x80000404,
            [DescriptionAttribute("aperture priority at capture")]
            Capture_Av_At_Capture = 0x80000405,
            [DescriptionAttribute("shutter priority at capture")]
            Capture_Tv_At_Capture = 0x80000406,
            [DescriptionAttribute("exposure compensation at capture")]
            Capture_ExposureCompensation_At_Capture = 0x80000407,
            [DescriptionAttribute("flash compensation at capture")]
            Capture_FlashCompensation_At_Capture = 0x80000408,
            [DescriptionAttribute("focal length at capture")]
            Capture_FocalLength_At_Capture = 0x80000409,
            [DescriptionAttribute("available shots at capture")]
            Capture_AvailableShots_At_Capture = 0x8000040a,
            [DescriptionAttribute("bracket at capture")]
            Capture_Bracket_At_Capture = 0x8000040b,
            [DescriptionAttribute("white balance bracket at capture")]
            Capture_WhiteBalanceBracket_At_Capture = 0x8000040c,
            [DescriptionAttribute("lens name at capture")]
            Capture_LensName_At_Capture = 0x8000040d,
            [DescriptionAttribute("AE bracket at capture")]
            Capture_AEBracket_At_Capture = 0x8000040e,
            [DescriptionAttribute("FE bracket at capture")]
            Capture_FEBracket_At_Capture = 0x8000040f,
            [DescriptionAttribute("ISO bracket at capture")]
            Capture_ISOBracket_At_Capture = 0x80000410,
            [DescriptionAttribute("noise reduction at capture")]
            Capture_NoiseReduction_At_Capture = 0x80000411,
            [DescriptionAttribute("flash on at capture")]
            Capture_FlashOn_At_Capture = 0x80000412,
            [DescriptionAttribute("red eye at capture")]
            Capture_RedEye_At_Capture = 0x80000413,
            [DescriptionAttribute("flash mode at capture")]
            Capture_FlashMode_At_Capture = 0x80000414,
            [DescriptionAttribute("lens status at capture")]
            Capture_LensStatus_At_Capture = 0x80000416,

        #endregion

        [DescriptionAttribute("artist")]
        Artist	            = 0x00000418,
        [DescriptionAttribute("copyright")]
        Copyright	        = 0x00000419,
        [DescriptionAttribute("depth of field")]
        DepthOfField	        = 0x0000041b,
        [DescriptionAttribute("EF compensation")]
        EFCompensation       = 0x0000041e,

		/*----------------------------------
		 EVF Properties
		----------------------------------*/
        [DescriptionAttribute("evf output device")]
        Evf_OutputDevice        = 0x00000500,
        [DescriptionAttribute("evf mode")]
        Evf_Mode                = 0x00000501,
        [DescriptionAttribute("evf white balance")]
        Evf_WhiteBalance        = 0x00000502,
        [DescriptionAttribute("evf color temperature")]
        Evf_ColorTemperature    = 0x00000503,
        [DescriptionAttribute("evf depth of field preview")]
        Evf_DepthOfFieldPreview = 0x00000504,

		// EVF IMAGE DATA Properties
        [DescriptionAttribute("evf zoom")]
        Evf_Zoom                = 0x00000507,
        [DescriptionAttribute("evf zoom position")]
        Evf_ZoomPosition        = 0x00000508,
        [DescriptionAttribute("focus aid")]
        Evf_FocusAid            = 0x00000509,
        [DescriptionAttribute("histogram")]
        Evf_Histogram           = 0x0000050A,
        [DescriptionAttribute("image position")]
        Evf_ImagePosition       = 0x0000050B,
        [DescriptionAttribute("histogram status")]
		Evf_HistogramStatus     = 0x0000050C,
        [DescriptionAttribute("auto focus mode")]
        Evf_AFMode              = 0x0000050E,
             
        /*----------------------------------
         Image GPS Properties
        ----------------------------------*/
        GPS_VersionID			 =  0x00000800,	
        GPS_LatitudeRef		 =  0x00000801,		
        GPS_Latitude			 =  0x00000802,	
        GPS_LongitudeRef		 =  0x00000803,	
        GPS_Longitude			 =  0x00000804,		
		GPS_AltitudeRef		 =  0x00000805,		
        GPS_Altitude			 =  0x00000806,		
        GPS_TimeStamp			 =  0x00000807,		
        GPS_Satellites		 =  0x00000808,		
        GPS_Status			 =  0x00000809,
        GPS_MapDatum			 =  0x00000812,		
		GPS_DateStamp			 =  0x0000081D
    }
}