/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import AccesoDatos.Bd;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Breyner
 */
public class ModeloSimple {
    private final Bd bd;
    protected final String Tabla;
    private final String[] Campos;
    private String Alias="El Registro";
    
    //-----------------------Metodos Contructores-------------------------------
    public ModeloSimple(String vTabla, String vAlias) {
        bd = new Bd("com.mysql.jdbc.Driver","test","jdbc:mysql://127.0.0.1/","12345","root");
        //bd = new Bd("oracle.jdbc.driver.OracleDriver",null,"jdbc:oracle:thin:@localhost:1521:xe","oracle","breyner");
        this.Tabla = vTabla;
        this.Alias = vAlias;
        this.Campos = getNombresCampos(this.Tabla);
    }
    
    private String[] getNombresCampos(String vTabla){
        String[] campos;
        DefaultTableModel dtm=bd.EjecutarConsulta("SELECT * FROM "+vTabla);
        campos=new String[dtm.getColumnCount()];
        for(int i=0;i<=dtm.getColumnCount()-1;i++){
            campos[i]=dtm.getColumnName(i);
        }
        return campos;
    }
    //-----------------------Metodos Privados-----------------------------------
    private String prepararCondicion(String[] vCampos){
        String sql;
        if(vCampos==null){
            sql="";
        }else{
            sql=" WHERE ";
            boolean sw = false;
            //
            //preparamos condicion
            //
            for(int i=0;i<this.Campos.length;i++){
                if(i==this.Campos.length-1){
                    if(vCampos[i]==null){

                    }else{
                        if(sw){
                            sql+=" AND "+this.Campos[i]+"='"+vCampos[i]+"'";
                        }else{
                            sql+=this.Campos[i]+"='"+vCampos[i]+"'";
                        }
                    }
                }else{
                    if(vCampos[i]==null){

                    }else{
                        if(sw){
                            sql+=" AND "+this.Campos[i]+"='"+vCampos[i]+"'";
                            sw=true;
                        }else{
                            sql+=this.Campos[i]+"='"+vCampos[i]+"'";
                            sw=true;
                        }
                    }
                }
            }
        }
        
        return sql;
    }
    
    private String prepararCondicionLike(String[] vCampos, String valor){
        String sql;
        if(vCampos==null){
            sql="";
        }else{
            sql=" WHERE ";
            boolean sw = false;
            //
            //preparamos condicion
            //
            for(int i=0;i<this.Campos.length;i++){
                if(i==this.Campos.length-1){
                    if(vCampos[i]==null){

                    }else{
                        if(sw){
                            sql+=" OR "+this.Campos[i]+" LIKE '%"+valor+"%'";
                        }else{
                            sql+=this.Campos[i]+" LIKE '%"+valor+"%'";
                        }
                    }
                }else{
                    if(vCampos[i]==null){

                    }else{
                        if(sw){
                            sql+=" OR "+this.Campos[i]+" LIKE '%"+valor+"%'";
                            sw=true;
                        }else{
                            sql+=this.Campos[i]+" LIKE '%"+valor+"%'";
                            sw=true;
                        }
                    }
                }
            }
        }
        
        return sql;
    }
    //-----------------------Metodos Protegidos---------------------------------
    protected DefaultTableModel getRead(String[] vCampos, String[] vCondicion, String[] vCamposGlobales, String vTabla){
        boolean sw=false;
        String sql ="SELECT ";
        //
        //preparamos campos
        //
        if(vCampos==null){
            sql+="* FROM "+vTabla;
        }else{
            for(int i=0;i<vCamposGlobales.length;i++){
                if(i==vCamposGlobales.length-1){
                    if(vCampos[i]==null){

                    }else{
                        if(sw){
                            sql+=", "+vCamposGlobales[i]+" FROM "+vTabla;
                        }else{
                            sql+=vCamposGlobales[i]+" FROM "+vTabla;
                        }
                    }
                }else{
                    if(vCampos[i]==null){

                    }else{
                        if(sw){
                            sql+=", "+vCamposGlobales[i];
                            sw=true;
                        }else{
                            sql+=vCamposGlobales[i];
                            sw=true;
                        }
                    }
                }
            }
        }
        //
        //preparamos condicion
        //
        sql += prepararCondicion(vCondicion);
        //regresamos consulta
        return bd.EjecutarConsulta(sql);
    }
    
