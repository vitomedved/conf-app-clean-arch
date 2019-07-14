package com.android.template.ui.schedule

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import butterknife.BindView
import com.airbnb.lottie.LottieAnimationView
import com.android.template.R
import com.android.template.base.BaseFragment
import com.android.template.base.ScopedPresenter
import com.android.template.injection.fragment.FragmentComponent
import com.android.template.ui.schedule.adapter.ScheduleRecyclerAdapter
import com.android.template.utils.view.ViewUtils
import template.android.com.domain.model.Event
import javax.inject.Inject

class ScheduleFragment : BaseFragment(), ScheduleContract.View {

    companion object {
        const val TAG = "ScheduleFragment"

        @JvmStatic
        fun newInstance(): ScheduleFragment = ScheduleFragment()
    }

    @BindView(R.id.fragment_schedule_recycler_view)
    lateinit var recyclerView: RecyclerView

    @BindView(R.id.fragment_schedule_loading_animation)
    lateinit var loadingAnimation: LottieAnimationView

    @Inject
    lateinit var presenter: ScheduleContract.Presenter

    @Inject
    lateinit var viewUtils: ViewUtils

    @Inject
    lateinit var linearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var scheduleRecyclerAdapter: ScheduleRecyclerAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initRecyclerView()

        presenter.init()
    }

    private fun initRecyclerView() {
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = scheduleRecyclerAdapter
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_schedule
    }

    override fun inject(component: FragmentComponent) {
        component.inject(this)
    }

    override fun getPresenter(): ScopedPresenter {
        return presenter
    }

    override fun hideRecyclerView() {
        viewUtils.makeInvisible(recyclerView)
    }

    override fun showRecyclerView() {
        viewUtils.makeVisible(recyclerView)
    }

    override fun render(events: List<Event>) {
        scheduleRecyclerAdapter.setItems(events)
    }

    override fun hideLoading() {
        viewUtils.makeInvisible(loadingAnimation)
    }
}