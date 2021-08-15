package scripts.firstscript

import scripts.firstscript.account.Account
import scripts.firstscript.account.AccountHandler
import scripts.firstscript.account.LoginHandler
import scripts.firstscript.enums.GameState
import scripts.firstscript.enums.LoginState


class BotController : TaskListener {

    private var mIsExecuting = false
    val isExecuting get() = mIsExecuting
    private lateinit var currentTask: Task
    private lateinit var currentAccount: Account
    private var loginHandler = LoginHandler(this)

    fun start(){
        mIsExecuting = true
        if (!this::currentAccount.isInitialized) currentAccount = Interactor.findAccount()
        currentTask = Interactor.findNextTask(this, currentAccount)
        if (checkGameState() == GameState.LoggedOut) loginHandler.login(currentAccount)
        else currentTask.startTask()
    }

    fun startSpecificTask(task: Task, account: Account){
        mIsExecuting = true
        currentTask = task
        task.startTask()
    }

    fun stopCurrentTask(){
        if (this::currentTask.isInitialized) currentTask.stopTask()
    }

    override fun onTaskComplete() {
        start()
    }

    override fun onLoginComplete(loginState: LoginState) {
        when (loginState){
            LoginState.SUCCESS -> currentTask.startTask()
            LoginState.BANNED -> {
                AccountHandler.discardAccount(currentAccount)
                findNewAccountAndRetry()
            }
            LoginState.LOCKED -> TODO()
            LoginState.CONNECTION_ERROR -> TODO()
            LoginState.USER_ALREADY_ONLINE -> TODO()
            LoginState.INVALID_USER -> TODO()
        }

    }

    private fun findNewAccountAndRetry(){
        currentAccount = Interactor.findAccount()
        currentTask = Interactor.findNextTask(this, currentAccount)
        loginHandler.login(currentAccount)
    }


    private fun checkGameState(): GameState {
        return GameState.LoggedOut
    }
}