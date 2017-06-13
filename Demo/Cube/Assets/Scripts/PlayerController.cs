using UnityEngine;
using System.Collections;

public class PlayerController : MonoBehaviour {
	private float sita;

	void FixedUpdate () {
		float tarY = 2 * Mathf.Sin (sita);
		gameObject.transform.position = new Vector3 (gameObject.transform.position.x, tarY, gameObject.transform.position.z);
		sita += Mathf.PI / 60;
	}
}