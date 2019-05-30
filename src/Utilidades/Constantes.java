/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Model.Carta;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Formatter;

/**
 *
 * @author Techiend
 */
public class Constantes {
    
    public static String[] pintas_name = {"basto","copa","espada","oro"};
    public static String[] pintas_cod = {"00","01","10","11"};
    public static int num_cartas = 40;
    
    public static String msg_flag = "01111110";
    public static InputStream in;
    public static OutputStream out;
    public static ArrayList<byte[]> bufferList_send = new ArrayList<byte[]>();
    public static ArrayList<byte[]> bufferList_recb = new ArrayList<byte[]>();
    
    public static String numero_jugador = "";
    
    public static String ByteToString(byte b){

        String string = Integer.toBinaryString(b & 0xFF);

        Formatter fmt = new Formatter();
        fmt.format("%08d", Integer.parseInt(string));

        return fmt.toString();

    }
    
    public static Carta stringToCarta(String trama){
            
        Carta carta = new Carta(Integer.parseInt(trama.substring(2,6),2), pintas_name[Integer.parseInt(trama.substring(6,8),2)]);
        return carta;
    }
    
    public static boolean validateUser(String trama){
        
        System.out.println("User: "+trama.substring(0,2));
        System.out.println("Miuser: "+numero_jugador);
        
        if (trama.substring(0,2).equals(numero_jugador))
            return true;
        
        return false;
    }
}
