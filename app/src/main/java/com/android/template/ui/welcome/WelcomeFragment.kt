package com.android.template.ui.welcome

import com.android.template.R
import com.android.template.base.BaseFragment
import com.android.template.base.ScopedPresenter
import com.android.template.injection.fragment.FragmentComponent
import javax.inject.Inject

class WelcomeFragment: BaseFragment(), WelcomeContract.View {

    companion object {
        const val TAG = "WelcomeFragment"

        @JvmStatic
        fun newInstance(): WelcomeFragment = WelcomeFragment()
    }

    @Inject
    lateinit var presenter: WelcomeContract.Presenter

    // TODO: set all of the content from xml to the middle in one linear layout i guess
    // TODO: add functionality to QR code button to scan for ID
    // TODO: add functionality to enter conference ID
    // TODO: connect to SQL database for everything to work fine

    override fun getLayoutResourceId(): Int {
        return R.layout.welcome_fragment
    }

    override fun inject(component: FragmentComponent) {
        component.inject(this)
    }

    override fun getPresenter(): ScopedPresenter {
        return presenter
    }

}