package com.pt.amoba.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.pt.amoba.data.api.Patients
import com.pt.amoba.data.api.User
import com.pt.amoba.databinding.RowPatientBinding
import com.pt.amoba.ui.fragment.PatientsFragmentDirections

class PatientsAdapter(listPatients: ArrayList<Patients>) :
    RecyclerView.Adapter<PatientsAdapter.PatientsViewHolder>() {

    private var patientsList: ArrayList<Patients> = listPatients

    fun filter(newFilteredList: ArrayList<Patients>) {
        patientsList = newFilteredList
        notifyDataSetChanged()
    }


    class PatientsViewHolder(val binding: RowPatientBinding) : RecyclerView.ViewHolder(binding.root)


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PatientsViewHolder {
        return PatientsViewHolder(
            RowPatientBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )

    }

    override fun onBindViewHolder(holder: PatientsViewHolder, position: Int) {
        val data = patientsList[position]

        val nameComplete = "${data.name} ${data.surname}"
        holder.binding.namePatients.text = nameComplete

        holder.binding.imageView.setOnClickListener {
            
        }

        holder.binding.imageProfile.text = nameComplete.filter { it.isUpperCase() }

        holder.binding.root.setOnClickListener {

            val action = PatientsFragmentDirections.actionPatientsFragmentToDetailsFragment(data)
            Navigation.findNavController(it).navigate(action)
        }
    }

    override fun getItemCount(): Int {
        return patientsList.size
    }
}