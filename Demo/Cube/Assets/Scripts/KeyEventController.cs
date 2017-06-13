using System.Collections;
using System.Collections.Generic;
using UnityEngine;

public class KeyEventController : MonoBehaviour {

	void OnGUI() {
		if (Input.GetKeyUp (KeyCode.Escape)) {
			Application.Quit ();
		}
	}
}
