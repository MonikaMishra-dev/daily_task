package com.example.dynamicviewproject


import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.get
import com.example.dynamicviewproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var  binding : ActivityMainBinding
    private lateinit var dynamicViewModel: DynamicViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)

        dynamicViewModel = ViewModelProvider(this,DynamicViewModelFactory(this,binding)).get()
        dynamicViewModel.createParagraph()
        binding.viewModel = dynamicViewModel
        binding.lifecycleOwner  = this
    }

}