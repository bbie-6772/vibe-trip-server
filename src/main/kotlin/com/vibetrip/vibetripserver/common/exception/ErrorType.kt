package com.didim.common.exception

import org.springframework.boot.logging.LogLevel
import org.springframework.http.HttpStatus

enum class ErrorType(
    val status: HttpStatus,
    val errorCode: ErrorCode,
    val message: String,
    val logLevel: LogLevel
) {
    INVALID_ACCESS_PATH(HttpStatus.BAD_REQUEST, ErrorCode.E400, "잘못된 접근 경로입니다.", LogLevel.WARN),
    REQUIRED_AUTH(HttpStatus.UNAUTHORIZED, ErrorCode.E401, "리소스에 접근하기 위한 인증이 필요합니다.", LogLevel.WARN),
    FAILED_AUTH(HttpStatus.FORBIDDEN, ErrorCode.E403, "인증에 실패했습니다.", LogLevel.WARN),
    FORBIDDEN(HttpStatus.FORBIDDEN, ErrorCode.E403, "해당 리소스에 대한 권한이 없습니다.", LogLevel.WARN),
    NOT_FOUND_DATA(HttpStatus.NOT_FOUND, ErrorCode.E404, "해당 데이터를 찾을 수 없습니다.", LogLevel.WARN),
    CONFLICT(HttpStatus.CONFLICT, ErrorCode.E409, "요청이 충돌했습니다. 다시 시도해주세요.", LogLevel.WARN),
    TOO_MANY_REQUESTS(HttpStatus.TOO_MANY_REQUESTS, ErrorCode.E429, "너무 많은 요청을 보냈습니다.", LogLevel.WARN),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "알 수 없는 오류가 발생했습니다.", LogLevel.ERROR),

    FILE_GENERATE_FAIL(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "새 파일을 생성할 수 없습니다.", LogLevel.ERROR),
    FILE_NOT_FOUND(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "파일을 찾을 수 없습니다.", LogLevel.ERROR),

    IMAGE_UPLOAD_FAILED(HttpStatus.INTERNAL_SERVER_ERROR, ErrorCode.E500, "이미지 업로드에 실패했습니다.", LogLevel.ERROR)
}
