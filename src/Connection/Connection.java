/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Controller.Mesa_Controller;
import Model.Baraja;
import Model.Carta;
import Model.Mano;
import Utilidades.Constantes;
import com.fazecast.jSerialComm.SerialPort;
import java.awt.Window;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Techiend
 */
public class Connection {

    public static ArrayList<byte[]> bufferList_send = new ArrayList<byte[]>();
    
    public Connection() {
    }
    
    public static Connection getInstance() {
        return NewSingletonHolder.INSTANCE;
    }
    
    private static class NewSingletonHolder {
        private static final Connection INSTANCE = new Connection();
    }   
    
    public boolean connect (String portName)
    {

        
        SerialPort comPort = SerialPort.getCommPort(portName);
        if (!comPort.openPort())
            return false;
            
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

        Constantes.in = comPort.getInputStream();
        Constantes.out = comPort.getOutputStream();

            
        (new Thread(new SerialReader(Constantes.in))).start();
//        (new Thread(new SerialWriter(Constantes.out))).start();

        System.out.println("Se ha establecido conexion mediante el puerto "+portName);
        
        return true;
    }
    
    public static void notificarRepartidor(){
    
        String notificacion = "";
        
        notificacion += Constantes.numero_jugador + "000" + "00" + "1";
        
        byte[] trama = new byte[6];
        trama[0] = (byte) Short.parseShort(Constantes.msg_flag, 2);
        trama[1] = (byte) Short.parseShort("00000000", 2);
        trama[2] = (byte) Short.parseShort("00000000", 2);
        trama[3] = (byte) Short.parseShort(notificacion, 2);
        trama[4] = (byte) Short.parseShort("00000000", 2);
        trama[5] = (byte) Short.parseShort("00000000", 2);
        trama[6] = (byte) Short.parseShort("00000000", 2);
        trama[7] = (byte) Short.parseShort(Constantes.msg_flag, 2);

        addByte(trama);
    } 
    
    public void sendCarta(Carta carta, int jugador){
        
        String notificacion = "";
        
        
        Formatter fmt = new Formatter();
        fmt.format("%02d", Integer.parseInt(Integer.toBinaryString(jugador)));
        
        notificacion += fmt.toString() + carta.getNumBin() + carta.getBinaryPinta();
        
        byte[] trama = new byte[6];
        trama[0] = (byte) Short.parseShort(Constantes.msg_flag, 2);
        trama[1] = (byte) Short.parseShort(notificacion, 2);
        trama[2] = (byte) Short.parseShort("00000000", 2);
        trama[3] = (byte) Short.parseShort("00000000", 2);
        trama[4] = (byte) Short.parseShort("00000000", 2);
        trama[5] = (byte) Short.parseShort("00000000", 2);
        trama[6] = (byte) Short.parseShort("00000000", 2);
        trama[7] = (byte) Short.parseShort(Constantes.msg_flag, 2);

        addByte(trama);
        
    }
    
    public void sendCartaVira(Carta carta){
        
        String notificacion = "";
        
        notificacion += "00" + carta.getNumBin() + carta.getBinaryPinta();
        
        byte[] trama = new byte[6];
        trama[0] = (byte) Short.parseShort(Constantes.msg_flag, 2);
        trama[1] = (byte) Short.parseShort("00000000", 2);
        trama[2] = (byte) Short.parseShort("00000000", 2);
        trama[3] = (byte) Short.parseShort("00000000", 2);
        trama[4] = (byte) Short.parseShort(notificacion, 2);
        trama[5] = (byte) Short.parseShort("00000000", 2);
        trama[6] = (byte) Short.parseShort("00000000", 2);
        trama[7] = (byte) Short.parseShort(Constantes.msg_flag, 2);

        addByte(trama);
        
    }
    
