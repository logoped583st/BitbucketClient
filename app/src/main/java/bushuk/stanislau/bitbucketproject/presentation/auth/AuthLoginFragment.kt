package bushuk.stanislau.bitbucketproject.presentation.auth

import android.annotation.SuppressLint
import android.app.ProgressDialog
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import bushuk.stanislau.bitbucketproject.Injectable
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.databinding.ActivityAuthLoginBinding
import bushuk.stanislau.bitbucketproject.global.LoadingState
import bushuk.stanislau.bitbucketproject.room.user.User
import bushuk.stanislau.bitbucketproject.utils.exceptions.CustomExceptions
import com.jakewharton.rxbinding2.widget.RxTextView
import io.reactivex.Observable
import kotlinx.android.synthetic.main.activity_auth_login.*
import javax.inject.Inject

class AuthLoginFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: AuthProtocol.IAuthLogin<User>

    lateinit var dialog: ProgressDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthLoginViewModel::class.java)

        val authLoginActivityMainBinding: ActivityAuthLoginBinding =
                DataBindingUtil.inflate(inflater, R.layout.activity_auth_login, container, false)

        authLoginActivityMainBinding.let {
            it.loginViewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }
        return authLoginActivityMainBinding.root
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
                is LoadingState.LoadingStateSealed.Loading<*, *> -> {
                    dialog.show()
                }
                is LoadingState.LoadingStateSealed.Error<*, CustomExceptions> -> {
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
