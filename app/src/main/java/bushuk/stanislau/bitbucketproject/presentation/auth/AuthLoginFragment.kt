package bushuk.stanislau.bitbucketproject.presentation.auth

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.text.Html
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import bushuk.stanislau.bitbucketproject.Injectable
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.databinding.ActivityAuthLoginBinding
import bushuk.stanislau.bitbucketproject.global.LoadingStateSealed
import bushuk.stanislau.bitbucketproject.presentation.base.BaseBindingFragment
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_auth_login.*
import javax.inject.Inject

class AuthLoginFragment : BaseBindingFragment<AuthLoginViewModel, ActivityAuthLoginBinding>(), Injectable {

    override val viewModelClass: Class<AuthLoginViewModel> = AuthLoginViewModel::class.java

    override val containerId: Int = R.layout.activity_auth_login

    override val scope: ViewModelScope = ViewModelScope.FRAGMENT

    @Inject
    override lateinit var viewModelFactory: ViewModelProvider.Factory

    private lateinit var dialog: ProgressDialog

    override fun applyBinding() {
        binding.loginViewModel = viewModel
    }

    @SuppressLint("CheckResult")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog = ProgressDialog(activity)
        dialog.setMessage("Loading")

        auth_login_screen.requestFocus()
        auth_login_powered_textView.text = Html.fromHtml(getString(R.string.auth_login_screen_powered_text))

        auth_login_screen_login_button.setOnClickListener {
            viewModel.getUserBaseAuth(auth_login_screen_login_editText.text.toString(), auth_login_screen_password_editText.text.toString())
        }

        Observable.merge(
                RxTextView.textChanges(auth_login_screen_login_editText),
                RxTextView.textChanges(auth_login_screen_password_editText)
        ).subscribe {
            if (auth_login_screen_password_input_layout.isErrorEnabled) {
                auth_login_screen_login_input_layout.error = null
                auth_login_screen_password_input_layout.error = null
            }
        }


        viewModel.state.observe(this, Observer {
            when (it) {
                is LoadingStateSealed.Loading<*, *> -> {
                    dialog.show()
                }
                is LoadingStateSealed.Error<*, CustomExceptions> -> {
                    when (it.error) {
                        is CustomExceptions.UnAuthorized -> {
                            auth_login_screen_login_input_layout.error = " "
                            auth_login_screen_password_input_layout.error = "Wrong login or password"
                        }
                    }
                    dialog.hide()

                }
                else -> {
                    dialog.hide()
                }
            }
        })


        auth_login_screen_browser_textView.setOnClickListener {
            viewModel.navigateToBrowser() //BROWSER AUTH
        }
    }


}
