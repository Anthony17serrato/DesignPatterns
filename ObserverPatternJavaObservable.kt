import java.util.Observable
import java.util.Observer

fun main() {
    val weatherData = WeatherData()
    val currentConditionsDisplay = CurrentConditionsDisplay(weatherData)
    val currentConditionsDisplay2 = CurrentConditionsDisplay(weatherData)
    weatherData.setMeasurements(87f, 20f, 100f)
    weatherData.setMeasurements(88f, 10f, 20f)
    weatherData.setMeasurements(60f, 5f, 100f)
}

class WeatherData : Observable() {
    var temperature: Float = 0f
    var humidity: Float = 0f
    var pressure: Float = 0f
    
    
    fun measurementsChanged() {
        setChanged()
        notifyObservers()
    }
    
    fun setMeasurements(temperature: Float, humidity: Float, pressure: Float) {
        this.temperature = temperature
        this.humidity = humidity
        this.pressure = pressure
        measurementsChanged()
    }
}

class CurrentConditionsDisplay(
    val observable: Observable
) : Observer {
    private var temperature: Float = 0f
    private var humidity: Float = 0f
    
    init {
        observable.addObserver(this)
    }
    
    override fun update(observable: Observable, arg: Any?) {
        if (observable is WeatherData) {
            this.temperature = observable.temperature
        	this.humidity = observable.humidity
        	display()
        }
        
    }
    
    fun display() {
        println("Current conditions: $temperature F degrees and $humidity% humidity")
    }
}
