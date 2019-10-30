package com.yhn.githubclient.repo

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yhn.githubclient.R
import com.yhn.githubclient.domain.LoginUseCase
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_repo_list.*
import javax.inject.Inject


class RepoListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    private val viewModel by viewModels<RepoListViewModel> { viewModelFactory }

    private val loginUseCase: LoginUseCase
        get() {
            return LoginUseCase(activity)
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_repo_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        searchView.setOnQueryTextListener(queryTextListener)
    }

    override fun onResume() {
        super.onResume()
        viewModel.start()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel.error.observe(this, Observer {
            loginUseCase.invoke()
        })
    }

    private val queryTextListener = object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(p0: String?): Boolean {
            viewModel.search(p0, 1)
            return true
        }

        override fun onQueryTextChange(p0: String?): Boolean {
            viewModel.search(p0, 1)
            return true
        }

    }
}
