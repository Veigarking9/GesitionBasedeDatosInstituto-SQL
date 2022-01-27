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
import java.sql.CallableStatement;
import vo.Alumno;
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.sql.SQLException;
import java.sql.ResultSet;


/**
 *
 * @author xabier.barreiroalber
 */
public class AlumnoDAO implements Dao<Alumno> {

    @Override
    public Alumno get(long id) {
        return new Alumno();
    }
    

    @Override
    public List<Alumno> getAll(Connection conn) {
        List<Alumno> listaAlum = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM ALUM;");

            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            listaAlum = new ArrayList<Alumno>(totalRows);
            while (rs.next()) {
                String DNI = rs.getString(1);
                String aname = rs.getString(2);        
                String aapel = rs.getString(3);
                int telf = rs.getInt(4);  
                
                listaAlum.add(new Alumno(DNI, aname, aapel, telf));
            }
        } catch (SQLException e) {
            System.out.println("ERROR MOSTRANDO TODOS LOS EMPLEADOS: "+"\n");

            e.printStackTrace();
        }
// TODO Auto-generated method stub
        return listaAlum;
    }
    public void setAlumnoBatchFile(Connection conn) throws SQLException{
        String cadena;
        File f = new File("src/batch/batchalumno.csv");
        PreparedStatement ps = conn.prepareStatement("INSERT INTO ALUM(DNI, ANAME, AAPEL, TELF)"+" VALUES(?,?,?,?)");
            try (FileReader fr = new FileReader(f);
                BufferedReader bfr = new BufferedReader(fr)) {
                
                while((cadena=bfr.readLine()) != null){
                    String[] cadenas = cadena.split(";");
                    int i = 0;
                    
                    for(String a : cadenas ){
                        i++;
                        if(i == 1) ps.setString(i, a);
                        if(i == 2) ps.setString(i, a);
                        if(i == 3) ps.setString(i, a);
                        if(i == 4) ps.setInt(4 ,Integer.parseInt(a));
                        
                       
                        
                    }
                    ps.addBatch();  
                    
                    
                }
                ps.executeBatch();
            } catch (IOException ex) { System.err.printf("ERROR EN EL INSERT POR BATCH DE LOS ESTUDIANTES: %s\n",ex.getMessage()); }
        
    }
    public List<Alumno> getByDNI(Connection conn, String DNIsearch){
        
        List<Alumno> listaAlumDNI = new ArrayList<Alumno>();
        
        try{
            
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM ALUM WHERE DNI = ?;");
            ps.setString(1,DNIsearch);
            ResultSet rs = ps.executeQuery();
            listaAlumDNI = new ArrayList<Alumno>();
            while (rs.next()) {
                String DNI = rs.getString(1);
                String aname = rs.getString(2);        
                String aapel = rs.getString(3);
                int telf = rs.getInt(4);  
                
                listaAlumDNI.add(new Alumno(DNI, aname, aapel, telf));
            }  
        }catch(SQLException e){
            System.out.println("ERROR BUSCANDO POR DNI: "+"\n");
            e.printStackTrace();
            
        }
     return listaAlumDNI;
    }
    public void setAlumnoInsert(Connection conn, String DNI, String ANAME, String AAPEL, int TELF) throws SQLException{

        PreparedStatement ps = conn.prepareStatement("INSERT INTO ALUM(DNI, ANAME, AAPEL, TELF)"+" VALUES(?,?,?,?)");
        ps.setString(1,DNI);
        ps.setString(2, ANAME);
        ps.setString(3, AAPEL);
        ps.setInt(4 ,TELF);
        ps.executeUpdate();
        
    }
}