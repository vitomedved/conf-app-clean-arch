package com.android.template.utils.requestcodes

interface RequestCodeUtils {

    fun getQrScanRequestCode(): Int

    fun getSignInRequestCode(): Int

    enum class RequestCode (val value: Int) {
        QR(100),
        SIGN_IN(101);
    }
}