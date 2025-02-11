package shoppingCart

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import shoppingCart.addToCart
import shoppingCart.createStoreInventory

@TestMethodOrder(MethodOrderer.DisplayName::class)
class AddToCartTests {

    private lateinit var store: MutableMap<Int, MutableMap<String, Any>>
    private lateinit var cart: MutableMap<Int, Int>

    @BeforeEach
    fun setup() {
        // Start each test with a fresh store and empty cart
        store = createStoreInventory()
        cart = mutableMapOf()
    }

    @Test
    @DisplayName("1) Valid add to cart (small quantity within stock)")
    fun testAddToCartValid() {
        val success = addToCart(store, cart, 1, 2)  // e.g., 2 Laptops
        assertTrue(success, "Expected addToCart to succeed for a valid product and quantity.")
        assertEquals(2, cart[1], "Cart should have quantity 2 for product ID 1.")
        val newStock = store[1]?.get("stock") as? Int ?: -1
        assertEquals(8, newStock, "Expected stock of product 1 to be reduced by 2 (initially 10).")
    }

    @Test
    @DisplayName("2) Adding multiple times accumulates in cart")
    fun testAddMultipleTimes() {
        // Add 2 laptops, then 3 laptops
        addToCart(store, cart, 1, 2)
        addToCart(store, cart, 1, 3)
        assertEquals(5, cart[1], "Cart should have quantity 5 for product ID 1 after two additions.")

        val newStock = store[1]?.get("stock") as? Int ?: -1
        assertEquals(5, newStock, "Expected stock of product 1 to be reduced by 5.")
    }

    @Test
    @DisplayName("3) Attempting to add a product with ID that doesn't exist")
    fun testAddNonExistentProduct() {
        val success = addToCart(store, cart, 999, 1)
        assertFalse(success, "Expected addToCart to fail for an invalid productId (999).")
        assertTrue(cart.isEmpty(), "Cart should remain empty.")
    }

    @Test
    @DisplayName("4) Attempting to add more than available stock should fail")
    fun testAddExceedingStock() {
        // e.g., product 1 has stock 10, try adding 20
        val success = addToCart(store, cart, 1, 20)
        assertFalse(success, "Expected addToCart to fail when adding more than stock.")
        assertNull(cart[1], "Cart should not contain product 1 after a failed addition.")
        val stockAfter = store[1]?.get("stock") as? Int
        assertEquals(10, stockAfter, "Stock should remain unchanged after failed addition.")
    }
}
