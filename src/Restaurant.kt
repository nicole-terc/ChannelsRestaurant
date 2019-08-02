import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.runBlocking
import kotlin.system.measureTimeMillis

@kotlinx.coroutines.ExperimentalCoroutinesApi
fun main() = runBlocking(CoroutineName("main")) {
    println("Restaurant is open")
    prepareDishes(getOrders())
}

fun getOrders() = listOf<Order>(
    Order.Hamburger(MeatDoneness.RARE),
    Order.Hamburger(MeatDoneness.MEDUIM),
    Order.Hamburger(MeatDoneness.WELL_DONE)
)

/*
All steps are executed one after the other
prints:

Restaurant is open
Serve RARE Hamburger
Serve MEDIUM Hamburger
Serve WELL_DONE Hamburger
 */

fun prepareDishes(orders: List<Order>) {
    for (order in orders) {
        when (order) {
            is Order.Hamburger -> {
                val meat = grillMeat(order.meatDoneness)
                val bread = getBread()
                val toppings = getToppings()
                val hamburger = makeHamburger(order, meat, bread, toppings)
                println("Serve $hamburger")
            }
        }
    }
}

fun grillMeat(doneness: MeatDoneness): Meat {
    return Meat(doneness)
}

fun getBread(): Bread {
    return Bread()
}

fun getToppings(): Toppings {
    return Toppings()
}

fun makeHamburger(order: Order.Hamburger, meat: Meat, bread: Bread, toppings: Toppings): Dish.Hamburguer {
    return Dish.Hamburguer(order, meat, bread, toppings)
}



