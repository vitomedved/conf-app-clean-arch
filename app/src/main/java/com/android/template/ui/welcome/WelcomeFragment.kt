package com.android.template.ui.welcome

import android.content.Intent
import android.widget.EditText
import butterknife.BindView
import butterknife.OnClick
import com.android.template.R
import com.android.template.base.BaseFragment
import com.android.template.base.ScopedPresenter
import com.android.template.injection.fragment.FragmentComponent
import com.android.template.utils.qr.QrCodeUtils
import com.android.template.utils.ui.ToastUtil
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
import template.android.com.domain.utils.string.StringUtils
import javax.inject.Inject


class WelcomeFragment : BaseFragment(), WelcomeContract.View {

    companion object {
        const val TAG = "WelcomeFragment"

        @JvmStatic
        fun newInstance(): WelcomeFragment = WelcomeFragment()
    }

    @BindView(R.id.fragment_welcome_conference_id_input)
    lateinit var conferenceIdInput: EditText

    @Inject
    lateinit var presenter: WelcomeContract.Presenter

    @Inject
    lateinit var toastUtil: ToastUtil

    @Inject
    lateinit var stringUtils: StringUtils

    @Inject
    lateinit var qrCodeUtils: QrCodeUtils

    override fun getLayoutResourceId(): Int {
        return R.layout.fragment_welcome
    }

    override fun inject(component: FragmentComponent) {
        component.inject(this)
    }

    override fun getPresenter(): ScopedPresenter {
        return presenter
    }

    override fun showInvalidConferenceIdError() {
        toastUtil.showLongToast(resources.getString(R.string.invalid_conference_id_error))
    }

    override fun showConferenceDoesNotExistError() {
        toastUtil.showLongToast(resources.getString(R.string.conference_does_not_exist_error))
    }

    @OnClick(R.id.fragment_welcome_conference_id_input_submit)
    fun onConferenceIdInputSubmitClick() {
        presenter.checkIfConferenceExists(conferenceIdInput.text.toString())
    }

    @OnClick(R.id.fragment_welcome_qr_code_image_view)
    fun onConferenceIdQrInputClick() {
        qrCodeUtils.startQrScan(this)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == qrCodeUtils.getRequestCode()) {
            presenter.checkIfConferenceExists(stringUtils.itOrDefault(data?.getStringExtra(Intents.Scan.RESULT), ""))
        } else {
            super.onActivityResult(requestCode, resultCode, data)
        }
    }
}