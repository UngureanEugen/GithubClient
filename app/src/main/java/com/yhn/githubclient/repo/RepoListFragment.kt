package com.yhn.githubclient.repo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.yhn.githubclient.R
import com.yhn.githubclient.domain.AuthorizeUseCase
import com.yhn.githubclient.domain.SearchReposUseCase.Companion.PAGE_SIZE
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_repo_list.*
import java.util.*
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
            val linearLayoutManager =
                LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            layoutManager = linearLayoutManager
            adapter = repoAdapter
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView.setOnQueryTextListener(queryTextListener)
        languageSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(p0: AdapterView<*>?) {
                // nop
            }

            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, position: Int, p3: Long) {
                onQuery(
                    searchView.query.toString(),
                    resources.getStringArray(R.array.languages)[position],
                    true
                )
            }

        }
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
            onQuery(p0, languageSpinner.selectedItem.toString(), true)
            return true
        }

        override fun onQueryTextChange(p0: String): Boolean {
            onQuery(p0, languageSpinner.selectedItem.toString(), true)
            return true
        }

    }

    private val onQuery: (String, String, Boolean) -> Unit = { query, language, shouldReset ->
        if (shouldReset) {
            repoAdapter.clear()
        }
        viewModel.search(query, language, shouldReset)
    }
}
