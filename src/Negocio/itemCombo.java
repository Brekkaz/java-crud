/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

/**
 *
 * @author Breyner
 */
public class itemCombo {
    String id, nombre;

    public itemCombo(String id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public String getId() {
        return id==null?null:id;
    }

    public String toString() {
        return nombre==null?null:nombre;
    } 
    
}
