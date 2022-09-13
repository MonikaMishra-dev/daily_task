package com.example.dynamicviewproject

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class DynamicViewModel:ViewModel() {
     val paragraphLiveData = MutableLiveData<String>()
     val bulletedLiveData = MutableLiveData<ArrayList<String>>()
     val stepsLiveData = MutableLiveData<ArrayList<String>>()

     private val bulletedArrayList = arrayListOf("Yahoo","Hotmail","Gmail","Facebook","Reddit","Instagram")
     private val stepsArrayList = arrayListOf("open the link on a separate tab", "enter your login id",
     "enter your password", "enter your registered mobile number", "enter otp received on the registered number",
     "click on submit")

     fun setParagraphText()
     {
       paragraphLiveData.value = "Generating random paragraphs can be an excellent" +
               " way for writers to get their creative flow going at the beginning " +
               "of the day. The writer has no idea what topic the random paragraph " +
               "will be about when it appears."
     }

    fun setBulletedText()
    {
       bulletedLiveData.value = bulletedArrayList
    }

    fun setStepsText()
    {
     stepsLiveData.value = stepsArrayList
    }

}