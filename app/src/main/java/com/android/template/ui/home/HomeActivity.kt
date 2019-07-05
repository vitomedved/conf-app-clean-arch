package com.android.template.ui.home

import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.view.MenuItem
import butterknife.BindView
import com.android.template.R
import com.android.template.base.BaseActivity
import com.android.template.base.ScopedPresenter
import com.android.template.injection.activity.ActivityComponent
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View {

    @BindView(R.id.activity_home_toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.activity_home_drawer_layout)
    lateinit var drawerLayout: DrawerLayout

    @BindView(R.id.activity_home_navigation_view)
    lateinit var navigationView: NavigationView

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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActionBar()

        navigationView.setNavigationItemSelectedListener { menuItem ->
            menuItem.isChecked = true
            drawerLayout.closeDrawers()

            when (menuItem.itemId) {
                // TODO: fix order and function calls when fragments are created
                R.id.nav_about -> presenter.showAboutConferenceScreen()
                R.id.nav_schedule -> presenter.showAboutConferenceScreen()
                R.id.nav_exhibitors -> presenter.showAboutConferenceScreen()
                R.id.nav_favourite_events -> presenter.showAboutConferenceScreen()
                R.id.nav_blueprint -> presenter.showAboutConferenceScreen()
                R.id.nav_share -> presenter.showAboutConferenceScreen()
                R.id.nav_email_developers -> presenter.showAboutConferenceScreen()
            }

            true
        }

        if (null == savedInstanceState) {
            presenter.showAboutConferenceScreen()
        }
    }

    private fun initActionBar() {
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }
}