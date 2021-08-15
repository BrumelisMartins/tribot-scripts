package scripts.firstscript.execution.treecuttertask.data

import org.tribot.api2007.types.RSArea
import org.tribot.api2007.types.RSTile

enum class GameArea (val area: RSArea){
    LUMBRIDGE_CASTLE(RSArea(RSTile(3234, 3234), RSTile(3202, 3203))),
    TREE_AREA(RSArea(RSTile(3224, 3234), RSTile(3227, 3231)))
}