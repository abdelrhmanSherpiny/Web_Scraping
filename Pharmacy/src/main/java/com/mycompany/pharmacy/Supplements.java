/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacy;



/**
 *
 * @author abdel
 */
public class Supplements extends PharmacyItem {
    private String flavor;
    private String usage;
    

    public Supplements(String name, float price, String expireDate, int pharmacyItemsQuantity, String flavor, String usage) {
        super(name, price, expireDate, pharmacyItemsQuantity);
        this.flavor = flavor;
        this.usage = usage;
        type = "Supplements";
    }

    @Override
    public void displayInfo() {
       super.displayInfo();
       System.out.println("Flavor: "+flavor);
       System.out.println("Usage: "+usage);
    }

    public String getFlavor() {
        return flavor;
    }

    public String getUsage() {
        return usage;
    }
  
}