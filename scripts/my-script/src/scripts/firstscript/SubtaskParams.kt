package scripts.firstscript

import scripts.firstscript.enums.SubtaskName

abstract class SubtaskParams<SubtaskModel> {
    abstract val name: SubtaskName
    abstract val model: SubtaskModel
}