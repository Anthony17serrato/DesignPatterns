fun main() {
    val mallard = MallardDuck()
    println("> A mallard has been created")
    mallard.performQuack()
    mallard.performFly()
    println("> The mallard has been captured by a human and had it's wings trimmed")
    mallard.flyBehavior = FlyNoWay()
    mallard.performFly()
}

public class MallardDuck(
	quackBehavior: QuackBehavior = Quack(),
    flyBehavior: FlyBehavior = FlyWithWings()                           
) : Duck(flyBehavior, quackBehavior)

abstract class Duck(
    var flyBehavior: FlyBehavior,
    var quackBehavior: QuackBehavior
) {
    fun performFly() {
        flyBehavior.fly()
    }
    
    fun performQuack() {
        quackBehavior.quack()
    }
    
    fun swim() {
        println("All ducks float")
    }
}

interface FlyBehavior {
    fun fly()
}
   
class FlyWithWings : FlyBehavior {
    override fun fly() {
        println("Im flying!")
    }
}

class FlyNoWay : FlyBehavior {
    override fun fly() {
        println("I can't fly")
    }
}

interface QuackBehavior {
    fun quack()
}

class Quack : QuackBehavior {
    override fun quack() {
        println("Quack")
    }
}

class MuteQuack : QuackBehavior {
    override fun quack() {
        println("<<Silence>>")
    }
}

class Squeak : QuackBehavior {
    override fun quack() {
        println("Squeak")
    }
}