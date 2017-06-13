using UnityEngine;
using System.Collections;

public class CubeXYController : MonoBehaviour {
	private float sita;

	void FixedUpdate () {
		float tarX = 4 * Mathf.Cos (sita);
		float tarY = 4 * Mathf.Sin (sita);
		gameObject.transform.position = new Vector3 (tarX, tarY, gameObject.transform.position.z);
		sita += Mathf.PI / 180;
	}
}
