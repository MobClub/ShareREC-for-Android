namespace cn.sharerec {
#if UNITY_ANDROID
	public enum LevelMaxFrameSize {
		LEVEL_480_360 = 0, //4:3   (Half-size VGA)
		LEVEL_1280_720 = 1,//16:9  720p 1280*720 标清
		LEVEL_1920_1080 = 2,//16:9  1080p 1920*1080 高清

		LEVAL_320_240 = 3,//
		LEVAL_400_240 = 4,
		LEVAL_432_240 = 5,
		LEVEL_480_320 = 6,
		
		LEVEL_800_480 = 7,//5:3 WVGA 800*480 (Wide VGA)
		LEVEL_800_600 = 8,//4:3 SVGA 800*600 (Super VGA)
		LEVEL_854_480 = 9,
		LEVEL_1280_768 = 10,//
		LEVEL_2048_1152  = 11,
		LEVEL_2560_1440 = 12
	}
#endif
}
