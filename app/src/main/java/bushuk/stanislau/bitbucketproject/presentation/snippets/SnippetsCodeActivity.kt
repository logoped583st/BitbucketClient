package bushuk.stanislau.bitbucketproject.presentation.snippets

import android.arch.lifecycle.Observer
import android.os.Bundle
import bushuk.stanislau.bitbucketproject.presentation.codeeditor.BaseCodeEditor
import sharedcode.turboeditor.texteditor.PageSystem

class SnippetsCodeActivity : BaseCodeEditor<SnippetsCodeViewModel>() {

    override var viewModelClass: Class<SnippetsCodeViewModel> = SnippetsCodeViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel.getCode(intent.getStringExtra("Path"))
        viewModel.code.observe(this, Observer {
            pageSystem = PageSystem(this, this, it)
            showTextEditor()
            mEditor.enableTextChangedListener()
        })
    }
}