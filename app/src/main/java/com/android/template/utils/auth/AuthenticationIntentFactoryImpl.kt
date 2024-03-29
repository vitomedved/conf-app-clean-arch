package com.android.template.utils.auth

import android.content.Intent
import android.content.res.Resources
import com.android.template.R
import com.firebase.ui.auth.AuthUI
import com.google.android.gms.common.Scopes

class AuthenticationIntentFactoryImpl (private val resources: Resources) : AuthenticationIntentFactory {

    private var allowEmailLogin = false
    private var allowGoogleLogin = false

    override fun buildSignInIntent(): Intent {
        return buildSignInIntentInternal()
    }

    override fun buildSignInIntent(allowEmailLogin: Boolean, allowGoogleLogin: Boolean): Intent {

        this.allowEmailLogin = allowEmailLogin
        this.allowGoogleLogin = allowGoogleLogin

        return buildSignInIntentInternal()
    }

    private fun buildSignInIntentInternal(): Intent {
        return AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setTheme(R.style.AppTheme)
                .setLogo(AuthUI.NO_LOGO)
                .setAvailableProviders(getAvailableProviders())
                .setIsSmartLockEnabled(true, true)
                //.setTosAndPrivacyPolicyUrls(tosUrl, privacyPolicyUrl) // TODO you can remove this if no TOS and PP is to be added
                .build()
    }

    private fun getAvailableProviders(): List<AuthUI.IdpConfig> {
        val providers: MutableList<AuthUI.IdpConfig> = mutableListOf()

        if(allowEmailLogin) {
            addEmailProvider(providers)
        }

        if(allowGoogleLogin) {
            addGoogleProvider(providers)
        }

        // TODO: if you want to use email-link provider (send user email to log in) check out this example app and add mUseEmailLinkProvider useCase from example:
        // https://github.com/firebase/FirebaseUI-Android/blob/master/app/src/main/java/com/firebase/uidemo/auth/AuthUiActivity.java

        return providers
    }

    private fun addEmailProvider(providers: MutableList<AuthUI.IdpConfig>) {
        providers.add(AuthUI.IdpConfig
                              .EmailBuilder()
                              .setRequireName(true)
                              .setAllowNewAccounts(true)
                              .build())
    }

    private fun addGoogleProvider(providers: MutableList<AuthUI.IdpConfig>) {
        providers.add(AuthUI.IdpConfig
                              .GoogleBuilder()
                              .setScopes(getGoogleScopes())
                              .build())
    }

    private fun getGoogleScopes(): List<String> {
        val result: MutableList<String> = mutableListOf()

        result.add(resources.getString(R.string.google_sign_in_scope_youtube))

        result.add(Scopes.DRIVE_FILE)

        return result
    }
}