package bushuk.stanislau.bitbucketproject.presentation.baseAuth

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.navigation.MainNavigator
import bushuk.stanislau.bitbucketproject.presentation.baseAuth.viewModel.AuthLoginViewModel
import kotlinx.android.synthetic.main.activity_auth_login.*
import ru.terrakok.cicerone.NavigatorHolder
import javax.inject.Inject

class AuthLoginActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    override fun onCreate(savedInstanceState: Bundle?) {
        App.component.inject(this)

        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_auth_login)
        auth_login_screen.requestFocus()
        auth_login_powered_textView.text = Html.fromHtml(getString(R.string.auth_login_screen_powered_text))
        val viewModel: AuthLoginViewModel = ViewModelProviders.of(this).get(AuthLoginViewModel::class.java)

        auth_login_screen_login_button.setOnClickListener {
            viewModel.getUserBaseAuth(auth_login_screen_login_editText.text.toString(),
                    auth_login_screen_password_editText.text.toString())
        }

//        auth_login_screen_browser_textView.setOnClickListener {
//            viewModel.navigateToBrowser()
//        }
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
