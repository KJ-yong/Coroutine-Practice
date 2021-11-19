package com.example.coroutinepractice

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.coroutinepractice.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    lateinit var viewModel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        binding.startButton.setOnClickListener {
            viewModel.sorting()
        }
        viewModel.numberList.observe(this,{
            binding.numberText.text = viewModel.listToString()
        })
        viewModel.count.observe(this,{
            binding.countText.text = it.toString()
        })
    }
}