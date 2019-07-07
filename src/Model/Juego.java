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
    private static ArrayList<String> grupo1, grupo2;

    public Juego() {
        partidas_jugadas = new ArrayList<Partida>();
        grupo1 = new ArrayList<String>();
        grupo2 = new ArrayList<String>();
        
        grupo1.add("00");
        grupo1.add("10");
        
        grupo2.add("01");
        grupo2.add("11");
        
        if (Constantes.numero_jugador.equals("00") || Constantes.numero_jugador.equals("10")){
            numero_de_grupo = 1;
        }
        if (Constantes.numero_jugador.equals("01") || Constantes.numero_jugador.equals("11")){
            numero_de_grupo = 2;
        }
        
        nextPartida();
        
    }
    
    public static void nextPartida(){
        
        if (partidas_jugadas.size() == 0){
            System.out.println("No tienes partidas.");
            
            Partida partidaInicial = new Partida();
            partidaInicial.setStatus(true);
            addPartida(partidaInicial);
        }
        else{
            System.out.println("Ya tienes partidas.");
            
            // El estado de la ultima partida lo coloco falso
            partidas_jugadas.get(partidas_jugadas.size()-1).setStatus(false);
           
            // Creo una nueva partida
            Partida partida = new Partida();
            partida.setStatus(true);
            addPartida(partida);
        }
    }
    
    public static Juego getInstance() {
        return NewSingletonHolder.INSTANCE;
    }
    
    private static class NewSingletonHolder {
        private static final Juego INSTANCE = new Juego();
    }   

    public static ArrayList<Partida> getPartidas_jugadas() {
        return partidas_jugadas;
    }
    
    public static void addPartida(Partida partida){
    
        partidas_jugadas.add(partida);
    
    }

    public static int getNumero_de_grupo() {
        return numero_de_grupo;
    }
    
    public static int playerNumGroup(String player){
    
        if (player.equals("00") || player.equals("10")){
             return 1;
        }
        
        if (player.equals("01") || player.equals("11")){
            return 2;
        }
        
        return -1;
    }
    
    public static boolean isInMyTeam(String numeroJugador){
        
        if (numero_de_grupo == 1){
            for (int i = 0; i < grupo1.size(); i ++){
                String player = grupo1.get(i);
                
                if (player.equals(numeroJugador)){
                    System.out.println("Si esta en tu grupo.");
                    return true;
                }
            }
            System.out.println("No esta en tu grupo.");
            return false;
        }
        
        if (numero_de_grupo == 2){
            for (int i = 0; i < grupo2.size(); i ++){
                String player = grupo2.get(i);
                
                if (player.equals(numeroJugador)){
                    System.out.println("Si esta en tu grupo.");
                    return true;
                }
            }
            System.out.println("No esta en tu grupo.");
            return false;
        }
        
        
        System.out.println("Error, no estas en un grupo. :C ");
        return false;
    }
    
    public static int getPointsOfGroup(int numGrupo){
        int points = 0;
        
        for (int i = 0; i<partidas_jugadas.size(); i++){
            Partida partida = partidas_jugadas.get(i);
            
            if (partida.getNumero_equipo_ganador() == numGrupo){
            
                points += partida.getValor_partida();
                
            }
                        
        }
        
        return points;
    }
    
}
