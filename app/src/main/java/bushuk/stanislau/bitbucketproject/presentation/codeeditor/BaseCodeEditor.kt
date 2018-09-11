package bushuk.stanislau.bitbucketproject.presentation.codeeditor

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import sharedcode.turboeditor.activity.MainActivity

abstract class BaseCodeEditor<ViewModel : android.arch.lifecycle.ViewModel> : MainActivity() {

    override fun showInterstitial(): Boolean {
        return true
    }

    abstract var viewModelClass: Class<ViewModel>

    lateinit var viewModel: ViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        viewModel = ViewModelProviders.of(this).get(viewModelClass)
    }

}