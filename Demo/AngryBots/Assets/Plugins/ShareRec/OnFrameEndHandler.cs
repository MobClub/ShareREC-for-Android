using UnityEngine;
using System.Collections;

namespace cn.sharerec {
#if UNITY_ANDROID

	public class OnFrameEndHandler : MonoBehaviour {
		private JavaInterface javaInter;
		private int g_screenfbo = 0;
		
		public void SetJavaInterface(JavaInterface javaInter) {
			this.javaInter = javaInter;
		}

		IEnumerator OnPostRender() {
			yield return new WaitForEndOfFrame();
			#if ((UNITY_2_6 || UNITY_3_0 || UNITY_3_1 || UNITY_3_2 || UNITY_3_3 || UNITY_3_4 || UNITY_3_5 || UNITY_4_0 || UNITY_4_1 || UNITY_4_3_4 || UNITY_5_1_2))
				javaInter.onPostRender(g_screenfbo);
			#else
				javaInter.onPostRender(ShareRec.getScreenFbo());
			#endif
		}

	}

#endif
}