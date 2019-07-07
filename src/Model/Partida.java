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
    
//    private ArrayList<Ronda> rondas;
    private ArrayList<Jugada> jugadas = new ArrayList<Jugada>();
    
    private String numero_jugador_ganador = "";
    private int numero_equipo_ganador = 0;
    private int valor_partida = 1;
    private boolean isActive = false;

//    public void addCartaJugada(Ronda ronda){
//        this.rondas_jugadas.add(ronda);
//    }

    public Partida() {
        Jugada jugada = new Jugada();
        addJugada(jugada);
    
//        Ronda ronda = new Ronda();
//        addRonda(ronda);
    
    }

//    public ArrayList<Ronda> getRondas() {
//        return rondas;
//    }
//
//    public void setRondas(ArrayList<Ronda> rondas) {
//        this.rondas = rondas;
//    }
//    
//    public void addRonda (Ronda ronda){
//        
//        this.rondas.add(ronda);
//        
//    }
    
    
    public ArrayList<Jugada> getJugadas() {
        return jugadas;
    }

    public void setJugadas(ArrayList<Jugada> jugadas) {
        this.jugadas = jugadas;
    }
    
    public void addJugada (Jugada jugada){
        
        this.jugadas.add(jugada);
        
    }

    public String getNumero_jugador_ganador() {
        return numero_jugador_ganador;
    }

    public void setNumero_jugador_ganador(String numero_jugador_ganador) {
        this.numero_jugador_ganador = numero_jugador_ganador;
    }

    public int getNumero_equipo_ganador() {
        return numero_equipo_ganador;
    }

    public void setNumero_equipo_ganador(int numero_equipo_ganador) {
        this.numero_equipo_ganador = numero_equipo_ganador;
    }

    public int getValor_partida() {
        return valor_partida;
    }

    public void setValor_partida(int valor_partida) {
        this.valor_partida = valor_partida;
    }

    public boolean isIsActive() {
        return isActive;
    }

    public void setIsActive(boolean isActive) {
        this.isActive = isActive;
    }
            
    public void setStatus(boolean status){
        isActive = status;
    } 
    
    
}
