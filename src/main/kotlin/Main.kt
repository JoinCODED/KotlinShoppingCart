package shoppingCart
/**
 *   1 -> { "name"="Laptop", "price"=350.000, "stock"=10 },
 *   2 -> { "name"="Smart TV", "price"=200.000, "stock"=5 },
 *   3 -> { "name"="Headphones", "price"=50.000, "stock"=20 },
 *   4 -> { "name"="Gaming Console", "price"=150.000, "stock"=8 },
 *   5 -> { "name"="Wireless Mouse", "price"=8.500, "stock"=15 },
 */
fun createStoreInventory(): MutableMap<Int, MutableMap<String, Any>> {
    return mutableMapOf(
        1 to mutableMapOf("name" to "Laptop", "price" to 350.000, "stock" to 10),
        2 to mutableMapOf("name" to "Smart TV", "price" to 200.000, "stock" to 5),
        3 to mutableMapOf("name" to "Headphones", "price" to 50.000, "stock" to 20),
        4 to mutableMapOf("name" to "Gaming Console", "price" to 150.000, "stock" to 8),
        5 to mutableMapOf("name" to "Wireless Mouse", "price" to 8.500, "stock" to 15),
    )
}

fun addToCart(
    storeInventory: MutableMap<Int, MutableMap<String, Any>>,
    cart: MutableMap<Int, Int>,
    productId: Int,
    quantity: Int
): Boolean {
        val product = storeInventory[productId]  //check if product exists
        if (product == null) {
            println("Product not found in inventory.")
            return false
        }

        // Get stock value and check if there is enough stock
        val productStock = product["stock"] as Int
        if (productStock < quantity) {
            println("Not enough stock. Requested: $quantity, Available: $productStock")
            return false
        }

        // Update stock in inventory
        product["stock"] = productStock - quantity
        println("Stock of product ID $productId updated to ${product["stock"]}")

        // Add to cart or update quantity in cart
        val currentQuantity = cart[productId] ?: 0
        cart[productId] = currentQuantity + quantity
        println("Product ID $productId quantity in cart updated to ${cart[productId]}.")

        println("Successfully added product ID $productId to cart!")
        return true
    }

fun removeFromCart(
    storeInventory: MutableMap<Int, MutableMap<String, Any>>,
    cart: MutableMap<Int, Int>,
    productId: Int,
    quantity: Int
): Boolean {
    if (!storeInventory.containsKey(productId)) {
        println("Product not found in inventory.")
        return false
    }
    val currentQuantity = cart[productId] ?: run {
        println("Product ID $productId not found in cart.")
        return false
    }
    if (currentQuantity < quantity) {
        println("Not enough quantity in cart. Requested to remove: $quantity, Available: $currentQuantity")
        return false
    }

    storeInventory[productId]?.set("stock", (storeInventory[productId]?.get("stock") as Int) + quantity)
    println("Stock of product ID $productId restored to ${(storeInventory[productId]?.get("stock") as Int) + quantity}.")

    if (currentQuantity == quantity) {
        cart.remove(productId)
        println("Product ID $productId completely removed from cart.")
    } else {
        cart[productId] = currentQuantity - quantity
        println("Product ID $productId quantity in cart decreased to ${cart[productId]}.")
    }

    println("Successfully removed $quantity of product ID $productId from cart!")
    return true
}

fun calculateTotal(
    storeInventory: MutableMap<Int, MutableMap<String, Any>>,
    cart: MutableMap<Int, Int>
): Double {
        var total = 0.0 //initial value

        for (productId in cart.keys) { //iterate through every item in cart
            val quantity = cart.get(productId) ?: 0 //return value zero if item not found/null
            val price = storeInventory.get(productId)?.get("price") as Double // Directly casting to Double
            val subtotal = price * quantity // cost of the product
            total += subtotal // adding cost to cart total
        }

    println("Final total cost: $total")
    return total
}

fun filterProductsByName(
    storeInventory: MutableMap<Int, MutableMap<String, Any>>,
    keyword: String
): List<Int> {
    val filteredProducts = mutableListOf<Int>() // creating a list to save product id that matches

    for (productId in storeInventory.keys) { // iterate through every item
        val productName = storeInventory[productId]?.get("name") as? String // save product name

        if (productName != null && productName.contains(keyword, ignoreCase = true)) { // check if name contains keyword
            filteredProducts.add(productId) // add matching product id to filtered list
        }
    }
    println("Matching product IDs: $filteredProducts")
    return filteredProducts // return the final list of matching product ids
}


fun main() {
    // Initialize store inventory and empty cart
    val storeInventory = createStoreInventory()
    val cart = mutableMapOf<Int, Int>()

    fun printStoreInventory(title: String) {
        println(title)
        for (productId in storeInventory.keys) {
            println("ID: $productId -> ${storeInventory[productId]}")
        }
        println()
    }

    fun printCart(title: String) {
        println(title)
        for ((productId, quantity) in cart) {
            println("Product ID: $productId, Quantity: $quantity")
        }
        println()
    }

    fun main() {
        printStoreInventory("Initial Store Inventory:")

        // Add to cart
        addToCart(storeInventory, cart, 1, 2)
        addToCart(storeInventory, cart, 3, 1)
        addToCart(storeInventory, cart, 5, 3)
        printCart("Your cart after adding items:")

        // Remove from cart
        removeFromCart(storeInventory, cart, 1, 1)
        removeFromCart(storeInventory, cart, 5, 2)
        printCart("Your cart after removing items:")

        // Calculate total
        println("Total price of items in cart: $${calculateTotal(storeInventory, cart)}\n")

        // Filter products by keyword
        println("Filtered Product IDs: ${filterProductsByName(storeInventory, "laptop")}\n")

        printStoreInventory("Store Inventory After Changes:")
        printCart("Final Cart State:")
    }
}
