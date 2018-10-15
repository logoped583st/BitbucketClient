package bushuk.stanislau.bitbucketproject.presentation.addrepository


import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.databinding.FragmentAddRepositoryBinding
import bushuk.stanislau.bitbucketproject.presentation.main.MainScreenActivity
import kotlinx.android.synthetic.main.fragment_add_repository.*
import kotlinx.android.synthetic.main.fragment_add_repository.view.*
import ru.noties.markwon.Markwon


class AddRepositoryFragment : Fragment() {


    lateinit var binding: FragmentAddRepositoryBinding
    lateinit var viewModel: AddRepositoryViewModel
    lateinit var description: String

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_add_repository, container, false)
        viewModel = ViewModelProviders.of(this).get(AddRepositoryViewModel::class.java)
        binding.let {
            it.fragment = this
            it.setLifecycleOwner(this)
        }
        setToolbar(binding)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (add_repository_screen_tab_layout as TabLayout).addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(p0: TabLayout.Tab?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTabUnselected(p0: TabLayout.Tab?) {
                //TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onTabSelected(p0: TabLayout.Tab?) {
                if (p0!!.position == 1) {
                    description = add_repository_screen_description_editText.text.toString()
                    add_repository_screen_layout.requestFocus()
                    add_repository_screen_description_editText.isFocusable = false
                    Markwon.setMarkdown(add_repository_screen_description_editText, description)
                } else {
                    add_repository_screen_description_editText.isFocusableInTouchMode = true
                    add_repository_screen_description_editText.requestFocus()
                    add_repository_screen_description_editText.setText(description)
                }
            }
        })
    }

    fun createButtonClick(view: View) {
        viewModel.createRepository(add_repository_screen_title_editText.text.toString(),
                add_repository_screen_description_editText.text.toString(),
                add_repository_screen_isPrivate_checkbox.isChecked)
    }

    private fun setToolbar(binding: FragmentAddRepositoryBinding) {
        (activity as AppCompatActivity).supportActionBar?.hide()
        setHasOptionsMenu(true)
        (activity as AppCompatActivity).setSupportActionBar(binding.root.add_repository_screen_toolbar)
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
}
