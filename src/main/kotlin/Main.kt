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
//    TODO("Implement createStoreInventory()")
    val inventory: MutableMap<Int, MutableMap<String, Any>> = mutableMapOf(
        1 to mutableMapOf("name" to "Laptop", "price" to 350.000, "stock" to 10),
        2 to mutableMapOf("name" to "Smart TV", "price" to 200.000, "stock" to 5),
        3 to mutableMapOf("name" to "Headphones", "price" to 50.000, "stock" to 20),
        4 to mutableMapOf("name" to "Gaming Console", "price" to 150.000, "stock" to 8),
        5 to mutableMapOf("name" to "Wireless Mouse", "price" to 8.500, "stock" to 15)
    )

    return inventory
}

/**
 * Tries to add [quantity] of [productId] to [cart], if there's enough stock in [storeInventory].
 * - If valid, reduce the stock and increase the cart quantity for this product.
 * - Return true if successful, false otherwise.
 */
fun addToCart(
    storeInventory: MutableMap<Int, MutableMap<String, Any>>,
    cart: MutableMap<Int, Int>,
    productId: Int,
    quantity: Int
): Boolean {
//    TODO("Implement addToCart()")
    val isInInventory: Boolean = storeInventory.containsKey(productId)
    if (isInInventory) {
        val stock: Int = storeInventory.getValue(productId)["stock"] as Int
        val isInCart: Boolean = cart.containsKey(productId)
        if (stock >= quantity) {
            if (isInCart) {
                val stockInCart: Int = cart.getValue(productId)
                cart[productId] = stockInCart + quantity
            } else {
                cart[productId] = quantity
            }
            storeInventory[productId]?.set("stock", stock - quantity)
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
//    TODO("Implement removeFromCart()")
    val isInInventory: Boolean = storeInventory.containsKey(productId)
    if (isInInventory) {
        val stock: Int = storeInventory.getValue(productId)["stock"] as Int
        val isInCart: Boolean = cart.containsKey(productId)
        if (stock >= quantity) {
            if (isInCart) {
                val stockInCart: Int = cart.getValue(productId)
                if (stockInCart - quantity == 0) {
                    cart.remove(productId)
                } else {
                    cart[productId] = stockInCart - quantity
                }

            } else {
                return false
            }
            storeInventory[productId]?.set("stock", stock + quantity)
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
//    TODO("Implement calculateTotal()")
    var totalCost: Double = 0.0
    cart.forEach {
        totalCost += (storeInventory[it.key]?.get("price") as Double * it.value.toDouble())
    }
    return totalCost
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
//    TODO("Implement filterProductsByName()")

    val productList: MutableList<Int> = mutableListOf()

    storeInventory.forEach {
        if (it.value["name"].toString().lowercase().contains(keyword.lowercase())) {
            println(it.value["name"].toString())
            productList.add(it.key)
        }
    }

    return productList
}

/**
 * Entry point for the project.
 * Run the JUnit tests to see if you've implemented each function correctly.
 */
fun main() {
    println("Welcome to the Kotlin Store project!")
    println("All functions currently throw NotImplementedError.")
    println("Open each function and replace TODO(...) with your implementation.")
}
