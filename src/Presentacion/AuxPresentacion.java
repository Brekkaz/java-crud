/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Presentacion;

import Negocio.itemCombo;
import javax.swing.JComboBox;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Breyner
 */
public class AuxPresentacion {
    
        public static void MostrarFormularioInternoPH(JInternalFrame vFrm, JDesktopPane vPanel){
            vPanel.removeAll();
            vPanel.add(vFrm);
            //ventana.toFront();
            vFrm.show();
        }
        
        public static void MostrarFormularioInternoHH(JInternalFrame vFrm, JDesktopPane vPanel, JInternalFrame vFrmGo){
            vPanel.add(vFrmGo);
            vPanel.remove(vFrm);
            vFrmGo.setVisible(true);
        }
        
        public static void LLenarTabla(JTable vTabla, DefaultTableModel dtm){
            vTabla.setModel(dtm);
            vTabla.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
            //no organizar columnas
            vTabla.getTableHeader().setReorderingAllowed(false);
            //tClientes.moveColumn(0,2);
        }
        
        public static String[] GetRowDeJTable(JTable vTabla, java.awt.event.MouseEvent evt){
            int y= vTabla.rowAtPoint(evt.getPoint());
            int x= vTabla.columnAtPoint(evt.getPoint());
            String registro[]= new String[vTabla.getColumnCount()];
            
            if((y>-1)&&(x>-1)){
                for(int i=0;i<vTabla.getColumnCount();i++){
                    String valor;
                    valor=vTabla.getValueAt(y, i).toString();
                    registro[i]=valor;
                }
            }
            return registro;
        }
        
        public static void SoloNumeros(java.awt.event.KeyEvent evt){
            char caracter=evt.getKeyChar();
            if(!(Character.isDigit(caracter))&&(caracter!='\b')){
                evt.consume();
            }
        }
        
        public static void SoloLetras(java.awt.event.KeyEvent evt){
            char c=evt.getKeyChar();
            if(!(Character.isLetter(c))&& c!='\b'){
                //getToolkit().beep();
                evt.consume();
            }
        }
        
        public static void NumeroDeCaracteres(javax.swing.JTextArea vTextArea, int nCaracteres, java.awt.event.KeyEvent evt){
            if (vTextArea.getText().length()== nCaracteres){
                evt.consume();
            }
        }
        
         public static void SeleccionCombo(JComboBox vCombo,String vId){
             itemCombo ic;
             for(int i=0;i<=vCombo.getItemCount()-1;i++){
                 ic= (itemCombo)vCombo.getItemAt(i);
                 if(ic.getId().equals(vId)){
                     vCombo.setSelectedItem(ic);
                 }
             }
         }
        
        //String[]frutas={"Manzana","Pera","Uva"};
        //JOptionPane.showMessageDialog(contenedor, mensaje, titulo, icono, icono personalizado); *el icono debe interpretar la interfaz Icon
        //(String)JOptionPane.showInputDialog(contenedor, mensaje, titulo, icono, icono personalizado,arreglo,opcion default);
        //(int0-3)JOptionPane.showConfirmDialog(contenedor, mensaje, titulo, tipo de opcion, icono, icono personalizado);
        //(int0-*)JOptionPane.showOptionDialog(contenedor, mensaje, titulo, tipo de opcion, icono, icono personalizado, opciones, opcion default);
        //int res = JOptionPane.showOptionDialog(null,"Seleccione Una Fruta","Frutas",JOptionPane.OK_CANCEL_OPTION,JOptionPane.ERROR_MESSAGE,null,frutas,frutas[0]);
         
}
