
package Controlador;

import Modelo.Persona;
import Modelo.PersonaDAO;
import Vista.NewJFrame;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author MINEDUCYT
 */
public class controlador implements ActionListener {
     PersonaDAO dao = new PersonaDAO ();
    Persona p = new Persona ();
    //incluimos el Jframe
    NewJFrame njm = new NewJFrame();
    DefaultTableModel modelo = new DefaultTableModel();
            
            public controlador (NewJFrame njm){
                  this.njm = njm;
                  this.njm.btnListar.addActionListener(this);
                  this.njm.btnGuardar.addActionListener(this);
                  this.njm.btnEliminar.addActionListener(this);
                  this.njm.btnEditar.addActionListener(this);
                  this.njm.btnBuscar.addActionListener(this);
              };
            public void Listar(JTable tabla) {
            modelo = (DefaultTableModel) tabla.getModel();

            while (modelo.getRowCount() > 0) {
                    modelo.removeRow(0);
            }

            List<Persona> lista = (List<Persona>) dao.listar();
            // Aca aclaramos el numero de
            // columnas que se encuentran
            Object[] object = new Object[4];

            // Usaremos un for para iterar en cada uno de los campos
            for (int i = 0; i < lista.size(); i++) {
                object[0] = lista.get(i).getId();
                object[1] = lista.get(i).getNombre();
                object[2] = lista.get(i).getCorreo();
                object[3] = lista.get(i).getTelefono();

                // Agregamos al table los datos
                modelo.addRow(object);
            }  
}
            public void agregar(){
            String Nom = njm.tfNombre.getText();
            String correo =njm.tfCorreo.getText();
            String telefono = njm.tfTelefono.getText();

            p.setNombre(Nom);
            p.setCorreo(correo);
            p.setTelefono(telefono);

            int r = dao.agregar(p);
            if(r == 1){
                JOptionPane.showMessageDialog(null, "Persona agregada");
                njm.tfNombre.setText(Nom);
            } 

        }
            public void modificar() {
		int id = Integer.parseInt(njm.tfID.getText());
		String nombre = njm.tfNombre.getText();
		String correo = njm.tfCorreo.getText();
		String telefono = njm.tfTelefono.getText();

		p.setId(id);
		p.setNombre(nombre);
		p.setCorreo(correo);
		p.setTelefono(telefono);

		int r = dao.actualizar(p);

                if (r == 1) {
			JOptionPane.showMessageDialog(null, "Persona modificada");
		} else {
			JOptionPane.showMessageDialog(null, "Error al modificar");
		}
		
	}
            public void eliminar() {
		int id = Integer.parseInt(njm.tfID.getText());
		dao.eliminar(id);
		JOptionPane.showMessageDialog(null, "Persona eliminada");
		njm.tfNombre.setText("");
		njm.tfCorreo.setText("");
		njm.tfTelefono.setText("");
	}
            
            public void filtrarPorNombre(JTable tabla) {
	    String nombre = njm.tfNombre.getText();
	    filtrar(tabla, nombre, "nombre");
	}
	
	public void filtrarPorTelefono(JTable tabla) {
	    String telefono = njm.tfTelefono.getText();
	    filtrar(tabla, telefono, "telefono");
	}
	
	public void filtrarPorCorreo(JTable tabla) {
	    String correo = njm.tfCorreo.getText();
	    filtrar(tabla, correo, "correo");
	}
	
	public void filtrar(JTable tabla, String valor, String campo) {
	    modelo = (DefaultTableModel) tabla.getModel();
	
	    while (modelo.getRowCount() > 0) {
	        modelo.removeRow(0);
	    }
	
	    List<Persona> lista = (List<Persona>) dao.filtrar(valor, campo);
	    Object[] object = new Object[4];
	
	    for (int i = 0; i < lista.size(); i++) {
	        object[0] = lista.get(i).getId();
	        object[1] = lista.get(i).getNombre();
	        object[2] = lista.get(i).getCorreo();
	        object[3] = lista.get(i).getTelefono();
	
	        modelo.addRow(object);
	    }
	
	    if (lista.isEmpty()) {
	        JOptionPane.showMessageDialog(null, "No se encontraron resultados");
	    }
	}

            
         @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == njm.btnListar){
            Listar(njm.jTable2);
        }
        if(e.getSource() == njm.btnEditar){
            modificar();
       }
        
        if(e.getSource() == njm.btnGuardar){
           agregar();
        }
        
        if(e.getSource() == njm.btnEliminar){
            eliminar();
       }
         if (e.getSource() == njm.btnBuscar) {
	        String filterOption = (String) njm.jComboBox1.getSelectedItem();
	        switch (filterOption) {
	            case "Nombre":
	                filtrarPorNombre(njm.jTable2);
	                break;
	            case "Teléfono":
	                filtrarPorTelefono(njm.jTable2);
	                break;
	            case "Correo":
	                filtrarPorCorreo(njm.jTable2);
	                break;
	        }
	    }
    }
     
        
}
