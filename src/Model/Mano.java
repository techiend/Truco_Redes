/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Controller.Mesa_Controller;
import java.util.ArrayList;

/**
 *
 * @author Techiend
 */
public class Mano {
    
    public ArrayList<Carta> mano;

    public Mano() {
        this.mano = new ArrayList<Carta>();
    }
    
    public static Mano getInstance() {
        return NewSingletonHolder.INSTANCE;
    }
    
    private static class NewSingletonHolder {
        private static final Mano INSTANCE = new Mano();
    }   
    
    public void addCarta(Carta carta){
        if(mano.size() <= 3) // Solamente puede tener 3 cartas en la mano
            mano.add(carta);
    }
    
    public void restoreHand(){
        this.mano = new ArrayList<Carta>();
        Mesa_Controller mesa = Mesa_Controller.getInstance();
        mesa.UpdateHand();
    }
    
    @Override
    public String toString() {
        
        for (int i = 0; i < mano.size(); i++){
            Carta carta = mano.get(i);
            System.out.println(carta.toString()+"\n");
        }
        
        return "";
    }
    
}
