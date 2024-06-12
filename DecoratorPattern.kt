
fun main() {
    Whip(Mocha(Espresso())).let {
        println("${it.description} $${it.cost()}")
    }
}

abstract class Beverage(open val description: String = "Unknown Beverage") {
    
    abstract fun cost() : Double
}

abstract class CondimentDecorator() : Beverage() {
    abstract override val description: String
}

class Espresso : Beverage(description = "Espresso") {
    override fun cost() : Double {
        return 1.99
    }
}

class HouseBlend : Beverage(description = "House Blend Coffee") {
    override fun cost() : Double {
        return 0.89
    }
}

class Mocha(
    val beverage: Beverage,
    override val description: String = beverage.description + ", Mocha"
) : CondimentDecorator() {
    
    override fun cost() : Double {
        return .20 + beverage.cost()
    } 
}

class Whip(
    val beverage: Beverage,
    override val description: String = beverage.description + ", Whip"
) : CondimentDecorator() {
    
    override fun cost() : Double {
        return .10 + beverage.cost()
    } 
}