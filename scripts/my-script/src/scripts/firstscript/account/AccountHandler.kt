package scripts.firstscript.account

object AccountHandler {
    /*
    * Account handler is made to contact repository and get accounts
    * */
    val accounts = arrayListOf(
        Account("hurblekurble2@mail.com", "kkkoookkk", AccountStatus.COMPLETED),
        Account("bottester1234@gmail.com", "skjiivis22", AccountStatus.COMPLETED)
    )

    fun getAccount(status: AccountStatus): Account? {
        // Returns null if account of that status is not available
        return accounts.first()
    }

    fun discardAccount(account: Account) {
        accounts.removeIf { it.username == account.username }
    }

    fun changeAccountStatus(account: Account, status: AccountStatus) {

    }

    fun getAllAccounts(status: AccountStatus): List<Account> {
        return listOf()
    }
}