using UnityEngine;

namespace cn.sharerec {
#if UNITY_ANDROID

	public class OnFrameBeginHandler : MonoBehaviour {

		void OnPreRender() {
			ShareRECImpl.OnPreRender();
		}

	}

#endif
}