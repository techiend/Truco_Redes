/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import Model.Carta;
import Model.Mano;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTable;

/**
 *
 * @author Techiend
 */
public class Mesa_Controller {
    
    private JLabel carta1;
    private JLabel carta2;
    private JLabel carta3;
    private JTable tableCards;

    public JTable getTableCards() {
        return tableCards;
    }

    public void setTableCards(JTable tableCards) {
        this.tableCards = tableCards;
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
    
    private Mano mano;

    public Mesa_Controller() {
//        this.carta1 = carta1;
//        this.carta2 = carta2;
//        this.carta3 = carta3;
        this.mano = Mano.getInstance();
    }
    
    public static Mesa_Controller getInstance() {
        return NewSingletonHolder.INSTANCE;
    }
    
    private static class NewSingletonHolder {
        private static final Mesa_Controller INSTANCE = new Mesa_Controller();
    }  
    
    public void Update(){
    
        for (int i = 0; i < mano.mano.size(); i++){
            Carta carta = mano.mano.get(i);
            
            if (i == 0){
                System.out.println(carta.getImageRoute());
                carta1.setIcon(new ImageIcon(getClass().getResource(carta.getImageRoute())));
            }
            
            if (i == 1){
                System.out.println(carta.getImageRoute());
                carta2.setIcon(new ImageIcon(getClass().getResource(carta.getImageRoute())));
            }
            
            if (i == 2){
                System.out.println(carta.getImageRoute());
                carta3.setIcon(new ImageIcon(getClass().getResource(carta.getImageRoute())));
            }
        }
    
    }

    
   
    
    
    
}
