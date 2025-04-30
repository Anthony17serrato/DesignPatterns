// Factory Design pattern; Kotlin translation from head first design patterns.

fun main() {
    val nyPizzaStore = NyPizzaStore()
    println(nyPizzaStore.orderPizza(PizzaType.Cheese).toString())
    println(nyPizzaStore.orderPizza(PizzaType.Veggie).toString())
    val chicagoPizzaStore = ChicagoPizzaStore()
    println(chicagoPizzaStore.orderPizza(PizzaType.Cheese).toString())
    println(chicagoPizzaStore.orderPizza(PizzaType.Veggie).toString())
}

class NyPizzaStore : PizzaStore() {
    override fun createPizza(type: PizzaType): Pizza {
        val pizzaIngredientFactory = NyPizzaIngredientFactory()
        return when (type) {
            PizzaType.Cheese -> CheesePizza(
                ingredientFactory = pizzaIngredientFactory,
            	name = "NyStyleCheesePizza"
            )
            PizzaType.Veggie -> VeggiePizza(
            	ingredientFactory = pizzaIngredientFactory,
            	name = "NyStyleVeggiePizza"
            )
        }
    }
}

class ChicagoPizzaStore : PizzaStore() {
    override fun createPizza(type: PizzaType): Pizza {
        val pizzaIngredientFactory = ChicagoPizzaIngredientFactory()
        return when (type) {
            PizzaType.Cheese -> CheesePizza(
                ingredientFactory = pizzaIngredientFactory,
            	name = "ChicagoStyleCheesePizza"
            )
            PizzaType.Veggie -> VeggiePizza(
                ingredientFactory = pizzaIngredientFactory,
            	name = "ChicagoStyleVeggiePizza"
            )
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

class CheesePizza(
    private val ingredientFactory: PizzaIngredientFactory,
	override val name: String
): Pizza() {
    override fun prepare() {
        println("prepare $name")
        dough = ingredientFactory.createDough()
        sauce = ingredientFactory.createSauce()
        cheese = ingredientFactory.createCheese()
    }
}

class VeggiePizza(
    private val ingredientFactory: PizzaIngredientFactory,
	override val name: String
): Pizza() {
    override fun prepare() {
        println("prepare $name")
        dough = ingredientFactory.createDough()
        sauce = ingredientFactory.createSauce()
        cheese = ingredientFactory.createCheese()
        veggies = ingredientFactory.createVeggies()
    }
}

abstract class Pizza {
    abstract val name: String
    
    var dough: Dough? = null
    var sauce: Sauce? = null
    var veggies: List<Veggies> = emptyList()
    var cheese: Cheese? = null
    var pepperoni: Pepperoni? = null
    var clams: Clams? = null
    
    abstract fun prepare()
    
    fun bake() {
        println("bake $name")
    }
    fun cut() {
        println("cut $name")
    }
    fun box() {
        println("box $name")
    }
    
    override fun toString() = "prepared pizza with ingredients: " +
    	"dough $dough, sauce $sauce, veggies $veggies, cheese $cheese, pepperoni $pepperoni, clams $clams"
}

interface PizzaIngredientFactory {
    fun createDough(): Dough
    fun createSauce(): Sauce
    fun createCheese(): Cheese
    fun createVeggies(): List<Veggies>
    fun createPepperoni(): Pepperoni
    fun createClams(): Clams
}

class NyPizzaIngredientFactory: PizzaIngredientFactory {
    override fun createDough() = ThinCrustDough
    override fun createSauce() = MarinaraSauce
    override fun createCheese() = ReggianoCheese
    override fun createVeggies() = listOf(Garlic, Onion, Mushroom, RedPepper)
    override fun createPepperoni() = SlicedPepperoni
    override fun createClams() = FreshClams
}

class ChicagoPizzaIngredientFactory: PizzaIngredientFactory {
    override fun createDough() = ThickCrustDough
    override fun createSauce() = PlumTomatoSauce
    override fun createCheese() = Mozzarella
    override fun createVeggies() = listOf(BlackOlives, Spinach, EggPlant, RedPepper)
    override fun createPepperoni() = OversizedPepperoni
    override fun createClams() = FrozenClams
}

interface Dough
interface Sauce
interface Veggies
interface Cheese
interface Pepperoni
interface Clams
// Available ingredients
data object ThinCrustDough: Dough
data object ThickCrustDough: Dough
data object MarinaraSauce: Sauce
data object PlumTomatoSauce: Sauce
data object ReggianoCheese: Cheese
data object Mozzarella: Cheese
data object Garlic: Veggies
data object Onion: Veggies
data object Mushroom: Veggies
data object RedPepper: Veggies
data object BlackOlives: Veggies
data object Spinach: Veggies
data object EggPlant: Veggies
data object SlicedPepperoni: Pepperoni
data object OversizedPepperoni: Pepperoni
data object FreshClams: Clams
data object FrozenClams: Clams