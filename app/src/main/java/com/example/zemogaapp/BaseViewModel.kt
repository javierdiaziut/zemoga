package com.example.zemogaapp

import androidx.lifecycle.*
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.*


open class BaseViewModel : ViewModel() {

    protected suspend fun executeUseCase(function: suspend () -> Flow<Result<Any>>, channel : Channel<Result<Any>>) {
        function.invoke().collect {
            channel.send(it)
        }
    }

}