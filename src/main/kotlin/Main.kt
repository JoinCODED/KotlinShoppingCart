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

    val storeInventory: MutableMap<Int,  MutableMap<String, Any>> = mutableMapOf()


    fun addToStoreInventory(key:Int, name:String, price:Double, stock:Int){
        val product:MutableMap<String, Any> = mutableMapOf()
        product["name"] = name
        product["price"] = price
        product["stock"] = stock
        storeInventory[key] = product
    }
    addToStoreInventory(1, "Laptop", 350.000, 10)
    addToStoreInventory(2, "Smart TV", 200.000, 5)
    addToStoreInventory(3, "Headphones", 50.000, 20)
    addToStoreInventory(4, "Gaming Console", 150.000, 8)
    addToStoreInventory(5, "Wireless Mouse", 8.500, 15)

    return  storeInventory
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
    // get product first if not found return false
    val product = storeInventory[productId] ?: return false
    // get store quantity
    val storeProductQuantity = product["stock"] as Int
    // if more than what is already ava return false
    if(storeProductQuantity < quantity) return false

    // here we should add to the cart and then update the store and then return true

    val productQuantityInCart = cart[productId]
    if(productQuantityInCart == null){
        cart[productId] = quantity
    }else{
        cart[productId] = productQuantityInCart + quantity
    }

    // remove from store
    storeInventory[productId]?.set("stock", storeProductQuantity - quantity)
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

    // get cart quantity
    val cartProductQuantity = cart[productId] ?: return false

    // if more than what is already ava return false
    if(cartProductQuantity < quantity) return false

    // here we should remove from the cart and then update the store and then return true

    val productQuantityInCart = cart[productId] ?: return false

    val newCartQuantity = productQuantityInCart  - quantity
    if(newCartQuantity == 0){
        cart.remove(productId)
    }
    else{
        cart[productId] = newCartQuantity
    }


    // get product quan
    val storeProductQuantity = storeInventory[productId]?.get("stock") as Int

    // remove from store
    storeInventory[productId]?.set("stock", storeProductQuantity + quantity)
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

    val total = cart.entries.fold(0.0) { acc, entry ->
        val productId = entry.key
        val quantity = entry.value
        val product = storeInventory[productId]
        val price = product?.get("price") as? Double ?: 0.0

        acc + (price * quantity)
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

    return storeInventory.filter { it.value["name"].toString().lowercase().contains(keyword.lowercase()) }.map { it.key }
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
