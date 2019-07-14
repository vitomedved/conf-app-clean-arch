package com.android.template.ui.home

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.design.widget.NavigationView
import android.support.v4.view.GravityCompat
import android.support.v4.widget.DrawerLayout
import android.support.v7.widget.Toolbar
import android.util.Log
import android.view.MenuItem
import android.view.View
import android.widget.TextView
import butterknife.BindView
import com.android.template.R
import com.android.template.base.BaseActivity
import com.android.template.base.ScopedPresenter
import com.android.template.injection.activity.ActivityComponent
import com.android.template.utils.auth.AuthenticationIntentFactory
import com.android.template.utils.ui.ToastUtil
import com.firebase.ui.auth.IdpResponse
import de.hdodenhof.circleimageview.CircleImageView
import template.android.com.domain.model.User
import javax.inject.Inject

class HomeActivity : BaseActivity(), HomeContract.View {

    companion object {
        private const val SIGN_IN_REQUEST_CODE = 100
    }

    @BindView(R.id.activity_home_toolbar)
    lateinit var toolbar: Toolbar

    @BindView(R.id.activity_home_drawer_layout)
    lateinit var drawerLayout: DrawerLayout

    @BindView(R.id.activity_home_navigation_view)
    lateinit var navigationView: NavigationView

    @Inject
    lateinit var presenter: HomeContract.Presenter

    @Inject
    lateinit var authenticationIntentFactory: AuthenticationIntentFactory

    @Inject
    lateinit var toastUtil: ToastUtil

    private lateinit var navBarHeaderTitleTextView: TextView
    private lateinit var navBarHeaderCircleImageView: CircleImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initActionBar()

        initNavigationDrawerHeaderViews()

        initNavigationDrawer()

        if (null == savedInstanceState) {
            presenter.init()
        }
    }

    override fun inject(component: ActivityComponent) {
        component.inject(this)
    }

    override fun getLayoutResourceId(): Int {
        return R.layout.activity_home
    }

    override fun getPresenter(): ScopedPresenter {
        return presenter
    }

    private fun initActionBar() {
        setSupportActionBar(toolbar)

        supportActionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(R.drawable.ic_menu_white_24dp)
        }
    }

    private fun initNavigationDrawerHeaderViews() {
        val headerView = navigationView.getHeaderView(0)

        navBarHeaderTitleTextView = headerView.findViewById(R.id.activity_home_nav_drawer_title_text_view)
        navBarHeaderCircleImageView = headerView.findViewById(R.id.activity_home_nav_drawer_avatar_image)
    }

    private fun initNavigationDrawer() {
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
            R.id.nav_schedule -> presenter.showScheduleScreen()
            R.id.nav_exhibitors -> assert(false)
            R.id.nav_favourite_events -> assert(false)
            R.id.nav_blueprint -> assert(false)
            R.id.nav_share -> assert(false)
            R.id.nav_email_developers -> assert(false)
            R.id.nav_add_conference -> assert(false)
            R.id.nav_my_conferences -> assert(false)
            R.id.nav_login -> handleSignInClicked(menuItem)
            R.id.nav_logout -> handleSignOutClicked(menuItem)
        }
    }

    private fun handleSignInClicked(menuItem: MenuItem) {
        menuItem.isChecked = false
        showSignInScreen()
    }

    private fun handleSignOutClicked(menuItem: MenuItem) {
        menuItem.isChecked = false
        presenter.signOutCurrentUser()
    }

    private fun showSignInScreen() {
        startActivityForResult(authenticationIntentFactory.buildSignInIntent(), SIGN_IN_REQUEST_CODE)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            android.R.id.home -> {
                drawerLayout.openDrawer(GravityCompat.START)
                true
            }

            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (SIGN_IN_REQUEST_CODE == requestCode) {
            handleSignInResponse(resultCode, data)
        }
    }

    @SuppressLint("RestrictedApi") // Suppress for line 138 where user name is accessed from response which is 2 different APIs, probably nothing to worry about.
    private fun handleSignInResponse(resultCode: Int, data: Intent?) {
        val response: IdpResponse = IdpResponse.fromResultIntent(data) ?: return

        if (Activity.RESULT_OK == resultCode) {
            presenter.handleUserSignedIn()
        } else {
            toastUtil.showLongToast("There was a problem with signing in. Please try again.")
            Log.d("HomeActivity", "Firebase log in error: ${response.error?.errorCode}")
        }
    }

    override fun renderUserSignedInNavigationDrawer(user: User) {
        navBarHeaderTitleTextView.text = user.email

        navigationView.menu.findItem(R.id.nav_conferences_submenu).isVisible = true
        navigationView.menu.findItem(R.id.nav_login).isVisible = false
        navigationView.menu.findItem(R.id.nav_logout).isVisible = true
    }

    override fun renderUserNotSignedInNavigationDrawer() {
        navBarHeaderTitleTextView.text = "You are currently not logged in"

        navigationView.menu.findItem(R.id.nav_conferences_submenu).isVisible = false
        navigationView.menu.findItem(R.id.nav_login).isVisible = true
        navigationView.menu.findItem(R.id.nav_logout).isVisible = false
    }

    override fun showApplicationError() {
        toastUtil.showLongToast("There was an application error, please restart the application.")
    }

    override fun showSignOutError() {
        toastUtil.showLongToast("There was an error with signing out, please try again.")
    }
}