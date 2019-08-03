import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.channels.ReceiveChannel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

@kotlinx.coroutines.ExperimentalCoroutinesApi
fun main() = runBlocking(CoroutineName("main")) {
    println("Restaurant is open")
    val orders = getOrders()

    val ordersChannel = Channel<Order>()

    launch {
        for (order in orders) {
            ordersChannel.send(order)
        }
        ordersChannel.close()
    }

    //Each cook is a coroutine
    val cook1 = launch { prepareDishes("Cook 1", ordersChannel) }
    val cook2 = launch { prepareDishes("Cook 2", ordersChannel) }
}

fun getOrders() = listOf<Order>(
    Order.Hamburger(MeatDoneness.RARE),
    Order.Hamburger(MeatDoneness.MEDIUM),
    Order.Hamburger(MeatDoneness.WELL_DONE)
)

/*
Cooks work concurrently, orders aren't repeated

Restaurant is open
Cook 1 is preparing RARE Hamburger Order
Cook 2 is preparing MEDIUM Hamburger Order
Cook 1 is serving RARE Hamburger
Cook 1 is preparing WELL_DONE Hamburger Order
Cook 2 is serving MEDIUM Hamburger
Cook 1 is serving WELL_DONE Hamburger
 */

suspend fun prepareDishes(cook: String, ordersChannel: ReceiveChannel<Order>) {
    for (order in ordersChannel) {
        println("$cook is preparing $order")

        when (order) {
            is Order.Hamburger -> {
                val meat = grillMeat(order.meatDoneness)
                val bread = getBread()
                val toppings = getToppings()
                val hamburger = makeHamburger(order, meat, bread, toppings)
                println("$cook is serving $hamburger")
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



