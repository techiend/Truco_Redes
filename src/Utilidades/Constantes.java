/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Utilidades;

import Connection.Connection;
import Model.Baraja;
import Model.Carta;
import Model.Partida;
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
    public static ArrayList jugadores = new ArrayList();
    
    public static String msg_flag = "01111110";
    public static InputStream in;
    public static OutputStream out;
    
    public static String numero_jugador = "";
    public static int max_jugadores = 4;
    public static int max_cartas_por_jugador = 3;
    public static Carta vira;
    public static int trama6 = 0;
    
    // -1 - Nada
    //  0 - No lo eres
    //  1 - Lo eres
    public static int repartidor = -1;
    public static String numero_jugador_repartidor = "";
    
    public static String ByteToString(byte b){

        String string = Integer.toBinaryString(b & 0xFF);

        Formatter fmt = new Formatter();
        fmt.format("%08d", Integer.parseInt(string));

        return fmt.toString();

    }
    
    public static void repartirCartas(){
        
        System.out.println("Repartiendo las cartas.");
        
        Connection conn = Connection.getInstance();
        Baraja baraja = Baraja.getInstance();

        for (int i = 0; i < max_jugadores; i++){
        
            for (int j = 0; j < max_cartas_por_jugador; j++){
            
                
                conn.sendCarta(baraja.mazo.get(0), i);
                baraja.mazo.remove(0);
                
            }
        
        }
        
        conn.sendCartaVira(baraja.mazo.get(0));
        baraja.mazo.remove(0);
        
        
    
    }
    
    public static Carta stringToCarta(String trama){
        
        Formatter fmt = new Formatter();
        fmt.format("%04d", Integer.parseInt(Integer.toBinaryString(Integer.parseInt(trama.substring(2,6),2))));
        
        Carta carta = new Carta(Integer.parseInt(trama.substring(2,6),2), pintas_name[Integer.parseInt(trama.substring(6,8),2)], fmt.toString());
//        System.out.println("Carta: "+carta.toString());
        return carta;
    }
    
    public static boolean validateUser(String trama){
        
//        System.out.println("User: "+trama.substring(0,2));
//        System.out.println("Miuser: "+numero_jugador);
        
        if (trama.substring(0,2).equals(numero_jugador))
            return true;
        
        return false;
    }
    
    public static void validateTrama3(String trama){
        
        if (!trama.substring(0, 2).equals(numero_jugador)){
            // Si la trama que recibes no fue enviada por ti y no fue modificada
            
            
            if (repartidor == 1){
                // Yo soy el repartidor, modifico la trama y la envio al siguiente
                String notificacion = "";
        
                notificacion += trama.substring(0, 2) + "000" + Constantes.numero_jugador + "0";

                byte[] tramaB = new byte[6];
                tramaB[0] = (byte) Short.parseShort(Constantes.msg_flag, 2);
                tramaB[1] = (byte) Short.parseShort("00000000", 2);
                tramaB[2] = (byte) Short.parseShort("00000000", 2);
                tramaB[3] = (byte) Short.parseShort(notificacion, 2);
                tramaB[4] = (byte) Short.parseShort("00000000", 2);
                tramaB[5] = (byte) Short.parseShort("00000000", 2);
                tramaB[6] = (byte) Short.parseShort("00000000", 2);
                tramaB[7] = (byte) Short.parseShort(Constantes.msg_flag, 2);

                Connection.addByte(tramaB);
            }
            else if(repartidor == 0){
                // si no soy el repartidor, pues.... envio la trama al siguiente
                
                
                numero_jugador_repartidor = trama.substring(0,2);
                
                byte[] tramaB = new byte[6];
                tramaB[0] = (byte) Short.parseShort(Constantes.msg_flag, 2);
                tramaB[1] = (byte) Short.parseShort("00000000", 2);
                tramaB[2] = (byte) Short.parseShort("00000000", 2);
                tramaB[3] = (byte) Short.parseShort(trama, 2);
                tramaB[4] = (byte) Short.parseShort("00000000", 2);
                tramaB[5] = (byte) Short.parseShort("00000000", 2);
                tramaB[6] = (byte) Short.parseShort("00000000", 2);
                tramaB[7] = (byte) Short.parseShort(Constantes.msg_flag, 2);

                Connection.addByte(tramaB);
            }
            
        }
        else{
            // Si la trama que recibes fue enviada por ti
            // validamos que no haya sido modificada.
            
            
            if (trama.substring(7, 8).equals("0")){
                //Si modificaron la trama, te jodiste, ya hay repartidor
                numero_jugador_repartidor = trama.substring(5,7);
                repartidor = 0;
                System.out.println("El repartidor es: "+ numero_jugador_repartidor);
            }
            else{
                //Si no modificaron la trama, tu eres el repartidor
                repartidor = 1;
                numero_jugador_repartidor = numero_jugador;
            
            }
            
        }
    
    }
}
