package com.yhn.githubclient.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.yhn.githubclient.R
import com.yhn.githubclient.domain.AuthorizeUseCase
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_repo_list.*
import javax.inject.Inject


class RepoListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<RepoListViewModel> { viewModelFactory }

    private lateinit var repoAdapter: RepoListAdapter

    private val authorizeUseCase: AuthorizeUseCase
        get() {
            return AuthorizeUseCase(activity)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_repo_list, container, false)
        repoAdapter = RepoListAdapter(context)
        with(view.findViewById<RecyclerView>(R.id.recycler)) {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = repoAdapter
            addOnScrollListener(object : RecyclerView.OnScrollListener() {
                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)

                }
            })
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView.setOnQueryTextListener(queryTextListener)
    }

    override fun onResume() {
        super.onResume()
        viewModel.verifyAccessToken()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.error.observe(this, Observer {
            Snackbar.make(recycler, it, Snackbar.LENGTH_SHORT).show()
        })
        viewModel.repos.observe(this, Observer {
            repoAdapter.populate(it)
            repoAdapter.notifyDataSetChanged()
        })
        viewModel.refreshAccessToken.observe(this, Observer {
            authorizeUseCase.invoke()
        })
    }

    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(p0: String): Boolean {
            viewModel.search(p0, false)
            repoAdapter.clear()
            return true
        }

        override fun onQueryTextChange(p0: String): Boolean {
            viewModel.search(p0, true)
            repoAdapter.clear()
            return true
        }

    }
}
