package template.android.com.domain.model

data class ConferenceDates(
        var startDate: String,
        var endDate: String
) {
    companion object {
        @JvmStatic
        fun empty(): ConferenceDates = ConferenceDates("", "")
    }
}