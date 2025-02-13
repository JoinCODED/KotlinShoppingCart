package shoppingCart

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import shoppingCart.addToCart
import shoppingCart.calculateTotal
import shoppingCart.createStoreInventory

@TestMethodOrder(MethodOrderer.DisplayName::class)
class CalculateTotalTests {

    private lateinit var store: MutableMap<Int, MutableMap<String, Any>>
    private lateinit var cart: MutableMap<Int, Int>

    @BeforeEach
    fun setup() {
        store = createStoreInventory()
        cart = mutableMapOf()
    }

    @Test
    @DisplayName("1) Total is 0.0 if cart is empty")
    fun testEmptyCart() {
        val total = calculateTotal(store, cart)
        assertEquals(0.0, total, 0.0001, "Expected total to be 0.0 for an empty cart.")
    }

    @Test
    @DisplayName("2) Single item in cart calculates correctly")
    fun testSingleItem() {
        addToCart(store, cart, 1, 3)  // 3 Laptops
        // Price = 350.000 (Laptop) * 3 = 1050.000
        val total = calculateTotal(store, cart)
        assertEquals(1050.000, total, 0.0001, "Total should be price * quantity = 350.000 * 3.")
    }

    @Test
    @DisplayName("3) Multiple items in cart adds up correctly")
    fun testMultipleItems() {
        // Suppose product 1 (Laptop) = 350.000, product 2 (Smart TV) = 200.000
        addToCart(store, cart, 1, 2)  // 2 Laptops
        addToCart(store, cart, 2, 1)  // 1 Smart TV
        // total = (350.000 * 2) + (200.000 * 1) = 900.00
        val total = calculateTotal(store, cart)
        assertEquals(900.00, total, 0.0001, "Expected sum of laptops + smart TV.")
    }
}
