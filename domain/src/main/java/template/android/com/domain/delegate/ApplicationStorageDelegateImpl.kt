package template.android.com.domain.delegate

import com.annimon.stream.Optional
import template.android.com.domain.storage.ApplicationStorage
import template.android.com.domain.utils.string.StringUtils

class ApplicationStorageDelegateImpl (private val applicationStorage: ApplicationStorage, private val stringUtils: StringUtils) : ApplicationStorageDelegate {

    private val CONFERENCE_ID_KEY = "initialConferenceId"

    override fun saveConferenceId(id: String) {
        if(!stringUtils.isEmpty(id)) {
            applicationStorage.saveString(CONFERENCE_ID_KEY, id)
        }
    }

    override fun getConferenceId(): Optional<String> {
        return Optional.of(applicationStorage.getString(CONFERENCE_ID_KEY))
    }
}