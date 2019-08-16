package template.android.com.data.firebase.mapper


import template.android.com.data.firebase.model.FirebaseConference
import template.android.com.data.firebase.model.FirebaseEventData
import template.android.com.data.firebase.model.FirebaseEventInfo
import template.android.com.domain.model.Conference
import template.android.com.domain.model.ConferenceDates
import template.android.com.domain.model.EventData
import template.android.com.domain.model.EventInfo
import template.android.com.domain.utils.string.StringUtils

class FirebaseMapperImpl(private val stringUtils: StringUtils) : FirebaseMapper {
    override fun mapToConference(firebaseConference: FirebaseConference?): Conference {
        if (null == firebaseConference) {
            return Conference.empty()
        }
        return Conference(firebaseConference.uid, firebaseConference.name, firebaseConference.startDate, firebaseConference.endDate, firebaseConference.about, firebaseConference.location)
    }

    override fun mapToEventInfo(firebaseEventInfo: FirebaseEventInfo?): EventInfo {

        if (null == firebaseEventInfo) {
            return EventInfo.empty()
        }

        return EventInfo(stringUtils.itOrDefault(firebaseEventInfo.date, ""),
                         firebaseEventInfo.durationInMinutes ?: 0,
                         stringUtils.itOrDefault(firebaseEventInfo.hall, ""),
                         stringUtils.itOrDefault(firebaseEventInfo.name, ""),
                         stringUtils.itOrDefault(firebaseEventInfo.startingTime, ""),
                         stringUtils.itOrDefault(firebaseEventInfo.type, ""),
                         stringUtils.itOrDefault(firebaseEventInfo.uid, ""))
    }

    override fun mapToConferenceDates(firebaseConference: FirebaseConference?): ConferenceDates {
        if (null == firebaseConference) {
            return ConferenceDates.empty()
        }

        return ConferenceDates(stringUtils.itOrDefault(firebaseConference.startDate, ""), stringUtils.itOrDefault(firebaseConference.endDate, ""))
    }

    override fun mapToEventData(firebaseEventData: FirebaseEventData?): EventData {
        if(null == firebaseEventData) {
            return EventData.empty()
        }

        // TODO: FIX LISTS OF COMMENTS AND PRESENTERS
        return EventData(stringUtils.itOrDefault(firebaseEventData.about, ""), firebaseEventData.commentIds?.keys?.toMutableList()?: mutableListOf(), firebaseEventData.presenterIds?.keys?.toMutableList()?: mutableListOf(), stringUtils.itOrDefault(firebaseEventData.uid, ""))
    }
}