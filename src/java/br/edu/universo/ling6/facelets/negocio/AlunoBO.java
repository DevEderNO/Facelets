/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.edu.universo.ling6.facelets.negocio;


import br.edu.universo.ling6.facelets.dao.AlunoDAO;
import java.util.ArrayList;
import br.edu.universo.ling6.facelets.to.AlunoTO;

public class AlunoBO {
    private AlunoDAO alunoDAO;
    
    public AlunoBO() throws Exception {
         alunoDAO = new AlunoDAO();
    }
    
    public String incluir(AlunoTO alunoTO) throws Exception {
        String ret = consisteDados(alunoTO);
        if (!ret.equals("")) {
            return ret; //o alunoTO Está inconsistente
        }
        ret = alunoDAO.incluir(alunoTO);
        return ret;
    }
    
    public String alterar(AlunoTO alunoTO) throws Exception {
        String ret = consisteDados(alunoTO);
        if (!ret.equals("")) {
            return ret;
        }
        ret = alunoDAO.alterar(alunoTO);
        return ret;
    }    
    
    private String consisteDados(AlunoTO alunoTO){
        if (alunoTO.getNome().equals("")) {
            return "Aluno não informado";
        }
        if (alunoTO.getEndereco().equals("")) {
            return "Endereço não informado";
        }
        if (alunoTO.getTelefone().equals("")) {
            return "Telefone não informado";
        }

        return "";
    }

    public ArrayList <AlunoTO> consultarTodos() throws Exception {
        AlunoDAO alunoDao = new AlunoDAO();
        ArrayList clientes = alunoDao.consultarTodos();
        return clientes;
    }

    public AlunoTO consultarID(long id) throws Exception {
        AlunoDAO alunoDao = new AlunoDAO();
        AlunoTO AlunoTO = alunoDao.consultarID(id);
        return AlunoTO;
    }    
    
    public void excluirID (long id) throws Exception {
        AlunoDAO alunoDao = new AlunoDAO();
        alunoDao.excluirID(id);
    }
}
