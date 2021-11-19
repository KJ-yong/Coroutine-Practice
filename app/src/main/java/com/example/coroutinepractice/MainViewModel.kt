package com.example.coroutinepractice

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.util.Random

class MainViewModel : ViewModel() {
    private val _numberList = MutableLiveData<MutableList<Int>>()
    private val _count = MutableLiveData<Int>()
    val numberList: LiveData<MutableList<Int>>
        get() = _numberList
    val count: LiveData<Int>
        get() = _count

    init {
        _count.value = 0
        _numberList.value = mutableListOf(1,2,3,4,5)
    }

    fun sorting() {
        _count.value = 0
        viewModelScope.launch {
            while(true){
                _count.postValue(_count.value?.plus(1))
                randomSortInCoroutine()
                if(isSorted()) break
                delay(100)
            }
        }
    }

    fun listToString(): String {
        val tempList = _numberList.value!!
        var str = ""
        for (item in tempList) {
            str = "$str$item "
        }
        return str
    }

    private fun randomSortInCoroutine() {
        val tempList = _numberList.value!!
        for (i in 0..4) {
            val rand = Random().nextInt(5 - i) + i
            val temp = tempList[i]
            tempList[i] = tempList[rand]
            tempList[rand] = temp
        }
        _numberList.postValue(tempList)
    }

    private fun isSorted(): Boolean {
        val tempList = _numberList.value!!
        for(i in 0..3){
            if(tempList[i] > tempList[i+1])
                return false
        }
        return true
    }
}