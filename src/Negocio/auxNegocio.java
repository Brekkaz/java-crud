/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import AccesoDatos.Bd;
import javax.swing.DefaultComboBoxModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Breyner
 */
public class auxNegocio {
    private final Bd bd;
    
    public auxNegocio() {
        bd = new Bd("com.mysql.jdbc.Driver","test","jdbc:mysql://127.0.0.1/","12345","root");
        //bd = new Bd("oracle.jdbc.driver.OracleDriver",null,"jdbc:oracle:thin:@localhost:1521:xe","oracle","breyner");
    }
    
    public DefaultComboBoxModel LlenarCombo(String[] vCampos, String vTabla, String vCondicion){
        DefaultComboBoxModel cmb= new DefaultComboBoxModel();
        
        String vSql="SELECT ";
        if(vCampos==null){
            vSql+="* FROM "+vTabla;
        }else{
            vSql+=vCampos[0]+","+vCampos[1]+" FROM "+vTabla;
        }
        if(vCondicion!=null){
            vSql+=" "+vCondicion;
        }
        
        DefaultTableModel dtm=bd.EjecutarConsulta(vSql);
        for (int i=0;i<dtm.getRowCount();i++){
            itemCombo ic=new itemCombo(String.valueOf(dtm.getValueAt(i,0)), String.valueOf(dtm.getValueAt(i,1)));
            cmb.addElement(ic);
        }
        return cmb;
    }
    
}
