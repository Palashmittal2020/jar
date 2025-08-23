package service.models

import apiModels.ApiResponse

/**
 * Only one success else error
 *
 * @param[data] return type
 * @param[message]
 */
sealed class APIResult<T>(
    val data: ApiResponse<T>?,
    val message: String
) {
    class SUCCESS<T>(
        successData: ApiResponse<T>,
    ) : APIResult<T>(data = successData, message = "")

    class ERROR<T>(
        message: String
    ) : APIResult<T>(data = null, message = message)

    fun isSuccess(): Boolean = this is SUCCESS

    fun isError(): Boolean = isSuccess().not()
}
