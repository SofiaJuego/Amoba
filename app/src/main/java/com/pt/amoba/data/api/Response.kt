package com.pt.amoba.data.api

interface Response {
    fun onLoading()
    fun onSucessfull(list: ArrayList<Patients>)
    fun onError()
}