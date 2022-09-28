package com.pt.amoba.data.api

interface Response {
    fun onLoading()
    fun onSuccessful(list: ArrayList<Patients>)
    fun onError()
}