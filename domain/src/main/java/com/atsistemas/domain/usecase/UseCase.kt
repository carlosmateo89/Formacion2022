package com.atsistemas.domain.usecase

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext


abstract class UseCase<I, O> {

    protected abstract suspend fun executeUseCase(input: I): O

    suspend fun execute(input: I): O {
        return withContext(dispatcher) {
            executeUseCase(input)
        }
    }

    fun executeSyncInCurrentThread(input: I): O {
        return runBlocking {
            executeUseCase(input)
        }
    }

    fun executeSyncInDispatcher(input: I): O {
        return runBlocking(dispatcher) {
            executeUseCase(input)
        }
    }

    open val dispatcher = Dispatchers.IO
}