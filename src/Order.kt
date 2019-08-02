sealed class Order {
    data class Hamburger(
        val meatDoneness: MeatDoneness
    ) : Order()
}
