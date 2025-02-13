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
     val product1 = mutableMapOf("name" to "Laptop", "price" to 350.00, "stock" to 10)
   val  product2 = mutableMapOf("name" to "Smart TV", "price" to 200.00, "stock" to 5)
   val  product3 = mutableMapOf("name" to "Headphones", "price" to 50.00, "stock" to 20)
    val product4 = mutableMapOf("name" to "Gaming Console", "price" to 150.00, "stock" to 8)
    val product5 = mutableMapOf("name" to "Wireless Mouse", "price" to 8.500, "stock" to 15)

    val productId = mutableMapOf(1 to product1, 2 to product2, 3 to product3, 4 to product4, 5 to product5)

    return productId as MutableMap<Int, MutableMap<String, Any>>



}

   // val productId = mutableMapOf(1 to product1, 2 to product2, 3 to product3, 4 to product4, 5 to product5)





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

//    if(quantity )
    if (!storeInventory.containsKey(productId))
    { return false }
    val pId =storeInventory.getValue(productId)
    var numOfStock = pId.getValue("stock") as Int       // quantity of product

    if (numOfStock >= quantity) {

        if (!cart.containsKey(productId))
        {
            cart.put(productId, quantity)
        }
       var oldQuantity = cart.getValue(productId)
        var newQuantity = oldQuantity + quantity
        cart.put(productId,newQuantity )
        var newStock = numOfStock - quantity
        pId.replace("stock", newStock)
        storeInventory.get(productId)?.set("stock",newStock)
//        pId.set("stock",newStock)
        return true

    }else
        return false



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
    if (!cart.containsKey(productId))   // I dont have product
    {return false  }


    var castomerQuantity = cart.getValue(productId)
    if (castomerQuantity < quantity)  {
        return false
    }
    if (castomerQuantity >= quantity)  //  I  have product
    {
        castomerQuantity -= quantity

        cart.replace(productId,castomerQuantity )   // new quantity in cart

        val pId =storeInventory.getValue(productId)
        var newStock = pId.getValue("stock") as Int
        newStock += quantity
        storeInventory.get(productId)?.set("stock",newStock)    // new quantity for store

        return true

    }
    else
    {   return false}

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
    TODO("Implement calculateTotal()")
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
    TODO("Implement filterProductsByName()")
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
