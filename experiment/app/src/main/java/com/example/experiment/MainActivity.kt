package com.example.experiment

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.experiment.adapter.TodoAdapter
import com.example.experiment.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.net.URLEncoder

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var recyclerView: RecyclerView
    private lateinit var selectedRadioText: String
    private var str = mutableListOf("Hii", "byee")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        val view: View = binding.root
        setContentView(view)

        val sharedPreferences = this.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
        val jsonString = sharedPreferences.getString("my_list", null)
        if (jsonString != null) {
            val type = object : TypeToken<MutableList<String>>() {}.type
            val gson = Gson()
            str = gson.fromJson(jsonString, type)
        }
        binding.OpenDialer.setOnClickListener{
            if(binding.num.text.toString() != "") {
                val u = Uri.parse("tel:" + binding.num.text.toString())
                val i = Intent(Intent.ACTION_DIAL, u)

                try {
                    startActivity(i)
                } catch (s: SecurityException) {
                    Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                Toast.makeText(this, "Enter number", Toast.LENGTH_SHORT).show()
            }
        }

        binding.OpenWtsp.setOnClickListener{
            if(binding.num.text.toString() != "") {
                val u = Uri.parse("https://api.whatsapp.com/send?phone=" + binding.num.text.toString() + "&text=" + URLEncoder.encode(
                    Companion.selectedRadioText, "UTF-8"))
                try {
                    val i = Intent(Intent.ACTION_VIEW, u)
                    startActivity(i)
                } catch (s: SecurityException) {
                    Toast.makeText(this, "An error occurred", Toast.LENGTH_LONG)
                        .show()
                }
            } else {
                Toast.makeText(this, "Enter number", Toast.LENGTH_SHORT).show()
            }
        }
        recyclerView = binding.todoList
        binding.addTodo.setOnClickListener{
            if(binding.wtspText.text.toString() != "") {
                str.add(binding.wtspText.text.toString())
                binding.wtspText.text.clear()
                recyclerView.adapter = TodoAdapter(str)
            } else {
                Toast.makeText(this, "Enter text", Toast.LENGTH_SHORT).show()
            }
        }

        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = TodoAdapter(str)
    }
    companion object {
        private var selectedRadioText: String? = null

        @JvmStatic
        fun selectText(radioText: String?) {
            if (radioText != null) {
                selectedRadioText = radioText
            }
        }

        @JvmStatic
        fun saveList(context: Context?, list : MutableList<String>) {
            val sharedPreferences = context?.getSharedPreferences("my_prefs", Context.MODE_PRIVATE)
            val gson = Gson()
            val jsonString = gson.toJson(list)
            sharedPreferences?.edit()?.putString("my_list", jsonString)?.apply()
        }
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        if (id == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}