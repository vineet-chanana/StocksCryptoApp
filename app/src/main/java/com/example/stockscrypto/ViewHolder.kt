import android.graphics.Color
import android.util.Log
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.models.Category
import com.example.models.CryptosData
import com.example.models.StocksData
import com.example.stockscrypto.CategoriesFragment
import com.example.stockscrypto.MyAdapter
import com.example.stockscrypto.R

//package com.example.stockscrypto
//
//import android.view.View
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//

class CategoryViewHolder(itemView: View, val categoryClickListener: CategoriesFragment) :
    RecyclerView.ViewHolder(itemView), View.OnClickListener {
    val category_name = itemView.findViewById<TextView>(R.id.category_name)
    val category_desc = itemView.findViewById<TextView>(R.id.category_desc)
    val showData = itemView.findViewById<ImageView>(R.id.showData)
    lateinit var category_id: String

    init {
        showData.setOnClickListener(this)
    }

    fun bind(item: Category) {
        category_name.text = item.name
        category_desc.text = item.desc.smallDesc
        category_id = item._id


        if(item.showCategoryData) showData.rotation = 180F;
        else showData.rotation = 0F;
    }

    override fun onClick(p0: View?) {
        Log.d("Adapter", "click listener")
        categoryClickListener.onCategoryClick(adapterPosition, category_id)
    }


}

class StocksRecyclerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val stocksRecyclerView = itemView.findViewById<RecyclerView>(R.id.stocks_recyclerview)

    fun bind(item: List<StocksData>) {

        val stocksAdapter = MyAdapter(null)
        stocksRecyclerView.layoutManager = LinearLayoutManager(itemView.context)
        stocksAdapter.addData(item)
        stocksRecyclerView.adapter = stocksAdapter

    }
}


private fun setImage(media: String, itemView: View, stockImage: ImageView) {

    Glide.with(itemView.context)
        .load(media)
        .override(100, 100)
        .placeholder(R.drawable.ic_launcher_background)
        .error(R.drawable.ic_launcher_foreground)
        .into(stockImage)

}

private fun setOneDayChange(stockOneDayChange: TextView, priceChange: Double) {
    stockOneDayChange.text = String.format("%.2f", priceChange) + "%"
    if (priceChange > 0)
        stockOneDayChange.setTextColor(Color.GREEN)
    else
        stockOneDayChange.setTextColor(Color.RED)
}

class StocksViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val stockSymbol = itemView.findViewById<TextView>(R.id.stockSymbol)
    val stockName = itemView.findViewById<TextView>(R.id.stockName)
    val stockCurrentPrice = itemView.findViewById<TextView>(R.id.stockCurrentPrice)
    val stockImage = itemView.findViewById<ImageView>(R.id.stockImage)
    val stockOneDayChange = itemView.findViewById<TextView>(R.id.stockOneDayChange)


    fun bind(item: StocksData) {
        val media = Companion.BASE_URL + item.logo
        setImage(media, itemView, stockImage)
        stockName.text = item.name.subSequence(0,item.name.length - 8)
        stockSymbol.text = item.symbol
        stockCurrentPrice.text = "\u20B9" + item.currentPrice.toString()

        setOneDayChange(stockOneDayChange, item.oneDayChange)

    }

    companion object {
        const val BASE_URL = "http://api.pennyup.club/"
    }


}

class CryptoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val stockSymbol = itemView.findViewById<TextView>(R.id.stockSymbol)
    val stockName = itemView.findViewById<TextView>(R.id.stockName)
    val stockCurrentPrice = itemView.findViewById<TextView>(R.id.stockCurrentPrice)
    val stockImage = itemView.findViewById<ImageView>(R.id.stockImage)
    val stockOneDayChange = itemView.findViewById<TextView>(R.id.stockOneDayChange)


    fun bind(item: CryptosData) {
        setImage(item.logo, itemView, stockImage)
        stockSymbol.text = item.symbol
        stockName.text = item.name

        stockCurrentPrice.text = item.currentPrice.toString()
        setPrice(item.currentPrice)
        setOneDayChange(stockOneDayChange, item.oneDayChange)
    }

    private fun setPrice(price: Double) {

        stockCurrentPrice.text = "\u20B9" + String.format("%.2f", price)
    }
}

class TitleViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
    val title = itemView.findViewById<TextView>(R.id.title)

    fun bind(item: String) {
        title.text = item
    }
}