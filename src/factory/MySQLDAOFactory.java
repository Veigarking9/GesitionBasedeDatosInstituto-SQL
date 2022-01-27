/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import dao.AlumnoDAO;
import dao.AsignaturaDAO;
import dao.ClaseDAO;
import dao.ClaseViewDAO;
import dao.ProfesorDAO;
import pool.BasicConnectionPool;
import java.sql.SQLException;
import java.sql.Connection;


/**
 *
 * @author xabier.barreiroalber
 */
public class MySQLDAOFactory extends DAOFactory {

    final static String url = "jdbc:mysql:///INSTI";
    final static String user = "root";
    final static String password = "xabierba2002";
    static BasicConnectionPool bcp;

    public MySQLDAOFactory() {
        try {
            bcp = BasicConnectionPool.create(url, user, password);
        } catch (SQLException e) {
// TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public Connection getConnection() throws SQLException {
        return bcp.getConnection();
    }

    public boolean releaseConnection(Connection connection) {
        return bcp.releaseConnection(connection);
    }

    public int getSize() {
        return bcp.getSize();
    }
//add getUser, getURL....

    public void shutdown() throws SQLException {
        bcp.shutdown();
    }
    @Override
    public AlumnoDAO getAlumnoDAO(){
        return new AlumnoDAO();
    }
    
    @Override 
    public ProfesorDAO getProfesorDAO() { 
        return new ProfesorDAO();
    }
    
    public AsignaturaDAO getAsignaturaDAO() { 
        return new AsignaturaDAO();
    }

    
    public ClaseDAO getClaseDAO() {
       return new ClaseDAO(); 
    }
    
    public ClaseViewDAO getClaseViewDAO(){
        return new ClaseViewDAO();
    }
     
}