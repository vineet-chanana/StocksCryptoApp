package com.example.stockscrypto

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView


class CategoriesFragment : Fragment(),MyAdapter.onCategoryClickListener {

    private lateinit var viewmodel:CategoriesFragmentViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_categories, container, false)

        viewmodel = ViewModelProvider(this).get(CategoriesFragmentViewModel::class.java)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)
        val adapter = MyAdapter(this)


        recyclerView.layoutManager = LinearLayoutManager(context)

        viewmodel.data.observe(viewLifecycleOwner){ categoriesData->
            adapter.addData(categoriesData)
            recyclerView.adapter = adapter
        }

        viewmodel.positionLiveData.observe(viewLifecycleOwner){
            adapter.notifyItemChanged(it)
            adapter.notifyItemRangeInserted(it+1,4)
        }

        return view
    }

    override fun onCategoryClick(position: Int,id:String) {
        Log.d("Fragment","Category clicked")
        Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show()
        viewmodel.categoryData(position,id)
    }


}