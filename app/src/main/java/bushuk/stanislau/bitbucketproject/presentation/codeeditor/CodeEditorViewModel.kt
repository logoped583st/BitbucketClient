package bushuk.stanislau.bitbucketproject.presentation.codeeditor

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import timber.log.Timber
import javax.inject.Inject

class CodeEditorViewModel : ViewModel() {

    @Inject
    lateinit var codeEditorModel: CodeEditorModel

    val code : MutableLiveData<String> = MutableLiveData()

    init {
        //App.component.inject(this)
        codeEditorModel.getCode()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    code.postValue(it)
                },{
                    Timber.e(it.message)
                })
    }
}