package bushuk.stanislau.bitbucketproject.presentation.user


import androidx.lifecycle.ViewModelProviders
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.databinding.FragmentUserBinding
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenActivity
import kotlinx.android.synthetic.main.fragment_user.view.*

class UserFragment : Fragment() {

    lateinit var binding: FragmentUserBinding
    lateinit var viewModel: UserViewModel

    lateinit var avatar: String
    private lateinit var userName: String
    private lateinit var displayName: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_user, container, false)
        viewModel = ViewModelProviders.of(this).get(UserViewModel::class.java)
        getArgs()
        viewModel.setUser(userName)
        setToolbar(binding)

        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.let {
            it.fragment = this
            it.handler = this
            it.viewModel = viewModel
        }

        binding.pager.isSaveFromParentEnabled = false //ned check it.

    }

    private fun setToolbar(binding: FragmentUserBinding) {
        (activity as AppCompatActivity).supportActionBar?.hide()
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.root.toolbar_user)
        (activity as AppCompatActivity).supportActionBar!!.title = userName
        (activity as AppCompatActivity).supportActionBar!!.setDisplayHomeAsUpEnabled(true)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        viewModel.exitFromFragment()
        return super.onOptionsItemSelected(item)
    }

    override fun onDestroyView() {
        (activity as MainScreenActivity).recreateToolbar()
        super.onDestroyView()
    }

    private fun getArgs() {
        avatar = arguments!!.getString("AVATAR")
        userName = arguments!!.getString("USERNAME")
        displayName = arguments!!.getString("DISPLAYNAME")
    }
}
