/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.universo.ling6.facelets.util;

import java.util.LinkedList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;

@ManagedBean
@SessionScoped
public class Sistema {

    private String tema;
    private List<SelectItem> temas;

    public List<SelectItem> getTemas() {
        return temas;
    }

    public void setTemas(List<SelectItem> temas) {
        this.temas = temas;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public Sistema() {
        
        temas = new LinkedList<SelectItem>();
        temas.add(new SelectItem("aristo", "aristo"));
        temas.add(new SelectItem("bluesky", "bluesky"));
        temas.add(new SelectItem("cupertino", "cupertino"));
        tema="bluesky";
        
    }
    public String reloadTema(){
        return "/index";
    }
}
