package com.aleyla.shopping.model;

import java.util.Map;

public class DeliveryCostCalculator {

    private double fixedCost;
    private double costPerProduct;
    private double costPerDelivery;

    public DeliveryCostCalculator(double fixedCost, double costPerProduct, double costPerDelivery) {
        this.fixedCost = fixedCost;
        this.costPerProduct = costPerProduct;
        this.costPerDelivery = costPerDelivery;
    }

    public double calculateFor(ShoppingCart cart) {
        Map<Product, Integer> products = cart.getProducts();
        long numberOfDelivery = getNumberOfDelivery(products);
        long numberOfProducts = getNumberOfProducts(products);
        double totalDeliveryCost = getDeliveryCost(numberOfDelivery) + getProductCost(numberOfProducts) + fixedCost;
        cart.setDeliveryCost(totalDeliveryCost);
        return totalDeliveryCost;
    }

    private double getDeliveryCost(long numberOfDelivery) {
        return (numberOfDelivery * costPerDelivery);
    }

    private double getProductCost(long numberOfProducts) {
        return numberOfProducts * costPerProduct;
    }

    private long getNumberOfDelivery(Map<Product, Integer> products) {
        return products.keySet().stream().map(Product::getCategory).distinct().count();
    }

    private long getNumberOfProducts(Map<Product, Integer> products) {
        return products.values().stream().reduce(0, Integer::sum);
    }


    public double getFixedCost() {
        return fixedCost;
    }

    public void setFixedCost(double fixedCost) {
        this.fixedCost = fixedCost;
    }

    public double getCostPerProduct() {
        return costPerProduct;
    }

    public void setCostPerProduct(double costPerProduct) {
        this.costPerProduct = costPerProduct;
    }

    public double getCostPerDelivery() {
        return costPerDelivery;
    }

    public void setCostPerDelivery(double costPerDelivery) {
        this.costPerDelivery = costPerDelivery;
    }
}
