/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacy;



/**
 *
 * @author abdel
 */
public class MedicalDevice extends PharmacyItem implements Discountable{
    private String warranty;
    private String purpose;


    public MedicalDevice(String name, float price, String expireDate, int pharmacyItemsQuantity, String warranty, String purpose) {
        super(name, price, expireDate, pharmacyItemsQuantity);
        this.warranty = warranty;
        this.purpose= purpose;
        type = "Medical Device";
    }

    @Override
    public void displayInfo() {
       super.displayInfo();
       System.out.println("Warranty: "+warranty);
       System.out.println("Purpose: "+purpose);
    }

    public String getWarranty() {
        return warranty;
    }

    public String getPurpose() {
        return purpose;
    }
    
    
    
    @Override
    public float getDiscountPrice()
    {
        return super.getPrice() * DISCOUNT;
    }

}

