package template.android.com.data.firebase.mapper

import template.android.com.data.firebase.model.FirebaseConference
import template.android.com.data.firebase.model.FirebaseEventData
import template.android.com.data.firebase.model.FirebaseEventInfo
import template.android.com.domain.model.Conference
import template.android.com.domain.model.ConferenceDates
import template.android.com.domain.model.EventData
import template.android.com.domain.model.EventInfo

interface FirebaseMapper {
    fun mapToConference(firebaseConference: FirebaseConference?): Conference

    fun mapToEventInfo(firebaseEventInfo: FirebaseEventInfo?): EventInfo

    fun mapToConferenceDates(firebaseConference: FirebaseConference?): ConferenceDates

    fun mapToEventData(firebaseEventData: FirebaseEventData?): EventData
}