package com.example.myapplication

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase


data class Person(var firstName: String, var location: String) { /*...*/ }
class MainActivity : AppCompatActivity() {
    lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addbutton = findViewById<Button>(R.id.main_add)
        val addpeoplebutton = findViewById<Button>(R.id.main_addpeople)
        db = Firebase.firestore
        val mapsFragment = MapsFragment()
        val fragmentManager = supportFragmentManager
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.add(R.id.fragmentContainerView_maps, mapsFragment)
        fragmentTransaction.commit()
    }

    fun addPerson(v: View) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView_maps) as MapsFragment
        val name = findViewById<EditText>(R.id.editTextTextPersonName)
        println(mapFragment.getLocation())
        val person = mapFragment.getLocation()?.let { Person(name.text.toString(), location = it) }
        if (person != null && name.text.toString() != "") {
            db.collection("people")
                .add(person)
                .addOnSuccessListener { documentReference ->
                    Log.d("ADD PERSON", "DocumentSnapshot added with ID ${documentReference.id}")
                }
                .addOnFailureListener { e ->
                    Log.w("ADD PERSON", "Error adding document", e)
                }
        }
        name.text.clear()
    }


    fun goToAnotherActivity(view: View) {
        val intent = Intent(this, PeopleActivity::class.java)
        startActivity(intent)
    }
}