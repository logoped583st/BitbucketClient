package bushuk.stanislau.bitbucketproject.presentation.codeeditor

import android.os.Bundle
import androidx.lifecycle.Observer
import sharedcode.turboeditor.texteditor.PageSystem


class CodeEditorActivity : BaseCodeEditor<CodeEditorViewModel>() {

    override var viewModelClass: Class<CodeEditorViewModel> = CodeEditorViewModel::class.java

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.code.observe(this, Observer {
            pageSystem = PageSystem(this, this, it)
            showTextEditor()
            mEditor.enableTextChangedListener()
        })
    }
}