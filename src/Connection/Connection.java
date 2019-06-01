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
        
        byte[] trama = new byte[5];
        trama[0] = (byte) Short.parseShort(Constantes.msg_flag, 2);
        trama[1] = (byte) Short.parseShort("00000000", 2);
        trama[2] = (byte) Short.parseShort("00000000", 2);
        trama[3] = (byte) Short.parseShort(notificacion, 2);
        trama[4] = (byte) Short.parseShort(Constantes.msg_flag, 2);

        addByte(trama);
    } 
    
    public void sendCarta(Carta carta, int jugador){
        
        String notificacion = "";
        
        
        Formatter fmt = new Formatter();
        fmt.format("%02d", Integer.parseInt(Integer.toBinaryString(jugador)));
        
        notificacion += fmt.toString() + carta.getNumBin() + carta.getBinaryPinta();
        
        byte[] trama = new byte[5];
        trama[0] = (byte) Short.parseShort(Constantes.msg_flag, 2);
        trama[1] = (byte) Short.parseShort(notificacion, 2);
        trama[2] = (byte) Short.parseShort("00000000", 2);
        trama[3] = (byte) Short.parseShort("00000000", 2);
        trama[4] = (byte) Short.parseShort(Constantes.msg_flag, 2);

        addByte(trama);
        
    }
    
    public static void addByte(byte[] trama){
        try {
            Constantes.out.write(trama);
//            System.out.println("Envio");
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
            byte[] buffer = new byte[5];
            int len = -1;
            try
            {
                while ( ( len = this.in.read(buffer)) > -1 )
                {
                    while (buffer.length<4){}

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
//                        System.out.println("Estas recibiendo algo en la tercera pos.");
                        
                        Constantes.validateTrama3(Constantes.ByteToString(buffer[3]));
                        
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
