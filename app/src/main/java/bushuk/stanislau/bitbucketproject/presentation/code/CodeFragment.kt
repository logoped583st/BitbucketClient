package bushuk.stanislau.bitbucketproject.presentation.code


import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import bushuk.stanislau.bitbucketproject.R
import bushuk.stanislau.bitbucketproject.adapters.RecyclerCodeAdapter
import bushuk.stanislau.bitbucketproject.adapters.RecyclerCodePathAdapter
import bushuk.stanislau.bitbucketproject.adapters.SpinnerAdapter
import bushuk.stanislau.bitbucketproject.databinding.FragmentCodeBinding
import bushuk.stanislau.bitbucketproject.presentation.follow.ClickFollow
import bushuk.stanislau.bitbucketproject.room.code.Code
import kotlinx.android.synthetic.main.fragment_code.*

class CodeFragment : Fragment(), ClickFollow, RecyclerCodePathAdapter.PathClick {

    override fun onClickPath(path: String, position: Int) {
        viewModel.reloadPathWithHash(this, codeAdapter, path)
        codePathAdapter.deletePath(position)
    }

    lateinit var binding: FragmentCodeBinding
    lateinit var viewModel: CodeViewModel

    private val codePathAdapter = RecyclerCodePathAdapter(mutableListOf("src"), this)
    private val codeAdapter = RecyclerCodeAdapter()


    override fun onClickItem(view: View, data: Any) {
        if ((data as Code).type == "commit_directory") {
            codePathAdapter.changePath(data.path)
            viewModel.reloadPathWithHash(this, codeAdapter, data.path)
        } else {
            viewModel.navigateToCodeEditor(data)
        }

    }


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {


        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_code, container, false)
        viewModel = ViewModelProviders.of(this).get(CodeViewModel::class.java)

        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.let {
            it.viewModel = viewModel
            it.setLifecycleOwner(this)
        }

        val branchSpinnerAdapter: ArrayAdapter<String> = SpinnerAdapter(activity!!, android.R.layout.simple_spinner_item, mutableListOf())
        branchSpinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        code_screen_branch_spinner.adapter = branchSpinnerAdapter


        //code_screen_code_path_recycler.setHasFixedSize(true)
        code_screen_code_path_recycler.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL, false)
        code_screen_code_path_recycler.adapter = codePathAdapter

        val mainBranch: String? = viewModel.factory.codeDataSource.repositoryModel.repository.value.mainbranch?.name
        var positionOfMainBranch = 0

        viewModel.branches.observe(this, Observer {
            it!!.forEachIndexed { index, branch ->
                if (mainBranch == branch.name) {
                    positionOfMainBranch = index
                }
                branchSpinnerAdapter.add(branch.name)
            }

            code_screen_branch_spinner.setSelection(positionOfMainBranch)
            viewModel.observeSpinner(code_screen_branch_spinner, codeAdapter, this)
        })

        code_screen_recycler.layoutManager = LinearLayoutManager(activity)
        codeAdapter.clickFollow = this
        code_screen_recycler.adapter = codeAdapter
        viewModel.code.observe(this, Observer(codeAdapter::submitList))
    }
}
