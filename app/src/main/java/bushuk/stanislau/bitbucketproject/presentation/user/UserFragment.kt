package bushuk.stanislau.bitbucketproject.presentation.user


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.databinding.FragmentUserBinding

class UserFragment : Fragment() {

    lateinit var binding: FragmentUserBinding
    lateinit var viewModel: UserViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        binding.let {
            it.handler=this
            it.manager=childFragmentManager
            it.viewModel = viewModel
        }

        // view.findViewById<ViewPager>(R.id.user_screen_pager).adapter = ViewPagerUserAdapter(childFragmentManager)
        //R.id.userScreenPager.adapter = ViewPagerUserAdapter(fragmentManager!!)
    }
}
