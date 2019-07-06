/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Carta;
import Model.Mano;
import Utilidades.Constantes;
import java.awt.Color;
import javax.swing.ImageIcon;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author Techiend
 */
public class Mesa_Controller {
    
    private Mano mano;
    private JLabel carta1;
    private JLabel carta2;
    private JLabel carta3;
    private JLabel cartaT00;
    private JLabel cartaT01;
    private JLabel cartaT10;
    private JLabel cartaT11;
    private JLabel cartaVira;
    private JLabel turno;

    public Mano getMano() {
        return mano;
    }

    public void setMano(Mano mano) {
        this.mano = mano;
    }

    public JLabel getTurno() {
        return turno;
    }

    public void setTurno(JLabel turno) {
        this.turno = turno;
    }
    private JComboBox cbCartas;

    public Mesa_Controller() {
        this.mano = Mano.getInstance();
    }
    
    public JLabel getCartaT00() {
        return cartaT00;
    }

    public void setCartaT00(JLabel cartaT00) {
        this.cartaT00 = cartaT00;
    }

    public JLabel getCartaT01() {
        return cartaT01;
    }

    public void setCartaT01(JLabel cartaT01) {
        this.cartaT01 = cartaT01;
    }

    public JLabel getCartaT10() {
        return cartaT10;
    }

    public void setCartaT10(JLabel cartaT10) {
        this.cartaT10 = cartaT10;
    }

    public JLabel getCartaT11() {
        return cartaT11;
    }

    public void setCartaT11(JLabel cartaT11) {
        this.cartaT11 = cartaT11;
    }

    public JLabel getCartaVira() {
        return cartaVira;
    }

    public void setCartaVira(JLabel cartaVira) {
        this.cartaVira = cartaVira;
    }
    
    public JComboBox getCbCartas() {
        return cbCartas;
    }

    public void setCbCartas(JComboBox cbCartas) {
        this.cbCartas = cbCartas;
    }
    
    public JLabel getCarta1() {
        return carta1;
    }

    public void setCarta1(JLabel carta1) {
        this.carta1 = carta1;
    }

    public JLabel getCarta2() {
        return carta2;
    }

    public void setCarta2(JLabel carta2) {
        this.carta2 = carta2;
    }

    public JLabel getCarta3() {
        return carta3;
    }

    public void setCarta3(JLabel carta3) {
        this.carta3 = carta3;
    }
    
    public static Mesa_Controller getInstance() {
        return NewSingletonHolder.INSTANCE;
    }
    
    private static class NewSingletonHolder {
        private static final Mesa_Controller INSTANCE = new Mesa_Controller();
    }  
    
    public void UpdateHand(){
    
        cbCartas.removeAllItems();
        for (int i = 0; i < mano.mano.size(); i++){
            Carta carta = mano.mano.get(i);
            
            cbCartas.addItem(carta.getNumero());
            if (i == 0){
//                System.out.println(carta.getImageRoute());
                carta1.setIcon(new ImageIcon(getClass().getResource(carta.getImageRoute())));
                
            }
            
            if (i == 1){
//                System.out.println(carta.getImageRoute());
                carta2.setIcon(new ImageIcon(getClass().getResource(carta.getImageRoute())));
            }
            
            if (i == 2){
//                System.out.println(carta.getImageRoute());
                carta3.setIcon(new ImageIcon(getClass().getResource(carta.getImageRoute())));
            }
        }
        
        if (Constantes.vira != null)
            cartaVira.setIcon(new ImageIcon(getClass().getResource(Constantes.vira.getImageRoute())));
        else{
            // VALIDAR QUE PUEDE COMENZAR EL TURNO

        }

        
        
    }

    public void UpdateTurno(boolean isTurno){
    
        if (isTurno){
            turno.setText("TURNO");
        }
        else{
            turno.setText("");
        }
        
    }
   
    
    
    
}
