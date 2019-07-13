package template.android.com.domain.model

data class Conference (
        var id: String,
        var name: String,
        var startDate: String,
        var endDate: String,
        var about: String,
        var location: String//,

        // TODO: idea here is to set event rankings (most subscribed or something) and exhibitor rankings to sort top ones on about conference fragment
        //val topEvents: List<User>,
        //val topExhibitors: List<User>
) {
    companion object {
        @JvmStatic
        fun empty(): Conference = Conference("", "", "", "", "", "")
    }
}