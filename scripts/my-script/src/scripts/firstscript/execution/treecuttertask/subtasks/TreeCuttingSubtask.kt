package scripts.firstscript.execution.treecuttertask.subtasks

import org.tribot.api.DynamicClicking
import org.tribot.api.General
import org.tribot.api2007.Constants
import org.tribot.api2007.Objects
import org.tribot.api2007.Player
import org.tribot.api2007.WebWalking
import org.tribot.api2007.types.RSObject
import scripts.firstscript.Subtask
import scripts.firstscript.SubtaskParams
import scripts.firstscript.SubtaskListener

class TreeCuttingSubtask (override val completionUnit: () -> Unit,
                          override val params: SubtaskParams<TreeCuttingModel>
) : Subtask(){

    private var hasTreeBeenCut = false
    override fun start(listener: SubtaskListener) {
        hasTreeBeenCut = false
        General.println("Restarted")
        super.start(listener)
        if (!params.model.area.contains(Player.getPosition())) {
            WebWalking.walkTo(params.model.area.randomTile)
        }
        General.sleep(200, 400)
        while (!hasTreeBeenCut){
            findTree()
        }
        onSubtaskCompleted()
    }

    private fun findTree() {
        val trees = Objects.findNearest(4, "Tree")
        if (trees.isNotEmpty()){
            cutTree(trees.first())
        } else General.sleep(3000, 7000)
    }
    private fun cutTree(tree: RSObject){
        DynamicClicking.clickRSObject(tree, "")
        General.sleep(2000)
        while (Player.getAnimation() != -1){
            General.sleep(200, 400)
            General.println("SLEEPING")
        }
        hasTreeBeenCut = true
    }

}