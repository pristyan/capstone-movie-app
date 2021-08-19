package com.aoc.core.network

import com.aoc.core.BuildConfig
import com.aoc.core.constant.NetworkConstant
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject


/**
 * Created by Chandra.
 **/
class NetworkInterceptor @Inject constructor(): Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {
        val originRequest = chain.request()

        val urlBuilder = originRequest.url.newBuilder()
        urlBuilder.addQueryParameter(NetworkConstant.QUERY_API_KEY, BuildConfig.API_KEY)

        val requestBuilder = originRequest.newBuilder()
        requestBuilder.url(urlBuilder.build())
        requestBuilder.header(
            NetworkConstant.HEADER_CONTENT_TYPE, NetworkConstant.HEADER_CONTENT_TYPE_VALUE
        )

        return chain.proceed(requestBuilder.build())
    }
}