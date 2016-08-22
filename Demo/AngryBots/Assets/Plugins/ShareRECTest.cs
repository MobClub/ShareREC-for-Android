using UnityEngine;
using System;
using System.Collections;
using cn.sharerec;

/// <summary>
/// <para>ShareREC示例程序，将此脚本拖进MainCamera下即可使用</para>
/// <para>(A demo script of ShareREC, drag this script to MainCamera and run)</para>
/// </summary>
public class ShareRECTest : MonoBehaviour {
	public enum DemoType {
		RECBar,
		RECClassic
	}

	/// <summary>
	/// demo的类型 (type of Demo)
	/// </summary>
	public DemoType type = DemoType.RECClassic;

	void Start() {
		switch (type) {
			case DemoType.RECBar: gameObject.AddComponent<RECBarTest>(); break;
			default: gameObject.AddComponent<RECClassicTest>(); break;
		}
	}

}
