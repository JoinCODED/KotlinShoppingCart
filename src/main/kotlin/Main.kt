package shoppingCart

/**
 * Returns a map of productId -> MutableMap("name" to String, "price" to Double, "stock" to Int).
 * Your task: Add exactly 5 items with correct details (IDs 1..5).
 * Example:
 *   1 -> { "name"="Laptop", "price"=350.000, "stock"=10 },
 *   2 -> { "name"="Smart TV", "price"=200.000, "stock"=5 },
 *   3 -> { "name"="Headphones", "price"=50.000, "stock"=20 },
 *   4 -> { "name"="Gaming Console", "price"=150.000, "stock"=8 },
 *   5 -> { "name"="Wireless Mouse", "price"=8.500, "stock"=15 },
 */

fun createStoreInventory(): MutableMap<Int, MutableMap<String, Any>> {
    val inventory =  mutableMapOf(
        1 to mutableMapOf(
            "name" to "Laptop",
            "price" to 350.000 as Any,
            "stock" to 10 as Any
        ),
        2 to mutableMapOf(
            "name" to "Smart TV",
            "price" to 200.000 as Any,
            "stock" to 5 as Any
        ),
        3 to mutableMapOf(
            "name" to "Headphones",
            "price" to 50.000 as Any,
            "stock" to 20 as Any
        ),
        4 to mutableMapOf(
            "name" to "Gaming Console",
            "price" to 150.000 as Any,
            "stock" to 8 as Any
        ),
        5 to mutableMapOf(
            "name" to "Wireless Mouse",
            "price" to 8.500 as Any,
            "stock" to 15 as Any
        )
    )

    return inventory

}


// Output: {name=[Laptop, Tablet, Phone], price=350.0, stock=10}

/**
 * Tries to add [quantity] of [productId] to [cart], if there's enough stock in [storeInventory].
 * - If valid, reduce the stock and increase the cart quantity for this product.
 * - Return true if successful, false otherwise.
 */
fun addToCart(
    storeInventory: MutableMap<Int, MutableMap<String, Any>>,
    cart: MutableMap<Int, Int>,
    productId: Int,
    quantity: Int):
        Boolean {
    val item = storeInventory[productId]

    if (item != null) {

        val currentStock = item["stock"] as Int

        if(currentStock >= quantity && currentStock != 0 ) {
            cart[productId] = cart.getOrDefault(productId, 0) + quantity
            item["stock"] = currentStock - quantity
            return true
        } else {
            return false
        }
    } else {
        return false
    }

}

/**
 * Removes [quantity] of [productId] from [cart], restoring the same amount to [storeInventory].
 * - If cart has enough of this product, remove it and restore stock.
 * - Return true if successful, false otherwise.
 */
fun removeFromCart(
    storeInventory: MutableMap<Int, MutableMap<String, Any>>,
    cart: MutableMap<Int, Int>,
    productId: Int,
    quantity: Int
): Boolean {
    val item = storeInventory[productId]

    if (item != null && cart.containsKey(productId)) {
        val cartQuantity = cart[productId]!!

        if (cartQuantity > quantity) {
            cart[productId] = cartQuantity - quantity
            item["stock"] = (item["stock"] as Int) + quantity
            return true
        } else if (cartQuantity == quantity) {
            cart.remove(productId)
            item["stock"] = (item["stock"] as Int) + quantity
            return true
        } else {
            return false
        }
    } else {
        return false
    }
}


/**
 * Calculates the total cost of items in [cart].
 * - For each (productId -> quantity), retrieve the price from [storeInventory].
 * - Multiply (price * quantity) and sum for the final total.
 */
fun calculateTotal(
    storeInventory: MutableMap<Int, MutableMap<String, Any>>,
    cart: MutableMap<Int, Int>
): Double {
    var totalCost = 0.0
    if (cart.isEmpty()) {
        return 0.0
    } else {
        for ((productId, quantity) in cart) {
            val item = storeInventory[productId]
            if (item != null) {
                val price = item["price"] as Double
                totalCost = totalCost + (price * quantity)
            }
        }
        return totalCost
    }

}

/**
 * Returns a list of product IDs whose "name" contains [keyword].
 * - For example, if keyword = "lap", then "Laptop" (ID 1) should match.
 * - Matching is case-insensitive.
 */
fun filterProductsByName(
    storeInventory: MutableMap<Int, MutableMap<String, Any>>,
    keyword: String
): List<Int> {
    val productIDs = mutableListOf<Int>()

    for ((productId, item) in storeInventory) {
        val name = item["name"] as String
        if (name.lowercase().contains(keyword.lowercase())) {
            productIDs.add(productId)
        }
    }
    return productIDs
}

fun main() {
    val inventory = createStoreInventory()
    val cart: MutableMap<Int, Int> = mutableMapOf()

    println("-----------\nTask 1\n-----------")
    println(inventory)

    println("-----------\nTask 2\n-----------")
    println("Cart before adding: $cart")
    println("Inventory before adding: $inventory")

    // Add items to the cart
    addToCart(inventory, cart, 3, 10)
    addToCart(inventory, cart, 1, 6)

    println("Cart after adding: $cart")
    println("Inventory after adding: $inventory")

    // Now calculate the total after adding items
    val total: Double = calculateTotal(inventory, cart)
    println("-----------\nTask 4\n-----------")
    println("Cart total amount: $total")

    println("-----------\nTask 3\n-----------")
    // Removing items from the cart
    removeFromCart(inventory, cart, 3, 6)
    removeFromCart(inventory, cart, 1, 10)

    println("Cart after removal: $cart")
    println("Inventory after removal: $inventory")

    println("-----------\nTask 5\n-----------")
    val searchResult = filterProductsByName(inventory, "lap")
    println("Matching Product IDs: $searchResult")
}
