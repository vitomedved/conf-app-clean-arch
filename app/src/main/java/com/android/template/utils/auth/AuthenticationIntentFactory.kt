package com.android.template.utils.auth

import android.content.Intent

interface AuthenticationIntentFactory {
    // TODO: maybe add overloads for theme, logo, etc. or:
    // TODO: maybe add private variables of logo value, theme value, etc. and set setters
    fun buildSignInIntent(): Intent

    fun buildSignInIntent(allowEmailLogin: Boolean, allowGoogleLogin: Boolean): Intent
}