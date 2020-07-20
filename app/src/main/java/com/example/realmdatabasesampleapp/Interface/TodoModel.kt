package com.example.realmdatabasesampleapp.Interface

import com.example.realmdatabasesampleapp.Model.Todo
import io.realm.Realm

class TodoModel :TodoInterface{

    override fun addTodo(realm: Realm, todo: Todo): Boolean {

        return try {

            realm.beginTransaction()
            //query to check current id from realm database
            val currentId:Number? = realm.where(Todo::class.java).max("id")

            val nextId:Int

            nextId = if (currentId == null){
                1
            }else{
                currentId.toInt()+1
            }

            todo.id = nextId

            realm.copyToRealmOrUpdate(todo)

            realm.commitTransaction()

            true
        }catch (e:Exception){
            false
        }
    }

    override fun deleteTodo(realm: Realm, int: Int): Boolean {
        return try {

            realm.beginTransaction()
            //query to delete data from realm database
            realm.where(Todo::class.java).equalTo("id",int).findFirst()?.deleteFromRealm()
            realm.commitTransaction()
            true
        }catch (e:Exception){
            false
        }
    }

    override fun updateTodo(realm: Realm, todo: Todo): Boolean {
        return try {

            true
        }catch (e:Exception){
            false
        }
    }

}