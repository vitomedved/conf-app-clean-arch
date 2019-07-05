package com.android.template.ui.home

import com.android.template.R
import com.android.template.base.BaseActivity
import com.android.template.base.ScopedPresenter
import com.android.template.injection.activity.ActivityComponent
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View {

    @Inject
    lateinit var presenter: HomeContract.Presenter

    override fun inject(component: ActivityComponent) {
        component.inject(this)
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_home
    }

    override fun getPresenter(): ScopedPresenter {
        return presenter
    }

}