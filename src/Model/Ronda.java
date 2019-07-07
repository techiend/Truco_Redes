/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.util.ArrayList;

/**
 *
 * @author Techiend
 */
public class Ronda {
    
    private ArrayList<Jugada> jugadas = new ArrayList<Jugada>();

    public Ronda() {
        Jugada jugada = new Jugada();
        addJugada(jugada);
    }

    public ArrayList<Jugada> getJugadas() {
        return jugadas;
    }

    public void setJugadas(ArrayList<Jugada> jugadas) {
        this.jugadas = jugadas;
    }
    
    
    public void addJugada (Jugada jugada){
        
        this.jugadas.add(jugada);
        
    }
    
}
