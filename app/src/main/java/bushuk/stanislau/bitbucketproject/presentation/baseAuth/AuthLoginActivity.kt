package bushuk.stanislau.bitbucketproject.presentation.baseAuth

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.text.Html
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.databinding.ActivityAuthLoginBinding
import bushuk.stanislau.bitbucketproject.navigation.MainNavigator
import bushuk.stanislau.bitbucketproject.presentation.baseAuth.viewModel.AuthLoginViewModel
import com.jakewharton.rxbinding2.view.clicks
import kotlinx.android.synthetic.main.activity_auth_login.*
import ru.terrakok.cicerone.NavigatorHolder
import timber.log.Timber
import javax.inject.Inject

class AuthLoginActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)

        super.onCreate(savedInstanceState)

        val authLoginActivityMainBinding: ActivityAuthLoginBinding = DataBindingUtil.setContentView(this, R.layout.activity_auth_login)
        val viewModel: AuthLoginViewModel = ViewModelProviders.of(this).get(AuthLoginViewModel::class.java)
        authLoginActivityMainBinding.setLifecycleOwner(this)


        authLoginActivityMainBinding.let {
            it.loginViewModel = viewModel
            it.setLifecycleOwner(this)
        }

        auth_login_screen.requestFocus()
        auth_login_powered_textView.text = Html.fromHtml(getString(R.string.auth_login_screen_powered_text))

        auth_login_screen_login_button.clicks().doOnNext {
            viewModel.getUserBaseAuth(
                    auth_login_screen_login_editText.text.toString(),
                    auth_login_screen_password_editText.text.toString())
        }.subscribe()

        viewModel.getSnackBarAction().observe(this, Observer
        { Timber.e("SNACK")
            Snackbar.make(auth_login_powered_textView, it!!, Snackbar.LENGTH_LONG).show()
        })

        auth_login_screen_browser_textView.setOnClickListener {
            viewModel.navigateToBrowser() //BROWSER AUTH
        }
    }

    override fun onResume() {
        navigatorHolder.setNavigator(MainNavigator(this, R.id.main_screen_container))
        super.onResume()
    }

    override fun onPause() {
        navigatorHolder.removeNavigator()
        super.onPause()
    }
}
