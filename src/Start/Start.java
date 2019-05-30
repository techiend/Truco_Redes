/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Start;

import Connection.Connection;
import Model.Baraja;
import Model.Mano;
import Utilidades.Constantes;
import java.util.Collections;
import java.util.Scanner;

/**
 *
 * @author Techiend
 */
public class Start {

    public static void main(String[] args) {
        
        Baraja barajaPartida = Baraja.getInstance();
        
        Connection conn = new Connection();
        conn.connect("COM2");
        
        Scanner scanner = new Scanner(System.in);
        while (Constantes.numero_jugador.equals("")){

            System.out.println("\n Introduce el numero del jugador: ");
            String line = scanner.nextLine();

            if (line.equals("00") || line.equals("01") || line.equals("10") || line.equals("11")){
                System.out.println("Jugador guardado");
                Constantes.numero_jugador = line;
            }
        }
        
        System.out.println("El jugador de esta computadora es: "+Constantes.numero_jugador);
        
        Collections.shuffle(barajaPartida.mazo);
        
//        barajaPartida.toString();

//        Mano mano = Mano.getInstance();
//        System.out.println(mano.toString());
        
    }
    
}