    protected DefaultTableModel getReadLike(String[] vCampos, String[] vCondicion, String valor, String[] vCamposGlobales, String vTabla){
        boolean sw=false;
        String sql ="SELECT ";
        //
        //preparamos campos
        //
        if(vCampos==null){
            sql+="* FROM "+vTabla;
        }else{
            for(int i=0;i<vCamposGlobales.length;i++){
                if(i==vCamposGlobales.length-1){
                    if(vCampos[i]==null){

                    }else{
                        if(sw){
                            sql+=", "+vCamposGlobales[i]+" FROM "+vTabla;
                        }else{
                            sql+=vCamposGlobales[i]+" FROM "+vTabla;
                        }
                    }
                }else{
                    if(vCampos[i]==null){

                    }else{
                        if(sw){
                            sql+=", "+vCamposGlobales[i];
                            sw=true;
                        }else{
                            sql+=vCamposGlobales[i];
                            sw=true;
                        }
                    }
                }
            }
        }
        //
        //preparamos condicion
        //
        sql += prepararCondicionLike(vCondicion, valor);
        //regresamos consulta
        return bd.EjecutarConsulta(sql);
    }
    //------------------------------CRUD----------------------------------------
    public String Create(String[] Values){
        String msg;
        boolean sw=false;
        String sql ="INSERT INTO "+this.Tabla+" (";
        //
        //preparamos campos
        //
        for(int i=0;i<this.Campos.length;i++){
            if(i==this.Campos.length-1){
                sql+=this.Campos[i]+ ") VALUES (";
            }else{
                sql+=this.Campos[i]+ ", ";
            }
        }
        //
        //preparamos valores
        //
        for(int i=0;i<this.Campos.length;i++){
            if(i==this.Campos.length-1){
                if(Values[i]==null){
                    sql+="null)";
                }else{
                    sql+="'"+Values[i]+"')";
                }
            }else{
                if(sw){
                    if(Values[i]==null){
                        sql+="null, ";
                    }else{
                        sql+="'"+Values[i]+"',";
                        sw=true;
                    }
                }else{
                    if(Values[i]==null){
                        sql+="null, ";
                        sw=true;
                    }else{
                        sql+="'"+Values[i]+"', ";
                        sw=true;
                    }
                }
            }
        }
        //
        //ejecutamos
        //
        if(bd.EjecutarComando(sql)){
            msg= this.Alias+" Se A Creado Correctamente";
        }else{
            msg="No Se A Podido Crear "+this.Alias;
        }
        return msg;
    }
    
    public DefaultTableModel Read(String[] vCampos, String[] vCondicion){
        return getRead(vCampos, vCondicion, this.Campos, this.Tabla);
    }
    
    public String Update(String[] Values, String[] vCampos){
        String msg;
        boolean sw=false;
        String sql="UPDATE "+this.Tabla+" SET ";
        //
        //preparamos campos
        //
        for(int i=0;i<this.Campos.length;i++){
            if(i==this.Campos.length-1){
                if(Values[i]==null){
                    
                }else{
                    if(sw){
                        sql+=", "+this.Campos[i]+"='"+Values[i]+"'";
                    }else{
                        sql+=this.Campos[i]+"='"+Values[i]+"'";
                    }
                }
            }else{
                if(Values[i]==null){
                    
                }else{
                    if(sw){
                        sql+=", "+this.Campos[i]+"='"+Values[i]+"'";
                        sw=true;
                    }else{
                        sql+=this.Campos[i]+"='"+Values[i]+"'";
                        sw=true;
                    }
                }
            }
        }
        //
        //preparamos condicion
        //
        sql += prepararCondicion(vCampos);
        //
        //ejecutamos
        //
        if(bd.EjecutarComando(sql)){
            msg= this.Alias+" Se A Actualizado Correctamente";
        }else{
            msg="No Se A Podido Actualizar "+this.Alias;
        }
        return msg;
    }
    
    public String Delete(String[] vCampos){
        String msg;
        String sql="DELETE FROM "+Tabla;
        //
        //calculamos condicion
        //
        sql += prepararCondicion(vCampos);
        //
        //ejecutamos
        //
        if(bd.EjecutarComando(sql)){
            msg= this.Alias+" Se A Eliminado Correctamente";
        }else{
            msg="No Se A Podido Eliminar "+this.Alias;
        }
        return msg;
    }
    //--------------------------Metodos Especiales------------------------------
    public DefaultTableModel ReadLike(String[] vCampos, String[] vCondicion, String valor){
        return getReadLike(vCampos, vCondicion, valor, this.Campos, this.Tabla);
    }
    //--------------------------Metodos Auxiliares------------------------------
    public DefaultTableModel auxConsulta(String sql){
        return this.bd.EjecutarConsulta(sql);
    }
    
    public boolean auxComando(String sql){
        return this.bd.EjecutarComando(sql);
    }
    
}
