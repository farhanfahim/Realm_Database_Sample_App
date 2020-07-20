package com.example.realmdatabasesampleapp.Interface

import com.example.realmdatabasesampleapp.Model.Todo
import io.realm.Realm

interface TodoInterface {
    fun addTodo(realm: Realm, todo: Todo):Boolean
    fun deleteTodo(realm: Realm,id: Int):Boolean
    fun updateTodo(realm: Realm, todo: Todo):Boolean
}