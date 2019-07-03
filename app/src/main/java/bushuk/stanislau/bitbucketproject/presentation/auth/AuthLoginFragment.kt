package bushuk.stanislau.bitbucketproject.presentation.auth

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
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.activity_auth_login.*
import timber.log.Timber
import javax.inject.Inject

class AuthLoginFragment : Fragment(), Injectable {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: AuthLoginViewModel

    lateinit var dialog: ProgressDialog

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthLoginViewModel::class.java)

        val authLoginActivityMainBinding: ActivityAuthLoginBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.activity_auth_login, container, false)

        Timber.e("INFLATE")
        authLoginActivityMainBinding.let {
            it.loginViewModel = viewModel
            it.lifecycleOwner = viewLifecycleOwner
        }
        return authLoginActivityMainBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        dialog = ProgressDialog(activity)
        dialog.setMessage("Loading")

        auth_login_screen.requestFocus()
        auth_login_powered_textView.text = Html.fromHtml(getString(R.string.auth_login_screen_powered_text))

        auth_login_screen_login_button.clicks()
                .doOnNext {
                    viewModel.getUserBaseAuth(auth_login_screen_login_editText.text.toString(), auth_login_screen_password_editText.text.toString())
        }.subscribe()


        viewModel.state().observe(this, Observer {
            when (it) {
                is LoadingState.LoadingStateSealed.Loading<*, *> -> {
                    dialog.show()
                }
                else -> {
                    dialog.hide()
                }
            }
        })

        viewModel.snackBarAction.observe(this, Observer {
            Snackbar.make(auth_login_powered_textView, it!!, Snackbar.LENGTH_LONG).show()
        })

        auth_login_screen_browser_textView.setOnClickListener {
            viewModel.navigateToBrowser() //BROWSER AUTH
        }
    }


}
