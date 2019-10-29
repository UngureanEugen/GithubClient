package com.yhn.githubclient.repo

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.yhn.githubclient.R
import com.yhn.githubclient.domain.LoginUseCase
import dagger.android.support.DaggerFragment
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
}
