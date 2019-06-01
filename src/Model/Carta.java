/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

/**
 *
 * @author Techiend
 */
public class Carta {
    
    private int numero;
    private String numBin;
    private String pinta;

    public Carta(int numero, String pinta, String binary) {
        this.numero = numero;
        this.pinta = pinta;
        this.numBin = binary;
    }

    public String getNumBin() {
        return numBin;
    }

    public void setNumBin(String numBin) {
        this.numBin = numBin;
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
        
        ruta = "/Utilidades/Images/"+this.pinta+"/"+this.numero+".jpg";
        
        return ruta;
        
    }
    
    public String getBinaryPinta(){
    
        switch (this.pinta){
        
            case "basto":
                return "00";
            case "copa":
                return "01";
            case "espada":
                return "10";
            case "oro":
                return "11";
           
        }
        
        return "00";
        
    }
    
}
