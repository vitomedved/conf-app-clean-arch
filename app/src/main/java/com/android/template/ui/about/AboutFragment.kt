package com.android.template.ui.about

import android.content.res.Resources
import android.os.Bundle
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.airbnb.lottie.LottieAnimationView
import com.android.template.R
import com.android.template.base.BaseFragment
import com.android.template.base.ScopedPresenter
import com.android.template.injection.fragment.FragmentComponent
import com.android.template.utils.ui.ToastUtil
import com.android.template.utils.view.ViewUtils
import template.android.com.domain.model.Conference
import javax.inject.Inject

class AboutFragment : BaseFragment(), AboutContract.View {

    companion object {
        const val TAG = "AboutFragment"

        @JvmStatic
        fun newInstance(): AboutFragment = AboutFragment()
    }

    @BindView(R.id.fragment_about_animation_loading)
    lateinit var loadingAnimation: LottieAnimationView

    @BindView(R.id.fragment_about_conference_name_text)
    lateinit var conferenceNameTextView: TextView

    @Inject
    lateinit var presenter: AboutContract.Presenter

    @Inject
    lateinit var viewUtils: ViewUtils

    @Inject
    lateinit var toastUtil: ToastUtil

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter.init()
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_about
    }

    override fun inject(component: FragmentComponent) {
        component.inject(this)
    }

    override fun getPresenter(): ScopedPresenter {
        return presenter
    }

    override fun render(conference: Conference) {

        // TODO: Add rest of the design on aboutFragment, currently only showing name. This will be done later.

        conferenceNameTextView.text = conference.name

        viewUtils.makeGone(loadingAnimation)
        viewUtils.makeVisible(conferenceNameTextView)
    }

    override fun showGetInitialConferenceIdError() {
        toastUtil.showLongToast(resources.getString(R.string.get_initial_conference_id_error))
    }

    override fun showGetConferenceDataError() {
        toastUtil.showLongToast(resources.getString(R.string.get_conference_data_error))
    }
}