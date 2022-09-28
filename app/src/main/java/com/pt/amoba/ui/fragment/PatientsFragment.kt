package com.pt.amoba.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.pt.amoba.R
import com.pt.amoba.adapter.PatientsAdapter
import com.pt.amoba.data.api.Patients
import com.pt.amoba.data.api.Response
import com.pt.amoba.data.viewmodel.ViewModel
import com.pt.amoba.databinding.FragmentPatientsListBinding

class PatientsFragment : BaseFragment() , Response {

    private lateinit var binding: FragmentPatientsListBinding
    private val viewModel: ViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = FragmentPatientsListBinding.inflate(layoutInflater, container, false)

        viewModel.getAllPatients(this)

        binding.bottomLogout.setOnClickListener {
            showAlertDialogLogout()
        }
        return binding.root
    }

    override fun onLoading() {
        showLoadingCase()
    }

    override fun onSuccessful(list: ArrayList<Patients>) {
        configListPatients(list)
    }

    override fun onError() {
        showViews()
    }

    private fun configListPatients(list: ArrayList<Patients>) {
        showViews()
        val adapter = PatientsAdapter(list)
        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            val floatingButton = binding.bottomLogout
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy > 0 && floatingButton.visibility == View.VISIBLE) {
                    floatingButton.hide()
                } else if (dy < 0 && floatingButton.visibility != View.VISIBLE)
                {
                    floatingButton.show()
                }
            }
        })
    }

    private fun showLoadingCase() {
        binding.apply {
            progressBarPatiens.visibility = View.VISIBLE
            recyclerView.visibility = View.INVISIBLE
        }
    }

    private fun showViews() {
        binding.apply {
            progressBarPatiens.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        }

    }
}