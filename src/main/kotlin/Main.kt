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
    val product1 : MutableMap<String, Any> = mutableMapOf("name" to "Laptop", "price" to 350.000, "stock" to 10 );
    val product2 : MutableMap<String, Any> = mutableMapOf("name" to "Smart TV", "price" to 200.000, "stock" to 5);
    val product3 : MutableMap<String, Any> = mutableMapOf("name"  to "Headphones", "price" to 50.000, "stock" to 20);
    val product4 : MutableMap<String, Any> = mutableMapOf("name" to "Gaming Console", "price" to 150.000, "stock" to 8);
    val product5 : MutableMap<String, Any> = mutableMapOf("name" to "Wireless Mouse", "price" to 8.500, "stock" to 15);

    val storeInventory = mutableMapOf(
        1 to product1,
        2 to product2,
        3 to product3,
        4 to product4,
        5 to product5,
    )
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


    val storeQuantity = storeInventory[productId]?.get("stock")

    if (storeInventory.containsKey(productId)) {
        if (storeQuantity.toString().toInt() != null && storeQuantity.toString().toInt() >= quantity) {
            var updatedStoreQuantity = storeQuantity.toString().toInt()
            updatedStoreQuantity = updatedStoreQuantity - quantity

            storeInventory[productId]?.put("stock", updatedStoreQuantity)

            //cart updated
            var updatedCartQuantity = cart[productId]?: 0
            updatedCartQuantity = updatedCartQuantity + quantity

            cart.put(productId, updatedCartQuantity)

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

    val currentQuantityInCart = cart[productId] ?: return false

    if (currentQuantityInCart < quantity) {
        return false
    }

    cart[productId] = currentQuantityInCart - quantity

    val currentStock = storeInventory[productId]?.get("stock").toString().toInt()
    storeInventory[productId]?.set("stock", currentStock + quantity)

    if (cart[productId] == 0) {
        cart.remove(productId)
    }

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

    var totalPrice = 0.0

    if (cart.isEmpty())
    {
        totalPrice = 0.0

        return  totalPrice
    }
    else
    {
            for ((productId, quantity) in cart) {
                val itemPrice = storeInventory[productId]?.get("price").toString().toDouble()
                totalPrice +=  itemPrice.toString().toDouble() * quantity
            }

            return totalPrice

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

    val matchingProductIds = mutableListOf<Int>()

    val lowerCaseKeyword = keyword.lowercase()
    
    for ((productId, productDetails) in storeInventory) {

        val productName = productDetails["name"].toString()
        if (productName.lowercase().contains(lowerCaseKeyword)) {
            matchingProductIds.add(productId)
        }
    }

    return matchingProductIds
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
