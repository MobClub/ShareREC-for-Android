using System;
using UnityEngine;

namespace cn.sharerec {
#if UNITY_ANDROID

	public class RECBar {
		private AndroidJavaObject recBar;

		public RECBar(AndroidJavaObject recorder, string gameObject, string callback) {
			try {
				recBar = new AndroidJavaObject("cn.sharerec.gui.Unity3DRecBar");
				recBar.Call("setRecorder", recorder);
				recBar.Call("setRecBarCallback", gameObject, callback);
				recBar.Call("setRecBarAnchor", 1);
			} catch(Exception e) {
				throw e;
			}
		}

		public void ShowRecBar() {
			recBar.Call("showRecBar");
		}

		public void HideRecBar() {
			recBar.Call("hideRecBar");
		}

	}

#endif
}

