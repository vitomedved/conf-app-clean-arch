package com.android.template.utils.qr

import android.content.res.Resources
import android.support.v4.app.Fragment
import com.android.template.R
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity

class QrCodeUtilsImpl (private val resources: Resources) : QrCodeUtils {

    override fun startQrScan(fragment: Fragment, QR_REQUEST_CODE: Int) {
        // TODO: orientation of scanner is horizontal, fix this later or add another library
        IntentIntegrator.forSupportFragment(fragment)
                .setPrompt(resources.getString(R.string.scan_conference_id_prompt))
                .setOrientationLocked(true)
                .setBeepEnabled(true)
                .setRequestCode(QR_REQUEST_CODE)
                .setCaptureActivity(CaptureActivity::class.java)
                .initiateScan()
    }
}