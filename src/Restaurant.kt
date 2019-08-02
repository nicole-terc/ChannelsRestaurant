import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.delay
import kotlinx.coroutines.runBlocking

@kotlinx.coroutines.ExperimentalCoroutinesApi
fun main() = runBlocking(CoroutineName("main")) {
    println("Restaurant is open")
    prepareDishes(getOrders())
}

fun getOrders() = listOf<Order>(
    Order.Hamburger(MeatDoneness.RARE),
    Order.Hamburger(MeatDoneness.MEDIUM),
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

suspend fun prepareDishes(orders: List<Order>) {
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

suspend fun grillMeat(doneness: MeatDoneness): Meat {
    when (doneness) {
        MeatDoneness.RARE -> delay(100)
        MeatDoneness.MEDIUM -> delay(200)
        MeatDoneness.WELL_DONE -> delay(300)
    }
    return Meat(doneness)
}

suspend fun getBread(): Bread {
    delay(50)
    return Bread()
}

suspend fun getToppings(): Toppings {
    delay(50)
    return Toppings()
}

suspend fun makeHamburger(order: Order.Hamburger, meat: Meat, bread: Bread, toppings: Toppings): Dish.Hamburguer {
    delay(10)
    return Dish.Hamburguer(order, meat, bread, toppings)
}



