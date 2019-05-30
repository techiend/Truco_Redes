/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

import Utilidades.Constantes;
import java.util.ArrayList;

/**
 *
 * @author Techiend
 */
public class Baraja {
    
    public ArrayList<Carta> mazo;
    
    private Baraja() {
        this.mazo = new ArrayList<Carta>();
        
        generateMazo();
    }
    
    public static Baraja getInstance() {
        return NewSingletonHolder.INSTANCE;
    }
    
    private static class NewSingletonHolder {
        private static final Baraja INSTANCE = new Baraja();
    }   
    
    private void generateMazo(){
    
        for (int i = 0; i < Constantes.pintas_name.length; i++){
        
            for (int j = 1; j < 13; j++){
            
                if (j != 8 && j != 9){
                
                    Carta carta = new Carta(j, Constantes.pintas_name[i]);
                    mazo.add(carta);
                    
                }
                
            }
            
        }    
        
    }

    @Override
    public String toString() {
        
        for (int i = 0; i < mazo.size(); i++){
            Carta carta = mazo.get(i);
            System.out.println(carta.toString()+"\n");
        }
        
        return "";
    }
    
    
    
    
}
