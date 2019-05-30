/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Start;

import Objects.Baraja;
import Utilidades.Constantes;
import java.util.Collections;

/**
 *
 * @author Techiend
 */
public class Start {

    public static void main(String[] args) {
        
        Baraja barajaPartida = Baraja.getInstance();
        
        Collections.shuffle(barajaPartida.mazo);
        
//        barajaPartida.toString();

        Constantes.stringToCarta("00110001");
        
    }
    
}
