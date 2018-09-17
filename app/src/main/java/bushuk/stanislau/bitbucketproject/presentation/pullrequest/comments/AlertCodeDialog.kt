package bushuk.stanislau.bitbucketproject.presentation.pullrequest.comments

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.DialogFragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bushuk.stanislau.bitbucketproject.App
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.databinding.CodeEditorDialogBinding
import bushuk.stanislau.bitbucketproject.room.comments.Comment
import kotlinx.android.synthetic.main.code_editor_dialog.*
import sharedcode.turboeditor.activity.Editor
import sharedcode.turboeditor.activity.MainActivity.pageSystem
import sharedcode.turboeditor.texteditor.PageSystem


class AlertCodeDialog : DialogFragment(), PageSystem.PageSystemInterface {
    override fun onPageChanged(page: Int) {
        //
    }

    lateinit var binding: CodeEditorDialogBinding
    lateinit var comment: Comment
    lateinit var editor: Editor
    lateinit var viewModel: AlertCodeDialogViewModel


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(LayoutInflater.from(context), R.layout.code_editor_dialog, null, false)

        viewModel = ViewModelProviders.of(this).get(AlertCodeDialogViewModel::class.java)
        viewModel.setComment(comment)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupTextEditor()
        hideTextEditor()


        binding.let {
            it.data = comment
        }
        viewModel.code.observe(this, Observer {
            pageSystem = PageSystem(context, this, it)
            showTextEditor()
        })
    }

    override fun onResume() {
        super.onResume()
        dialog.window!!.setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    }

    private fun showTextEditor() {
        editor.visibility = View.VISIBLE
        editor.resetVariables()
        editor.disableTextChangedListener()
        editor.setReadOnly(true)
        editor.replaceTextKeepCursor(pageSystem.currentPageText)
    }

    private fun hideTextEditor() {
        editor.visibility = View.INVISIBLE
        editor.replaceTextKeepCursor("")
    }

    private fun setupTextEditor() {
        editor = test_editor
        editor.setBackgroundColor(App.resourcesApp.getColor(R.color.black))
        pageSystem = PageSystem(context, this, "Loading...")
        editor.setupEditor()
    }
}