package bushuk.stanislau.bitbucketproject.presentation.user


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.*
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.databinding.FragmentUserBinding
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenActivity
import kotlinx.android.synthetic.main.fragment_user.view.*

class UserFragment : Fragment() {

    lateinit var binding: FragmentUserBinding
    lateinit var viewModel: UserViewModel

    lateinit var avatar: String
    lateinit var userName: String
    lateinit var displayName: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        viewModel.setUser(arguments?.getString("USERNAME").toString())
        getArgs()
        setToolbar(binding)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.let {
            it.fragment = this
            it.handler = this
            it.manager = childFragmentManager
            it.viewModel = viewModel
        }

    }

    private fun setToolbar(binding: FragmentUserBinding) {
        (activity as AppCompatActivity).supportActionBar?.hide()
        (activity as AppCompatActivity).setSupportActionBar(binding.root.toolbar_user)
        (activity as AppCompatActivity).supportActionBar!!.title = "TEST"
        setHasOptionsMenu(false)


    }

    override fun onDestroy() {
        (activity as MainScreenActivity).recreateToolbar()

        super.onDestroy()
    }

    private fun getArgs() {
        avatar = arguments!!.getString("AVATAR")
        userName = arguments!!.getString("USERNAME")
        displayName = arguments!!.getString("DISPLAYNAME")
    }
}
