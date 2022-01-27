/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package baseinstituto;

import baseinstituto.Vista;
import dao.AlumnoDAO;
import dao.AsignaturaDAO;
import dao.ProfesorDAO;
import dao.ClaseDAO;
import dao.ClaseViewDAO;
import factory.DAOFactory;
import java.lang.reflect.AnnotatedElement;
import java.util.ArrayList;
import java.util.List;
import vo.Alumno;
import vo.Asignatura;
import vo.Clase;
import vo.ClaseView;
import vo.Profesor;

/**
 *
 * @author xabier.barreiroalber
 */
public class Controller {

    static List<Alumno> alumnos;
    static List<Alumno> alumnoDNI;

    static List<Profesor> profesores;

    static List<Asignatura> asignaturas;
    static List<Asignatura> asignaturasCOD;

    static List<Clase> clases;
    
    static List<ClaseView> clasesview;

    public static void main(String[] args) throws Exception {
        alumnoDNI = new ArrayList<Alumno>();

//Create factory
        DAOFactory mySQLFactory = DAOFactory.getDAOFactory(DAOFactory.MYSQL);
//Create dAO
        AlumnoDAO alumDAO = (AlumnoDAO) mySQLFactory.getAlumnoDAO();
        ProfesorDAO profDAO = (ProfesorDAO) mySQLFactory.getProfesorDAO();
        AsignaturaDAO asigDAO = (AsignaturaDAO) mySQLFactory.getAsignaturaDAO();
        ClaseDAO clasDAO = (ClaseDAO) mySQLFactory.getClaseDAO();
        ClaseViewDAO clasViewDAO =  mySQLFactory.getClaseViewDAO();


//Inicializar todo
//...
//Vista
        Vista v = new Vista();
        int opcion = v.mostrarMenu();
        switch (opcion) {

            
            case 1:
            //GET ALL ALUMNOS
            try {
                alumnos = alumDAO.getAll(mySQLFactory.getConnection());
            } catch (Exception e) {
                System.out.println("ERROR EJECUTANDO LA OPTENCIÓN DE TODOS LOS ALUMNOS PARA MOSTRARLAS EN LA VISTA: "+"\n");
                e.printStackTrace();
            }
            
            //MOSTRAR ALUMNOS
            String output = new String();
            for (int i = 0; i < alumnos.size(); i++) {
                output += " DNI -> " + alumnos.get(i).getDNI() + " | NOMBRE -> " + alumnos.get(i).getANAME() + " | APELLIDOS -> " + alumnos.get(i).getAAPEL() + " | TELEFONO -> " + alumnos.get(i).getTELF() + "\n";
            }
            v.showMessage(output);
            break;
            
            
            

            
            case 2:
                //GET ALL PROFESORES
                try {
                    profesores = profDAO.getAll(mySQLFactory.getConnection());
                } catch (Exception e) {
                    System.out.println("ERROR EJECUTANDO LA OPTENCIÓN DE TODOS LOS PROFESORES PARA MOSTRARLAS EN LA VISTA: "+"\n");
                    e.printStackTrace();
                }
                
                //MOSTRAR PROFESORES
                String output2 = new String();
                for (int i = 0; i < profesores.size(); i++) {
                    output2 += " DNI -> " + profesores.get(i).getDNI() + " | NOMBRE -> " + profesores.get(i).getPNAME() + " | APELLIDOS -> " + profesores.get(i).getPAPEL() + " | DEPARTAMENTO -> " + profesores.get(i).getDEP() + "\n";
                }
                break;

               
            case 3:
                //GET ALL ASIGNATURAS
                try {
                    asignaturas = asigDAO.getAll(mySQLFactory.getConnection());
                } catch (Exception e) {
                    System.out.println("ERROR EJECUTANDO LA OPTENCIÓN DE TODAS LAS ASIGNATURAS PARA MOSTRARLAS EN LA VISTA: "+"\n");
                    e.printStackTrace();
                }
                
                //MOSTRAR ASIGNATURAS 
                String output3 = new String();
                for (int i = 0; i < asignaturas.size(); i++) {
                    output3 += " CÓDIGO DE ASIGNATURA -> " + asignaturas.get(i).getCOD_ASIG() + " | NOMBRE -> " + asignaturas.get(i).getANAME() + "\n";
                }
                break;

              
            case 4:
                //GET ALL CLASES
                try {
                    clases = clasDAO.getAll(mySQLFactory.getConnection());
                } catch (Exception e) {
                    System.out.println("ERROR EJECUTANDO LA OPTENCIÓN DE TODAS LAS CLASES PARA MOSTRARLAS EN LA VISTA: "+"\n");
                    e.printStackTrace();
                }
                
                //MOSTRAR CLASES  
                String output4 = new String();
                for (int i = 0; i < clases.size(); i++) {
                    output4 += "NÚMERO DE CLASE -> " + clases.get(i).getNCLASE() + " | PROFESOR -> " + clases.get(i).getPROF() + " | ALUMNO -> " + clases.get(i).getALUM() + " | ASIGNATURA -> " + clases.get(i).getASIG() + "\n";
                }
                break;
                

            //INSERT DE ALUMNOS POR BATCH LEYENDO EN UN FICHERO    
            case 5:
                try {
                    alumDAO.setAlumnoBatchFile(mySQLFactory.getConnection());
                } catch (Exception e) {
                    System.out.println("ERROR EJECUTANDO EL INSERT POR BATCH DE ALUMNOS: "+"\n");
                    e.printStackTrace();
                }
                break;
            

            //INSERT DE PROFESORES POR BATCH LEYENDO EN UN FICHERO     
            case 6:
                try {
                    profDAO.setProfesorBatchFile(mySQLFactory.getConnection());
                } catch (Exception e) {
                    System.out.println("ERROR EJECUTANDO EL INSERT POR BATCH DE PROFESORES: "+"\n");
                    e.printStackTrace();
                }
                break;
            

            //INSERT DE ASIGNATURAS POR BATCH LEYENDO EN UN FICHERO     
            case 7:
                try {
                    asigDAO.setAsignaturaBatchFile(mySQLFactory.getConnection());
                } catch (Exception e) {
                    System.out.println("ERROR EJECUTANDO EL INSERT POR BATCH DE ASIGNATURAS: "+"\n");
                    e.printStackTrace();
                }
                break;
            

            //INSERT DE CLASES POR BATCH LEYENDO EN UN FICHERO     
            case 8:
                try {
                    clasDAO.setClaseBatchFile(mySQLFactory.getConnection());
                } catch (Exception e) {
                    System.out.println("ERROR EJECUTANDO EL INSERT POR BATCH DE LAS CLASES: "+"\n");
                    e.printStackTrace();
                }
                break;


            case 9:
                //GET ALUMNOS POR DNI    
                try {
                    String DNIsearch = v.getDNI();
                    alumnoDNI = alumDAO.getByDNI(mySQLFactory.getConnection(), DNIsearch);
                } catch (Exception e) {
                    System.out.println("ERROR EJECUTANDO LA OPTENCIÓN DE ALUMNOS POR DNI PARA MOSTRARLAS EN LA VISTA: "+"\n");
                    e.printStackTrace();
                }

                //MOSTRAR ALUMNOS POR DNI
                String output5 = new String();
                for (int i = 0; i < alumnoDNI.size(); i++) {
                    output5 += " DNI -> " + alumnoDNI.get(i).getDNI() + " | NOMBRE -> " + alumnoDNI.get(i).getANAME() + " | APELLIDOS -> " + alumnoDNI.get(i).getAAPEL() + " | TELEFONO -> " + alumnoDNI.get(i).getTELF() + "\n";
                }
                v.showMessage(output5);
                break;

            case 10:
                //GET ASIGNATURAS PRO COD_ASIG
                try {
                    int CODsearch = v.getCODsearch();
                    asignaturasCOD = asigDAO.getByCOD_ASIG(mySQLFactory.getConnection(), CODsearch);
                } catch (Exception e) {
                    System.out.println("ERROR EJECUTANDO LA OPTENCIÓN DE LAS ASIGNATURAS POR CÓDIGO PARA MOSTRARLAS EN LA VISTA: "+"\n");
                    e.printStackTrace();
                }
                
                
                //MOSTRAR ASIGNATURAS POR COD_ASIG
                String output6 = new String();
                for (int i = 0; i < asignaturasCOD.size(); i++) {
                    output6 += " CÓDIGO DE ASIGNATURA -> " + asignaturasCOD.get(i).getCOD_ASIG() + " | NOMBRE -> " + asignaturasCOD.get(i).getANAME() + "\n";
                }
                v.showMessage(output6);
                break;
            
                
            //CREAR VIEW CLASEVIEW    
            case 11:
                try {
                    clasViewDAO.CreatViewClases(mySQLFactory.getConnection());
                } catch (Exception e) {
                    System.out.println("ERROR EJECUTANDO LA OPTENCIÓN DE TODAS LAS CLASES COMPLETAS PARA MOSTRARLAS EN LA VISTA: "+"\n");
                    e.printStackTrace();
                }
                break;
                
            case 12: 
                //GET CLASE COMPLETAS
                try {
                    clasesview = clasViewDAO.getAll(mySQLFactory.getConnection());
                } catch (Exception e) {
                    System.out.println("ERROR EJECUTANDO LA OPTENCIÓN DE LA TABLA CLASEVIEW PARA MOSTRARLAS EN LA VISTA: "+"\n");
                    e.printStackTrace();
                }
                
                //MOSTRAR CLASES COMPLETAS  
                String output7 = new String();
                for (int i = 0; i < clasesview.size(); i++) {
                    output7 += "NÚMERO DE CLASE -> " + clasesview.get(i).getNCLASE() +" | DNI ALUMNO -> " + clasesview.get(i).getDNI_ALUMNO()+" | NOMBRE ALUMNO -> " + clasesview.get(i).getNOMBRE_ALUMNO()+" | DNI PROFESOR -> " + clasesview.get(i).getDNI_PROFESOR()+ " | NOMBRE PROFESOR -> " + clasesview.get(i).getNOMBRE_PROFESOR()+" | CÓDIGO ASIGNATURA -> " + clasesview.get(i).getCOD_ASIG()+ " | NOMBRE ASIGNATURA -> " + clasesview.get(i).getNOMBRE_ASIGNATURA()+ "\n";
                }
                v.showMessage(output7);
                break;
                
            case 13:
                //GET CLASES COMPLETAS POR NUMERO DE CLASE
                try {
                    int NCLASESearch = v.getNCLASESearch();
                    clasesview = clasViewDAO.getAllbyNCLASE(mySQLFactory.getConnection(), NCLASESearch);
                } catch (Exception e) {
                    System.out.println("ERROR EJECUTANDO LA OPTENCIÓN DE LA TABLA CLASEVIEW POR NÚMERO DE CLASE PARA MOSTRARLAS EN LA VISTA: "+"\n");
                    e.printStackTrace();
                }
                
                
                //MOSTRAR CLASES COMPLETAS POR NÚMERO DE CLASE
                String output8 = new String();
                for (int i = 0; i < clasesview.size(); i++) {
                    output8 += "NÚMERO DE CLASE -> " + clasesview.get(i).getNCLASE() +" | DNI ALUMNO -> " + clasesview.get(i).getDNI_ALUMNO()+" | NOMBRE ALUMNO -> " + clasesview.get(i).getNOMBRE_ALUMNO()+" | DNI PROFESOR -> " + clasesview.get(i).getDNI_PROFESOR()+ " | NOMBRE PROFESOR -> " + clasesview.get(i).getNOMBRE_PROFESOR()+" | CÓDIGO ASIGNATURA -> " + clasesview.get(i).getCOD_ASIG()+ " | NOMBRE ASIGNATURA -> " + clasesview.get(i).getNOMBRE_ASIGNATURA()+ "\n";
                }
                v.showMessage(output8);
                break;
                
            case 14:
                //INSERT ALMUNOS   
                    String DNI = v.getDNI();
                    String ANAME = v.getNAMEInsert();
                    String AAPEL = v.getAPELInsert();
                    int TELF = v.getTELFInsert();
                try {
                    alumDAO.setAlumnoInsert(mySQLFactory.getConnection(), DNI, ANAME, AAPEL, TELF);
  
                } catch (Exception e) {
                    System.out.println("ERROR INTRODUCIENDO ALUMNOS");
                    e.printStackTrace();
                    
                }
                break;
            
            case 15:
                //INSERT PROFESOR   
                    DNI = v.getDNI();
                    String PNAME = v.getNAMEInsert();
                    String PAPEL = v.getAPELInsert();
                    String DEP = v.getDEPInsert();
                try {
                    profDAO.setProfesorInsert(mySQLFactory.getConnection(), DNI, PNAME, PAPEL, DEP);
  
                } catch (Exception e) {
                    System.out.println("ERROR INTRODUCIENDO PROFESORES");
                    e.printStackTrace();
                    
                }
               
            case 16:
                //INSERT ASIGNATURAS   
                    ANAME = v.getNAMEInsert();
                try {
                    asigDAO.setAsignaturaInsert(mySQLFactory.getConnection(), ANAME);
  
                } catch (Exception e) {
                    System.out.println("ERROR INTRODUCIENDO ASIGNATURAS");
                    e.printStackTrace();
                    
                }
                break;
                
                

        }
                

                
                

        }  
    }


