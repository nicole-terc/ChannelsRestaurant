sealed class Order {
    data class Hamburger(
        val meatDoneness: MeatDoneness
    ) : Order() {
        override fun toString() = "${meatDoneness.name} Hamburger Order"
    }
}
