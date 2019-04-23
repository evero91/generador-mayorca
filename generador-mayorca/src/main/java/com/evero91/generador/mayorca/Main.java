/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evero91.generador.mayorca;

import com.evero91.generador.mayorca.modelo.AccesoBD;
import com.evero91.generador.mayorca.modelo.pojo.InfoTabla;
import com.evero91.generador.mayorca.servicios.ServicioInfoTabla;
import com.evero91.generador.mayorca.servicios.ServicioTablas;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sistemas
 */
public class Main {

    public static void main(String args[]) {
//        String directorioTrabajo = "/Users/sistemas/Documents/Ever/qdi/mayorca/";
//        String archivo = "Mayorca - Hoja 1.csv";

//        ServicioTablas servicioTablas = new ServicioTablas();
//        List<String> tablas = servicioTablas.leerArchivo(directorioTrabajo + archivo);
//        
//        if (tablas == null) {
//            System.out.println("Archivo sin informacion: " + directorioTrabajo + archivo);
//            return;
//        }
//        
//        for (String tabla : tablas) {
//            System.out.println("DELETE FROM " + tabla + ";");
//        }
        AccesoBD sqlite = new AccesoBD();

        if (!sqlite.establecerConexionSQLite()) {
            System.out.println("No se establecio conexion con sqlite");
            return;
        }

        ServicioInfoTabla servicioInfoTabla = new ServicioInfoTabla();
//        List<InfoTabla> infoTablas = servicioInfoTabla.obtenerInfoTabla(sqlite);
//        
//        if (infoTablas == null) {
//            System.out.println("Error al obtener infoTablas");
//            return;
//        }
//        
//        for (InfoTabla infoTabla : infoTablas) {
//            System.out.println(infoTabla.getTabla() + "\t" + infoTabla.getColumna());
//        }

        List<String> tablasSQLite = servicioInfoTabla.obtenerTablas(sqlite);

        if (tablasSQLite == null) {
            System.out.println("Error al listar tablas sqlite");
            return;
        }

        List<String> tablasImportantes = new ArrayList<>();
        tablasImportantes.add("Asignaciones");
        tablasImportantes.add("BajarCambios");
        tablasImportantes.add("Estados");
        tablasImportantes.add("Fachadas");

        tablasImportantes.add("FachadasImagenes");
        tablasImportantes.add("FachadasModelos");
        tablasImportantes.add("Imagenes");
        tablasImportantes.add("Lotes");

        tablasImportantes.add("LotesModelos");
        tablasImportantes.add("Mapas");
        tablasImportantes.add("Modelos");
        tablasImportantes.add("ModelosImagenes");

        tablasImportantes.add("Secciones");
        tablasImportantes.add("TiposLotes");
        tablasImportantes.add("Usuarios");
        tablasImportantes.add("Vendedores");

        String insert;
        String values;
        String select;
        String from;

        for (String tablaImportante : tablasImportantes) {
            List<String> columnasTablaSQlite = servicioInfoTabla.obtenerColumnas(sqlite, tablaImportante);

            if (columnasTablaSQlite == null) {
                System.out.println("Error al obtener columnas");
                continue;
            }

            insert = "INSERT INTO " + tablaImportante + " (";
            values = "VALUES (";

            select = "SELECT ";
            from = "FROM " + tablaImportante + ";";

            for (String columnaSQLite : columnasTablaSQlite) {
                insert += columnaSQLite + ", ";
                values += "?, ";
                select += columnaSQLite + ", ";
            }

            int ultimoInsert = insert.lastIndexOf(", ");
            int ultimoValues = values.lastIndexOf(", ");
            int ultimoSelect = select.lastIndexOf(", ");
            insert = insert.substring(0, ultimoInsert) + ") ";
            values = values.substring(0, ultimoValues) + ");";
            select = select.substring(0, ultimoSelect) + " ";

            System.out.println(select + from);
            System.out.println(insert + values);
        }

        // TABLAS IMPORTANTES: Asignaciones, ﻿BajarCambios, Estados, Fachadas, 
        // FachadasImagenes, ﻿FachadasModelos, Imagenes, Lotes, 
        //﻿LotesModelos, Mapas, ﻿Modelos, ﻿ModelosImagenes, 
        // Secciones, TiposLotes, Usuarios, Vendedores
        // LEER SQLITE
        // CREAR SENTENCIAS PARA ESCRIBIR EN MYSQL Y REPLICAR INFORMACION
    }
}
