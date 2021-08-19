package com.aoc.core.constant


/**
 * Created by Chandra.
 **/
object NetworkConstant {

    const val CONNECTION_TIMEOUT: Long = 120
    const val WRITE_TIMEOUT: Long = 1

    const val HEADER_CONTENT_TYPE = "Content-Type"
    const val HEADER_CONTENT_TYPE_VALUE = "application/json"
    const val QUERY_API_KEY = "api_key"

    private const val API_VERSION = "3"
    const val MOVIE_ENDPOINT_PREFIX = "$API_VERSION/movie"
    const val TV_ENDPOINT_PREFIX = "$API_VERSION/tv"
    const val SEARCH_ENDPOINT_PREFIX = "$API_VERSION/search"

    const val PARSING_ERROR = "Failed to parse response."
    const val NO_CONNECTION_INTERNET = "You have no internet connection."
    const val SOMETHING_WRONG = "Something goes wrong. Please try again later."
    const val INTERNAL_ERROR = "Internal server error. Please try again later"

    const val CODE_HTTP_500 = 500
    const val CODE_NO_INTERNET = 0

    const val IMG_BASE_URL = "https://image.tmdb.org/t/p/w500"
}