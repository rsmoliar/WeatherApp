package com.rtwotech.weather.util

data class Resource<out T>(val status: Status, val data: T?, val message: String?){

    companion object{

        fun<T> onSuccess(data: T?): Resource<T>{
            return Resource(Status.SUCCESS, data, null)
        }

        fun<T> onLoading(data: T?): Resource<T>{
            return Resource(Status.LOADING, data, null)
        }

        fun<T> onError(data: T?, message: String?) : Resource<T>{
            return Resource(Status.ERROR, data, message)
        }
    }
}