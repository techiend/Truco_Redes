/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Objects.Mano;
import Utilidades.Constantes;
import com.fazecast.jSerialComm.SerialPort;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Formatter;

/**
 *
 * @author Techiend
 */
public class Connection {

    public Connection() {
    }
    
    public static void connect (String portName)
    {

        String flag = "01111110";

        SerialPort comPort = SerialPort.getCommPort(portName);
        comPort.openPort();
        comPort.setComPortTimeouts(SerialPort.TIMEOUT_READ_SEMI_BLOCKING, 0, 0);

        Constantes.in = comPort.getInputStream();
        Constantes.out = comPort.getOutputStream();

            
        (new Thread(new SerialReader(Constantes.in))).start();
        (new Thread(new SerialWriter(Constantes.out))).start();

//        byte[] trama = new byte[5];
//        trama[0] = (byte) Short.parseShort(flag, 2);
//        trama[1] = (byte) Short.parseShort("00110000", 2);
//        trama[2] = (byte) Short.parseShort("00000000", 2);
//        trama[3] = (byte) Short.parseShort("00000000", 2);
//        trama[4] = (byte) Short.parseShort(flag, 2);

//        addByte(trama);

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
                        System.out.println("Estas recibiendo Cartas");
                        Mano mano = Mano.getInstance();
                        mano.addCarta(Constantes.stringToCarta(Constantes.ByteToString(buffer[1])));
                    }
                    
                    if (!Constantes.ByteToString(buffer[2]).equals("00000000")){
                        System.out.println("Estas recibiendo algo en la segunda pos.");
                    }
                    
                    if (!Constantes.ByteToString(buffer[3]).equals("00000000")){
                        System.out.println("Estas recibiendo algo en la tercera pos.");
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
                    if (Constantes.bufferList_send.size() != 0) {
                        while (Constantes.bufferList_send.size() != 0) {
                            this.out.write(Constantes.bufferList_send.get(0));
                            Constantes.bufferList_send.remove(0);
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
