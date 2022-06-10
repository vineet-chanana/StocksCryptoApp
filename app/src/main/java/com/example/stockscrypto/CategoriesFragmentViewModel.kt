package com.example.stockscrypto

import android.util.Log
import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.models.Category
import com.example.models.Data
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.http.POST

class CategoriesFragmentViewModel : ViewModel() {
    private val repository = Repository()

    private var _data = MutableLiveData<MutableList<Any>>()
    val data: LiveData<MutableList<Any>>
        get() = _data

    private var _positionLiveData = MutableLiveData<Int>()
    val positionLiveData: LiveData<Int>
        get() = _positionLiveData


    init {
        getAllCategories()
    }


    fun getAllCategories() {
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getAllCategories()
            _data.postValue(response?.toMutableList())
        }
    }


    fun categoryData(position: Int, id: String) {
        val showCategoryData: Boolean = (_data.value?.get(position) as Category).showCategoryData
        if (!showCategoryData) getCategoryById(position, id)
        else deleteData(position)
    }

    private fun deleteData(position: Int) {
        val newList = _data.value
        newList?.subList(position + 1, position + 5)?.clear()

        (newList?.get(position) as Category).showCategoryData = false
        _data.value = newList
    }


    private fun getCategoryById(position: Int, id: String) {
        //var response: Data? = null
        viewModelScope.launch(Dispatchers.IO) {
            val response = repository.getCategoryById(id)

            Log.d("Viewmodel", "received response" + response?.category?.name)
            addData(position, response)
        }


    }

    private fun addData(position: Int, response: Data?) {

        _data.value?.apply {
            add(position+1,"Stocks");
            add(position + 2, response?.stocksData!!)
            add(position + 3, "Crypto")
            add(position + 4, response?.cryptosData!!)
            (get(position) as Category).showCategoryData = true  //change the state of showCategoryData
        }

        _positionLiveData.postValue(position)

        //showData.setImageResource(android.R.drawable.arrow_up_float)

        //Don't bring the view refernce outside of recyclerview..because the views get recycled and they get used again
        //so changes made will be reflected when the view gets recycled


        /*
        newList?.add(position + 1, "Stocks")
        add(position + 2, response?.stocksData!!)
        add(position + 3, "Crypto")
        add(position + 4, response?.cryptosData!!)

        _data.postValue(newList)
        */

        //Instead of changing the whole list(which was calling notifyDatasetChanged() because live data value is getting changed )
        //we add the values in the list without changing the live data, hence we are manually notifying the adapter that these changes have been made
        //this will not cause the whole list to be updated and hence the recyclerview position will be maintained


    }



}