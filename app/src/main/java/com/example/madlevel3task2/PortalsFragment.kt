package com.example.madlevel3task2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_portals.*
import kotlinx.android.synthetic.main.portal_reminder.view.*

class PortalsFragment : Fragment() {

    private val portals = arrayListOf<Portal>()
    private val portalAdapter = PortalAdapter(portals, { portal : Portal -> reminderClicked(portal)})

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun databind(reminder: Portal) {
            itemView.tvReminder.text = reminder.portalName
            itemView.tvReminderLink.text = reminder.portalLink
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_portals, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        observeAddReminderResult()
    }

    private fun initViews(){
        rvPortals.layoutManager = GridLayoutManager(
            context,
            2,
            GridLayoutManager.VERTICAL,
            false
        )
        rvPortals.adapter = portalAdapter
    }

    private fun observeAddReminderResult() {
        setFragmentResultListener(REQ_REMINDER_KEY) { key, bundle ->
            bundle.getParcelable<Portal>(BUNDLE_REMINDER_KEY)?.let {
                val reminder = Portal(it.portalName, it.portalLink)
                portals.add(reminder)
                portalAdapter.notifyDataSetChanged()

            } ?: Log.e("PortalFragment", "Request triggered, but empty portal text!")

        }
    }

    private fun reminderClicked(reminderItem: Portal){
        Toast.makeText(context, "Clicked: ${reminderItem.portalName}", Toast.LENGTH_LONG).show()
    }
}