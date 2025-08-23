package service

import apiModels.ApiResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.HttpStatusCode
import service.models.APIResult

suspend inline fun <reified T> HttpClient.getRequest(endPoint: String): APIResult<T> =
    try {
        val result = get(endPoint)
        if (result.status == HttpStatusCode.OK) {
            APIResult.SUCCESS(successData = result.body<ApiResponse<T>>())
        } else {
            APIResult.ERROR(message = "Something went wrong!")
        }

    } catch (e: Exception) {
        // TODO Palash log exception
        APIResult.ERROR(message = "Something went wrong!")
    }