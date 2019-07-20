package template.android.com.data.firebase.mapper

import template.android.com.data.firebase.model.FirebaseConference
import template.android.com.domain.model.Conference

interface FirebaseMapper {
    fun mapToConference(firebaseConference: FirebaseConference?): Conference
}