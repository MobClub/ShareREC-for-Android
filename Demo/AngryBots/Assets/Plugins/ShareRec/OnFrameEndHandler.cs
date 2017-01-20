using UnityEngine;
using System.Collections;
using System.IO;

namespace cn.sharerec {
#if UNITY_ANDROID

	public class OnFrameEndHandler : MonoBehaviour {

		IEnumerator OnPostRender() {
			yield return new WaitForEndOfFrame();
			if (ShareRECImpl.IsRecordGUILayer ()) {
				ShareRECImpl.OnPostRender();
			}
		}

		void OnRenderImage(RenderTexture src, RenderTexture dest) {
			ShareRECImpl.addCameraRecord(src);
			if (!ShareRECImpl.IsRecordGUILayer ()) {
				ShareRECImpl.OnPostRender();
			}
		}
	}

#endif
}