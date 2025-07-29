/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacy;



/**
 *
 * @author abdel
 */
public abstract class PharmacyItem implements Comparable <PharmacyItem>{
    
    private String name ;
    private float price;
    private String expiryDate;
    private int quantity;
    public String type;
    private int itemId;

    
    public PharmacyItem(String name, float price, String expiryDate, int quantity) {
        this.name = name;
        this.price = price;
        this.expiryDate = expiryDate;
        this.quantity = quantity;
        
    }

 
    public int getItemId() {
        return itemId;
    }
    

    
    public String getName() {
        return name;
    }

    public float getPrice() {
        return price;
    }

   

    public String getExpiryDate() {
        return expiryDate;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void sellItem(int quantity) {
        this.quantity -= quantity;
    }
    
    public void updateStock(int quantity) {
        this.quantity += quantity;
    }
    
    public void displayInfo()
    {
        System.out.println(name);
        System.out.println(price);
        System.out.println(expiryDate);
        System.out.println(quantity);
        
        
    }
    
    @Override
    public int compareTo(PharmacyItem other) {
        return this.name.compareTo(other.name);
    }
    
    
}
