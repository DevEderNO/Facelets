/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.universo.ling6.facelets.to;

import java.io.Serializable;

public class AlunoTO implements Serializable {
    private long id;
    private String nome;
    private String endereco;
    private String telefone;
    private int escolaridade;

    public int getEscolaridade() {
        return escolaridade;
    }

    public void setEscolaridade(int escolaridade) {
        this.escolaridade = escolaridade;
    }
    
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }
    
}
