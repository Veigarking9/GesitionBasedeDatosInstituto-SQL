/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;
public class Alumno {

    private String DNI;
    private String ANAME;
    private String AAPEL;
    private int TELF;

    public Alumno(String DNI, String ANAME, String AAPEL,int TELF) {
          this.DNI = DNI;
          this.ANAME = ANAME;
          this.AAPEL = AAPEL;
          this.TELF = TELF;
           
    
}

    public Alumno() {
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }
    
    public String getANAME() {
        return ANAME;
    }

    public void setANAME(String ANAME) {
        this.ANAME = ANAME;
    }

    public String getAAPEL() {
        return AAPEL;
    }

    public void setAAPEL(String AAPEL) {
        this.AAPEL = AAPEL;
    }

    public int getTELF() {
        return TELF;
    }

    public void setTELF(int TELF) {
        this.TELF = TELF;
    }
}
