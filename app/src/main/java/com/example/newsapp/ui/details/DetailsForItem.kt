package com.example.newsapp.ui.details

import android.annotation.SuppressLint
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.fragment.navArgs
import com.example.newsapp.R
import com.example.newsapp.data.NewsArticle
import com.example.newsapp.databinding.FragmentHomeBinding
import com.example.newsapp.ui.home.HomeFragment
import com.example.newsapp.ui.home.HomeFragmentDirections

class DetailsForItem : Fragment() {
    lateinit var image: ImageView
    lateinit var name: TextView
    lateinit var title: TextView
    lateinit var date: TextView
    lateinit var author: TextView
    lateinit var descrption: TextView
    lateinit var content: TextView
    val args: DetailsForItemArgs by navArgs()

    companion object {
        fun newInstance() = DetailsForItem()
    }

    private lateinit var viewModel: DetailsViewModel

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_details, container, false)
        image = root.findViewById(R.id.newsImage)
        title = root.findViewById(R.id.newsTitle)
        title.text = "newsTitle:  " + args.news.title
        date = root.findViewById(R.id.newsPublishedAt)
        date.text = "newsPublishedAt=  " + args.news.publishedAt
        author = root.findViewById(R.id.newsAuthor)
        author.text = "newsAuthor:  " + args.news.author
        descrption = root.findViewById(R.id.newsDescrption)
        descrption.text = "newsDescrption:  " + args.news.description
        content = root.findViewById(R.id.newsContent)
        content.text = "newsContent:  " + args.news.content
        return root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(DetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}