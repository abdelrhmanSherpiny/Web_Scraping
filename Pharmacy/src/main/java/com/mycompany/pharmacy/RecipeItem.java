/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.pharmacy;

/**
 *
 * @author abdel
 */
public class RecipeItem {
    private PharmacyItem phi;
    private int recipeItemQuantity;
    private boolean isAvilable;

    public RecipeItem(PharmacyItem phi, int recipeItemQuantity) {
        this.phi = phi;
        this.recipeItemQuantity = recipeItemQuantity;
        
        
    }

    public PharmacyItem getPhi() {
        return phi;
    }

    public int getRecipeItemQuantity() {
        return recipeItemQuantity;
    }

    public boolean isIsAvilable() {
        return isAvilable;
    }

    public void setIsAvilable(boolean isAvilable) {
        this.isAvilable = isAvilable;
    }
    
}
