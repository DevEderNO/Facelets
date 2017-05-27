/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.universo.ling6.facelets.util;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;

@FacesConverter("EscolaridadeConverter")
public class EscolaridadeConverter implements Converter {

    public Object getAsObject(FacesContext arg0, UIComponent arg1, String valor) throws ConverterException {
        if (valor == null || valor.toString().trim().equals("")) {
            return valor + ": Escolaridade desconhecida";
        }
        switch (Integer.getInteger(valor)) {
            case 1:
                return "Analfabeto";
            case 2:
                return "Ensino Fundamental";
            case 3:
                return "Ensino Médio";
            case 4:
                return "Graduação";
            case 5:
                return "Especialização";
            case 6:
                return "Mestrado";
            case 7:
                return "Doutorado";
            case 8:
                return "Pós Doutorado";
        }
        return valor + ": Escolaridade desconhecida";
    }

    public String getAsString(FacesContext arg0, UIComponent arg1, Object valor) throws ConverterException {
        if (valor == null || valor.toString().trim().equals("")) {
            return valor + ": Escolaridade desconhecida";
        }
        switch (Integer.parseInt(valor.toString())) {

            case 1:
                return "Analfabeto";
            case 2:
                return "Ensino Fundamental";
            case 3:
                return "Ensino Médio";
            case 4:
                return "Graduação";
            case 5:
                return "Especialização";
            case 6:
                return "Mestrado";
            case 7:
                return "Doutorado";
            case 8:
                return "Pós Doutorado";
          
        }
        return valor + ": Escolaridade desconhecida";

    }
}