package template.android.com.domain.model

data class EventInfo (
        var date: String = "",
        var durationInMinutes: Int = 0,
        var hall: String = "",
        var name: String = "",
        var startingTime: String = "",
        var eventType: String = "",
        var uid: String = ""
) {
    companion object {
        @JvmStatic
        fun empty(): EventInfo = EventInfo("", 0, "", "", "", "", "")
    }
}