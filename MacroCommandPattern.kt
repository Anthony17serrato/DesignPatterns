// Command Design pattern; Kotlin translation from head first design patterns.
fun main() {
    val remoteControl = RemoteControl()
    
    val livingRoomLight = Light("Living Room")
    val kitchenLight = Light("Kitchen")
    val ceilingFan = CeilingFan("Living Room")
    val garageDoor = GarageDoor("Home")
    val stereo = Stereo("Living Room")
    
    val livingRoomLightOn = LightOnCommand(livingRoomLight)
    val livingRoomLightOff = LightOffCommand(livingRoomLight)
    val kitchenLightOn = LightOnCommand(livingRoomLight)
    val kitchenLightOff = LightOffCommand(livingRoomLight)
    val ceilingFanOn = CeilingFanOnSetHighCommand(ceilingFan)
    val ceilingFanOff = CeilingFanOffCommand(ceilingFan)
    val garageDoorOpen = GarageDoorOpenCommand(garageDoor)
    val garageDoorClose = GarageDoorCloseCommand(garageDoor)
    val stereoOnWithCd = StereoOnWithCdCommand(stereo)
    val stereoOff = StereoOffCommand(stereo)
    val partyOn = listOf(livingRoomLightOn, ceilingFanOn, stereoOnWithCd)
    val partyOnMacro = MacroCommand(partyOn)
    val partyOff = listOf(livingRoomLightOff, ceilingFanOff, stereoOff)
    val partyOffMacro = MacroCommand(partyOff)
    
    remoteControl.setCommand(0, livingRoomLightOn, livingRoomLightOff)
    remoteControl.setCommand(1, kitchenLightOn, kitchenLightOff)
    remoteControl.setCommand(2, ceilingFanOn, ceilingFanOff)
    remoteControl.setCommand(3, garageDoorOpen, garageDoorClose)
    remoteControl.setCommand(4, stereoOnWithCd, stereoOff)
    remoteContral.setCommand(5, partyOnMacro, partyOffMacro)
    
    println(remoteControl)
    
    remoteControl.onButtonWasPushed(0)
    remoteControl.offButtonWasPushed(0)
    remoteControl.onButtonWasPushed(1)
    remoteControl.offButtonWasPushed(1)
    remoteControl.onButtonWasPushed(2)
    remoteControl.offButtonWasPushed(2)
    remoteControl.onButtonWasPushed(3)
    remoteControl.offButtonWasPushed(3)
    remoteControl.onButtonWasPushed(4)
    remoteControl.offButtonWasPushed(4)
    remoteControl.onButtonWasPushed(5)
    remoteControl.offButtonWasPushed(5)
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

class MacroCommand(private val commands: List<Command>) {
    override fun excecute() {
        commands.forEach { command ->
            command.execute()
        }
    }
}


