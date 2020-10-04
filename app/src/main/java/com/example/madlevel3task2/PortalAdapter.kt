package com.example.madlevel3task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel3task2.databinding.PortalReminderBinding

class PortalAdapter(private val reminders: List<Portal>, private val clickListener:
    (Portal) -> Unit) : RecyclerView.Adapter<PortalAdapter.ViewHolder>(){

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        val binding = PortalReminderBinding.bind(itemView)

        fun databind(reminder: Portal, clickListener: (Portal) -> Unit) {
            binding.tvReminder.text = reminder.portalName
            binding.tvReminderLink.text = reminder.portalLink
            itemView.setOnClickListener{
                clickListener(reminder)
            }
        }
    }

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.portal_reminder, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return reminders.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(reminders[position], clickListener)
    }



}