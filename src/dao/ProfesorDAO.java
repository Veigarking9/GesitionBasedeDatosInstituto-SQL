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
import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.sql.SQLException;
import java.sql.ResultSet;
import vo.Profesor;


/**
 *
 * @author xabier.barreiroalber
 */
public class ProfesorDAO implements Dao<Profesor> {

    @Override
    public Profesor get(long id) {
        return new Profesor();
    }

    @Override
    public List<Profesor> getAll(Connection conn) {
        List<Profesor> listaProf = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM PROF;");

            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            listaProf = new ArrayList<Profesor>(totalRows);
            while (rs.next()) {
                String DNI = rs.getString(1);
                String pname = rs.getString(2);        
                String papel = rs.getString(3);
                String dep = rs.getString(4);  
                
                listaProf.add(new Profesor(DNI, pname, papel, dep));
            }
        } catch (SQLException e) {
            System.out.println("ERROR MOSTRANDO TODOS LOS PROFESORES: "+"\n");
            e.printStackTrace();
        }
// TODO Auto-generated method stub
        return listaProf;
    }
    public void setProfesorBatchFile(Connection conn) throws SQLException{
        String cadena;
        File f = new File("src/batch/batchprofesor.csv");
        PreparedStatement ps = conn.prepareStatement("INSERT INTO PROF(DNI, PNAME, PAPEL, DEP)"+" VALUES(?,?,?,?)");
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
                        if(i == 4) ps.setString(i, a);
                        
                       
                        
                    }
                    ps.addBatch();  
                    
                    
                }
                ps.executeBatch();
            } catch (IOException ex) { System.err.printf("ERROR EN EL INSERT POR BATCH DE LOS PROFESORES:%s\n",ex.getMessage()); }
        
    }
    public void setProfesorInsert(Connection conn, String DNI, String PNAME, String PAPEL, String DEP) throws SQLException{

        PreparedStatement ps = conn.prepareStatement("INSERT INTO PROF(DNI, PNAME, PAPEL, DEP)"+" VALUES(?,?,?,?)");
        ps.setString(1,DNI);
        ps.setString(2, PNAME);
        ps.setString(3, PAPEL);
        ps.setString(4, DEP);
        ps.executeUpdate();
        
    }
}