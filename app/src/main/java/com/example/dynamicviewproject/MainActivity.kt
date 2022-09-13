package com.example.dynamicviewproject


import android.content.res.ColorStateList
import android.graphics.Typeface
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.util.TypedValue
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.text.HtmlCompat
import androidx.core.view.setPadding
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.dynamicviewproject.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var dynamicViewModel: DynamicViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        dynamicViewModel = ViewModelProvider(this).get(DynamicViewModel::class.java)



        val btnArray = arrayOfNulls<Button>(3)
        btnArray[0] = binding.btnParagraph
        btnArray[1] = binding.btnBulleted
        btnArray[2] = binding.btnSteps

        btnArray[0]?.isSelected = true
        btnArray[1]?.isSelected = false
        btnArray[2]?.isSelected = false
        changeBackground(btnArray)
        createParagraph()

        binding.btnParagraph.setOnClickListener {
            btnArray[0]?.isSelected = true
            btnArray[1]?.isSelected = false
            btnArray[2]?.isSelected = false
           changeBackground(btnArray)
           createParagraph()
        }
        binding.btnBulleted.setOnClickListener {
            btnArray[0]?.isSelected = false
          btnArray[1]?.isSelected = true
         btnArray[2]?.isSelected = false
           changeBackground(btnArray)
            createBulleted()
        }
        binding.btnSteps.setOnClickListener {
           btnArray[0]?.isSelected = false
          btnArray[1]?.isSelected = false
          btnArray[2]?.isSelected = true
           changeBackground(btnArray)
            createSteps()
        }
    }

    private fun changeBackground(btnArray: Array<Button?>) {
        for (btn in btnArray) {
            if (btn?.isSelected == true) {
                btn.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(applicationContext, R.color.purple_700)
                )
                btn.setTextColor(ContextCompat.getColor(applicationContext, R.color.white))
            } else {
                btn?.backgroundTintList = ColorStateList.valueOf(
                    ContextCompat.getColor(applicationContext, R.color.white)
                )
                btn?.setTextColor(ContextCompat.getColor(applicationContext, R.color.purple_700))
            }
        }
    }

    private fun createParagraph() {
        val title = TextView(this)
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
            this.setTextColor(ContextCompat.getColor(applicationContext, R.color.purple_700))
        }
        val title1 = TextView(this)
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
            this.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
        }
        val paragraph = TextView(this)
        dynamicViewModel.paragraphLiveData.observe(this, Observer {
                paragraph.text = HtmlCompat.fromHtml(it,HtmlCompat.FROM_HTML_MODE_LEGACY)
        })
        paragraph.apply {
            val params = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            params.setMargins(20, 20, 20, 20)
            this.layoutParams = params
            //this.text = resources.getText(R.string.para)
            dynamicViewModel.setParagraphText()
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
            this.setLineSpacing(
                TypedValue.applyDimension(
                    TypedValue.COMPLEX_UNIT_DIP, 5.0f,
                    resources.displayMetrics
                ), 1.0f
            )
            this.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
            this.linksClickable = true
            this.movementMethod = LinkMovementMethod.getInstance()
            this.setLinkTextColor(ContextCompat.getColor(applicationContext, R.color.purple_500))
        }
        binding.linearLayout.apply {
            this.removeAllViews()
            this.addView(title)
            this.addView(title1)
            this.addView(paragraph)
        }
    }

    private fun createBulleted() {
        val title = TextView(this)
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
            this.setTextColor(ContextCompat.getColor(applicationContext, R.color.purple_700))
        }
        val rb = arrayOfNulls<RadioButton>(6)
        dynamicViewModel.bulletedLiveData.observe(this, Observer {
            for(i in 0..5)
            rb[i]?.text = it[i]
        })
        val rg = RadioGroup(this)
        rg.orientation = RadioGroup.VERTICAL
        rg.setPadding(16)
        for (i in 0..5) {
            rb[i] = RadioButton(this)
            rb[i].apply {
               // this?.text = resources.getStringArray(R.array.listItems)[i]
                dynamicViewModel.setBulletedText()
                this?.id = i
                this?.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
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
        binding.linearLayout.apply {
            this.removeAllViews()
            this.addView(title)
            this.addView(rg)
        }
        rg.setOnCheckedChangeListener { radioGroup, _ ->
            val rbId = radioGroup.checkedRadioButtonId
            val radioButton = findViewById<RadioButton>(rbId)
            Toast.makeText(this, "${radioButton.text} selected", Toast.LENGTH_SHORT).show()
        }
    }

    private fun createSteps() {
        binding.linearLayout.removeAllViews()
        val title = TextView(this)
        title.apply {
            this.layoutParams = LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            this.text = resources.getText(R.string.follow)
            this.setTypeface(null, Typeface.BOLD)
            this.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20F)
            this.setTextColor(ContextCompat.getColor(applicationContext, R.color.purple_700))
        }
        binding.linearLayout.addView(title)
        val tv = arrayOfNulls<TextView>(6)
        dynamicViewModel.stepsLiveData.observe(this, Observer {
            for(i in 0..5)
            tv[i]?.text = "${i + 1}. ${it[i]}"
        })
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        params.setMargins(10, 20, 0, 0)

        for (i in 0..5) {
            tv[i] = TextView(this)
            tv[i].apply {
               // this?.text = "${i + 1}. ${resources.getStringArray(R.array.steps)[i]}"
                dynamicViewModel.setStepsText()
                this?.layoutParams = params
                this?.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18F)
                binding.linearLayout.addView(tv[i])
                this?.setTextColor(ContextCompat.getColor(applicationContext, R.color.black))
            }
        }
    }
}
