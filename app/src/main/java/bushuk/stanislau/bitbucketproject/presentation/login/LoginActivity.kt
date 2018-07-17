package bushuk.stanislau.bitbucketproject.presentation.login


import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.Constants
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.utils.TokenUtils.TokenPreferencesApi23
import kotlinx.android.synthetic.main.login_fragment.*
import timber.log.Timber
import javax.inject.Inject


class LoginActivity : AppCompatActivity() {

    @Inject
    lateinit var tokenPreferences: TokenPreferencesApi23

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        App.component.inject(this)
        setContentView(R.layout.login_fragment)
        val webSettings = web_view.settings
        webSettings.javaScriptEnabled = true
        web_view.webViewClient = object : WebViewClient() {//for check redirect

            override fun shouldOverrideUrlLoading(webView: WebView, url: String): Boolean {
                return shouldOverrideUrlLoading(url)
            }

            @TargetApi(Build.VERSION_CODES.N)
            override fun shouldOverrideUrlLoading(webView: WebView, request: WebResourceRequest): Boolean {
                return shouldOverrideUrlLoading(request.url.toString())
            }

            private fun shouldOverrideUrlLoading(url: String): Boolean {
                val tempString: String
                Timber.e(url + "   " + this)
                if (url.contains("access_token=")) { //catch access token from redirect
                    tempString = url.subSequence(url.indexOf("=") + 1, url.indexOf("&")).toString()
                    Timber.e(tempString)
                    tokenPreferences.setToken(tempString)
                    finish()
                    return true
                }

                return false // Returning True means that application wants to leave the current WebView and handle the url itself, otherwise return false.
            }
        }


        web_view.loadUrl(Constants.AUTH_URL)

    }

}
