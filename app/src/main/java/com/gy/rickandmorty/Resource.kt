package com.gy.rickandmorty

sealed class Resource<out T> {
    data class Success<out T>(val data: T? = null) : Resource<T>()
    class NetworkError<out T>(val data: Throwable? = null) : Resource<T>()
    class Loading<out T> : Resource<T>()
}