/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
String DNI_ALUMNO = rs.getString(2);
                String NOMBRE_ALUMNO = rs.getString(3);
                String DNI_PROFESOR = rs.getString(4);
                String NOMBRE_PROFESOR = rs.getString(5);
                int COD_ASIG = rs.getInt(6);
                String NOMBRE_ASIGNATURA = rs.getString(7);
 */
package vo;
public class ClaseView {
    
    private int NCLASE;
    private String DNI_ALUMNO;
    private String NOMBRE_ALUMNO;
    private String DNI_PROFESOR;
    private String NOMBRE_PROFESOR;
    private int COD_ASIG;
    private String NOMBRE_ASIGNATURA;

    

    public ClaseView(int NCLASE, String DNI_ALUMNO, String NOMBRE_ALUMNO, String DNI_PROFESOR, String NOMBRE_PROFESOR, int COD_ASIG, String NOMBRE_ASIGNATURA) {
        this.DNI_ALUMNO = DNI_ALUMNO;
        this.NOMBRE_ALUMNO = NOMBRE_ALUMNO;
        this.DNI_PROFESOR = DNI_PROFESOR;
        this.NOMBRE_PROFESOR = NOMBRE_PROFESOR;
        this.COD_ASIG = COD_ASIG;
        this.NOMBRE_ASIGNATURA = NOMBRE_ASIGNATURA;
    }

    public ClaseView() {
    }

    public int getNCLASE() {
        return NCLASE;
    }

    public void setNCLASE(int NCLASE) {
        this.NCLASE = NCLASE;
    }
    
    public String getDNI_ALUMNO() {
        return DNI_ALUMNO;
    }

    public void setDNI_ALUMNO(String DNI_ALUMNO) {
        this.DNI_ALUMNO = DNI_ALUMNO;
    }

    public String getNOMBRE_ALUMNO() {
        return NOMBRE_ALUMNO;
    }

    public void setNOMBRE_ALUMNO(String NOMBRE_ALUMNO) {
        this.NOMBRE_ALUMNO = NOMBRE_ALUMNO;
    }

    public String getDNI_PROFESOR() {
        return DNI_PROFESOR;
    }

    public void setDNI_PROFESOR(String DNI_PROFESOR) {
        this.DNI_PROFESOR = DNI_PROFESOR;
    }

    public String getNOMBRE_PROFESOR() {
        return NOMBRE_PROFESOR;
    }

    public void setNOMBRE_PROFESOR(String NOMBRE_PROFESOR) {
        this.NOMBRE_PROFESOR = NOMBRE_PROFESOR;
    }

    public int getCOD_ASIG() {
        return COD_ASIG;
    }

    public void setCOD_ASIG(int COD_ASIG) {
        this.COD_ASIG = COD_ASIG;
    }

    public String getNOMBRE_ASIGNATURA() {
        return NOMBRE_ASIGNATURA;
    }

    public void setNOMBRE_ASIGNATURA(String NOMBRE_ASIGNATURA) {
        this.NOMBRE_ASIGNATURA = NOMBRE_ASIGNATURA;
    }
}

    
