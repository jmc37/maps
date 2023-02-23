package com.example.myapplication

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

class PeopleActivity : AppCompatActivity() {
    class Person {
        var firstName: String? = null
        var location: String? = null

        constructor() {}

        constructor(firstname: String, location: String) {
            this.firstName = firstname
            this.location = location
        }
    }
    lateinit var db: FirebaseFirestore

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)
        val addbutton = findViewById<Button>(R.id.people_back)
        db = Firebase.firestore
        getPeople()
    }
    fun goBack(view: View) {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
    private fun getPeople(){
        db.collection("people")
            .get()
            .addOnSuccessListener { result ->
                val peopleList = mutableListOf<Person>()
                for (document in result){
                    val person = document.toObject<Person>()
                    peopleList.add(person)
                }
                val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_people)
                recyclerView.layoutManager = LinearLayoutManager(this)
                recyclerView.adapter = people_recycler(peopleList)
            }
            .addOnFailureListener{exception ->
                Log.w("GET PERSON", "Error getting documents", exception)
            }
    }
}