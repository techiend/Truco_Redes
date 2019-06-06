/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Utilidades.Constantes;
import java.util.ArrayList;

/**
 *
 * @author Techiend
 */
public class Juego {
    
    private static ArrayList<Partida> partidas_jugadas;
    private static int numero_de_grupo = 0;

    public Juego() {
        partidas_jugadas = new ArrayList<Partida>();
        
        if (Constantes.numero_jugador.equals("00") || Constantes.numero_jugador.equals("10")){
            numero_de_grupo = 1;
        }
        if (Constantes.numero_jugador.equals("01") || Constantes.numero_jugador.equals("11")){
            numero_de_grupo = 2;
        }
    }
    
    public static Juego getInstance() {
        return NewSingletonHolder.INSTANCE;
    }
    
    private static class NewSingletonHolder {
        private static final Juego INSTANCE = new Juego();
    }   
    
    public static void addPartida(Partida partida){
    
        partidas_jugadas.add(partida);
    
    }

    public static int getNumero_de_grupo() {
        return numero_de_grupo;
    }
    
    
}
