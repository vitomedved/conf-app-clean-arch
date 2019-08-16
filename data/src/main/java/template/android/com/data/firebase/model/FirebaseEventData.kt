package template.android.com.data.firebase.model

data class FirebaseEventData (
        var about: String? = "",
        var commentIds: HashMap<String, Boolean>? = hashMapOf(),
        var presenterIds: HashMap<String, Boolean>? = hashMapOf(),
        var uid: String? = ""
)