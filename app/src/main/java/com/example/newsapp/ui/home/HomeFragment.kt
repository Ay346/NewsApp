package com.example.newsapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.newsapp.data.NewsArticle
import com.example.newsapp.adapter.NewsAdapter
import com.example.newsapp.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import com.example.newsapp.ui.Result
import com.sawaf.weatherappex.tools.toGone
import com.sawaf.weatherappex.tools.toVisible
import  com.example.newsapp.ui.NewsModelUi
import timber.log.Timber

@AndroidEntryPoint
class HomeFragment : Fragment() {
    val homeviewModel: HomeViewModel by viewModels()
    private var _binding: FragmentHomeBinding? = null
    private val itemsList = ArrayList<NewsArticle>()
    private lateinit var customAdapter: NewsAdapter

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root
        val recyclerView: RecyclerView = binding.recyclerView
        val layoutManager = LinearLayoutManager(requireContext())
        recyclerView.layoutManager = layoutManager
        customAdapter = NewsAdapter(itemsList){ article ->
            findNavController().navigate(HomeFragmentDirections.openDetails(article))
        }
        recyclerView.adapter = customAdapter
//        homeviewModel.loadData()


        homeviewModel.uiStateweather.observe(viewLifecycleOwner) {
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
//                    Timber.d("${it.data !!.javaClass}")
                    binding.loading.toGone()
                    binding.recyclerView.toVisible()
                    if (it.data is ArrayList<*>) {
                        val uiModel = NewsModelUi.toUiModel(it.data as ArrayList<NewsArticle>)
                        itemsList.clear()
                        itemsList.addAll(uiModel.itemsList!!)
                        customAdapter.notifyDataSetChanged()

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


//
//    override fun click(newsArticle: NewsArticle) {
//        val bundle = Bundle()
//        bundle.putString("image", newsArticle.urlToImage)
//        bundle.putString("title", newsArticle.title)
//        bundle.putString("Date", newsArticle.publishedAt)
//        bundle.putString("author", newsArticle.author)
//        findNavController().navigate(R.id.open_details, bundle)
//    }
}


