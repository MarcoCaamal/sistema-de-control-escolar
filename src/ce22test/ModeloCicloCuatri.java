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
public class ModeloCicloCuatri extends Conexion{
    public ArrayList<CicloCuatri> lista = new ArrayList<>();
    public CicloCuatri cicloCuatri = new CicloCuatri();
    private final String tabla = "ciclo_cuatri";
    
    public ArrayList<CicloCuatri> consulta() {
        try {
            this.lista.clear();
            String query = "SELECT " + this.tabla + ".*, ciclos.nombre as ciclo, cuatrimestres.nombre as cuatrimestre FROM " + this.tabla  + " "
                    + "INNER JOIN ciclos ON " + this.tabla + ".id_ciclo = ciclos.id "
                    + "INNER JOIN cuatrimestres ON " + this.tabla + ".id_cuatri = cuatrimestres.id "
                    + "ORDER BY ciclo_cuatri.id";
            this.ps = this.conectar().prepareStatement(query);
            this.rs = this.ps.executeQuery();
            
            while(rs.next()) {
                CicloCuatri cuatriTemp = new CicloCuatri(rs.getInt("id"), rs.getInt("id_ciclo"), rs.getInt("id_cuatri"), 
                        new Ciclo(rs.getInt("id_ciclo"), rs.getString("ciclo")),
                        new Cuatrimestre(rs.getInt("id_cuatri"), rs.getString("cuatrimestre")));
                this.lista.add(cuatriTemp);
            }
            this.cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCicloCuatri.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.lista;
    }
    
    public ArrayList<CicloCuatri> buscar(String nombreCiclo, String nombreCuatri) {
        try {
            this.lista.clear();
            String query = "SELECT " + this.tabla + ".*, ciclos.nombre as ciclo, cuatrimestres.nombre as cuatrimestre FROM " + this.tabla  + " "
                    + "INNER JOIN ciclos ON " + this.tabla + ".id_ciclo = ciclos.id "
                    + "INNER JOIN cuatrimestres ON " + this.tabla + ".id_cuatri = cuatrimestres.id "
                    + "WHERE ciclos.nombre LIKE ? AND cuatrimestres.nombre LIKE ?";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setString(1, "%" + nombreCiclo + "%");
            this.ps.setString(2, "%" + nombreCuatri + "%");
            
            this.rs = this.ps.executeQuery();
            
            while(rs.next()) {
                CicloCuatri cuatriTemp = new CicloCuatri(rs.getInt("id"), rs.getInt("id_ciclo"), rs.getInt("id_cuatri"), 
                        new Ciclo(rs.getInt("id_ciclo"), rs.getString("ciclo")),
                        new Cuatrimestre(rs.getInt("id_cuatri"), rs.getString("cuatrimestre")));
                this.lista.add(cuatriTemp);
            }
            this.cnx.close();
        } catch (SQLException ex) {
            Logger.getLogger(ModeloCicloCuatri.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.lista;
    }
    
    public CicloCuatri crear(int idCiclo, int idCuatrimestre) {
        this.cicloCuatri = new CicloCuatri();
        try {
            String query = "INSERT INTO " + this.tabla + " (id_ciclo, id_cuatri) VALUES (?, ?) ";
            this.ps = this.conectar().prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            this.ps.setInt(1, idCiclo);
            this.ps.setInt(2, idCuatrimestre);
            if(this.ps.executeUpdate() > 0) {
                this.cicloCuatri.setIdCiclo(idCiclo);
                this.cicloCuatri.setIdCuatri(idCuatrimestre);
                this.rs = this.ps.getGeneratedKeys();
                if (this.rs.next()) {
                    this.cicloCuatri.setId(this.rs.getInt(1));
                    query = "SELECT ciclos.nombre as ciclo, cuatrimestres.nombre as cuatrimestre FROM " + this.tabla  + " "
                    + "INNER JOIN ciclos ON " + this.tabla + ".id_ciclo = ciclos.id "
                    + "INNER JOIN cuatrimestres ON " + this.tabla + ".id_cuatri = cuatrimestres.id "
                    + "WHERE " + this.tabla + ".id = ?";
                    
                    this.ps = this.cnx.prepareStatement(query);
                    this.ps.setInt(1, this.cicloCuatri.getId());
                    this.rs = ps.executeQuery();
                    
                    if(this.rs.next()) {
                        this.cicloCuatri.getCiclo().setId(idCiclo);
                        this.cicloCuatri.getCiclo().setNombre(rs.getString("ciclo"));
                        this.cicloCuatri.getCuatri().setId(idCuatrimestre);
                        this.cicloCuatri.getCuatri().setNombre(rs.getString("cuatrimestre"));
                    }
                }
            }
            this.cnx.close();
        } catch (Exception ex) { 
            Logger.getLogger(ModeloCicloCuatri.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.cicloCuatri;
    }
    
    public CicloCuatri actualizar(int id, int idCiclo, int idCuatri) {
        this.cicloCuatri = new CicloCuatri();
        try {
            String query = "UPDATE " + this.tabla + " SET id_ciclo = ?, id_cuatri = ? WHERE id = ?";
            this.ps = this.conectar().prepareStatement(query);
            this.ps.setInt(1, idCiclo);
            this.ps.setInt(2, idCuatri);
            this.ps.setInt(3, id);
            
            if(this.ps.executeUpdate() > 0) {
                this.cicloCuatri.setId(id);
                query = "SELECT ciclos.nombre as ciclo, cuatrimestres.nombre as cuatrimestre FROM " + this.tabla  + " "
                    + "INNER JOIN ciclos ON " + this.tabla + ".id_ciclo = ciclos.id "
                    + "INNER JOIN cuatrimestres ON " + this.tabla + ".id_cuatri = cuatrimestres.id "
                    + "WHERE " + this.tabla + ".id = ?";
                
                this.ps = this.cnx.prepareStatement(query);
                this.ps.setInt(1, id);
                this.rs = this.ps.executeQuery();
                
                if(this.rs.next()) {
                    this.cicloCuatri.setIdCiclo(idCiclo);
                    this.cicloCuatri.setIdCuatri(idCuatri);
                    this.cicloCuatri.setCiclo(new Ciclo(idCiclo, rs.getString("ciclo")));
                    this.cicloCuatri.setCuatri(new Cuatrimestre(idCuatri, this.rs.getString("cuatrimestre")));
                }
            }
            this.cnx.close();
        } catch (Exception ex) {
            Logger.getLogger(ModeloCicloCuatri.class.getName()).log(Level.SEVERE, null, ex);
        }
        return this.cicloCuatri;
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
            Logger.getLogger(ModeloCicloCuatri.class.getName()).log(Level.SEVERE, null, ex);
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
            
            if(rs.next()) {
                correcto = true;
            }
            this.cnx.close();
        } catch (Exception ex) {
            Logger.getLogger(ModeloCicloCuatri.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }
}
