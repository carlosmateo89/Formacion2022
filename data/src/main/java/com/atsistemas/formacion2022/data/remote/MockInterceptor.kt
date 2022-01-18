package com.atsistemas.formacion2022.data.remote

import android.content.Context
import com.atsistemas.formacion2022.data.R
import okhttp3.*
import java.io.BufferedReader
import java.io.InputStreamReader

/**
 * Created by Carlos Mateo Benito on 18/1/22.
 *
 * <p>
 * Copyright (c) 2022 by Carmabs. All rights reserved.
 * </p>
 *
 * @author <a href=“mailto:apps.carmabs@gmail.com”>Carlos Mateo Benito</a>
 */

class MockInterceptor(private val context: Context) : Interceptor {


    override fun intercept(chain: Interceptor.Chain): Response {

        val response =
            Response.Builder().protocol(Protocol.HTTP_1_0).code(200).request(chain.request())
        addResponse(response)

        return response.build()
    }

    private fun addResponse(response: Response.Builder) {
        val mockJson = getMockJSONResource(R.raw.transactions,context)
        response.message(mockJson)
        response.body(ResponseBody.create(MediaType.get("application/json"),mockJson))
    }

    private fun getMockJSONResource(resource: Int, context: Context): String {
        val inputStream = context.resources.openRawResource(resource)
        val br = BufferedReader(InputStreamReader(inputStream))
        val sb = StringBuilder()
        var line: String?

        line = br.readLine()

        while (line != null) {
            sb.append(line)
            line = br.readLine()
        }
        br.close()
        return sb.toString()
    }
}