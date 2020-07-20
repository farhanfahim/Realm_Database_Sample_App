package com.example.realmdatabasesampleapp.Activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.realmdatabasesampleapp.Adapter.TodoAdapter
import com.example.realmdatabasesampleapp.Model.Todo
import com.example.realmdatabasesampleapp.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import io.realm.Realm
import io.realm.RealmResults
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var realm: Realm
    private  var todolist = ArrayList<Todo>()
    private lateinit var addNotes: FloatingActionButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //init realm
        Realm.init(applicationContext)
        val realm = Realm.getDefaultInstance()


        //init views
        val  todoRV = findViewById<RecyclerView>(R.id.todoRV)
        addNotes = findViewById(R.id.addNotes)

        todoRV.layoutManager = StaggeredGridLayoutManager(2, LinearLayoutManager.VERTICAL)

        addNotes.setOnClickListener {

            startActivity(Intent(this,AddNotesActivity::class.java))
        }

        getAllTodo()

        addNotes.setOnLongClickListener {



            realm.beginTransaction()
            realm.deleteAll()
            realm.commitTransaction()

            getAllTodo()

            return@setOnLongClickListener true

        }

    }


    private fun getAllTodo() {


        todolist = ArrayList()

        val results: RealmResults<Todo> = realm.where<Todo>(
            Todo::class.java
        ).findAll()


        todoRV.adapter = TodoAdapter(this, results)





    }
}