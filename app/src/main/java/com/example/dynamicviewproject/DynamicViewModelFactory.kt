package com.example.dynamicviewproject

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.dynamicviewproject.databinding.ActivityMainBinding

class DynamicViewModelFactory(val context:Context, val binding: ActivityMainBinding):ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return DynamicViewModel(context,binding) as T
    }
}