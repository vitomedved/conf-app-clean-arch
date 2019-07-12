package template.android.com.domain.delegate

import com.annimon.stream.Optional

interface ApplicationStorageDelegate {
    fun saveConferenceId(id: String)

    fun getConferenceId(): Optional<String>
}