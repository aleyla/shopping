package com.aleyla.shopping.printer;

import com.aleyla.shopping.core.Category;
import com.aleyla.shopping.core.Product;
import com.aleyla.shopping.core.ShoppingCart;

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
                    builder.append(String.format("Category:%s\n", category.getTitle()));
                    productList.forEach(
                            product -> builder.append(String.format("\t \t Product: %1s \t Quantity: %2$d \t Unit Price: %3$.2f \t Total Price: %4$.2f",
                                    product.getTitle(), products.get(product), product.getPrice(), products.get(product) * product.getPrice())).append(("\n"))
                    );
                }
        );
        builder.append(String.format("Total Cart Amount: %1$.2f \t Discount: %2$.2f \t Delivery Cost: %3$.2f", shoppingCart.getTotalAmount(), shoppingCart.getTotalDiscount() ,shoppingCart.getDeliveryCost()));

        System.out.println(builder.toString());

        return builder.toString();
    }
}
