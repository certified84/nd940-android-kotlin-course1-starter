package com.udacity.shoestore

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe

class ShoeViewModel : ViewModel() {

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    init {
        _shoeList.value = mutableListOf(
            Shoe(
                "Airforce 1",
                43.0,
                "Nike",
                "The Airforce 1 is an amazing piece of work",
                null
            ),
            Shoe(
                "Airmax 720",
                43.0,
                "Nike",
                "The Airmax 720 is an amazing piece of work",
                null
            ),
            Shoe(
                "Airmax 270",
                43.0,
                "Nike",
                "The Airmax 270 is an amazing piece of work",
                null
            ),
            Shoe(
                "Jordan dior",
                43.0,
                "Nike",
                "The Jordan dior is an amazing piece of work",
                null
            ),
            Shoe(
                "Airmax 95",
                43.0,
                "Nike",
                "The Airmax 95 is an amazing piece of work",
                null
            )
        )
    }

    fun addShoe(shoe: Shoe) = _shoeList.value?.add(shoe)

}