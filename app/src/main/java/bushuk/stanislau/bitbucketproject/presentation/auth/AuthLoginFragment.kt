package bushuk.stanislau.bitbucketproject.presentation.auth

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

import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.databinding.ActivityAuthLoginBinding
import com.google.android.material.snackbar.Snackbar
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.activity_auth_login.*
import javax.inject.Inject

class AuthLoginFragment : Fragment() {

    //@Inject
    //lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: AuthLoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val authLoginActivityMainBinding: ActivityAuthLoginBinding =
                DataBindingUtil.inflate(layoutInflater, R.layout.activity_auth_login, container, false)

      //  viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthLoginViewModel::class.java)

        authLoginActivityMainBinding.let {
            it.loginViewModel = viewModel
            it.lifecycleOwner = this
        }
        return authLoginActivityMainBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        auth_login_screen.requestFocus()
        auth_login_powered_textView.text = Html.fromHtml(getString(R.string.auth_login_screen_powered_text))

        auth_login_screen_login_button.clicks().doOnNext {
            viewModel.getUserBaseAuth(
                    auth_login_screen_login_editText.text.toString(),
                    auth_login_screen_password_editText.text.toString())
        }.subscribe()

        viewModel.snackBarAction.observe(this, Observer {
            Snackbar.make(auth_login_powered_textView, it!!, Snackbar.LENGTH_LONG).show()
        })

        auth_login_screen_browser_textView.setOnClickListener {
            viewModel.navigateToBrowser() //BROWSER AUTH
        }
    }


}
