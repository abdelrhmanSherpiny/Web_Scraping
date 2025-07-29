/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacy;

import java.util.Vector;

/**
 *
 * @author abdel
 */
public class Recipe {
    Vector <RecipeItem> ris = new Vector<>();
    private float totalPrice;
    private boolean paid;
    private int recipeId;

    public Recipe(int recipeId) {
        this.recipeId = recipeId;
    }

    public int getRecipeId() {
        return recipeId;
    }
    
    
    
    public void addri (RecipeItem ri)
    {
        ris.add(ri);
        if (ri.getPhi().getQuantity() >= ri.getRecipeItemQuantity())
        {
            ri.setIsAvilable(true);
            
            if (ri.getPhi() instanceof MedicalDevice)
            {
                totalPrice += ((MedicalDevice) ri.getPhi()).getDiscountPrice() * ri.getRecipeItemQuantity();
            }
            
            else if (ri.getPhi() instanceof PersonalCareItems)
            {
                totalPrice += ( (PersonalCareItems) ri.getPhi()).getDiscountPrice() * ri.getRecipeItemQuantity();
            }
  
            else 
             
            totalPrice += ri.getPhi().getPrice() * ri.getRecipeItemQuantity();
            
            ri.getPhi().sellItem(ri.getRecipeItemQuantity());
        }
        else 
        {
            App.makeOrder(ri.getPhi().getName(),ri.getRecipeItemQuantity() - ri.getPhi().getQuantity());
        }
    }
    

    public void displayInfo()
    {
        for (RecipeItem ri:ris)
        {
            System.out.println(ri.getPhi().getName());
            if (ri.isIsAvilable()) 
                System.out.println("Found");
            else 
                System.out.println("Not Found");
        }
        
        if (paid) System.out.println("Paid");
        else System.out.println("Not Paid");
        
    }
    
    public void make_payment(float moneyPaid)throws IllegalStateException, IllegalArgumentException
    {
        
            
            if (paid) throw new IllegalStateException("Recipe is Already paid");
            if (moneyPaid != totalPrice) throw new IllegalArgumentException("The money paid is not same as required");
            paid = true;

            
    }

    public boolean isPaid() {
        return paid;
    }

    public float getTotalPrice() {
        return totalPrice;
    }
    
}
