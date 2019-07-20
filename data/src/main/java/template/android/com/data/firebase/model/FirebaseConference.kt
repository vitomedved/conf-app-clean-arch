package template.android.com.data.firebase.model

data class FirebaseConference (
        val about: String = "",
        val endDate: String = "",
        val eventIds: List<String> = listOf(),
        val exhibitorIds: List<String> = listOf(),
        val uid: String = "",
        val location: String = "",
        val name: String = "",
        val startDate: String = ""
)