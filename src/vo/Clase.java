/*

 */
package vo;

import java.util.Date;

public class Clase {
    
    private int NCLASE;
    private int ASIG;
    private String ALUM;
    private String PROF;

      

    

    public Clase(int NCLASE, int ASIG, String ALUM, String PROF) {
          this.NCLASE = NCLASE;
          this.ASIG = ASIG;
          this.ALUM = ALUM;
          this.PROF = PROF;

          
           
}

    public Clase() {
    }
    
    public int getNCLASE() {
        return NCLASE;
    }

    public void setNCLASE(int NCLASE) {
        this.NCLASE = NCLASE;
    }
    
    public int getASIG() {
        return ASIG;
    }

    public void setASIG(int ASIG) {
        this.ASIG = ASIG;
    }

    public String getALUM() {
        return ALUM;
    }

    public void setALUM(String ALUM) {
        this.ALUM = ALUM;
    }

    public String getPROF() {
        return PROF;
    }

    public void setPROF(String PROF) {
        this.PROF = PROF;
    }


    
    
}
