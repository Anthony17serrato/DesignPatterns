// Facade Design pattern; Kotlin translation from head first design patterns.
fun main() {
    val amplifier = Amplifier()
    val tuner = Tuner()
    val dvdPlayer = DvdPlayer()
    val cdPlayer = CdPlayer()
    val projector = Projector()
    val lights = TheaterLights()
    val screen = Screen()
    val popper = PopcornPopper()

    val homeTheater = HomeTheaterFacade(
        amplifier, tuner, dvdPlayer, cdPlayer, projector, lights, screen, popper
    )

    homeTheater.watchMovie("Inception")
    println("\n--- Movie Ended ---\n")
    homeTheater.endMovie()
}

class HomeTheaterFacade(
    private val amplifier: Amplifier,
    private val tuner: Tuner,
    private val dvdPlayer: DvdPlayer,
    private val cdPlayer: CdPlayer,
    private val projector: Projector,
    private val lights: TheaterLights,
    private val screen: Screen,
    private val popper: PopcornPopper
) {
    fun watchMovie(movie: String) {
        println("Get ready to watch a movie...")
        popper.on()
        popper.pop()
        lights.dim(10)
        screen.down()
        projector.on()
        projector.wideScreenMode()
        amplifier.on()
        amplifier.setDvd(dvdPlayer)
        amplifier.setSurroundSound()
        amplifier.setVolume(15)
        dvdPlayer.on()
        dvdPlayer.play(movie)
    }

    fun endMovie() {
        println("Shutting movie theater down...")
        popper.off()
        lights.on()
        screen.up()
        projector.off()
        amplifier.off()
        dvdPlayer.stop()
        dvdPlayer.eject()
        dvdPlayer.off()
    }
}

class Amplifier {
    private var dvdPlayer: DvdPlayer? = null

    fun on() {
        println("Top-O-Line Amplifier on")
    }

    fun off() {
        println("Top-O-Line Amplifier off")
    }

    fun setDvd(dvd: DvdPlayer) {
        dvdPlayer = dvd
        println("Top-O-Line Amplifier setting DVD player to Top-O-Line DVD Player")
    }

    fun setSurroundSound() {
        println("Top-O-Line Amplifier surround sound on (5 speakers, 1 subwoofer)")
    }

    fun setVolume(level: Int) {
        println("Top-O-Line Amplifier setting volume to $level")
    }
}

class DvdPlayer {
    fun on() {
        println("Top-O-Line DVD Player on")
    }

    fun off() {
        println("Top-O-Line DVD Player off")
    }

    fun play(movie: String) {
        println("Top-O-Line DVD Player playing \"$movie\"")
    }

    fun stop() {
        println("Top-O-Line DVD Player stopped")
    }

    fun eject() {
        println("Top-O-Line DVD Player eject")
    }
}

class Tuner {
    fun on() {
        println("Top-O-Line AM/FM Tuner on")
    }

    fun off() {
        println("Top-O-Line AM/FM Tuner off")
    }
}

class CdPlayer {
    fun on() {
        println("Top-O-Line CD Player on")
    }

    fun off() {
        println("Top-O-Line CD Player off")
    }
}

class Projector {
    fun on() {
        println("Top-O-Line Projector on")
    }

    fun off() {
        println("Top-O-Line Projector off")
    }

    fun wideScreenMode() {
        println("Top-O-Line Projector in widescreen mode (16x9 aspect ratio)")
    }
}

class TheaterLights {
    fun on() {
        println("Theater Ceiling Lights on")
    }

    fun dim(level: Int) {
        println("Theater Ceiling Lights dimming to $level%")
    }
}

class Screen {
    fun down() {
        println("Theater Screen going down")
    }

    fun up() {
        println("Theater Screen going up")
    }
}

class PopcornPopper {
    fun on() {
        println("Popcorn Popper on")
    }

    fun off() {
        println("Popcorn Popper off")
    }

    fun pop() {
        println("Popcorn Popper popping popcorn!")
    }
}