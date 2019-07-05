package template.android.com.domain.delegate

interface ApplicationStorageDelegate {
    fun saveConferenceId(id: String)

    fun getConferenceId(): String
}