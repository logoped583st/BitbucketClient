package bushuk.stanislau.bitbucketproject.presentation.loginPresentation


import android.annotation.SuppressLint
import android.annotation.TargetApi
import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import bushuk.stanislau.bitbucketproject.R
import kotlinx.android.synthetic.main.fragment_login.*
import timber.log.Timber


class LoginActivity : AppCompatActivity() {

    @SuppressLint("SetJavaScriptEnabled")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_login)
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

                if (url.contains("access_token=")) { //catch access token from redirect
                    tempString = url.subSequence(url.indexOf("=") + 1, url.indexOf("&")).toString()
                    Timber.e(tempString)
                    finish()
                    return true
                }

                return false // Returning True means that application wants to leave the current WebView and handle the url itself, otherwise return false.
            }
        }

        web_view.loadUrl("https://bitbucket.org/site/oauth2/authorize?client_id=yuPL4qKCg7VvRzAWAm&response_type=token")
    }
}
