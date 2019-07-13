package com.android.template.utils.qr

import android.support.v4.app.Fragment

interface QrCodeUtils {
    fun startQrScan(fragment: Fragment, QR_REQUEST_CODE: Int)
}