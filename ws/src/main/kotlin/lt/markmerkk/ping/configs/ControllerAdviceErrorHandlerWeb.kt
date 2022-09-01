package lt.markmerkk.ping.configs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.MessageSource
import org.springframework.core.annotation.Order
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import java.util.Locale
import javax.annotation.PostConstruct

@ControllerAdvice(annotations = [Controller::class])
@Order(2)
class ControllerExceptionHandler {

    @Autowired
    lateinit var messageSource: MessageSource

    @PostConstruct
    fun onCreate() {
    }

    @ExceptionHandler(Exception::class)
    fun handleUnexpectedException(
        locale: Locale,
        model: Model,
        e: Exception?,
    ): String {
        // val statusCode = request.getAttribute("javax.servlet.error.status_code") as Int
        val exceptionMessage = e?.message ?: "Unknown"
        model.addAttribute("errorMessage", exceptionMessage)
        return "error"
    }
}