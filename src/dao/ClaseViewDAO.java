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
import vo.ClaseView;


/**
 *
 * @author xabier.barreiroalber
 */
public class ClaseViewDAO implements Dao<ClaseView> {

    @Override
    public ClaseView get(long id) {
        return new ClaseView();
    }
    
    public boolean CreatViewClases(Connection conn){
       try{
           Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_UPDATABLE);
           boolean rs = s.execute("create view viewClase as select NCLASE, a.DNI DNI_ALUMNO,a.ANAME NOMBRE_ALUMNO,p.DNI DNI_PROFESOR,p.PNAME NOMBRE_PROFESOR,ag.COD_ASIG CODIGO_ASIGNATURA, ag.ANAME NOMBRE_ASIGNATURA from clase c inner join alum a on a.DNI = c.ALUM inner join prof p on p.DNI = c.PROF inner join asig ag on ag.COD_ASIG = c.ASIG GROUP BY NCLASE, COD_ASIG;");
           
           
       }catch(SQLException e){
       System.out.println("ERROR CREANDO VIEW CLASE: "+"\n");
       e.printStackTrace();
       }
        
      return false;
    }
    public List<ClaseView> getAll(Connection conn) {
        List<ClaseView> listaClaseView = null;
        try {
            Statement s = conn.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = s.executeQuery("SELECT * FROM viewClase;");

            int totalRows = 0;
            rs.last();
            totalRows = rs.getRow();
            rs.beforeFirst();
            listaClaseView = new ArrayList<ClaseView>(totalRows);
            while (rs.next()) {
                int NCLASE = rs.getInt(1);
                String DNI_ALUMNO = rs.getString(2);
                String NOMBRE_ALUMNO = rs.getString(3);
                String DNI_PROFESOR = rs.getString(4);
                String NOMBRE_PROFESOR = rs.getString(5);
                int COD_ASIG = rs.getInt(6);
                String NOMBRE_ASIGNATURA = rs.getString(7);

                
                listaClaseView.add(new ClaseView(NCLASE, DNI_ALUMNO, NOMBRE_ALUMNO, DNI_PROFESOR, NOMBRE_PROFESOR, COD_ASIG, NOMBRE_ASIGNATURA));
            }
        } catch (SQLException e) {
            System.out.println("ERROR OPTENIENDO TODOS LAS CLASES POR LA VIEW DE CLASE: "+"\n");
            e.printStackTrace();
        }
// TODO Auto-generated method stub
        return listaClaseView;
    }
    
    
    
    public List<ClaseView> getAllbyNCLASE(Connection conn, int NCLASESearch) {
        List<ClaseView> listaClaseView = null;
        try {
            PreparedStatement ps = conn.prepareStatement("SELECT * FROM viewClase WHERE NCLASE = ?;");
            ps.setInt(1,NCLASESearch);
            ResultSet rs = ps.executeQuery();
            int totalRows = 0;
            totalRows = rs.getRow();
            listaClaseView = new ArrayList<ClaseView>(totalRows);
            while (rs.next()) {
                int NCLASE = rs.getInt(1);
                String DNI_ALUMNO = rs.getString(2);
                String NOMBRE_ALUMNO = rs.getString(3);
                String DNI_PROFESOR = rs.getString(4);
                String NOMBRE_PROFESOR = rs.getString(5);
                int COD_ASIG = rs.getInt(6);
                String NOMBRE_ASIGNATURA = rs.getString(7);

                
                listaClaseView.add(new ClaseView(NCLASE, DNI_ALUMNO, NOMBRE_ALUMNO, DNI_PROFESOR, NOMBRE_PROFESOR, COD_ASIG, NOMBRE_ASIGNATURA));
            }
        } catch (SQLException e) {
            System.out.println("ERROR OPTENIENDO TODOS LAS CLASES POR NUMERO DE CLASE POR LA VIEW DE CLASE: "+"\n");
            e.printStackTrace();
        }
// TODO Auto-generated method stub
        return listaClaseView;
    }
    
    
}