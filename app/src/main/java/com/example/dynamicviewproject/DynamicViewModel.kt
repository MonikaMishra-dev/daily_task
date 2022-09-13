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
       paragraphLiveData.value = "<h6><font color=\"#FF0000\">About  article</font></h6>" +
               "random paragraphs can be an excellent way for writers to get their creative"+
               "flow going at the beginning of the day.  " +
               "<a href=\"https://kotlinlang.org/docs/home.html\">read more..</a>" +
               "<h6><font color=\"#FF0000\">About author</font></h6>A second option is to use the" +
               " random paragraph somewhere in a short story they create.The third option "+
         "is to have the random paragraph be the ending paragraph in a short story. "+
         "<a href=\"https://kotlinlang.org/docs/home.html\">read more..</a>"

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