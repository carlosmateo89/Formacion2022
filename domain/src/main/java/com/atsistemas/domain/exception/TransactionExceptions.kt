package com.atsistemas.domain.exception

/**
 * Created by Carlos Mateo Benito on 26/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */
sealed class TransactionExceptions : Exception() {
    class NetworkError : TransactionExceptions()
    class HttpError(val code: Int, override val message: String) : TransactionExceptions()
    class GenericError(override val message: String) : TransactionExceptions()
}