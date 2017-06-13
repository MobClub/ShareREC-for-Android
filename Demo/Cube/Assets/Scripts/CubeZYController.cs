using UnityEngine;
using System.Collections;

public class CubeZYController : MonoBehaviour {
	private float sita;

	void FixedUpdate () {
		float tarZ = 4 * Mathf.Cos (sita);
		float tarY = 4 * Mathf.Sin (sita);
		gameObject.transform.position = new Vector3 (gameObject.transform.position.x, tarY, tarZ);
		sita -= Mathf.PI / 180;
	}
}
