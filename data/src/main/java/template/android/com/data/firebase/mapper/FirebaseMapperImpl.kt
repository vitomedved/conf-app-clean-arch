package template.android.com.data.firebase.mapper


import template.android.com.data.firebase.model.FirebaseConference
import template.android.com.domain.model.Conference

class FirebaseMapperImpl : FirebaseMapper {
    override fun mapToConference(firebaseConference: FirebaseConference?): Conference {
        if(null == firebaseConference) {
            return Conference.empty()
        }
        return Conference(firebaseConference.uid, firebaseConference.name, firebaseConference.startDate, firebaseConference.endDate, firebaseConference.about, firebaseConference.location)
    }
}