package com.android.template.utils.qr

import android.content.res.Resources
import android.support.v4.app.Fragment
import com.android.template.R
import com.android.template.utils.requestcodes.RequestCodeUtils
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.CaptureActivity

class QrCodeUtilsImpl (private val resources: Resources, requestCodeUtils: RequestCodeUtils) : QrCodeUtils {

    private val QR_REQUEST_CODE = requestCodeUtils.getQrScanRequestCode()

    override fun getRequestCode(): Int = QR_REQUEST_CODE

    override fun startQrScan(fragment: Fragment) {
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