package shoppingCart

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import shoppingCart.addToCart
import shoppingCart.createStoreInventory
import shoppingCart.removeFromCart

@TestMethodOrder(MethodOrderer.DisplayName::class)
class RemoveFromCartTests {

    private lateinit var store: MutableMap<Int, MutableMap<String, Any>>
    private lateinit var cart: MutableMap<Int, Int>

    @BeforeEach
    fun setup() {
        // Each test starts with a fresh store and cart
        store = createStoreInventory()
        cart = mutableMapOf()

        // Put something in the cart for easier testing
        addToCart(store, cart, 1, 5) // e.g., 5 Laptops
    }

    @Test
    @DisplayName("1) Removing a valid quantity updates cart and stock")
    fun testRemoveValidQuantity() {
        val success = removeFromCart(store, cart, 1, 2)
        assertTrue(success, "Expected removeFromCart to succeed when removing 2 from cart.")
        assertEquals(3, cart[1], "Cart should now have 3 items of product 1.")

        val stock = store[1]?.get("stock") as? Int
        assertEquals(7, stock, "Stock should be restored by 2 (initially 10, minus 5 was 5, now back up to 7).")
    }

    @Test
    @DisplayName("2) Removing the full amount removes item from cart")
    fun testRemoveAllItems() {
        val success = removeFromCart(store, cart, 1, 5)
        assertTrue(success, "Expected removeFromCart to succeed when removing all 5 items.")
        assertFalse(cart.containsKey(1), "Cart should no longer contain product 1 after removing all.")

        val stock = store[1]?.get("stock") as? Int
        assertEquals(10, stock, "Stock should be fully restored (10) if we remove all 5 from cart.")
    }

    @Test
    @DisplayName("3) Removing more than in cart should fail")
    fun testRemoveExceedingCartQuantity() {
        val success = removeFromCart(store, cart, 1, 10)
        assertFalse(success, "Expected removeFromCart to fail when removing more than in cart (5).")

        assertEquals(5, cart[1], "Cart quantity should remain unchanged.")
        val stock = store[1]?.get("stock") as? Int
        assertEquals(5, stock, "Stock should remain unchanged after failed removal.")
    }

    @Test
    @DisplayName("4) Removing a product that isn't in the cart should fail")
    fun testRemoveNonExistentInCart() {
        val success = removeFromCart(store, cart, 2, 1) // Product 2 not in cart
        assertFalse(success, "Expected removeFromCart to fail for a product not in the cart.")
    }
}
