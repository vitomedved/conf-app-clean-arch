package template.android.com.domain.model

data class EventData (
        var about: String = "",
        var commentIds: MutableList<String> = mutableListOf(),
        var presenterIds: MutableList<String> = mutableListOf(),
        var uid: String = ""
) {
    companion object {
        @JvmStatic
        fun empty(): EventData = EventData("", mutableListOf(), mutableListOf(), "")
    }
}