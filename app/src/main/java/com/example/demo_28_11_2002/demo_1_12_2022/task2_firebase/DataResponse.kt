package com.example.demo_28_11_2002.demo_1_12_2022.task2_firebase

data class DataResponse<out T>(
    val resStatus: ApiStatus,
    val data: T?,
    val message: String?
) {
    companion object {
        fun <T> success(data: T): DataResponse<T> {
          return  DataResponse(resStatus = ApiStatus.SUCCESS, data = data, message = null)
        }


        fun <T> error(data: T?,message: String?): DataResponse<T> =
            DataResponse(resStatus = ApiStatus.ERROR, data = data, message = message)

        fun <T> loading(data: T?): DataResponse<T> =
            DataResponse(resStatus = ApiStatus.LOADING, data = data, message = null)
    }
}