/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.universo.ling6.facelets.dao;

import java.sql.Connection;
import java.sql.DriverManager;

public abstract class Conexao {

    public static Connection getConexao() throws Exception {
        Connection conn = null;
        try {
            
            String driver = "org.postgresql.Driver";
            String servidor = "localhost:5432";
            String conexao = "jdbc:postgresql";
            String bdNome = "ling6";
            String usuario = "ling6";
            String senha = "ling6";
            Class.forName(driver);
            conn = DriverManager.getConnection(conexao + "://" + servidor + "/" + bdNome, usuario, senha);

        } catch (Exception ex) {
            throw new Exception("Problemas no banco de dados: " +ex.getMessage());
        }
        return conn;
    }    
}
