/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;
public class Asignatura {
    
    
    private int COD_ASIG;
    private String ANAME;


    public Asignatura(int COD_ASIG, String ANAME) {
        this.COD_ASIG = COD_ASIG;
        this.ANAME = ANAME;

    }

    public Asignatura() {
    }
    
    

    
    public int getCOD_ASIG() {
        return COD_ASIG;
    }

    public void setCOD_ASIG(int COD_ASIG) {
        this.COD_ASIG = COD_ASIG;
    }

    public String getANAME() {
        return ANAME;
    }

    public void setANAME(String ANAME) {
        this.ANAME = ANAME;
    }


    
}
