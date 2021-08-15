package scripts.firstscript

import scripts.firstscript.enums.SubtaskName
import java.util.concurrent.Executors

abstract class Task : SubtaskListener {

    abstract val listener: TaskListener
    val executor = Executors.newSingleThreadExecutor()

    lateinit var subtasks: ArrayList<Subtask>

    open fun startTask() {
        executor.execute {
            getNextSubTask()
        }
    }
    open fun stopTask() {
        executor.shutdown()
    }
    abstract fun getNextSubTask(): Subtask
}

