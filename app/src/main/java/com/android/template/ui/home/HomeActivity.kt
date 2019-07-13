package com.android.template.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.widget.Toast
import butterknife.BindView
import com.android.template.R
import com.android.template.base.BaseActivity
import com.android.template.base.ScopedPresenter
import com.android.template.injection.activity.ActivityComponent
import com.android.template.utils.auth.AuthUtils
import com.android.template.utils.ui.ToastUtil
import com.firebase.ui.auth.ErrorCodes
import com.firebase.ui.auth.IdpResponse
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

    @Inject
    lateinit var authUtils: AuthUtils

    @Inject
    lateinit var toastUtil: ToastUtil

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

        initNavigationDrawer()

        if (null == savedInstanceState) {
            // After that redirect to correct fragment
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

    private fun initNavigationDrawer() {
        presenter.renderNavigationBar()

        navigationView.setNavigationItemSelectedListener { menuItem ->
            onNavigationDrawerMenuItemSelected(menuItem)
            true
        }
    }

    private fun onNavigationDrawerMenuItemSelected(menuItem: MenuItem) {
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
            R.id.nav_add_conference -> presenter.showAboutConferenceScreen()
            R.id.nav_my_conferences -> presenter.showAboutConferenceScreen()
            R.id.nav_login -> handleLoginClicked(menuItem)
            R.id.nav_logout -> presenter.signOutCurrentUser()
        }
    }

    private fun handleLoginClicked(menuItem: MenuItem) {
        menuItem.isChecked = false
        showSignInScreen()
    }

    private fun showSignInScreen() {
        startActivityForResult(authUtils.buildSignInIntent(), authUtils.getRequestCode())
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

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if(requestCode == authUtils.getRequestCode()) {
            handleSignInResponse(resultCode, data)
        }
    }

    @SuppressLint("RestrictedApi") // Suppress for line 138 where user name is accessed from response which is 2 different APIs, probably nothing to worry about.
    private fun handleSignInResponse(resultCode: Int, data: Intent?) {
        val response: IdpResponse = IdpResponse.fromResultIntent(data) ?: return

        if(Activity.RESULT_OK == resultCode) {
            // TODO: do something with the response
            toastUtil.showShortToast("Welcome, ${response.user.name}")
        } else {
            toastUtil.showLongToast("There was a problem with signing in. Please try again.")
            Log.d("HomeActivity", "Firebase log in error: ${response.error?.errorCode}")
        }
    }
}