package scripts.firstscript.execution.treecuttertask.tasks

import scripts.firstscript.Subtask
import scripts.firstscript.Task
import scripts.firstscript.TaskListener
import scripts.firstscript.enums.SubtaskName
import scripts.firstscript.execution.treecuttertask.data.GameArea
import scripts.firstscript.execution.treecuttertask.subtasks.TreeCuttingSubtask
import scripts.firstscript.execution.treecuttertask.subtasks.TreeCuttingModel
import scripts.firstscript.execution.treecuttertask.subtasks.TreeCuttingParams

class CutTreeTask(override val listener: TaskListener) : Task() {

    init {
        subtasks = arrayListOf(
            TreeCuttingSubtask(
                ::onSubtaskComplete,
                TreeCuttingParams(
                    SubtaskName.TREE_CUTTING,
                    TreeCuttingModel(GameArea.TREE_AREA.area)
                )
            )
        )
    }

    override fun startTask() {
        executor.execute {
            getNextSubTask().start(this)
        }
    }


    override fun getNextSubTask(): Subtask {
        return subtasks.find { it.params.name == SubtaskName.TREE_CUTTING }!!
    }

    override fun onSubtaskComplete() {
        startTask()
    }
}