// Command Design pattern; Kotlin translation from head first design patterns.
fun main() {
    val remoteControl = RemoteControl()
    
    val livingRoomLight = Light("Living Room")
    val kitchenLight = Light("Kitchen")
    val ceilingFan = CeilingFan("Living Room")
    val garageDoor = GarageDoor("")
    val stereo = Stereo("Living Room")
    
    println(remoteControl)
}

interface Command {
    fun execute()
}

class RemoteControl {
    val onCommands = MutableList<Command>(7) { NoCommand() }
    val offCommands = MutableList<Command>(7) { NoCommand() }
    
    fun setCommand(slot: Int, onCommand: Command, offCommand: Command) {
        onCommands[slot] = onCommand
        offCommands[slot] = offCommand
    }
    
    fun onButtonWasPushed(slot: Int) {
        onCommands.getOrNull(slot)?.execute() ?: println("Invalid slot")
    }
    
    fun offButtonWasPushed(slot: Int) {
        offCommands.getOrNull(slot)?.execute() ?: println("Invalid slot")
    }
    
    override fun toString(): String {
        val stringBuff = StringBuffer()
        stringBuff.append("\n------Remote Control --------\n")
        onCommands.forEachIndexed { index, _ ->
            stringBuff.append("[slot $index] ${onCommands[index]::class?.simpleName} ${offCommands[index]::class?.simpleName}\n")
        }
        stringBuff.append("\n")
        return stringBuff.toString()
    }
}

class NoCommand(): Command {
    override fun execute() {
        println("No command to execute")
    }
}

class Light(val name: String) {
    fun lightOn() {
        println("$name light is on")
    }
    
    fun lightOff() {
        println("$name light is off")
    }
}

class LightOnCommand(val light: Light): Command {
    override fun execute() {
        light.lightOn()
    }
}

class LightOffCommand(val light: Light): Command {
    override fun execute() {
        light.lightOff()
    }
}

class CeilingFan(val name: String) {
    fun setHigh() {
        println("$name ceiling fan is on high")
    }
    
    fun setOff() {
        println("$name ceiling fan is off")
    }
}

class CeilingFanOnSetHighCommand(val ceilingFan: CeilingFan): Command {
    override fun execute() {
        ceilingFan.setHigh()
    }
}

class CeilingFanOffCommand(val ceilingFan: CeilingFan): Command {
    override fun execute() {
        ceilingFan.setOff()
    }
}

class GarageDoor(val name: String) {
    fun garageDoorOpen() {
        println("$name Garage Door is open")
    }
    
    fun garageDoorClose() {
        println("$name Garage Door is closed")
    }
}

class GarageDoorOpenCommand(val garageDoor: GarageDoor): Command {
    override fun execute() {
        garageDoor.garageDoorOpen()
    }
}

class GarageDoorCloseCommand(val garageDoor: GarageDoor): Command {
    override fun execute() {
        garageDoor.garageDoorClose()
    }
}

class Stereo(val name: String) {
    fun on() {
        println("$name Stereo is on")
    }
    
    fun setCd() {
        println("Cd is set")
    }
    
    fun setVolume(level: Int) {
        println("Volume is set to $level")
    }
    
    fun off() {
        println("Stero is off")
    }
}

class StereoOnWithCdCommand(val stereo: Stereo): Command {
    override fun execute() {
        stereo.on()
        stereo.setCd()
        stereo.setVolume(11)
    }
}

class StereoOffCommand(val stereo: Stereo): Command {
    override fun execute() {
        stereo.off()
    }
}