package com.example.tasklists

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.tasklists.adapter.ItemAdapter
import com.example.tasklists.model.ToDo


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val recyclerView = findViewById<RecyclerView>(R.id.recycler_view) //Find resource ID for recyclerView in display view.

        val typeList = mutableListOf<ToDo>() //Initialize the list itself.

        val adapter =
            ItemAdapter(typeList)  //Pass in list to ItemAdapter and assign it to adapter variable.

        recyclerView.adapter = adapter //Bind the adapter to recyclerview to fill listed items.

        recyclerView.layoutManager =
            LinearLayoutManager(this) //The layoutManager positions the items correctly.

        recyclerView.setHasFixedSize(true)
        //Makes for better run performance when you set recyclerView to fixed size knowing content changes won't change
        //layout size


        findViewById<Button>(R.id.button2).setOnClickListener {
            val userInput = findViewById<EditText>(R.id.user_lists).text.toString() //Implement user inputted TO-Do items.
            Log.i("MainActivity", "data type")

         //   val photoView = findViewById<ImageView>(R.id.photo)

            typeList.add(ToDo(userInput))
            adapter.notifyDataSetChanged()

            //  Log.i("MainActivity", "displayCheck")
        }
        //  findViewById<CheckBox>(R.id.check_mark).setOnClickListener {
        //    val checker = findViewById<CheckBox>(R.id.check_mark).isChecked


    //}

    }
}

// findViewById<CheckBox>(R.id.check_mark).setOnClickListener {
// val checkBox = findViewById<CheckBox>(R.id.check_mark).isChecked
// typeList.add(ToDo(checkBox, userInput))
//
// adapter.notifyDataSetChanged() /*