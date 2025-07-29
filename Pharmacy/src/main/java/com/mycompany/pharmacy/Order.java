/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacy;

/**
 *
 * @author abdel
 */
public class Order {
    private PharmacyItem phi;
    private int orderedQuantity;
    private boolean isDelieverd;
    private static int numOrders = 1;
    private String orderId;
   

    public Order(PharmacyItem phi, int orderedQuantity) {
        this.phi = phi;
        this.orderedQuantity = orderedQuantity;
        orderId = "ID:" + numOrders++;
    }

    public static int getNumOrders() {
        return numOrders;
    }
    
    
    public String getOrderId() {
        return orderId;
    }

    public PharmacyItem getPhi() {
        return phi;
    }

    public int getOrderedQuantity() {
        return orderedQuantity;
    }

    public float getOrderPrice() {
        return orderedQuantity * phi.getPrice();
    }

    public boolean IsDelieverd() {
        return isDelieverd;
    }

    public void Delieverd() {
        this.isDelieverd = true;
        phi.updateStock(orderedQuantity);
    }
    
    
    
}
