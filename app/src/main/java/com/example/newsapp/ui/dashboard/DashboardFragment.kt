package com.example.newsapp.ui.dashboard

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.adapter.SourceAdapter
import com.example.newsapp.data.NewsArticle
import com.example.newsapp.data.model.Source
import com.example.newsapp.databinding.FragmentDashboardBinding
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.ui.NewsModelUi
import com.example.newsapp.ui.Result
import com.example.newsapp.ui.SourceModelUi
import com.example.newsapp.ui.home.HomeFragmentDirections
import com.example.newsapp.ui.home.HomeViewModel
import com.sawaf.weatherappex.tools.toGone
import com.sawaf.weatherappex.tools.toVisible
import dagger.hilt.android.AndroidEntryPoint
import timber.log.Timber

@AndroidEntryPoint
class DashboardFragment : Fragment() {
    val dashboardViewModel:DashboardViewModel by viewModels()
    private val itemsList = ArrayList<Source>()
    private lateinit var  sourceAdapter: SourceAdapter
    private var _binding: FragmentDashboardBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDashboardBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView: RecyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        sourceAdapter=SourceAdapter(itemsList){source ->
            findNavController().navigate(DashboardFragmentDirections.openSource(source))
        }
        recyclerView.adapter = sourceAdapter
        dashboardViewModel.uiStateweather.observe(viewLifecycleOwner) {
            Timber.d("$it")
            when (it) {
                is Result.Loading-> {
                    binding.loading.toVisible()
                    binding.recyclerView.toGone()
                }
                is Result.Error -> {
                    binding.loading.toGone()
                    binding.recyclerView.toGone()
                }
                is Result.Success<*> -> {
                    binding.loading.toGone()
                    binding.recyclerView.toVisible()
                    if (it.data is ArrayList<*>) {
                        val uiModel = SourceModelUi.toUiModel(it.data as ArrayList<Source>)
                        itemsList.clear()
                        itemsList.addAll(uiModel.itemsList!!)
                        sourceAdapter.notifyDataSetChanged()

                    }

                }

            }
        }
        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}