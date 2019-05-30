/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import java.util.ArrayList;

/**
 *
 * @author Techiend
 */
public class Mano {
    
    public ArrayList<Carta> mano;

    public Mano() {
    }
    
    public static Mano getInstance() {
        return NewSingletonHolder.INSTANCE;
    }
    
    private static class NewSingletonHolder {
        private static final Mano INSTANCE = new Mano();
    }   
    
    public void addCarta(Carta carta){
        mano.add(carta);
    }
    
    
}
