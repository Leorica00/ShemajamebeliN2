package com.example.shemajn2

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatEditText
import androidx.appcompat.widget.AppCompatTextView
import androidx.appcompat.widget.LinearLayoutCompat
import com.example.shemajn2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
private val listOfEditText = mutableListOf<String>()
private lateinit var listOfAnagrams: List<List<String>>
private lateinit var binding: ActivityMainBinding
private lateinit var anagramEditText: AppCompatEditText
private lateinit var saveButton: AppCompatButton
private lateinit var outputButton: AppCompatButton
private lateinit var anagramsCountTextView: AppCompatTextView
private lateinit var linearLayoutCont: LinearLayoutCompat
override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMainBinding.inflate(layoutInflater)
    with(binding) {
        anagramEditText = anagramEt
        saveButton = saveBtn
        outputButton = outputBtn
        anagramsCountTextView = anagramCountTv
        linearLayoutCont = linearLayoutContainer
    }
    setContentView(binding.root)
    clickOnSave(anagramEditText)
    clickOnOutput()

}
private fun clickOnSave(editText: AppCompatEditText){
    saveButton.setOnClickListener {
        val enteredText: String = editText.text.toString().trim()
        if (enteredText.isNotEmpty() && !listOfEditText.contains(enteredText)) {
            listOfEditText.add(enteredText)
            anagramEditText.setText("")
        }else{
            if (enteredText.isNotEmpty()){
                showToast(this, "Enter the field")
            }else {
                showToast(this, "Such text already exist")
            }
        }
    }
}
private fun clickOnOutput(){
    outputButton.setOnClickListener {
        listOfAnagrams = groupAnagrams(listOfEditText)
        anagramsCountTextView.text = listOfAnagrams.size.toString()
        listOfAnagrams.forEach {
            createTextView(it)
        }
    }
}
private fun showToast(context: Context, text: String) {
    Toast.makeText(context, text, Toast.LENGTH_SHORT).show()
}
private fun groupAnagrams(stringList: List<String>): List<List<String>> {
    return stringList.groupBy { it.toList().sorted() }.values.toList()
}
private fun createTextView(item: List<String>){
    val newTextView = AppCompatTextView(this)
    val layoutParams = LinearLayout.LayoutParams(
        LinearLayout.LayoutParams.MATCH_PARENT,
        LinearLayout.LayoutParams.WRAP_CONTENT
    )
    layoutParams.topMargin =
        resources.getDimensionPixelSize(R.dimen.text_view_margin_top)
    with(newTextView) {
        inputType = InputType.TYPE_CLASS_NUMBER
        textSize = 20f
        id = View.generateViewId()
        this.layoutParams = layoutParams
    }
    linearLayoutCont.addView(newTextView)
    newTextView.text = item.joinToString()
}
}