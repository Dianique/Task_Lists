package com.example.tasklists
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        //  supportFragmentManager?.beginTransaction()?.replace(R.id.nav_container, FragmentOne())?.commit()
        // fragmentManager?.beginTransaction()?.replace(R.id.nav_graph, FragmentOne()).commit()
    }
}