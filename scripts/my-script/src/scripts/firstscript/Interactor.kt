package scripts.firstscript

import scripts.firstscript.account.Account
import scripts.firstscript.account.AccountHandler
import scripts.firstscript.account.AccountStatus
import scripts.firstscript.execution.treecuttertask.tasks.CutTreeTask

object Interactor {
    /*
    * Interactors purpose is to find the next task for the bot, if necessary contact the db to check if some mule is required
    * or check how many active accounts are available
    * */

    fun findAccount(): Account{
        return AccountHandler.getAccount(AccountStatus.COMPLETED)!!
    }

    fun findNextTask(listener: TaskListener, account: Account): Task{
        return CutTreeTask(listener)
    }
}