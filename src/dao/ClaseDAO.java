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
import java.util.Date;
import vo.Clase;


/**
 *
 * @author xabier.barreiroalber
 */
public class ClaseDAO implements Dao<Clase> {

    @Override
    public Clase get(long id) {
        return new Clase();
    }

    @Override
    public List<Clase> getAll(Connection conn) {
        List<Clase> listaClase = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM CLASE;");

            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            listaClase = new ArrayList<Clase>(totalRows);
            while (rs.next()) {
                int NCLASE = rs.getInt(1);
                int ASIG = rs.getInt(2);
                String ALUM = rs.getString(3);
                String PROF = rs.getString(4);

                
                listaClase.add(new Clase(NCLASE, ASIG, ALUM, PROF));
            }
        } catch (SQLException e) {
            System.out.println("ERROR MOSTRANDO TODOS LAS CLASES: "+"\n");
            e.printStackTrace();
        }
// TODO Auto-generated method stub
        return listaClase;
    }
    public void setClaseBatchFile(Connection conn) throws SQLException{
        String cadena;
        File f = new File("src/batch/batchclase.csv");
        PreparedStatement ps = conn.prepareStatement("INSERT INTO CLASE(NCLASE, ASIG, ALUM, PROF)"+" VALUES(?,?,?,?)");
            try (FileReader fr = new FileReader(f);
                BufferedReader bfr = new BufferedReader(fr)) {
                
                while((cadena=bfr.readLine()) != null){
                    String[] cadenas = cadena.split(";");
                    int i = 0;
                    
                    for(String a : cadenas ){
                        i++;
                        if(i == 1) ps.setInt(i, Integer.parseInt(a));
                        if(i == 2) ps.setInt(i, Integer.parseInt(a));
                        if(i == 3) ps.setString(i, a);
                        if(i == 4) ps.setString(i, a);
                        
                                             
                    }
                    ps.addBatch();  
                    
                    
                }
                ps.executeBatch();
            } catch (IOException ex) { System.err.printf("ERROR EN EL INSERT POR BATCH DE LAS CLASES:%s\n",ex.getMessage()); }
        
    }
    
    
    
}