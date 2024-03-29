package bushuk.stanislau.bitbucketproject.presentation.auth


import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import bushuk.stanislau.bitbucketproject.Injectable
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.constants.Constants
import bushuk.stanislau.bitbucketproject.di.CiceroneFactory
import bushuk.stanislau.bitbucketproject.utils.preferences.ISharedPreferencesUtil
import kotlinx.android.synthetic.main.login_fragment.*
import timber.log.Timber
import javax.inject.Inject


class AuthWebFragment : Fragment(), Injectable {

    @Inject
    lateinit var tokenPreferences: ISharedPreferencesUtil

    @Inject
    lateinit var ciceroneFactory: CiceroneFactory

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var viewModel: AuthLoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)
    }


    @SuppressLint("SetJavaScriptEnabled")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val webSettings = web_view.settings
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AuthLoginViewModel::class.java)
        webSettings.javaScriptEnabled = true
        web_view.webViewClient = object : WebViewClient() { //for check redirect

            override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
                return shouldOverrideUrlLoading(url)
            }

            @TargetApi(Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(webView: WebView, request: WebResourceRequest): Boolean {
                return shouldOverrideUrlLoading(request.url.toString())
            }

            private fun shouldOverrideUrlLoading(url: String): Boolean {
                Timber.e(url)

                if (url.contains("google.com")) { //catch access token from redirect
                    Timber.e(url)
                    val authCode: String = url.subSequence(url.indexOf("=") + 1, url.length).toString()
                    Timber.e(authCode)
                    viewModel.getAccessToken(authCode)
//                    tokenPreferences.setToken(authCode)
//                    ciceroneFactory.provideCicerone(Cicerones.GLOBAL).router.newRootScreen(ScreensNavigator.StartScreen())
                    return true
                }

                return false // Returning True means that application wants to leave the current WebView and handle the url itself, otherwise return false.
            }
        }


        web_view.loadUrl(Constants.AUTH_URL)
    }
}
