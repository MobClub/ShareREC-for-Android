#pragma strict
#pragma downcast

//import UnityEditor;

// MenuItem adds a menu item in the GameObject menu
// and executes the following function when clicked
@MenuItem ("Tools/Sample Animation On Selected")
static function SampleAnimation () {
	var anim : Animation = Selection.activeGameObject.GetComponent.<Animation>();
	if (anim != null) {
		anim.clip.SampleAnimation(Selection.activeGameObject, 0);
	}
}
