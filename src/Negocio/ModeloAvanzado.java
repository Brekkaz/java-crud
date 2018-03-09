/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Breyner
 */
public class ModeloAvanzado extends ModeloSimple{
    private final String[] Campos;
    private final String Diminutivo, InnerJoin;
    
    //--------------------Metodos Constructores---------------------------------
    public ModeloAvanzado(String vTabla, String vAlias, String[] vCampos, String[] vInnerJoin) {
        super(vTabla.split(" ")[0], vAlias);
        this.Campos=vCampos;
        this.Diminutivo=vTabla.split(" ")[1];
        this.InnerJoin= getInnerJoin(vInnerJoin);
    }
    
    private String getInnerJoin(String[] Inners){
        String vInnerJoin=super.Tabla+" "+this.Diminutivo;
        for (String Inner : Inners) {
            vInnerJoin += " INNER JOIN " + Inner;
        }
        return vInnerJoin;
    }
    //-------------------------------CRUD---------------------------------------
    public DefaultTableModel ReadInner(String[] vCampos, String[] vCodicion){
        return super.getRead(vCampos, vCodicion, this.Campos, this.InnerJoin);
    }
    
    public DefaultTableModel ReadLikeInner(String[] vCampos, String[] vCodicion, String valor){
        return super.getReadLike(vCampos, vCodicion, valor, this.Campos, this.InnerJoin);
    }
    
}
