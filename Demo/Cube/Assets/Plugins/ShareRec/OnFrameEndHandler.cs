using UnityEngine;
using System.Collections;
using System.IO;

namespace cn.sharerec {
#if UNITY_ANDROID

	public class OnFrameEndHandler : MonoBehaviour {

		IEnumerator OnPostRender() {
			yield return new WaitForEndOfFrame();
		#if (!UNITY_5_6_OR_NEWER)
			if (ShareRECImpl.IsRecordGUILayer()) {
				ShareRECImpl.OnPostRender();
			}
		#endif
		}

		void OnRenderImage(RenderTexture src, RenderTexture dest) {
			ShareRECImpl.AddCameraRecord(src);
		#if (UNITY_5_6_OR_NEWER)
			ShareRECImpl.OnPostRender();
		#else
			if (!ShareRECImpl.IsRecordGUILayer()) {
				ShareRECImpl.OnPostRender();
			}
		#endif
		}
	}

#endif
}