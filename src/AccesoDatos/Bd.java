/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package AccesoDatos;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Breyner
 */
public class Bd {
    
    private final String DRIVER;
    private final String db;
    private final String host;
    private final String password;
    private final String user;
    
    private Connection con;
    private Statement cmd;
    
    public Bd(String vDriver, String vDb, String vHost, String vPassword, String vUser) {
        this.DRIVER=vDriver;
        if(this.DRIVER.equals("com.mysql.jdbc.Driver") || this.DRIVER.equals("org.gjt.mm.mysql.Driver")){
            this.db=vDb;
            this.host=vHost+this.db;
        }else{
            this.host=vHost;
            this.db=vDb;
        }
        this.password=vPassword;
        this.user=vUser;
    }
    
    private void Conectar(){
        try {
            Class.forName(DRIVER);
        }
        catch(ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "no se encuentra el driver");
        }

        try{
            con = DriverManager.getConnection(host,user,password);
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "error al realizar la conexion");
        }
        
        try{
            cmd=con.createStatement();
        }
        catch(Exception e){
            JOptionPane.showMessageDialog(null, "error al crear el objeto sentencia");
        }
    } 
    
    private void Desconectar() {
        try {
            cmd.close();
            con.close();
        }
        catch(SQLException ex) {
            JOptionPane.showMessageDialog(null, "error al desconectar la base de datos");
        }
    }
    
    public boolean EjecutarComando(String vSql){
        //JOptionPane.showInputDialog(null, "msg prueba",vSql);
        boolean sw=true;
        Conectar();
        try{
            cmd.execute(vSql);
        }catch(Exception e){
            sw=false;
        }
        Desconectar();
        return sw;
    }
    
    public DefaultTableModel EjecutarConsulta(String vSql){
        //JOptionPane.showInputDialog(null,"msg prueba",vSql);
        DefaultTableModel modelotabla=null;
        Conectar();
        try(ResultSet resultado = cmd.executeQuery(vSql)) {
            ResultSetMetaData metaData = resultado.getMetaData();
            
            int nCampos = metaData.getColumnCount();
            String[] lCampos = new String[nCampos];
            Object datos[]=new Object[nCampos];
            
            for(int i=0;i<nCampos;i++){
                lCampos[i]=metaData.getColumnName(i+1);
            }
            
            modelotabla = new DefaultTableModel(null,lCampos){@Override
            public boolean isCellEditable(int rowIndex, int columIndex){return false;}};
            
            while(resultado.next()){
                for(int i=0;i<nCampos;i++){                 
                    datos[i]=resultado.getObject(i+1);
                }
                modelotabla.addRow(datos);
            }
        } catch (SQLException ex) {}
        Desconectar();
        
        return modelotabla;
    }
    
}
