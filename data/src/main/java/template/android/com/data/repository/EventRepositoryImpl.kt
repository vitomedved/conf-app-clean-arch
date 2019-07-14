package template.android.com.data.repository

import io.reactivex.Single
import template.android.com.domain.model.Event
import template.android.com.domain.repository.EventRepository

class EventRepositoryImpl : EventRepository {
    override fun getEventsByConferenceId(id: String): Single<List<Event>> {
        // TODO: implement FireBase after merging WelcomeFragment with develop because I need some packages that are commited on the other branch, but not on develop
        return Single.fromCallable { listOf(Event("event1"), Event("event2")) }
    }
}