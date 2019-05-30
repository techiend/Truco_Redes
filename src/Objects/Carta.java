/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objects;

/**
 *
 * @author Techiend
 */
public class Carta {
    
    private int numero;
    private String pinta;

    public Carta(int numero, String pinta) {
        this.numero = numero;
        this.pinta = pinta;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getPinta() {
        return pinta;
    }

    public void setPinta(String pinta) {
        this.pinta = pinta;
    }

    @Override
    public String toString() {
        
        String carta_bonita = "";
        
        carta_bonita += "Numero: "+this.numero;
        carta_bonita += "\n";
        carta_bonita += "Pinta: "+this.pinta;
        
        return carta_bonita;
    }
    
    public String getImageRoute(){
    
        String ruta = "";
        
        ruta = "/utilidades/imagenes/"+this.pinta+"/"+this.numero+".jpg";
        
        return ruta;
        
    }
    
}
