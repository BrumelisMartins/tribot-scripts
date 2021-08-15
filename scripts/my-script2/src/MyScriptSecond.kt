package scripts


import org.tribot.api.General
import org.tribot.script.Script
import org.tribot.script.ScriptManifest

@ScriptManifest(name = "SecondScript", authors = ["Xpointerset"], category = "Template")
class MyScriptSecond : Script() {
    override fun run() {
        General.println("HELLO")
    }
}