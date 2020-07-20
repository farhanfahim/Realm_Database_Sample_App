package com.example.realmdatabasesampleapp.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.realmdatabasesampleapp.Interface.TodoModel
import com.example.realmdatabasesampleapp.Model.Todo
import com.example.realmdatabasesampleapp.R
import io.realm.Realm

class AddNotesActivity : AppCompatActivity() {
    private lateinit var titleET: EditText
    private lateinit var descET:EditText
    private lateinit var saveBtn: Button
    private lateinit var realm: Realm
    private lateinit var helper: TodoModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_notes)


        realm = Realm.getDefaultInstance()

        //init views

        titleET = findViewById(R.id.titleET)
        descET = findViewById(R.id.descET)
        saveBtn = findViewById(R.id.saveBtn)

        saveBtn.setOnClickListener {

            saveData()
        }

    }

    private fun saveData() {


        try {


            helper = TodoModel()
            val task = Todo()
            task.title = titleET.text.toString()
            task.description = descET.text.toString()

            //saving to Database
            helper.addTodo(realm,task)
            Toast.makeText(this,"Success",Toast.LENGTH_SHORT).show()

            startActivity(Intent(this, MainActivity::class.java))
            finish()



        } catch (e:Exception){

            Toast.makeText(this,"Failure", Toast.LENGTH_SHORT).show()

        }

    }

}