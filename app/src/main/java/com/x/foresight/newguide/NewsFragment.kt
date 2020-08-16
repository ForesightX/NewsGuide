package com.x.foresight.newguide

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.x.foresight.newguide.adapters.NewsItemAdapter
import com.x.foresight.newguide.databinding.FragmentNewsBinding
import com.x.foresight.newguide.viewmodel.NewsViewModel

class NewsFragment : Fragment() {

    // Lazy initialisation
    private val newsViewModel: NewsViewModel by lazy {
        ViewModelProvider(this,
            NewsViewModel.Factory(requireActivity().application)).get(NewsViewModel::class.java)
    }

    private lateinit var binding: FragmentNewsBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_news, container, false)

        binding.lifecycleOwner = this

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        binding.viewModel = newsViewModel

        binding.recyclerView.adapter = NewsItemAdapter()
    }
}