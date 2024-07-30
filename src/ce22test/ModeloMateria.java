/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ce22test;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author marco
 */
public class ModeloMateria extends Conexion {

    public ArrayList<Materia> lista = new ArrayList<>();
    public Materia materia = new Materia();
    private final String tabla = "materias";

    public ArrayList<Materia> consulta() {
        try {
            this.lista.clear();
            String query = "SELECT * FROM " + this.tabla;
            this.ps = this.conectar().prepareStatement(query);
            this.rs = this.ps.executeQuery();

            while (rs.next()) {
                Materia instanciaTemp = new Materia(this.rs.getInt("id"), this.rs.getString("nombre"));
                this.lista.add(instanciaTemp);
            }
            this.cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.lista;
    }

    public ArrayList<Materia> buscar(String nombre) {
        try {
            this.lista.clear();
            String query = "SELECT * FROM " + this.tabla + " WHERE nombre LIKE ?";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setString(1, "%" + nombre + "%");

            this.rs = this.ps.executeQuery();

            while (rs.next()) {
                Materia instanciaTemp = new Materia(this.rs.getInt("id"), this.rs.getString("nombre"));
                this.lista.add(instanciaTemp);
            }
            this.cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.lista;
    }

    public Materia crear(String nombre) {
        try {
            this.materia.setId(0);
            this.materia.setNombre("");
            String query = "INSERT INTO " + this.tabla + " (nombre) VALUES (?) ";
            this.ps = this.conectar().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            this.ps.setString(1, nombre);

            if (this.ps.executeUpdate() > 0) {
                this.materia.setNombre(nombre);

                this.rs = this.ps.getGeneratedKeys();
                if (this.rs.next()) {
                    this.materia.setId(this.rs.getInt(1));
                }
            }
            this.cnx.close();
        } catch (Exception ex) {
            Logger.getLogger(ModeloMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.materia;
    }

    public Materia actualizar(int id, String nombre) {
        try {
            this.materia.setId(0);
            this.materia.setNombre("");
            String query = "UPDATE " + this.tabla + " SET nombre = ? WHERE id = ?";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setString(1, nombre);
            this.ps.setInt(2, id);

            if (this.ps.executeUpdate() > 0) {
                this.materia.setNombre(nombre);
                this.materia.setId(id);
            }
            this.cnx.close();
        } catch (Exception ex) {
            Logger.getLogger(ModeloMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.materia;
    }

    public boolean eliminar(int id) {
        boolean correcto = false;
        try {
            String query = "DELETE FROM " + this.tabla + " WHERE id = ?";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setInt(1, id);

            correcto = this.ps.executeUpdate() > 0;
            this.cnx.close();
        } catch (Exception ex) {
            Logger.getLogger(ModeloMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }

    public boolean existe(int id) {
        boolean correcto = false;
        try {
            String query = "SELECT 1 FROM " + this.tabla + " WHERE id = ?;";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setInt(1, id);

            this.rs = this.ps.executeQuery();

            if (rs.next()) {
                correcto = true;
            }
            this.cnx.close();
        } catch (Exception ex) {
            Logger.getLogger(ModeloMateria.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }
}
