/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connection;

import Controller.Mesa_Controller;
import Model.Baraja;
import Model.Carta;
import Model.Juego;
import Model.Jugada;
import Model.Mano;
import Model.Partida;
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
import javax.swing.JOptionPane;

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
        
        byte[] trama = new byte[9];
        trama[0] = (byte) Short.parseShort(Constantes.msg_flag, 2);
        trama[1] = (byte) Short.parseShort("00000000", 2);
        trama[2] = (byte) Short.parseShort("00000000", 2);
        trama[3] = (byte) Short.parseShort(notificacion, 2);
        trama[4] = (byte) Short.parseShort("00000000", 2);
        trama[5] = (byte) Short.parseShort("00000000", 2);
        trama[6] = (byte) Short.parseShort("00000000", 2);
        trama[7] = (byte) Short.parseShort("00000000", 2);
        trama[8] = (byte) Short.parseShort(Constantes.msg_flag, 2);

        addByte(trama);
    } 
    
    public void sendCarta(Carta carta, int jugador){
        
        String notificacion = "";
        
        
        Formatter fmt = new Formatter();
        fmt.format("%02d", Integer.parseInt(Integer.toBinaryString(jugador)));
        
        notificacion += fmt.toString() + carta.getNumBin() + carta.getBinaryPinta();
        
        byte[] trama = new byte[9];
        trama[0] = (byte) Short.parseShort(Constantes.msg_flag, 2);
        trama[1] = (byte) Short.parseShort(notificacion, 2);
        trama[2] = (byte) Short.parseShort("00000000", 2);
        trama[3] = (byte) Short.parseShort("00000000", 2);
        trama[4] = (byte) Short.parseShort("00000000", 2);
        trama[5] = (byte) Short.parseShort("00000000", 2);
        trama[6] = (byte) Short.parseShort("00000000", 2);
        trama[7] = (byte) Short.parseShort("00000000", 2);
        trama[8] = (byte) Short.parseShort(Constantes.msg_flag, 2);

        addByte(trama);
        
    }
    
    public void sendCartaVira(Carta carta){
        
        String notificacion = "";
        
        notificacion += "00" + carta.getNumBin() + carta.getBinaryPinta();
        
        byte[] trama = new byte[9];
        trama[0] = (byte) Short.parseShort(Constantes.msg_flag, 2);
        trama[1] = (byte) Short.parseShort("00000000", 2);
        trama[2] = (byte) Short.parseShort("00000000", 2);
        trama[3] = (byte) Short.parseShort("00000000", 2);
        trama[4] = (byte) Short.parseShort(notificacion, 2);
        trama[5] = (byte) Short.parseShort("00000000", 2);
        trama[6] = (byte) Short.parseShort("00000000", 2);
        trama[7] = (byte) Short.parseShort("00000000", 2);
        trama[8] = (byte) Short.parseShort(Constantes.msg_flag, 2);

        addByte(trama);
        
    }
    
    
    public void sendCanto(String canto){
        
        String notificacion = "";
        
        notificacion += Constantes.numero_jugador + "00" + canto;
        
        byte[] trama = new byte[9];
        trama[0] = (byte) Short.parseShort(Constantes.msg_flag, 2);
        trama[1] = (byte) Short.parseShort("00000000", 2);
        trama[2] = (byte) Short.parseShort("00000000", 2);
        trama[3] = (byte) Short.parseShort("00000000", 2);
        trama[4] = (byte) Short.parseShort("00000000", 2);
        trama[5] = (byte) Short.parseShort(notificacion, 2);
        trama[6] = (byte) Short.parseShort("00000000", 2);
        trama[7] = (byte) Short.parseShort("00000000", 2);
        trama[8] = (byte) Short.parseShort(Constantes.msg_flag, 2);

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
            byte[] buffer = new byte[9];
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
                        
                        System.out.println("Listo, seteado el Repartidor. " + Constantes.repartidor);
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
                        
                        String trama = Constantes.ByteToString(buffer[5]);
                        
                        Juego game = Juego.getInstance();
                        
                        String jugador = trama.substring(0,2);
                        
                        String option = trama.substring(2,4);
                        
                        String canto = trama.substring(4,8);
                        String nombreCanto = "ninguno";
                        
                        if (!jugador.equals(Constantes.numero_jugador)){
                            // Si no soy la persona que envio la trama valido el canto
                            
                            if (!game.isInMyTeam(jugador)){
                                // Si el jugador que lo envio, NO ESTA en tu grupo, pregunto por el canto
                                
                                if (option.equals("00")){
                                    
                                    String optionResponse = "00";
                                    // Si aun no ha sido aceptada, le pregunto
                                    
                                    switch (canto){
                                    
                                        case "0001":
                                            nombreCanto = "Truco";
                                            break;
                                        case "0010":
                                            nombreCanto = "Retruco";
                                            break;
                                        case "0011":
                                            nombreCanto = "Envido";
                                            break;
                                    }

                                    int response = JOptionPane.showConfirmDialog(null, "Recibiendo "+ nombreCanto +" de "+jugador, "Confirmar canto", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

                                    if (response == 1 || response == -1){
                                        System.out.println("Canto rechazado.");
                                        optionResponse = "10";
                                    
                                        //Agarrar el # de partidas.
                                        int numPartidas = game.getPartidas_jugadas().size();
                                        //De la ultima partida, agarra el # de rondas.
        //                                        int numRondas = game.getPartidas_jugadas().get(numPartidas - 1).getRondas().size();
                                        // De la ultima ronda, agarra el # de jugadas.
                                        int numJugadas = game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().size();

                                        // A la ultima jugada, setea el canto y el numero del jugador
                                        Jugada jugada = game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().get(numJugadas - 1);

                                        jugada.setCanto(canto);
                                        jugada.setNumero_jugador(jugador);

                                        // Seteo de nuevo la jugada en su sitio
                                        game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().set(numJugadas - 1, jugada);

                                        Partida partida = game.getPartidas_jugadas().get(numPartidas - 1);

                                        partida.setNumero_jugador_ganador(jugador);
                                        partida.setNumero_equipo_ganador(game.playerNumGroup(jugador));

                                        game.getPartidas_jugadas().set(numPartidas - 1, partida);
                                    }
                                    else if (response == 0){
                                        System.out.println("Canto aceptado.");
                                        optionResponse = "01";
                                        
                                        //Agarrar el # de partidas.
                                        int numPartidas = game.getPartidas_jugadas().size();
                                        //De la ultima partida, agarra el # de rondas.
//                                        int numRondas = game.getPartidas_jugadas().get(numPartidas - 1).getRondas().size();
                                        // De la ultima ronda, agarra el # de jugadas.
                                        int numJugadas = game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().size();
                                        
                                        // A la ultima jugada, setea el canto y el numero del jugador
                                        game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().get(numJugadas - 1).setCanto(canto);
                                        game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().get(numJugadas - 1).setNumero_jugador(jugador);
                                        
                                        
                                        int valor = game.getPartidas_jugadas().get(numPartidas - 1).getValor_partida();
                                        game.getPartidas_jugadas().get(numPartidas - 1).setValor_partida(valor + 3);
                                        
                                        
                                    }
                                    
                                    buffer[5] = (byte) Short.parseShort(jugador + optionResponse + canto, 2);
                                    addByte(buffer);
                                    
                                }
                                else{
                                    System.out.println("El canto ya ha sido respondido, reenviando.");
                                    
                                    if (option.equals("10")){
                                        // CANTO NEGADO
                                    
                                        //Agarrar el # de partidas.
                                        int numPartidas = game.getPartidas_jugadas().size();
                                        //De la ultima partida, agarra el # de rondas.
        //                                        int numRondas = game.getPartidas_jugadas().get(numPartidas - 1).getRondas().size();
                                        // De la ultima ronda, agarra el # de jugadas.
                                        int numJugadas = game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().size();

                                        // A la ultima jugada, setea el canto y el numero del jugador
                                        Jugada jugada = game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().get(numJugadas - 1);

                                        jugada.setCanto(canto);
                                        jugada.setNumero_jugador(jugador);

                                        // Seteo de nuevo la jugada en su sitio
                                        game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().set(numJugadas - 1, jugada);

                                        Partida partida = game.getPartidas_jugadas().get(numPartidas - 1);

                                        partida.setNumero_jugador_ganador(jugador);
                                        partida.setNumero_equipo_ganador(game.playerNumGroup(jugador));

                                        game.getPartidas_jugadas().set(numPartidas - 1, partida);
                                    }
                                    else{
                                        // CANTO APROBADO

                                        //Agarrar el # de partidas.
                                        int numPartidas = game.getPartidas_jugadas().size();
                                        //De la ultima partida, agarra el # de rondas.
        //                                        int numRondas = game.getPartidas_jugadas().get(numPartidas - 1).getRondas().size();
                                        // De la ultima ronda, agarra el # de jugadas.
                                        int numJugadas = game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().size();

                                        // A la ultima jugada, setea el canto y el numero del jugador
                                        Jugada jugada = game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().get(numJugadas - 1);

                                        jugada.setCanto(canto);
                                        jugada.setNumero_jugador(jugador);

                                        // Seteo de nuevo la jugada en su sitio
                                        game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().set(numJugadas - 1, jugada);

                                        Partida partida = game.getPartidas_jugadas().get(numPartidas - 1);

                                        int valor = partida.getValor_partida();
                                        partida.setValor_partida(valor + 3);

                                        game.getPartidas_jugadas().set(numPartidas - 1, partida);
                                    }
                                    
                                    addByte(buffer);
                                }

                            }
                            else{
                                System.out.println("Tu companiero envio un canto, reenviando.");
                                
                                if (option.equals("10")){
                                    // CANTO NEGADO
                                    
                                    //Agarrar el # de partidas.
                                    int numPartidas = game.getPartidas_jugadas().size();
                                    //De la ultima partida, agarra el # de rondas.
    //                                        int numRondas = game.getPartidas_jugadas().get(numPartidas - 1).getRondas().size();
                                    // De la ultima ronda, agarra el # de jugadas.
                                    int numJugadas = game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().size();

                                    // A la ultima jugada, setea el canto y el numero del jugador
                                    Jugada jugada = game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().get(numJugadas - 1);

                                    jugada.setCanto(canto);
                                    jugada.setNumero_jugador(jugador);

                                    // Seteo de nuevo la jugada en su sitio
                                    game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().set(numJugadas - 1, jugada);

                                    Partida partida = game.getPartidas_jugadas().get(numPartidas - 1);

                                    partida.setNumero_jugador_ganador(jugador);
                                    partida.setNumero_equipo_ganador(game.playerNumGroup(jugador));

                                    game.getPartidas_jugadas().set(numPartidas - 1, partida);
                                }
                                else if (option.equals("01")){
                                    // CANTO APROBADO

                                    //Agarrar el # de partidas.
                                    int numPartidas = game.getPartidas_jugadas().size();
                                    //De la ultima partida, agarra el # de rondas.
    //                                        int numRondas = game.getPartidas_jugadas().get(numPartidas - 1).getRondas().size();
                                    // De la ultima ronda, agarra el # de jugadas.
                                    int numJugadas = game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().size();

                                    // A la ultima jugada, setea el canto y el numero del jugador
                                    Jugada jugada = game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().get(numJugadas - 1);

                                    jugada.setCanto(canto);
                                    jugada.setNumero_jugador(jugador);

                                    // Seteo de nuevo la jugada en su sitio
                                    game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().set(numJugadas - 1, jugada);

                                    Partida partida = game.getPartidas_jugadas().get(numPartidas - 1);

                                    int valor = partida.getValor_partida();
                                    partida.setValor_partida(valor + 3);

                                    game.getPartidas_jugadas().set(numPartidas - 1, partida);
                                }
                                
                                addByte(buffer);
                            }
                            
                        }
                        else{
                            // RECIBI MI TRAMA DE CANTO
                            System.out.println("Recibi mi canto");
                            
                            if (option.equals("10")){
                                // CANTO NEGADO
                                    
                                //Agarrar el # de partidas.
                                int numPartidas = game.getPartidas_jugadas().size();
                                //De la ultima partida, agarra el # de rondas.
//                                        int numRondas = game.getPartidas_jugadas().get(numPartidas - 1).getRondas().size();
                                // De la ultima ronda, agarra el # de jugadas.
                                int numJugadas = game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().size();

                                // A la ultima jugada, setea el canto y el numero del jugador
                                Jugada jugada = game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().get(numJugadas - 1);

                                jugada.setCanto(canto);
                                jugada.setNumero_jugador(jugador);

                                // Seteo de nuevo la jugada en su sitio
                                game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().set(numJugadas - 1, jugada);

                                Partida partida = game.getPartidas_jugadas().get(numPartidas - 1);

                                partida.setNumero_jugador_ganador(jugador);
                                partida.setNumero_equipo_ganador(game.playerNumGroup(jugador));

                                game.getPartidas_jugadas().set(numPartidas - 1, partida);
                                
                                
                                // Enviar trama de ganar partida 
                                notificarGanador("10");
                                System.out.println("Ya avise que gane por canto rechazado. Puntos: "+game.getPointsOfGroup(game.getNumero_de_grupo()));
                            }
                            else{
                                // CANTO APROBADO
                                
                                //Agarrar el # de partidas.
                                int numPartidas = game.getPartidas_jugadas().size();
                                //De la ultima partida, agarra el # de rondas.
//                                        int numRondas = game.getPartidas_jugadas().get(numPartidas - 1).getRondas().size();
                                // De la ultima ronda, agarra el # de jugadas.
                                int numJugadas = game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().size();

                                // A la ultima jugada, setea el canto y el numero del jugador
                                Jugada jugada = game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().get(numJugadas - 1);

                                jugada.setCanto(canto);
                                jugada.setNumero_jugador(jugador);
                                
                                // Seteo de nuevo la jugada en su sitio
                                game.getPartidas_jugadas().get(numPartidas - 1).getJugadas().set(numJugadas - 1, jugada);

                                Partida partida = game.getPartidas_jugadas().get(numPartidas - 1);
                                      
                                int valor = partida.getValor_partida();
                                partida.setValor_partida(valor + 3);
                                
                                game.getPartidas_jugadas().set(numPartidas - 1, partida);
                            }
                            
                        }
                    }
                    
                    if (!Constantes.ByteToString(buffer[6]).equals("00000000"))
                    {
                        System.out.println("Estas recibiendo lista de jugadores: "+Constantes.trama6);
                        
                        String lista = Constantes.ByteToString(buffer[6]);
                                
                        if (Constantes.repartidor == 1){
                            //Si recibo esta trama y soy el repartidor, es porque ya la envie, so es el chequeo de la lista
                            Constantes.trama6++;
                            
                            if (Constantes.trama6 == 1){
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
                            
                                Constantes.trama6++;
                                
                                addByte(buffer);
                            }
                            else{
                            
                                // COMIENZO EL JUEGO!!!!!!!!!
                                Constantes.jugador_turno = Constantes.numero_jugador_repartidor;
                                Constantes.pasarTurno();
                                
                            }
                        }
                        else{
                            if (Constantes.trama6 == 0){
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
                            else if (Constantes.trama6 == 1){
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
                                
                                Constantes.trama6++;
                                
                                addByte(buffer);
                            
                                // COMIENZO EL JUEGO!!!!!!!!!
                                Constantes.jugador_turno = Constantes.numero_jugador_repartidor;
                                Constantes.pasarTurno();
                                
                            }
                        }
                        
                    }
                    
                    
                    if (!Constantes.ByteToString(buffer[7]).equals("00000000")){
                        
                        System.out.println("Estas recibiendo una notificacion de ganador.");
                        
                        String trama = Constantes.ByteToString(buffer[7]);
                        String ganador = trama.substring(0,2);
                        String type = trama.substring(6,8);
                        
                        Juego game = Juego.getInstance();
                        
                        switch (type){
                            case "01":
                                //GANARON EL JUEGO.
                                
                                if (game.getPointsOfGroup(game.getNumero_de_grupo()) >= 4){
                                    // GANASTE EL JODIDO JUEGO.
                                    System.out.println("Ganaste, Felicidades.");
                                    JOptionPane.showMessageDialog(null, "Ganaste el juego!", "FELICIDADES!!", JOptionPane.INFORMATION_MESSAGE);
                                }
                                else {
                                    System.out.println("Perdiste, lo siento.");
                                    JOptionPane.showMessageDialog(null, "Perdiste el juego!", "LO SIENTO!!", JOptionPane.INFORMATION_MESSAGE);
                                }
                                
                                break;
                                
                            case "10":
                                // ALGUIEN GANO UNA PARTIDA
                                //Agarrar el # de partidas.
                                int numPartidas = game.getPartidas_jugadas().size();

                                // A la ultima partida, seteo el grupo ganador
                                Partida partida = game.getPartidas_jugadas().get(numPartidas - 1);
                                partida.setNumero_equipo_ganador(game.playerNumGroup(ganador));
                                
                                game.getPartidas_jugadas().set(numPartidas - 1, partida);

                                if (game.getPointsOfGroup(game.getNumero_de_grupo()) >= 4){
                                    // GANASTE EL JODIDO JUEGO.
                                    System.out.println("Ganaste, Felicidades.");
                                    notificarGanador("01");
                                }
//                                else{
                                    // SI NO SE ACABA EL JUEGO AUN, SIGO CAMBIANDO LA PARTIDA
                                    //Solicito una nueva partida
                                    game.nextPartida();
                                    if (Constantes.repartidor == 1){
                                        // si soy el repartidor, genero la baraja de nuevo.

                                        Baraja baraja = Baraja.getInstance();
                                        baraja.generateMazo();

                                        Mesa_Controller mesa = Mesa_Controller.getInstance();
                                        mesa.getBtnRepartirCards().setVisible(true);

                                    }

                                    // No importa el jugador, reinicio su mano.
                                    Mano mano = Mano.getInstance();
                                    System.out.println("RESTAURANDO MANO!!!!! "+mano.mano.size());
                                    mano.mano.remove(0);
                                    System.out.println("RESTAURANDO MANO!!!!! "+mano.mano.size());
                                    mano.mano.remove(0);
                                    System.out.println("RESTAURANDO MANO!!!!! "+mano.mano.size());
                                    mano.mano.remove(0);
                                    System.out.println("RESTAURANDO MANO!!!!! "+mano.mano.size());
                                    
                                    Constantes.vira = null;
//                                    mano.restoreHand();
                                    Mesa_Controller mesa = Mesa_Controller.getInstance();
//                                    mesa.UpdateTurno(false);
//                                    Constantes.jugador_turno = "";
                                    mesa.UpdateHand();
//                                }
                                break;
                                
                            case "11":
                                break;
                        }
                        
                        if (!ganador.equals(Constantes.numero_jugador))
                            addByte(buffer);
                        
                    }
                    
                    if (!Constantes.jugador_turno.equals("NA")){
                        Mesa_Controller mesa = Mesa_Controller.getInstance();
                        if (Constantes.jugador_turno.equals(Constantes.numero_jugador)){
                            System.out.println("ME TOCA JUGAR");
                            
                            mesa.UpdateTurno(true);
                            
                        }
                        else{
                            System.out.println("NO ME TOCA JUGAR");
                            
                            mesa.UpdateTurno(false);
                            
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
    
    /**
     * Enviar la trama notificando que gano algo
     *
     * @param tipo 01 (juego) - 10 (partida) - 11 (ronda)
     */
    public static void notificarGanador(String tipo){
        String notificacion = "";
        
        notificacion += Constantes.numero_jugador + "0000" + tipo;
        
        byte[] trama = new byte[9];
        trama[0] = (byte) Short.parseShort(Constantes.msg_flag, 2);
        trama[1] = (byte) Short.parseShort("00000000", 2);
        trama[2] = (byte) Short.parseShort("00000000", 2);
        trama[3] = (byte) Short.parseShort("00000000", 2);
        trama[4] = (byte) Short.parseShort("00000000", 2);
        trama[5] = (byte) Short.parseShort("00000000", 2);
        trama[6] = (byte) Short.parseShort("00000000", 2);
        trama[7] = (byte) Short.parseShort(notificacion, 2);
        trama[8] = (byte) Short.parseShort(Constantes.msg_flag, 2);

        addByte(trama);
    }
    
    public static void prepareTramaLista(){
        String lista = "00000000";
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
        
        byte[] trama = new byte[9];
        trama[0] = (byte) Short.parseShort(Constantes.msg_flag, 2);
        trama[1] = (byte) Short.parseShort("00000000", 2);
        trama[2] = (byte) Short.parseShort("00000000", 2);
        trama[3] = (byte) Short.parseShort("00000000", 2);
        trama[4] = (byte) Short.parseShort("00000000", 2);
        trama[5] = (byte) Short.parseShort("00000000", 2);
        trama[6] = (byte) Short.parseShort(listaNew, 2);
        trama[7] = (byte) Short.parseShort("00000000", 2);
        trama[8] = (byte) Short.parseShort(Constantes.msg_flag, 2);

        addByte(trama);
        System.out.println("Envio la lista.");
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
