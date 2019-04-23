/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evero91.generador.mayorca.servicios;

import com.evero91.generador.mayorca.modelo.AccesoBD;
import com.evero91.generador.mayorca.modelo.pojo.InfoTabla;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sistemas
 */
public class ServicioInfoTabla {

    public List<InfoTabla> obtenerInfoTabla(AccesoBD accesoBD) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT m.name AS tableName, p.name AS columnName \n"
                + "FROM sqlite_master m \n"
                + "LEFT OUTER JOIN pragma_table_info((m.name)) p ON m.name <> p.name \n"
                + "ORDER BY tableName, columnName";

        try {
            ps = accesoBD.getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            List<InfoTabla> infoTablas = new ArrayList<>();

            while (rs.next()) {
                InfoTabla infoTabla = new InfoTabla();
                infoTabla.setTabla(rs.getString("tableName"));
                infoTabla.setColumna(rs.getString("columnName"));
                infoTablas.add(infoTabla);
            }

            return infoTablas;
        } catch (SQLException e) {
            System.out.println("ServicioInfoTabla -> obtenerInfoTabla: " + e);
        } finally {
            accesoBD.liberarRecursos(ps);
            accesoBD.liberarRecursos(rs);
        }

        return null;
    }

    public List<String> obtenerTablas(AccesoBD accesoBD) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT m.name AS tableName \n"
                + "FROM sqlite_master m \n"
                + "ORDER BY tableName ";

        try {
            ps = accesoBD.getConexion().prepareStatement(sql);
            rs = ps.executeQuery();
            List<String> tablas = new ArrayList<>();

            while (rs.next()) {
                tablas.add(rs.getString("tableName"));
            }

            return tablas;
        } catch (SQLException e) {
            System.out.println("ServicioInfoTabla -> obtenerTablas: " + e);
        } finally {
            accesoBD.liberarRecursos(ps);
            accesoBD.liberarRecursos(rs);
        }

        return null;
    }

    public List<String> obtenerColumnas(AccesoBD accesoBD, String tabla) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT m.name AS tableName, p.name AS columnName \n"
                + "FROM sqlite_master m \n"
                + "LEFT OUTER JOIN pragma_table_info((m.name)) p ON m.name <> p.name \n"
                + "WHERE tableName = ? \n"
                + "ORDER BY tableName, columnName";

        try {
            ps = accesoBD.getConexion().prepareStatement(sql);
            ps.setString(1, tabla);
            rs = ps.executeQuery();
            List<String> columnas = new ArrayList<>();

            while (rs.next()) {
                columnas.add(rs.getString("columnName"));
            }

            return columnas;
        } catch (SQLException e) {
            System.out.println("ServicioInfoTabla -> obtenerColumnas: " + e);
        } finally {
            accesoBD.liberarRecursos(ps);
            accesoBD.liberarRecursos(rs);
        }

        return null;
    }

}
