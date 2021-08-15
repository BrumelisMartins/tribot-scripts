package scripts.firstscript

import scripts.firstscript.enums.LoginState

interface TaskListener {
    fun onTaskComplete()
    fun onLoginComplete(loginState: LoginState)
}