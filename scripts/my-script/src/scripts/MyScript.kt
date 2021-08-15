package scripts

import org.tribot.api.General
import org.tribot.script.Script
import org.tribot.script.ScriptManifest
import scripts.firstscript.BotController

@ScriptManifest(name = "FirstScript", authors = ["Xpointerset"], category = "Template")
class MyScript : Script() {
    override fun run() {
        General.println("HELLO")
        val bot = BotController()
        bot.start()
        while(bot.isExecuting){
            Thread.sleep(10000)
            if (!bot.isExecuting)  break
        }
    }
}
