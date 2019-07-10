package com.android.template.utils.requestcodes

class RequestCodeUtilsImpl : RequestCodeUtils {
    override fun getQrScanRequestCode(): Int = RequestCodeUtils.RequestCode.QR.value

    override fun getSignInRequestCode(): Int = RequestCodeUtils.RequestCode.SIGN_IN.value
}