    public static void addByte(byte[] trama){
        try {
            Constantes.out.write(trama);
            System.out.println("Envio");
        } catch (IOException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static class SerialReader implements Runnable
    {
        InputStream in;

        public SerialReader ( InputStream in )
        {
            this.in = in;
        }

        public void run ()
        {
            byte[] buffer = new byte[6];
            int len = -1;
            try
            {
                while ( ( len = this.in.read(buffer)) > -1 )
                {
                    while (buffer.length<5){}

                    // MANEJAR LO RECIBIDO
                    
                    if (!Constantes.ByteToString(buffer[1]).equals("00000000")){
//                        System.out.println("Estas recibiendo Cartas");
                        
                        if (Constantes.validateUser(Constantes.ByteToString(buffer[1]))){

                            Mano mano = Mano.getInstance();
                            mano.addCarta(Constantes.stringToCarta(Constantes.ByteToString(buffer[1])));
                            
                            Mesa_Controller mesa_controller = Mesa_Controller.getInstance();
                            
                            mesa_controller.UpdateHand();
                            
                        }
                        else{
                            if (Constantes.repartidor == 0){
                                Connection.addByte(buffer);
                            }
                            else{
                                //Agrego la carta de nuevo a la baraja
//                                System.out.println("Le devuelvo la carta al mazo");
                                Baraja baraja = Baraja.getInstance();
                                baraja.addCarta(Constantes.stringToCarta(Constantes.ByteToString(buffer[1])));
                            }
                        }
                    }
                    
                    if (!Constantes.ByteToString(buffer[2]).equals("00000000")){
                        System.out.println("Estas recibiendo que una persona boto una carta en la segunda pos.");
                    }
                    
                    if (!Constantes.ByteToString(buffer[3]).equals("00000000")){
                        
                        Constantes.validateTrama3(Constantes.ByteToString(buffer[3]));
                        
                        System.out.println("Listo, seteado el Repartidor.");
                    }
                    
                    if (!Constantes.ByteToString(buffer[4]).equals("00000000")){
                        System.out.println("Estas recibiendo la vira");
                        
                        if (Constantes.repartidor == 0){
                            Constantes.vira = Constantes.stringToCarta(Constantes.ByteToString(buffer[4]));
                            Connection.addByte(buffer);
                            
                            Mesa_Controller mesa_controller = Mesa_Controller.getInstance();

                            mesa_controller.UpdateHand();
                        }
                        else {
                            Constantes.vira = Constantes.stringToCarta(Constantes.ByteToString(buffer[4]));

                            Mesa_Controller mesa_controller = Mesa_Controller.getInstance();

                            mesa_controller.UpdateHand();
                        }
                        
                        System.out.println("Repartidor: "+Constantes.numero_jugador_repartidor);
                        System.out.println("Jugador: "+Constantes.numero_jugador);
                        
                        if ((Integer.parseInt(Constantes.numero_jugador_repartidor, 2) == Integer.parseInt(Constantes.numero_jugador, 2)-1))
                        {
                            System.out.println("Te toca jugar.");
                            Mesa_Controller mesaController = Mesa_Controller.getInstance();
                            mesaController.UpdateTurno(true);
                        }
                        else{
                            System.out.println("No te toca jugar.");
                            Mesa_Controller mesaController = Mesa_Controller.getInstance();
                            mesaController.UpdateTurno(false);
                        }
                        
                        
                    }
                    
                    if (!Constantes.ByteToString(buffer[5]).equals("00000000")){
                        System.out.println("Estas recibiendo que una persona hizo un canto.");
                    }
                    
                    if (!Constantes.ByteToString(buffer[6]).equals("00000000")){
                        System.out.println("Estas recibiendo lista de jugadores: "+Constantes.trama6);
                        
                        String lista = Constantes.ByteToString(buffer[6]);
                                
                        if (Constantes.repartidor == 1){
                            //Si recibo esta trama y soy el repartidor, es porque ya la envie, so es el chequeo de la lista
                            Constantes.trama6++;
                            
                            // Guardo todo para saber quien juega
                            
                            String j1 = lista.substring(0,2);
                            String j2 = lista.substring(2,4);
                            String j3 = lista.substring(4,6);
                            String j4 = lista.substring(6,8);

                            if (j1.equals("01")){
                                System.out.println("Jugador #1 esta en el juego.");
                                Constantes.jugadores.add("00");
                            }
                            
                            if (j2.equals("01")){
                                System.out.println("Jugador #2 esta en el juego.");
                                Constantes.jugadores.add("01");
                            }
                            
                            if (j3.equals("01")){
                                System.out.println("Jugador #3 esta en el juego.");
                                Constantes.jugadores.add("10");
                            }
                            
                            if (j4.equals("01")){
                                System.out.println("Jugador #4 esta en el juego.");
                                Constantes.jugadores.add("11");
                            }
                        }
                        else{
                            if (Constantes.repartidor == 0){
                                // La primera vez que la recibo, la lleno con mi asistencia
                            
                                int numeroJ = Integer.parseInt(Constantes.numero_jugador, 2);
                                String listaNew = "";
                                
                                switch (numeroJ){
                                    case 0:
                                        listaNew = "01" + lista.substring(2, lista.length());
                                        break;
                                    case 1:
                                        listaNew = lista.substring(0, 2);
                                        listaNew = listaNew + "01" + lista.substring(4, lista.length());
                                        break;
                                    case 2:
                                        listaNew = lista.substring(0, 4);
                                        listaNew = listaNew + "01" + lista.substring(6, lista.length());
                                        break;
                                    case 3:
                                        listaNew = lista.substring(0, 6);
                                        listaNew = listaNew + "01";
                                        break;
                                }
                                
                                buffer[6] = (byte) Short.parseShort(listaNew, 2);
                                
                                Constantes.trama6++;
                                
                                addByte(buffer);
                                
                                System.out.println("Enviando lista de asistencia.");
                                
                            }
                            else{
                                // La segunda, guardo todo para saber quien juega
                                
                                String j1 = lista.substring(0,2);
                                String j2 = lista.substring(2,4);
                                String j3 = lista.substring(4,6);
                                String j4 = lista.substring(6,8);

                                if (j1.equals("01")){
                                    System.out.println("Jugador #1 esta en el juego.");
                                    Constantes.jugadores.add("00");
                                }
                                
                                if (j2.equals("01")){
                                    System.out.println("Jugador #2 esta en el juego.");
                                    Constantes.jugadores.add("01");
                                }
                                
                                if (j3.equals("01")){
                                    System.out.println("Jugador #3 esta en el juego.");
                                    Constantes.jugadores.add("10");
                                }
                                
                                if (j4.equals("01")){
                                    System.out.println("Jugador #4 esta en el juego.");
                                    Constantes.jugadores.add("11");
                                }
                                
                                
                            }
                        }
                        
                    }

                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }

    }

    public static class SerialWriter implements Runnable
    {
        OutputStream out;


        public SerialWriter ( OutputStream out )
        {
            this.out = out;
        }


        public void run ()
        {
            try
            {
                while (true) {
                    if (bufferList_send.size() != 0) {
                        while (bufferList_send.size() != 0) {
                            Constantes.out.write(bufferList_send.get(0));
                            System.out.println("envio: "+bufferList_send.size());
                            bufferList_send.remove(0);
                        }
                    }
                }
            }
            catch ( IOException e )
            {
                e.printStackTrace();
            }
        }
    }
}
