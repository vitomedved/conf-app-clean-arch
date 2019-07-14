package com.android.template.ui.schedule

import com.android.template.R
import com.android.template.base.BaseFragment
import com.android.template.base.ScopedPresenter
import com.android.template.injection.fragment.FragmentComponent
import javax.inject.Inject

class ScheduleFragment : BaseFragment(), ScheduleContract.View {

    companion object {
        const val TAG = "ScheduleFragment"

        @JvmStatic
        fun newInstance(): ScheduleFragment = ScheduleFragment()
    }

    @Inject
    lateinit var presenter: ScheduleContract.Presenter

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_schedule
    }

    override fun inject(component: FragmentComponent) {
        component.inject(this)
    }

    override fun getPresenter(): ScopedPresenter {
        return presenter
    }

}