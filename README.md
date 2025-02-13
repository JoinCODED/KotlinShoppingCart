# Kotlin Shopping Cart Project

This project is a hands-on assignment to practice **Kotlin functions** and **basic logic** in a small “shopping cart” scenario. You will implement functions to manage store inventory, a shopping cart, item totals, and product filtering.

---

## Overview

You will:

1. Fork this repository to your own GitHub account.  
2. Clone your fork locally.  
3. Implement the required functions in `Main.kt` (found in `src/shoppingCart/`).  
4. Push your changes to your fork.  
5. Create a Pull Request (PR) back to the original repository.

All tests are located in the **`tests`** folder. They will indicate what functionality is missing or incorrect until they all pass successfully.

---

## Instructions

### 1. Fork & Clone

1. On the original GitHub repo, click “Fork” (top-right corner) and choose your own GitHub account.  
2. Copy the fork’s clone URL.  
3. In a terminal or command prompt:  
   git clone https://github.com/<YOUR_USERNAME>/kotlin-shopping-cart.git  
   cd kotlin-shopping-cart  
4. Open the project in IntelliJ (File > Open).

### 2. Implement Each Function

Open `Main.kt` in `src/shoppingCart/`. You’ll find 5 functions with `TODO(...)` calls. For each function, replace the `TODO(...)` code with your logic:

- **createStoreInventory()**  
  Returns a `MutableMap<Int, MutableMap<String, Any>>` with exactly 5 items (IDs 1..5).  
  Each product must have `"name"`, `"price"`, and `"stock"` keys.

- **addToCart(...)**  
  Checks if the product ID exists and if the requested quantity ≤ current stock.  
  If valid, reduce the stock and increase the cart’s quantity.  
  Returns true on success, false otherwise.

- **removeFromCart(...)**  
  Checks if the cart has enough of the product to remove.  
  If valid, remove items from the cart and restore them to the store stock.  
  Returns true on success, false otherwise.

- **calculateTotal(...)**  
  Sums the cost of items in the cart by (price * quantity).

- **filterProductsByName(...)**  
  Returns a list of product IDs whose `"name"` contains a given keyword.  
  May be case-insensitive, depending on the tests.

### 3. Run the Tests

- Locate the **`tests`** folder in your project tool window.  
- Right-click the folder or an individual `...Tests.kt` file.  
- Select “Run Tests” or “Run 'AddToCartTests'”, etc.

Initially, the tests will fail due to the `TODO(...)` lines. As you implement each function, re-run the tests until they pass.

### 4. Commit & Push

Once tests pass (or partially pass):

git add .  
git commit -m "Implement functions"  
git push origin main  

Adjust if you’re on a different branch.

### 5. Open a Pull Request

- Go to your fork on GitHub.  
- You’ll see a button or prompt to create a Pull Request (PR) to the original repository.  
- Provide a short description of your changes. The repository owners or instructors can then review your work.

---

## Tips & Troubleshooting

- **NotImplementedError**: Each function currently calls `TODO(...)`, which throws `NotImplementedError`. Remove that and implement real logic.  
- **Test Failures**: Read the error messages carefully — they usually explain what was expected vs. what you returned.  
- **Don’t Modify Tests**: The tests guide you on what’s needed. Keep them as is.

---

Good luck and have fun coding!
