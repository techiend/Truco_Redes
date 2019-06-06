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
public class Partida {
    
    private ArrayList<Ronda> rondas_jugadas;
    private String numero_jugador_ganador = "";
    private int valor_partida = 1;
    
    
    public void addCartaJugada(Ronda ronda){
        this.rondas_jugadas.add(ronda);
    }
    
}
