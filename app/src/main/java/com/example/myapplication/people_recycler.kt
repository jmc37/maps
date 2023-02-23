package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class people_recycler(private val mList: MutableList<PeopleActivity.Person>) :
    RecyclerView.Adapter<people_recycler.ViewHolder>() {

    // Holds the views
    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val nameTextView: TextView = itemView.findViewById(R.id.person_name)
        val locationTextView: TextView = itemView.findViewById(R.id.person_location)
    }
    // create new views
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_person, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person = mList[position]
        holder.nameTextView.text = person.firstName
        holder.locationTextView.text = person.location
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

}