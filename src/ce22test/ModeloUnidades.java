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
public class ModeloUnidades extends Conexion {

    public ArrayList<Unidad> lista = new ArrayList<>();
    public Unidad unidad = new Unidad();
    private final String tabla = "unidades";

    public ArrayList<Unidad> consulta() {
        try {
            this.lista.clear();
            String query = "SELECT " + this.tabla + ".*, materias.nombre as materia FROM " + this.tabla + " "
                    + "INNER JOIN materias ON materias.id = " + this.tabla + ".id_materia";
            this.ps = this.conectar().prepareStatement(query);
            this.rs = this.ps.executeQuery();

            while (rs.next()) {
                var instanciaTemp = new Unidad(this.rs.getInt("id"), this.rs.getString("nombre"), this.rs.getInt("id_materia"), 
                        new Materia(this.rs.getInt("id_materia"), this.rs.getString("materia")));
                this.lista.add(instanciaTemp);
            }
            this.cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloUnidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.lista;
    }

    public ArrayList<Unidad> buscar(String nombre) {
        try {
            this.lista.clear();
            String query = "SELECT " + this.tabla + ".*, materias.nombre as materia FROM " + this.tabla + " "
                    + "INNER JOIN materias ON materias.id = " + this.tabla + ".id_materia "
                    + "WHERE " + this.tabla + ".nombre LIKE ?";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setString(1, "%" + nombre + "%");

            this.rs = this.ps.executeQuery();

            while (rs.next()) {
                var instanciaTemp = new Unidad(this.rs.getInt("id"), this.rs.getString("nombre"), this.rs.getInt("id_materia"), 
                        new Materia(this.rs.getInt("id_materia"), this.rs.getString("materia")));
                this.lista.add(instanciaTemp);
            }
            this.cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloUnidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.lista;
    }

    public Unidad crear(String nombre, int idMateria) {
        this.unidad = new Unidad();
        try {
            String query = "INSERT INTO " + this.tabla + " (nombre, id_materia) VALUES (?, ?)";
            this.ps = this.conectar().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            this.ps.setString(1, nombre);
            this.ps.setInt(2, idMateria);

            if (this.ps.executeUpdate() > 0) {
                this.unidad.setNombre(nombre);

                this.rs = this.ps.getGeneratedKeys();
                if (this.rs.next()) {
                    this.unidad.setId(this.rs.getInt(1));
                    
                    query = "SELECT " + this.tabla + ".*, materias.nombre as materia FROM " + this.tabla + " "
                    + "INNER JOIN materias ON materias.id = " + this.tabla + ".id_materia "
                    + "WHERE " + this.tabla + ".id = ?";
                    
                    this.ps = this.cnx.prepareStatement(query);
                    this.ps.setInt(1, this.unidad.getId());
                    this.rs = this.ps.executeQuery();
                    
                    if(this.rs.next()) {
                        this.unidad = new Unidad(this.rs.getInt("id"), this.rs.getString("nombre"), this.rs.getInt("id_materia"), 
                        new Materia(this.rs.getInt("id_materia"), this.rs.getString("materia")));
                    }
                }
            }
            this.cnx.close();
        } catch (Exception ex) {
            Logger.getLogger(ModeloUnidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.unidad;
    }

    public Unidad actualizar(int id, String nombre, int idMateria) {
        this.unidad = new Unidad();
        try {
            String query = "UPDATE " + this.tabla + " SET nombre = ?, id_materia = ? WHERE id = ?";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setString(1, nombre);
            this.ps.setInt(2, idMateria);
            this.ps.setInt(3, id);

            if (this.ps.executeUpdate() > 0) {
                this.unidad.setNombre(nombre);
                this.unidad.setId(id);
                this.unidad.idMateria = idMateria;
                
                query = "SELECT materias.nombre FROM materias WHERE materias.id = ?";
                this.ps = this.cnx.prepareStatement(query);
                this.ps.setInt(1, idMateria);
                this.rs = this.ps.executeQuery();
                
                if(this.rs.next()) {
                   this.unidad.setMateria(new Materia(idMateria, this.rs.getString("nombre"))); 
                }
            }
            this.cnx.close();
        } catch (Exception ex) {
            Logger.getLogger(ModeloUnidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.unidad;
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
            Logger.getLogger(ModeloUnidades.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ModeloUnidades.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }
}
