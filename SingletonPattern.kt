// Singleton Design pattern; Kotlin translation from head first design patterns.

fun main() {
    println(SynchronizedSingleton.getSingletonInstance())
    println(EagerSingleton.instance)
    println(DoubleCheckedLockingSingleton.getSingletonInstance())
    println(KotlinSingleton)
}

// Syncronized singleton, pro: easy thread safe config, con: @Syncronized is considered expensive way to get singleton instance
class SynchronizedSingleton private constructor() {
    companion object {
        lateinit var instance: SynchronizedSingleton
        
        @Synchronized
        fun getSingletonInstance(): SynchronizedSingleton {
            if (::instance.isInitialized.not()) {
                instance = SynchronizedSingleton()
            }
            return instance
        }
    }
}

// Eagerly created singleton, pro: easy thread safe config, con: eagerly creates singleton when the class is loaded
class EagerSingleton private constructor() {
    companion object {
        val instance: EagerSingleton = EagerSingleton()
    }
}

// DoubleCheckedLockingSingleton singleton, pro: low cost of instance creation, con: more involved config
class DoubleCheckedLockingSingleton private constructor() {
    companion object {
        @Volatile
        lateinit var instance: DoubleCheckedLockingSingleton
        
       
        fun getSingletonInstance(): DoubleCheckedLockingSingleton {
            if (::instance.isInitialized.not()) {
                synchronized(DoubleCheckedLockingSingleton::class.java) {
                    if (::instance.isInitialized.not()) {
                    	instance = DoubleCheckedLockingSingleton()
                    }
                }
            }
            return instance
        }
    }
}

// Kotlin pro: Language construct, concise, lazy initialization
object KotlinSingleton