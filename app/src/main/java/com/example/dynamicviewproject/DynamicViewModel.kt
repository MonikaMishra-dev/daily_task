package com.example.dynamicviewproject

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.Typeface
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.*
import androidx.core.content.ContextCompat
import androidx.core.view.setPadding
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.dynamicviewproject.databinding.ActivityMainBinding

class DynamicViewModel(context: Context,binding: ActivityMainBinding):ViewModel() {
    private var bindingInstance = binding
    private var contextInstance = context
    private val btnArray = arrayOfNulls<Button>(3)
    init {
        btnArray[0] = bindingInstance.btnParagraph
        btnArray[1] = bindingInstance.btnBulleted
        btnArray[2] = bindingInstance.btnSteps
    }

     fun createParagraph() {
         btnArray[0]?.isSelected = true
         btnArray[1]?.isSelected = false
         btnArray[2]?.isSelected = false
         changeBackground(btnArray)
        val title = TextView(contextInstance)
        title.apply {
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(20, 20, 20, 20)
            this.layoutParams = params
            this.text = resources.getText(R.string.title)
            this.setTypeface(null, Typeface.BOLD)
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25F)
            this.setTextColor(ContextCompat.getColor(contextInstance, R.color.purple_700))
        }
        val title1 = TextView(contextInstance)
        title1.apply {
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(20, 20, 20, 20)
            this.layoutParams = params
            this.text = resources.getText(R.string.article1)
            this.setTypeface(null, Typeface.BOLD)
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
            this.setTextColor(ContextCompat.getColor(contextInstance, R.color.black))
        }
        val paragraph = TextView(contextInstance)
        paragraph.apply {
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(20, 20, 20, 20)
            this.layoutParams = params
           this.text = resources.getText(R.string.para)
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
            this.setLineSpacing(
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 5.0f,
                    resources.displayMetrics
                ), 1.0f
            )
            this.setTextColor(ContextCompat.getColor(contextInstance, R.color.black))
        }
        bindingInstance.linearLayout.apply {
            this.removeAllViews()
            this.addView(title)
            this.addView(title1)
            this.addView(paragraph)
        }
    }

     fun createBulleted() {
         btnArray[0]?.isSelected = false
         btnArray[1]?.isSelected = true
         btnArray[2]?.isSelected = false
         changeBackground(btnArray)
        val title = TextView(contextInstance)
        title.apply {
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(20, 20, 20, 20)
            this.layoutParams = params
            this.text = resources.getText(R.string.select)
            this.setTypeface(null, Typeface.BOLD)
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25F)
            this.setTextColor(ContextCompat.getColor(contextInstance, R.color.purple_700))
        }
        val rb = arrayOfNulls<RadioButton>(6)
        val rg = RadioGroup(contextInstance)
        rg.orientation = RadioGroup.VERTICAL
        rg.setPadding(16)
        for (i in 0..5) {
            rb[i] = RadioButton(contextInstance)
            rb[i].apply {
                this?.text = contextInstance.resources.getStringArray(R.array.listItems)[i]
                this?.id = i
                this?.setTextColor(ContextCompat.getColor(contextInstance, R.color.black))
                this?.setLineSpacing(
                    TypedValue.applyDimension(
                        TypedValue.COMPLEX_UNIT_DIP, 5.0f,
                        resources.displayMetrics
                    ), 1.0f
                )
                this?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
                rg.addView(rb[i])
            }
        }
        bindingInstance.linearLayout.apply {
            this.removeAllViews()
            this.addView(title)
            this.addView(rg)
        }
        rg.setOnCheckedChangeListener { radioGroup, _ ->
            val rbId = radioGroup.checkedRadioButtonId
            val radioButton = radioGroup.findViewById<RadioButton>(rbId)
            Toast.makeText(contextInstance, "${radioButton.text} selected", Toast.LENGTH_SHORT)
                .show()
        }
    }

     fun createSteps() {
         btnArray[0]?.isSelected = false
         btnArray[1]?.isSelected = false
         btnArray[2]?.isSelected = true
         changeBackground(btnArray)
        bindingInstance.linearLayout.removeAllViews()
        val title = TextView(contextInstance)
        title.apply {
            this.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            this.text = resources.getText(R.string.follow)
            this.setTypeface(null, Typeface.BOLD)
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
            this.setTextColor(ContextCompat.getColor(contextInstance, R.color.purple_700))
        }
        bindingInstance.linearLayout.addView(title)
        val tv = arrayOfNulls<TextView>(6)
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(10, 20, 0, 0)
        for (i in 0..5) {
            tv[i] = TextView(contextInstance)
            tv[i].apply {
                this?.text =
                    "${i + 1}. ${contextInstance.resources.getStringArray(R.array.steps)[i]}"
                this?.layoutParams = params
                this?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
                bindingInstance.linearLayout.addView(tv[i])
                this?.setTextColor(ContextCompat.getColor(contextInstance, R.color.black))
            }
        }
    }

     private fun changeBackground(btnArray: Array<Button?>){
        for(btn in btnArray) {
            if (btn?.isSelected==true) {
                btn.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(contextInstance, R.color.purple_700)
                )
                btn.setTextColor(ContextCompat.getColor(contextInstance, R.color.white))
            } else {
                btn?.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(contextInstance, R.color.white)
                )
                btn?.setTextColor(ContextCompat.getColor(contextInstance, R.color.purple_700))
            }
        }
    }
}