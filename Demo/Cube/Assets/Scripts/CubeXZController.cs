using UnityEngine;
using System.Collections;

public class CubeXZController : MonoBehaviour {
	private float sita;

	void FixedUpdate () {
		float tarX = 4 * Mathf.Cos (sita + Mathf.PI / 4);
		float tarZ = 4 * Mathf.Sin (sita + Mathf.PI / 4);
		gameObject.transform.position = new Vector3 (tarX, gameObject.transform.position.y, tarZ);
		sita += Mathf.PI / 180;
	}
}
