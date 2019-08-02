import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.runBlocking

@kotlinx.coroutines.ExperimentalCoroutinesApi
fun main() = runBlocking(CoroutineName("main")) {
    println("Restaurant is open")
    prepareDishes(emptyList())
}

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



