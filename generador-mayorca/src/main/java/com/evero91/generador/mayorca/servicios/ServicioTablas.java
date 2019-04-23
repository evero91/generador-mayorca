/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.evero91.generador.mayorca.servicios;

import com.evero91.generador.mayorca.utilerias.LectorCSV;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author sistemas
 */
public class ServicioTablas {
    
    public List<String> leerArchivo(String archivo) {
        LectorCSV lectorCSV = new LectorCSV();
        List<String[]> contenidoArchivo = lectorCSV.leerArchivo(archivo);
        
        if (contenidoArchivo != null) {
            List<String> lineas = new ArrayList<>();
            
            for (String[] linea : contenidoArchivo) {
                lineas.add(linea[0]);
            }
            
            return lineas;
        }
        
        return null;
    }
    
}
