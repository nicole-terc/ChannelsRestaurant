sealed class Dish {
    data class Hamburguer(val order: Order.Hamburger, val meat: Meat, val bread: Bread, val toppings: Toppings) {
        override fun toString() = "${meat.meatDoneness.name} Hamburger"
    }
}