/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacy;



/**
 *
 * @author abdel
 */
public class PersonalCareItems extends PharmacyItem implements Discountable{
    private String brand;
    private String category;


    public PersonalCareItems(String name, float price, String expireDate, int pharmacyItemsQuantity,
                             String brand, String category) {
        super(name, price, expireDate, pharmacyItemsQuantity);
        this.brand = brand;
        this.category = category;
        type = "PersonalCareItems";
    }

     @Override
    public void displayInfo() {
       super.displayInfo();
       System.out.println("Brand: "+brand);
       System.out.println("Category: "+category);
    }

    public String getBrand() {
        return brand;
    }

    public String getCategory() {
        return category;
    }
    
    @Override
    public float getDiscountPrice()
    {
        return super.getPrice() * DISCOUNT;
    }
 
}
