package com.example.stockscrypto

import CategoryViewHolder
import CryptoViewHolder
import StocksRecyclerViewHolder
import StocksViewHolder
import TitleViewHolder
import android.app.Activity
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.models.Category
import com.example.models.CryptosData
import com.example.models.StocksData

class MyAdapter(val categoryClickListener: CategoriesFragment?) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var data: List<Any> = ArrayList<Any>()


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        var viewHolder:RecyclerView.ViewHolder? = null

        when(viewType){
            VIEW_CATEGORY -> viewHolder = CategoryViewHolder(inflater.inflate(R.layout.category,parent,false),categoryClickListener!!)
            VIEW_STOCKS -> viewHolder = StocksViewHolder(inflater.inflate(R.layout.item_stocks, parent, false))
            VIEW_CRYPTO -> viewHolder = CryptoViewHolder(inflater.inflate(R.layout.item_stocks,parent,false))
            VIEW_TITLE -> viewHolder = TitleViewHolder(inflater.inflate(R.layout.item_title,parent,false))
            VIEW_RECYCLERVIEW -> viewHolder = StocksRecyclerViewHolder(inflater.inflate(R.layout.item_stocks_recyclerview,parent,false))
        }

        return viewHolder!!

    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when(data[position]){
            is Category -> (holder as CategoryViewHolder).bind(data[position] as Category)
            is String -> (holder as TitleViewHolder).bind(data[position] as String)
            is List<*> -> (holder as StocksRecyclerViewHolder).bind(data[position] as List<StocksData>)
            is StocksData -> (holder as StocksViewHolder).bind(data[position] as StocksData)
            is CryptosData -> (holder as CryptoViewHolder).bind(data[position] as CryptosData)
        }

    }

    override fun getItemCount(): Int {
        return data.size
    }

    override fun getItemViewType(position: Int): Int {
        return when(data.get(position)){
            is Category -> VIEW_CATEGORY
            is List<*> -> VIEW_RECYCLERVIEW
            is StocksData -> VIEW_STOCKS
            is CryptosData -> VIEW_CRYPTO
            is String -> VIEW_TITLE
            else -> -1
        }
    }


    fun addData(adapterData: List<Any>){
        data = adapterData
        notifyDataSetChanged()
    }


    interface onCategoryClickListener{
        fun onCategoryClick(position: Int,id:String)
    }


    companion object{
        const val VIEW_CATEGORY = 0
        const val VIEW_STOCKS = 1
        const val VIEW_CRYPTO = 2
        const val VIEW_RECYCLERVIEW = 3
        const val VIEW_TITLE = 4
    }

}

