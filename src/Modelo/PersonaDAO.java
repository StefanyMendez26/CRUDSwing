/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author MINEDUCYT
 */
public class PersonaDAO {
    
    Conexion conectar = new Conexion();
    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    
    public List listar (){
        List <Persona>datos  = new  ArrayList<>();
     
        
         String sql = "select * from persona";
        try{
            //Usamos las variables que declaramos al inicio
            con = conectar.getConnection();
            //establecemos la consulta
            ps = con.prepareStatement(sql);
            rs =ps.executeQuery();
            
            //Ejecucion de bucle para que iterar cada dato de la tabla
            while(rs.next()){
                Persona p = new Persona();
                //siempre usando
                //set y get para establecer los datos
                p.setId(rs.getInt(1));
                p.setNombre(rs.getString(2));
                p.setCorreo(rs.getString(3));
                p.setTelefono(rs.getString(4));
                //agregamos el Array de datos 
                datos.add (p);
                
            } 
            
        }catch (SQLException e){
            System.out.print("Error:" + e);
    }
        
        return  datos ;
    }
    
    public int agregar(Persona pi){
         String sql = "insert into persona(Nombre, Correo, Telefono) Values (?,?,?)";
         
        try{
            con = conectar.getConnection();
            // Iniciamos la consulta con prepareStatement
            ps = con.prepareStatement(sql);
            // Establecemos el orden de los valores en el orden del SQL
            ps.setString(1, pi.getNombre());
            ps.setString(2, pi.getCorreo());
            ps.setString(3, pi.getTelefono());
            ps.executeUpdate();
        }
        catch(Exception e){
            System.out.println("Error " + e);
        }
         
      return 1;
    }
    public int actualizar(Persona p) {
		int r = 0;
		String sql = "update persona set Nombre=?, Correo=?, Telefono=? where ID=?";
		try {
			con = conectar.getConnection();
			ps = con.prepareStatement(sql);
			ps.setString(1, p.getNombre());
			ps.setString(2, p.getCorreo());
			ps.setString(3, p.getTelefono());
			ps.setInt(4, p.getId());
			r = ps.executeUpdate();
			if (r == 1) {
				return 1;
			} else {
				return 0;
			}
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
		return r;
	}
    public void eliminar(int id) {
		String sql = "delete from persona where ID=?";
		try {
			con = conectar.getConnection();
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.executeUpdate();
		} catch (Exception e) {
			System.out.println("Error: " + e);
		}
	}
    
    public List<Persona> filtrar(String valor, String campo) {
	    List<Persona> lista = new ArrayList<>();
	    String sql = "SELECT * FROM persona WHERE " + campo + " LIKE ?";
	    try {
	        con = conectar.getConnection();
	        ps = con.prepareStatement(sql);
	        ps.setString(1, "%" + valor + "%");
	        rs = ps.executeQuery();
	        while (rs.next()) {
	            Persona p = new Persona();
	            p.setId(rs.getInt("id"));
	            p.setNombre(rs.getString("nombre"));
	            p.setCorreo(rs.getString("correo"));
	            p.setTelefono(rs.getString("telefono"));
	            lista.add(p);
	        }
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	    return lista;
	}

}
