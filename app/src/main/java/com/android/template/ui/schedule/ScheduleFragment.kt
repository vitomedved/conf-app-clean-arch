package com.android.template.ui.schedule

import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import butterknife.BindView
import butterknife.OnClick
import com.airbnb.lottie.LottieAnimationView
import com.android.template.R
import com.android.template.base.BaseFragment
import com.android.template.base.ScopedPresenter
import com.android.template.injection.fragment.FragmentComponent
import com.android.template.ui.schedule.adapter.ScheduleRecyclerAdapter
import com.android.template.utils.view.ViewUtils
import template.android.com.domain.model.EventInfo
import java.util.*
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

    @BindView(R.id.fragment_schedule_header_date_layout)
    lateinit var headerDateLayout: ConstraintLayout

    @BindView(R.id.fragment_schedule_header_button_left)
    lateinit var buttonPreviousDate: ImageButton

    @BindView(R.id.fragment_schedule_header_button_right)
    lateinit var buttonNextDate: ImageButton

    @BindView(R.id.fragment_schedule_header_date_number)
    lateinit var dateNumber: TextView

    @BindView(R.id.fragment_schedule_header_three_letter_month)
    lateinit var monthName: TextView

    @BindView(R.id.fragment_schedule_header_three_letter_weekday)
    lateinit var weekdayName: TextView

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

    override fun hideHeaderDate() {
        viewUtils.makeInvisible(headerDateLayout)
    }

    override fun showHeaderDate() {
        viewUtils.makeVisible(headerDateLayout)
    }

    override fun disableHeaderButtonLeft() {
        // TODO: when disabled, put shaded color maybe
        viewUtils.disableViews(buttonPreviousDate)
    }

    override fun enableHeaderButtonLeft() {
        // TODO: when enabled, return color to normal
        viewUtils.enableViews(buttonPreviousDate)
    }

    override fun disableHeaderButtonRight() {
        // TODO: when disabled, put shaded color maybe
        viewUtils.disableViews(buttonNextDate)
    }

    override fun enableHeaderButtonRight() {
        // TODO: when enabled, return color to normal
        viewUtils.enableViews(buttonNextDate)
    }

    override fun render(eventInfoList: List<EventInfo>) {
        scheduleRecyclerAdapter.setItems(eventInfoList)
    }

    override fun hideLoading() {
        viewUtils.makeInvisible(loadingAnimation)
    }

    override fun renderHeaderDateNumber(dateNumber: Int) {
        this.dateNumber.text = dateNumber.toString()
    }

    override fun renderHeaderMonth(shortMonthName: String) {
        monthName.text = shortMonthName
    }

    override fun renderHeaderWeekday(shortWeekdayName: String) {
        weekdayName.text = shortWeekdayName
    }

    @OnClick(R.id.fragment_schedule_header_button_left)
    fun onPreviousDayButtonClicked() {
        presenter.subtractDay()
    }

    @OnClick(R.id.fragment_schedule_header_button_right)
    fun onNextDayButtonClicked() {
        presenter.addDay()
    }
}