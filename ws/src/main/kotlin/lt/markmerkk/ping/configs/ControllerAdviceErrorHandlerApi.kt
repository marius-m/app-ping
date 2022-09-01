package lt.markmerkk.ping.configs

import org.springframework.core.annotation.Order
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestController
import javax.servlet.http.HttpServletResponse

@ControllerAdvice(annotations = [RestController::class])
@Order(1)
class RestControllerExceptionHandler {

    @ExceptionHandler(Exception::class)
    fun handleUnexpectedException(
        e: Exception?,
        servletResponse: HttpServletResponse
    ) {
        val message = e?.message ?: "Unknown error"
        servletResponse.sendError(HttpStatus.INTERNAL_SERVER_ERROR.value(), message)
    }

}