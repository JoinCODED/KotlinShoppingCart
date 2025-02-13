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
    val  itemsMap:  MutableMap<Int, MutableMap<String, Any>> = mutableMapOf(
        1 to mutableMapOf("name" to "Laptop", "price" to 350.000, "stock" to 10),
        2 to mutableMapOf("name" to "Smart TV", "price" to 200.000, "stock" to 5),
        3 to mutableMapOf("name" to "Headphones", "price" to 50.000, "stock" to 20),
        4 to mutableMapOf("name" to "Gaming Console", "price" to 150.000, "stock" to 8),
        5 to mutableMapOf("name" to "Wireless Mouse", "price" to 8.500, "stock" to 15),
        )
    return itemsMap
}


fun addToCart(
    storeInventory: MutableMap<Int, MutableMap<String, Any>>,
    cart: MutableMap<Int, Int>,
    productId: Int,
    quantity: Int
): Boolean {
    val isValidStockType: Boolean = storeInventory[productId]?.get("stock") !is Int
    if (isValidStockType ) {
         return false
    }

    val currentStock: Int = storeInventory[productId]?.get("stock") as Int;
    if (currentStock < quantity) {
        return false
    }

    storeInventory[productId]?.set("stock", currentStock - quantity)

    if (cart.containsKey(productId)) {
        cart[productId] = quantity + cart[productId] as Int
    } else {
        cart[productId] = quantity
    }


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
    if (cart[productId] == null) return false;

    val cartAmount: Int = cart[productId] as Int;
    if (cartAmount < quantity) return false;

    cart[productId] = cartAmount - quantity;

    if (cart[productId] == 0) {
        cart.remove(productId)
    }

    val newInventoryCount: Int = (storeInventory[productId]?.get("stock") as Int) + quantity;
    storeInventory[productId]?.set("stock", newInventoryCount)


    return true
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
    var total: Double = 0.00

    for  ( itemId in cart.keys) {
        val productPrice: Double = storeInventory[itemId]?.get("price") as Double;
        val productAmount: Int = cart[itemId]as Int;
        total += productPrice * productAmount //total = total + (productPrice * productAmount)
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

    val productIdList = mutableListOf<Int>()

    for ((id: Int, item: MutableMap<String, Any>) in storeInventory) {
        if (item.containsKey("name") && (item["name"] is String)) {
            val productIncludesName: Boolean = item["name"].toString().lowercase().contains(keyword.lowercase())
            if (productIncludesName){
                productIdList.add(id)
            }
        }

    }
    return productIdList


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
