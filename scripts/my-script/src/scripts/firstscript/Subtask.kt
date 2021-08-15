package scripts.firstscript

import scripts.firstscript.SubtaskParams
import scripts.firstscript.SubtaskListener

abstract class Subtask{
    abstract val params: SubtaskParams<out Any>
    abstract val completionUnit: (() -> Unit)
    protected lateinit var mListener: SubtaskListener
    open fun start(listener: SubtaskListener){
        mListener = listener
    }
    open fun onSubtaskCompleted(){
        mListener.onSubtaskComplete()
    }
}