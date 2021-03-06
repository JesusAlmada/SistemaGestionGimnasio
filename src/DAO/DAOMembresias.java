/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Modelos.ModeloCliente;
import Modelos.ModeloMembresia;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author phant
 */
public class DAOMembresias implements IDAO {

    @Override
    public boolean agregar(Object obj) {
        ConexionMySQL c = new ConexionMySQL();
        Connection conn = c.connect();
        ModeloMembresia mem = (ModeloMembresia) obj;
        
        String query = "INSERT INTO membresia(fecha_inicial, fecha_fin, idcliente) VALUES(\'" + mem.getFechaIn().toString() + "\',\'" + mem.getFechaFin().toString() + "\',\'"+ mem.getCliente().getId() +"\');";
        
        try {
            Statement s = conn.createStatement();
            s.execute(query);
            conn.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e);
            return false;
        }

    }

    @Override
    public boolean eliminar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean actualizar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean buscar(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Object get(Object obj) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List getAll() {
        List membresias = new ArrayList<>();
        ConexionMySQL c = new ConexionMySQL();
        Connection conn = c.connect();

        try {
            Statement s = conn.createStatement();
            String query = "SELECT * FROM membresia";
            ResultSet rs = s.executeQuery(query);
            while (rs.next()) {
                ModeloMembresia cte = new ModeloMembresia(Integer.parseInt(rs.getString("idmembresia")),rs.getString("fecha_inicial"), rs.getString("fecha_fin"), Integer.parseInt(rs.getString("idcliente")));
                membresias.add(cte);
            }
            conn.close();
        } catch (SQLException | NumberFormatException ex) {
            System.out.println(ex);
        }

        return membresias;
    }
    
//    private String javaDateToMysql(Date dt){ Ya no se ocupa
//        String pattern = "yyyy-MM-dd";
//        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
//        return formatter.format(dt);
//    }
}
