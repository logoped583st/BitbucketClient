package bushuk.stanislau.bitbucketproject.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import io.reactivex.disposables.CompositeDisposable

abstract class BaseBindingFragment<V : ViewModel, D : ViewDataBinding> : Fragment() {

    protected val disposable: CompositeDisposable = CompositeDisposable()

    abstract val viewModelFactory: ViewModelProvider.Factory

    abstract val containerId: Int

    protected lateinit var binding: D

    protected lateinit var viewModel: V

    abstract val viewModelClass: Class<V>

    abstract val scope: ViewModelScope

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        viewModel = when (scope) {
            ViewModelScope.ACTIVITY -> ViewModelProviders.of(activity!!, viewModelFactory).get(viewModelClass)
            ViewModelScope.FRAGMENT -> ViewModelProviders.of(this, viewModelFactory).get(viewModelClass)
        }

        binding = DataBindingUtil.inflate(layoutInflater, containerId, container, false)

        binding.lifecycleOwner = viewLifecycleOwner

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyBinding()
    }

    enum class ViewModelScope {
        ACTIVITY,
        FRAGMENT
    }


    abstract fun applyBinding()

}