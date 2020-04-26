package com.aleyla.shopping.printer;

import com.aleyla.shopping.model.Category;
import com.aleyla.shopping.model.Product;
import com.aleyla.shopping.model.ShoppingCart;

import java.util.*;

public class PrinterUtil {

    public static String print(ShoppingCart shoppingCart) {

        Map<Category, List<Product>> groupByCategory = new HashMap<>();

        Map<Product, Integer> products = shoppingCart.getProducts();
        for (Map.Entry<Product, Integer> productQuantityEntry : products.entrySet()) {
            Product product = productQuantityEntry.getKey();
            if (groupByCategory.containsKey(product.getCategory())) {
                groupByCategory.get(product.getCategory()).add(product);
            } else {
                groupByCategory.put(product.getCategory(), new ArrayList<>(Collections.singletonList(product)));
            }
        }

        StringBuilder builder = new StringBuilder();

        groupByCategory.forEach(
                (category, productList) -> {
                    builder.append(String.format("---- Category Name :  %s -----\n", category.getTitle()));
                    productList.forEach(
                            product -> builder.append(String.format("      * Product Name : %1s, Quantity: %2$d, Unit Price: %3$.2f, Total Price: %4$.2f",
                                    product.getTitle(), products.get(product), product.getPrice(), products.get(product) * product.getPrice())).append(("\n"))
                    );
                }
        );
        builder.append(String.format("Total Card Discount: %1$.2f", shoppingCart.getTotalDiscount()));

        System.out.println(builder.toString());

        return builder.toString();
    }
}
