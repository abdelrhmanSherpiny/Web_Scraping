/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacy;



/**
 *
 * @author abdel
 */
public class Medicine extends PharmacyItem {
    private String instructions;
    private String category;

    public Medicine(String name, float price, String expireDate, int pharmacyItemsQuantity, String category, String instructions) {
        super(name, price, expireDate, pharmacyItemsQuantity);
        this.instructions = instructions;
        this.category = category;
        type = "Medicine";
    }

    @Override
    public void displayInfo() {
       super.displayInfo();
       System.out.println("Category: "+category);
       System.out.println("Instructions: "+instructions);
    }

    public String getInstructions() {
        return instructions;
    }

    public String getCategory() {
        return category;
    }
    
    

}
