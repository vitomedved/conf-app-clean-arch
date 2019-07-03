package com.android.template.ui.welcome

import android.content.Intent
import android.widget.EditText
import butterknife.BindView
import butterknife.OnClick
import com.android.template.R
import com.android.template.base.BaseFragment
import com.android.template.base.ScopedPresenter
import com.android.template.injection.fragment.FragmentComponent
import com.android.template.ui.welcome.qr.CaptureActivityPortrait
import com.android.template.utils.ui.ToastUtil
import com.google.zxing.integration.android.IntentIntegrator
import com.google.zxing.integration.android.IntentResult
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

    // TODO: add functionality to QR code button to scan for ID - maybe change the way this is done

    // TODO: set all of the content from xml to the middle in one linear layout i guess
    // TODO: connect to SQL database or firebase database for everything to work fine

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
        toastUtil.showLongToast("You have inputted invalid conference ID or canceled QR scanning.")
    }

    override fun showConferenceDoesNotExistError() {
        toastUtil.showLongToast("Conference with entered ID does not exist. Please try again.")
    }

    @OnClick(R.id.fragment_welcome_conference_id_input_submit)
    fun onConferenceIdInputSubmitClick() {
        presenter.setConferenceId(conferenceIdInput.text.toString())
    }

    @OnClick(R.id.fragment_welcome_qr_code_image_view)
    fun onConferenceIdQrInputClick() {
        // TODO maybe move this to Router class and intent on activity from there
        // TODO maybe execute as useCase and then return a result (scanned QR code in string)
        IntentIntegrator.forSupportFragment(this)
                .setOrientationLocked(true)
                .setBeepEnabled(true)
                .setPrompt("Scan conference ID")
                .setCaptureActivity(CaptureActivityPortrait::class.java)
                .initiateScan()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        val result: IntentResult? = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)

        // TODO: send some kind of indication that QR scanning was canceled so this::showInvalidConferenceIdError() won't be called
        presenter.setConferenceId(result?.contents ?: "")
    }
}