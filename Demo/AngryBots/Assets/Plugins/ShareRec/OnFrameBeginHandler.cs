using UnityEngine;
using System.Collections;

namespace cn.sharerec {
#if UNITY_ANDROID

	public class OnFrameBeginHandler : MonoBehaviour {
		private JavaInterface javaInter;

		public void SetJavaInterface(JavaInterface javaInter) {
			this.javaInter = javaInter;
		}

		void OnPreRender() {
			#if ((UNITY_2_6 || UNITY_3_0 || UNITY_3_1 || UNITY_3_2 || UNITY_3_3 || UNITY_3_4 || UNITY_3_5 || UNITY_4_0 || UNITY_4_1 || UNITY_4_3_4 || UNITY_5_1_2))
				javaInter.onPreRender();
			#else
				GL.IssuePluginEvent(0);
			#endif
		}

	}

#endif
}