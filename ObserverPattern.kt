fun main() {
    val weatherData = WeatherData()
    val currentConditionsDisplay = CurrentConditionsDisplay(weatherData)
    val currentConditionsDisplay2 = CurrentConditionsDisplay(weatherData)
    weatherData.setMeasurements(87f, 20f, 100f)
    weatherData.setMeasurements(88f, 10f, 20f)
    weatherData.setMeasurements(60f, 5f, 100f)
}

interface Subject {
    fun registerObserver(observer: Observer)
    fun removeObserver(observer: Observer)
    fun notifyObservers()
}

interface Observer {
    fun update(temperature: Float, humidity: Float, pressure: Float)
}

class WeatherData : Subject {
    private val observers = mutableListOf<Observer>()
    private var temperature: Float = 0f
    private var humidity: Float = 0f
    private var pressure: Float = 0f
    
    override fun registerObserver(observer: Observer) {
        observers.add(observer)
    }
    
    override fun removeObserver(observer: Observer) {
        observers.remove(observer)
    }
    
    override fun notifyObservers() {
        observers.forEach { observer ->
            observer.update(temperature, humidity, pressure)
        }
    }
    
    fun measurementsChanged() {
        notifyObservers()
    }
    
    fun setMeasurements(temperature: Float, humidity: Float, pressure: Float) {
        this.temperature = temperature
        this.humidity = humidity
        this.pressure = pressure
        measurementsChanged()
    }
}

class CurrentConditionsDisplay(val weatherData: Subject) : Observer {
    private var temperature: Float = 0f
    private var humidity: Float = 0f
    
    init {
        weatherData.registerObserver(this)
    }
    
    override fun update(temperature: Float, humidity: Float, pressure: Float) {
        this.temperature = temperature
        this.humidity = humidity
        display()
    }
    
    fun display() {
        println("Current conditions: $temperature F degrees and $humidity% humidity")
    }
}
