package lt.markmerkk.ping.firebase

import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.ResponseStatus

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
class FBUserNotFound(val errorAsString: String): Exception(errorAsString)

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
class FBUserCannotCreate(val errorAsString: String): Exception(errorAsString)

@ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
class FBUserCannotDelete(val errorAsString: String): Exception(errorAsString)

