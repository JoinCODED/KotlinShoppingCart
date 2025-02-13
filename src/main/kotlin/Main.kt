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
    val listOfInnerMap: MutableList<MutableMap<String, Any>> = mutableListOf(
        mutableMapOf("name" to "Laptop", "price" to 350.000, "stock" to 10),
        mutableMapOf("name" to "Smart TV", "price" to 200.000, "stock" to 5),
        mutableMapOf("name" to "Headphones", "price" to 50.000, "stock" to 20),
        mutableMapOf("name" to "Gaming Console", "price" to 150.000, "stock" to 8),
        mutableMapOf("name" to "Wireless Mouse", "price" to 8.500, "stock" to 15))

    var storeInventory: MutableMap<Int, MutableMap<String, Any>> = mutableMapOf()

    var index = 1
    for(list in listOfInnerMap) {
        storeInventory[index] = list
        index++
    }

    return storeInventory
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
    if(storeInventory.contains(productId)) {
        if(storeInventory[productId]!!.get("stock") as Int >= quantity) {
            var newStock = storeInventory[productId]!!.get("stock") as Int - quantity
            storeInventory[productId]!!.set("stock", newStock)
            if(cart.contains(productId)){
                cart[productId] = quantity + cart[productId] as Int
            } else {
                cart[productId] = quantity
            }
            return true
        }
    }
    return  false
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
    if (cart.contains(productId)) {
        if (cart.get(productId) as Int >= quantity) {
            var newStock = storeInventory[productId]!!.get("stock") as Int + quantity
            storeInventory[productId]!!.set("stock", newStock)
            var newCart = cart.get(productId) as Int - quantity
            if (newCart == 0) {
                cart.remove(productId)
                if (cart.isEmpty()) {
                    return true
                } else {
                    return false
                }
            } else {
                cart.set(productId, newCart)
            }
            return true
        }
    }
    return  false
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
    var totalPrice: Double = 0.0
    var list: MutableList<Int> = mutableListOf()
    for (item in cart) {
        list.add(item.value)
    }

    var prices: MutableList<Double> = mutableListOf()

    for (item in storeInventory) {
        prices.add(item.value.get("price") as Double)
    }

    var index = 0
    for (quantity in list) {
        totalPrice = totalPrice + (quantity * prices[index])
        index++
    }
    return totalPrice
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
    val productIdFound: MutableList<Int> = mutableListOf()
    for ((productId: Int, product: MutableMap<String, Any>) in storeInventory) {
        if (product.containsKey("name")) {
            if (product["name"].toString().lowercase().contains(keyword.lowercase())){
                productIdFound.add(productId)
            }
        }
    }
    return productIdFound
}

/**
 * Entry point for the project.
 * Run the JUnit tests to see if you've implemented each function correctly.
 */
fun main() {
    val storeInventory = createStoreInventory()
    val cart: MutableMap<Int, Int> = mutableMapOf(1 to 0, 2 to 0, 3 to 0)
    if (addToCart(storeInventory, cart, 1, 3)) {
        println("Sold!!")
    }
    println("Welcome to the Kotlin Store project!")
    println("All functions currently throw NotImplementedError.")
    println("Open each function and replace TODO(...) with your implementation.")
}
