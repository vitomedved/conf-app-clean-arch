package com.android.template.utils.qr

import android.support.v4.app.Fragment

interface QrCodeUtils {
    fun getRequestCode(): Int

    fun startQrScan(fragment: Fragment)
}