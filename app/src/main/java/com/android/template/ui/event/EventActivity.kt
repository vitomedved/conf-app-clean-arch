package com.android.template.ui.event

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.widget.Toolbar
import android.view.View
import butterknife.BindView
import butterknife.OnClick
import com.android.template.R
import com.android.template.base.BaseActivity
import com.android.template.base.ScopedPresenter
import com.android.template.injection.activity.ActivityComponent
import template.android.com.domain.model.EventData
import template.android.com.domain.model.EventInfo
import javax.inject.Inject

class EventActivity : BaseActivity(), EventContract.View {

    companion object {
        const val EVENT_ID_TAG = "eventId"
    }

    @BindView(R.id.activity_event_toolbar)
    lateinit var toolbar: Toolbar

    @Inject
    lateinit var presenter: EventContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        if((null == savedInstanceState) && (null != intent.extras)) {
            initSupportActionBar()
            presenter.initEvent(intent.getStringExtra(EVENT_ID_TAG))
        }
    }

    private fun initSupportActionBar() {
        setSupportActionBar(toolbar)
    }

    override fun inject(component: ActivityComponent) {
        component.inject(this)
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_event
    }

    override fun getPresenter(): ScopedPresenter {
        return presenter
    }

    override fun renderEventInfo(eventInfo: EventInfo) {
        // TODO
    }

    override fun renderEventData(eventData: EventData) {
        // TODO
    }

    override fun hideAllContent() {
        // TODO
    }

    override fun showEventInfoContent() {
        // TODO
    }

    override fun showEventDataContent() {
        // TODO
    }

    override fun showLoading() {
        // TODO
    }

    override fun hideLoading() {
        // TODO
    }

    @OnClick(R.id.activity_event_fab)
    fun onFabClicked(view: View)
    {
        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                .setAction("Action", null)
                .show()
    }

}