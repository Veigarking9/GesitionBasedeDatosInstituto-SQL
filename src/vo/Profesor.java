/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;
public class Profesor {

    private String DNI;
    private String PNAME;
    private String PAPEL;
    private String DEP;

    public Profesor(String DNI, String PNAME, String PAPEL, String DEP) {
          this.DNI = DNI;
          this.PNAME = PNAME;
          this.PAPEL = PAPEL;
          this.DEP = DEP;
    }

    public Profesor() {
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getPNAME() {
        return PNAME;
    }

    public void setPNAME(String PNAME) {
        this.PNAME = PNAME;
    }

    public String getPAPEL() {
        return PAPEL;
    }

    public void setPAPEL(String PAPEL) {
        this.PAPEL = PAPEL;
    }

    public String getDEP() {
        return DEP;
    }

    public void setDEP(String DEP) {
        this.DEP = DEP;
    }
    


}

    
