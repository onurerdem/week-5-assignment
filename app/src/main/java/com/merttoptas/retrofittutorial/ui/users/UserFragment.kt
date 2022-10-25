package com.merttoptas.retrofittutorial.ui.users

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import com.google.android.material.snackbar.Snackbar
import com.merttoptas.retrofittutorial.data.model.DataState
import com.merttoptas.retrofittutorial.databinding.FragmentUserBinding
import com.merttoptas.retrofittutorial.ui.loadingprogress.LoadingProgressBar
import com.merttoptas.retrofittutorial.ui.users.adapter.UsersAdapter
import com.merttoptas.retrofittutorial.ui.users.viewmodel.UserViewEvent
import com.merttoptas.retrofittutorial.ui.users.viewmodel.UsersViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserFragment : Fragment() {
    lateinit var loadingProgressBar: LoadingProgressBar
    private lateinit var binding: FragmentUserBinding
    private val viewModel by viewModels<UsersViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentUserBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        loadingProgressBar = LoadingProgressBar(requireContext())

        binding.viewModel = viewModel
        binding.lifecycleOwner = viewLifecycleOwner

        viewModel.userLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is DataState.Success -> {
                    loadingProgressBar.hide()
                    it.data?.let { safeData ->
                        binding.rvUsersList.adapter = UsersAdapter(this@UserFragment).apply {
                            submitList(safeData)
                        }
                    } ?: run {
                        Toast.makeText(requireContext(), "No data", Toast.LENGTH_SHORT).show()
                    }
                }
                is DataState.Error -> {
                    loadingProgressBar.hide()
                    Snackbar.make(binding.root, it.message, Snackbar.LENGTH_LONG).show()
                }
                is DataState.Loading -> {
                    loadingProgressBar.show()
                }
            }
        }

        viewModel.eventStateLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is UserViewEvent.ShowMessage -> {}
                is UserViewEvent.NavigateToDetail -> {}
            }
        }

        /*
        Way 2
         viewModel.postLiveData.observe(viewLifecycleOwner) {
            binding.rvPostsList.adapter = PostsAdapter().apply {
                submitList(it)
            }
        }

         */
    }
}