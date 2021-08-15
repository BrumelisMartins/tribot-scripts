package scripts.firstscript.account

import org.tribot.api.General
import org.tribot.api.input.Mouse
import org.tribot.api2007.Camera
import org.tribot.api2007.ChooseOption
import org.tribot.api2007.Login
import org.tribot.api2007.Player
import scripts.firstscript.TaskListener
import scripts.firstscript.enums.LoginState
import kotlin.random.Random

class LoginHandler(private val listener: TaskListener) {
    private val loginTimeout = 60000L

    fun login(account: Account){
        Login.setLoginTimeout(loginTimeout)
        val login = Login.login(account.username, account.password)
        if (login) {
            General.sleep(1000, 4000)
            val angle = Camera.getOptimalAngleForPositionable(Player.getPosition())
            Camera.setCamera(Random.nextInt(0, 240), angle)
            listener.onLoginComplete(LoginState.SUCCESS)
        } else {
            val loginMessage = Login.getLoginMessage().toLoginState()
            if (loginMessage == LoginState.BANNED || loginMessage == LoginState.LOCKED) clickBack()
            listener.onLoginComplete(loginMessage)
        }
    }

    private fun clickBack(){
        Mouse.clickBox(312, 309, 452, 342, 1)
        General.sleep(100, 500)
    }
}

fun Login.LOGIN_MESSAGE.toLoginState(): LoginState{
    return when (this){
        Login.LOGIN_MESSAGE.LOCKED -> LoginState.LOCKED
        Login.LOGIN_MESSAGE.DISCONNECTED -> LoginState.CONNECTION_ERROR
        Login.LOGIN_MESSAGE.INVALID -> LoginState.INVALID_USER
        Login.LOGIN_MESSAGE.TOO_MANY_ATTEMPTS -> LoginState.CONNECTION_ERROR
        Login.LOGIN_MESSAGE.LOGIN_LIMIT_EXCEEDED -> LoginState.CONNECTION_ERROR
        Login.LOGIN_MESSAGE.BANNED -> LoginState.BANNED
        Login.LOGIN_MESSAGE.ALREADY_LOGGED_IN -> LoginState.USER_ALREADY_ONLINE
        Login.LOGIN_MESSAGE.SERVER_OFFLINE -> LoginState.CONNECTION_ERROR
        Login.LOGIN_MESSAGE.ERROR_CONNECTING -> LoginState.CONNECTION_ERROR
        else -> LoginState.CONNECTION_ERROR
    }
}