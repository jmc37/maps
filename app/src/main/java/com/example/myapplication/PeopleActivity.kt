package com.example.myapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class PeopleActivity : AppCompatActivity() {
    lateinit var db: FirebaseFirestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_people)
        db = Firebase.firestore
    }
    private fun getPeople(){
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview_people)
        db.collection("people")
            .get()
            .addOnSuccessListener { result ->
                for (document in result){
                    Log.d("GET PERSON", "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener{exception ->
                Log.w("GET PERSON", "Error getting documents", exception)
            }
    }
}