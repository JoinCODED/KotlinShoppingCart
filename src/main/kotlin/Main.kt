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
fun createStoreInventory(): MutableMap<Int, MutableMap<String, Any>>{
    val productMap: MutableMap<Int, MutableMap<String, Any>> = mutableMapOf()

    productMap[1] = mutableMapOf("name" to "Laptop", "price" to 350.0, "stock" to 10)
    productMap[2] = mutableMapOf("name" to "Smart TV", "price" to 200.0, "stock" to 5)
    productMap[3] = mutableMapOf("name" to "Headphones", "price" to 50.0, "stock" to 20)
    productMap[4] = mutableMapOf("name" to "Gaming Console", "price" to 150.0, "stock" to 8)
    productMap[5] = mutableMapOf("name" to "Wireless Mouse", "price" to 8.5, "stock" to 15)

    return productMap
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

    val product = storeInventory[productId] ?: return false
    val availableStock = product["stock"] as? Int ?: return false
    if (availableStock < quantity){
        return false
    }

    product["stock"] = availableStock - quantity

    cart[productId] = (cart[productId] ?: 0) + quantity

    return true
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

    val product = storeInventory[productId] ?: return false
    val availableStock = product["stock"] as? Int ?: return false
    val cartQuantity = cart[productId]?: return false

    if (cartQuantity >= quantity) {
        cart[productId] = cartQuantity - quantity


        product["stock"] = availableStock + quantity

        if (cart[productId] == 0) {
            cart.remove(productId)
        }

        return true
    }

    return false

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
    var total = 0.0

    for ((productId, quantity) in cart) {
        val product = storeInventory[productId]
        val price = product?.get("price") as? Double ?: continue
        total += price * quantity
    }
    return total
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

    val filteredProducts = mutableListOf<Int>()

    for ((productId, product) in storeInventory){
        val name = product["name"]as? String?: continue
        if (name.contains(keyword, ignoreCase = true)){
            filteredProducts.add(productId)
        }
    }
    return filteredProducts
}

/**
 * Entry point for the project.
 * Run the JUnit tests to see if you've implemented each function correctly.
 */
fun main() {
    println("Welcome to the Kotlin Store project!")
    println("All functions currently throw NotImplementedError.")
    println("Open each function and replace TODO(...) with your implementation.")
    println(createStoreInventory())
}
