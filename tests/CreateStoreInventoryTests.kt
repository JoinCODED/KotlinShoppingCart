package shoppingCart

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import shoppingCart.createStoreInventory

@TestMethodOrder(MethodOrderer.DisplayName::class)
class CreateStoreInventoryTests {

    // We'll assume these are the exact products required:
    // (ID -> (name, price, stock))
    private val expectedProducts = mapOf(
        1 to Triple("Laptop", 350.000, 10),
        2 to Triple("Smart TV", 200.000, 5),
        3 to Triple("Headphones", 50.000, 20),
        4 to Triple("Gaming Console", 150.000, 8),
        5 to Triple("Wireless Mouse", 8.500, 15)
    )

    @Test
    @DisplayName("1) Inventory is not empty")
    fun testInventoryNotEmpty() {
        val store = createStoreInventory()
        assertTrue(store.isNotEmpty(), "Expected createStoreInventory() to return a non-empty map.")
    }

    @Test
    @DisplayName("2) Inventory has exactly 5 items")
    fun testInventorySize() {
        val store = createStoreInventory()
        assertEquals(
            5, store.size,
            "Expected exactly 5 products in the inventory, found ${store.size}."
        )
    }

    @Test
    @DisplayName("3) Contains required product IDs (1..5)")
    fun testContainsRequiredIds() {
        val store = createStoreInventory()

        for (id in expectedProducts.keys) {
            assertTrue(
                store.containsKey(id),
                "Expected product with ID $id in the inventory, but it's missing."
            )
        }
    }

    @Test
    @DisplayName("4) No extra product IDs beyond the required five")
    fun testNoUnexpectedIds() {
        val store = createStoreInventory()
        val unexpectedIds = store.keys - expectedProducts.keys
        assertTrue(
            unexpectedIds.isEmpty(),
            "Found unexpected product IDs: $unexpectedIds. Only IDs ${expectedProducts.keys} should exist."
        )
    }

    @Test
    @DisplayName("5) Each product has the correct name, price, and stock")
    fun testProductsHaveCorrectAttributes() {
        val store = createStoreInventory()

        expectedProducts.forEach { (id, triple) ->
            val (expectedName, expectedPrice, expectedStock) = triple

            val productData = store[id]
                ?: fail("Product ID $id is missing from the map.")

            // Check for required keys
            assertTrue(productData.containsKey("name"), "Product $id is missing key \"name\".")
            assertTrue(productData.containsKey("price"), "Product $id is missing key \"price\".")
            assertTrue(productData.containsKey("stock"), "Product $id is missing key \"stock\".")

            // Cast and compare
            val actualName = productData["name"] as? String
                ?: fail("Product $id has invalid or null name.")
            val actualPrice = productData["price"] as? Double
                ?: fail("Product $id has invalid or null price.")
            val actualStock = productData["stock"] as? Int
                ?: fail("Product $id has invalid or null stock.")

            assertEquals(expectedName, actualName, "Name mismatch for product $id.")
            assertEquals(expectedPrice, actualPrice, 0.0001, "Price mismatch for product $id.")
            assertEquals(expectedStock, actualStock, "Stock mismatch for product $id.")
        }
    }
}
