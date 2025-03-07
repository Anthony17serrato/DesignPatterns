// Factory Design pattern; Kotlin translation from head first design patterns.

fun main() {
    val nyPizzaStore = NyPizzaStore()
    nyPizzaStore.orderPizza(PizzaType.Cheese)
    nyPizzaStore.orderPizza(PizzaType.Veggie)
    val chicagoPizzaStore = ChicagoPizzaStore()
    chicagoPizzaStore.orderPizza(PizzaType.Cheese)
    chicagoPizzaStore.orderPizza(PizzaType.Veggie)
}

class NyPizzaStore : PizzaStore() {
    override fun createPizza(type: PizzaType): Pizza {
        return when (type) {
            PizzaType.Cheese -> NyStyleCheesePizza()
            PizzaType.Veggie -> NyStyleVeggiePizza()
        }
    }
}

class ChicagoPizzaStore : PizzaStore() {
    override fun createPizza(type: PizzaType): Pizza {
        return when (type) {
            PizzaType.Cheese -> ChicagoStyleCheesePizza()
            PizzaType.Veggie -> ChicagoStyleVeggiePizza()
        }
    }
}

enum class PizzaType {
    Cheese,
    Veggie
}

abstract class PizzaStore {
    fun orderPizza(type: PizzaType): Pizza {
        val pizza = createPizza(type)
        
        pizza.prepare()
        pizza.bake()
        pizza.cut()
        pizza.box()
        
        return pizza
    }
    
    abstract fun createPizza(type: PizzaType): Pizza
}

class NyStyleCheesePizza: Pizza {
    private val name = "NyStyleCheesePizza"
    override fun prepare() = println("prepare $name")
    override fun bake() = println("bake $name")
    override fun cut() = println("cut $name")
    override fun box() = println("box $name")
}

class NyStyleVeggiePizza: Pizza {
    private val name = "NyStyleVeggiePizza"
    override fun prepare() = println("prepare $name")
    override fun bake() = println("bake $name")
    override fun cut() = println("cut $name")
    override fun box() = println("box $name")
}

class ChicagoStyleCheesePizza: Pizza {
    private val name = "ChicagoStyleCheesePizza"
    override fun prepare() = println("prepare $name")
    override fun bake() = println("bake $name")
    override fun cut() = println("cut $name")
    override fun box() = println("box $name")
}

class ChicagoStyleVeggiePizza: Pizza {
    private val name = "ChicagoStyleVeggiePizza"
    override fun prepare() = println("prepare $name")
    override fun bake() = println("bake $name")
    override fun cut() = println("cut $name")
    override fun box() = println("box $name")
}

interface Pizza {
    fun prepare()
    fun bake()
    fun cut()
    fun box()
}