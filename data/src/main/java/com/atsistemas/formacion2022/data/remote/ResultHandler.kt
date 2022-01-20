package com.atsistemas.formacion2022.data.remote

/**
 * Created by Carlos Mateo Benito on 20/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
sealed class ResultHandler<out T> {
    class Success<T>(val data: T) : ResultHandler<T>()
    class NetworkError : ResultHandler<Nothing>()
    class HttpError(val code: Int, val message: String) : ResultHandler<Nothing>()
    class GenericError(val message: String) : ResultHandler<Nothing>()
}