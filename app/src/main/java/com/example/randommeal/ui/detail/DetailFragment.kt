package com.example.randommeal.ui.detail

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.fragment.app.viewModels
import com.example.randommeal.R
import com.example.randommeal.databinding.FragmentDetailBinding
import com.example.randommeal.ui.common.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailFragment : Fragment(R.layout.fragment_detail) {

    private val viewModel: DetailViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val binding = FragmentDetailBinding.bind(view)

        binding.movieDetailToolbar.setNavigationOnClickListener { requireActivity().onBackPressed() }
        binding.mealDetailFavorite.setOnClickListener { viewModel.onFavoriteClicked() }
        viewLifecycleOwner.launchAndCollect(viewModel.state) { state ->
            if(state.detailMeal != null) {
                binding.mealDetail = state.detailMeal
            }
        }
    }
}