// Adapter Design pattern; Kotlin translation from head first design patterns.
fun main() {
    val mallardDuck = MallardDuck()
    val wildTurkey = WildTurkey()
    val turkeyDuckAdapter = TurkeyAdapter(turkey = wildTurkey)
    invokeDuckApis(mallardDuck)
    invokeDuckApis(turkeyDuckAdapter)
}

private fun invokeDuckApis(duck: Duck) {
    duck.quack()
    duck.fly()
}

interface Duck {
    fun quack()
    fun fly()
}

class MallardDuck: Duck {
    override fun quack() {
        println("Quack")
    }
    
    override fun fly() {
        println("I'm flying")
    }
}

interface Turkey {
    fun gobble()
    fun fly()
}

class WildTurkey: Turkey {
    override fun gobble() {
        println("Gobble gobble")
    }
    override fun fly() {
        println("Im flying a short distance")
    }
}

class TurkeyAdapter(private val turkey: Turkey): Duck {
    override fun quack() {
        turkey.gobble()
    }
    
    override fun fly() {
        (1..5).forEach { _ ->
            // Spam fly to make up for turkeys lack of flying ability
            turkey.fly()
        }
    }
}