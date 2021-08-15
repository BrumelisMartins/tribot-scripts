package scripts.firstscript.execution.treecuttertask.subtasks

import org.tribot.api2007.types.RSArea
import scripts.firstscript.SubtaskParams
import scripts.firstscript.enums.SubtaskName

class TreeCuttingParams(
    override val name: SubtaskName = SubtaskName.TREE_CUTTING,
    override val model: TreeCuttingModel
) :
    SubtaskParams<TreeCuttingModel>()

class TreeCuttingModel(val area: RSArea)