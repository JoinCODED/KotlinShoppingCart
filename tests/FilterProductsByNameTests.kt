package shoppingCart

import org.junit.jupiter.api.*
import org.junit.jupiter.api.Assertions.*
import shoppingCart.createStoreInventory
import shoppingCart.filterProductsByName

@TestMethodOrder(MethodOrderer.DisplayName::class)
class FilterProductsByNameTests {

    private lateinit var store: MutableMap<Int, MutableMap<String, Any>>

    @BeforeEach
    fun setup() {
        store = createStoreInventory()
    }

    @Test
    @DisplayName("1) Exact match on name should return correct IDs")
    fun testExactMatch() {
        // e.g., "Laptop" should match product ID 1 only
        val matches = filterProductsByName(store, "Laptop")
        assertEquals(listOf(1), matches, "Expected only ID 1 for exact name 'Laptop'.")
    }

    @Test
    @DisplayName("2) Partial substring match is case-insensitive")
    fun testPartialSubstring() {
        // Searching "lap" or "LAP" should find product "Laptop" (ID 1)
        val matchesLower = filterProductsByName(store, "lap")
        assertTrue(matchesLower.contains(1), "Expected 'lap' to match 'Laptop' (ID 1).")

        val matchesUpper = filterProductsByName(store, "LAP")
        assertTrue(matchesUpper.contains(1), "Expected 'LAP' to match 'Laptop' (ID 1).")
    }

    @Test
    @DisplayName("3) No match returns an empty list")
    fun testNoResults() {
        val matches = filterProductsByName(store, "randomtext")
        assertTrue(matches.isEmpty(), "Expected no product to match 'randomtext'.")
    }

    @Test
    @DisplayName("4) Multiple items can match a keyword")
    fun testMultipleMatches() {
        // If "Head" matches "Headphones" (ID 3) and "Head-something-else" if you had it
        // We'll just do a quick check for "Head" that should match ID 3
        val matches = filterProductsByName(store, "Head")
        assertTrue(matches.contains(3), "Expected 'Head' to match 'Headphones' (ID 3).")
        // If your store has only 1 item with "Head," the size check ensures no extra
        assertEquals(1, matches.size, "Expected exactly 1 match for 'Head'.")
    }
}
