package bushuk.stanislau.bitbucketproject.presentation.codeeditor

import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import bushuk.stanislau.bitbucketproject.App
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CodeEditorViewModel : ViewModel() {

    @Inject
    lateinit var codeEditorModel: CodeEditorModel

    val code : MutableLiveData<String> = MutableLiveData()

    init {
        Timber.e("INIT CODE VIEW MODEL")

        App.component.inject(this)
        codeEditorModel.getCode().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    code.postValue(it)
                },{
                    Timber.e(it.message)
                })
    }
}