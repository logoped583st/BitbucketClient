package bushuk.stanislau.bitbucketproject.presentation.codeeditor

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import bushuk.stanislau.bitbucketproject.utils.retrofit.UrlBuilder
import sharedcode.turboeditor.activity.MainActivity
import sharedcode.turboeditor.texteditor.PageSystem
import timber.log.Timber


class CodeEditorActivity : MainActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {

        Timber.e("INIT CODE VIEW MODEL")
        super.onCreate(savedInstanceState)

        val viewModel: CodeEditorViewModel = ViewModelProviders.of(this).get(CodeEditorViewModel::class.java)

        viewModel.code.observe(this, Observer {
            pageSystem = PageSystem(this, this, it)

            Timber.e(it)
            //mEditor.replaceTextKeepCursor(it)
            showTextEditor()
            mEditor.enableTextChangedListener()

        })
    }


    override fun showInterstitial(): Boolean {

        return true
    }

}