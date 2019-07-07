/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Techiend
 */
public class Jugada{
    
    private Carta  carta_jugada;
    private String numero_jugador;
    private String canto;

    public Jugada() {
    }
    
    public Jugada(Carta carta_jugada, String numero_jugador) {
        this.carta_jugada = carta_jugada;
        this.numero_jugador = numero_jugador;
    }

    public Jugada(String numero_jugador, String canto) {
        this.numero_jugador = numero_jugador;
        this.canto = canto;
    }

    public void setCarta_jugada(Carta carta_jugada) {
        this.carta_jugada = carta_jugada;
    }

    public void setNumero_jugador(String numero_jugador) {
        this.numero_jugador = numero_jugador;
    }

    public void setCanto(String canto) {
        this.canto = canto;
    }

    public Carta getCarta_jugada() {
        return carta_jugada;
    }

    public String getNumero_jugador() {
        return numero_jugador;
    }

    public String getCanto() {
        return canto;
    }
       
}
