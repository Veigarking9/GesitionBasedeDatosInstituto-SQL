/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.security.interfaces.RSAKey;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import vo.Alumno;
import vo.Asignatura;


/**
 *
 * @author xabier.barreiroalber
 */
public class AsignaturaDAO implements Dao<Asignatura> {

    @Override
    public Asignatura get(long id) {
        return new Asignatura();
    }

    public List<Asignatura> getAll(Connection conn) {
        List<Asignatura> listaAsig = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM ASIG;");

            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            listaAsig = new ArrayList<Asignatura>(totalRows);
            while (rs.next()) {
                int cod_asig = rs.getInt(1);
                String aname = rs.getString(2);
                listaAsig.add(new Asignatura(cod_asig, aname));
            }
        } catch (SQLException e) {
            System.out.println("ERROR MOSTRANDO TODOS LAS ASIGNATURAS: "+"\n");
            e.printStackTrace();
        }
// TODO Auto-generated method stub
        return listaAsig;
    }
    public void setAsignaturaBatchFile(Connection conn) throws SQLException{
        String cadena;
        File f = new File("src/batch/batchasignatura.csv");
        PreparedStatement ps = conn.prepareStatement("INSERT INTO ASIG(COD_ASIG, ANAME)"+" VALUES(?,?)");
            try (FileReader fr = new FileReader(f);
                BufferedReader bfr = new BufferedReader(fr)) {
                
                while((cadena=bfr.readLine()) != null){
                    String[] cadenas = cadena.split(";");
                    int i = 0;
                    
                    for(String a : cadenas ){
                        i++;
                        if(i == 1) ps.setInt(i ,Integer.parseInt(a));
                        if(i == 2) ps.setString(i, a);
                           
                    }
                    ps.addBatch();  
                    
                    
                }
                ps.executeBatch();
            } catch (IOException ex) { System.err.printf("ERROR EN EL INSERT POR BATCH DE LAS ASIGNATURAS:%s\n",ex.getMessage()); }
        
    }
    public List<Asignatura> getByCOD_ASIG(Connection conn, int CODsearch){
        
        List<Asignatura> listaAsigCOD = new ArrayList<Asignatura>();
        
        try{
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ASIG WHERE COD_ASIG = ?;");
            ps.setInt(1,CODsearch);
            ResultSet rs = ps.executeQuery();
            listaAsigCOD = new ArrayList<Asignatura>();
            while (rs.next()) {
                int cod_asig = rs.getInt(1);
                String aname = rs.getString(2);
                listaAsigCOD.add(new Asignatura(cod_asig, aname));
            }
        } catch (SQLException e) {
            System.out.println("ERROR MOSTRANDO LAS ASIGNATURAS POR COD_ASIG: "+"\n");
            e.printStackTrace();
        }

        return listaAsigCOD;
    }
    
    public void setAsignaturaInsert(Connection conn, String ANAME) throws SQLException{
        

        PreparedStatement ps = conn.prepareStatement("INSERT INTO ASIG(COD_ASIG, ANAME)"+" VALUES(?,?)");
        ps.setInt(1,0);
        ps.setString(2, ANAME);
        ps.executeUpdate();
        
    }
}