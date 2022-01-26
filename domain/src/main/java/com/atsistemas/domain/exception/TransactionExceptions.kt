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
sealed class TransactionExceptions {
    class NetworkError : TransactionExceptions()
    class HttpError(val code: Int, val message: String) : TransactionExceptions()
    class GenericError(val message: String) : TransactionExceptions()
}