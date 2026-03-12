package com.vibetrip.vibetripserver.support.response

import com.didim.common.exception.ErrorMessage
import com.didim.common.exception.ErrorType

data class ApiResponse<T>(
    val resultType: ResultType,
    val data: T?,
    val error: ErrorMessage?
) {

    companion object {
        fun success(): ApiResponse<Unit> {
            return ApiResponse(ResultType.SUCCESS, null, null)
        }

        fun <S> success(data: S): ApiResponse<S> {
            return ApiResponse(ResultType.SUCCESS, data, null)
        }

        fun error(error: ErrorType, errorData: Any?): ApiResponse<Unit> {
            return ApiResponse(ResultType.ERROR, null, ErrorMessage(error, errorData))
        }

        fun <S> error(error: ErrorType): ApiResponse<S> {
            return ApiResponse(ResultType.ERROR, null, ErrorMessage(error, null))
        }
    }
}