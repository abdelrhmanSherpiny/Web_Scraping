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
public class Client implements Comparable <Client>{
    private String name;
    private String phoneNum;
    private int recipeNum = 1;
    
    Vector <Recipe> rs = new Vector<> ();

    public Client(String name, String phoneNum) {
        this.name = name;
        this.phoneNum = phoneNum;
        
    }
  
    public String getName() {
        return name;
    }

    public String getPhoneNum() {
        return phoneNum;
    }
    

    public void addRecipe(Recipe r)
    {
        rs.add(r);
        recipeNum++;
    }

    public int getRecipeNum() {
        return recipeNum;
    }
    
    @Override
    public int compareTo(Client other) {
        return this.name.compareTo(other.name);
    }
    
}
