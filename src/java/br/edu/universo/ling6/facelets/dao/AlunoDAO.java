package br.edu.universo.ling6.facelets.dao;
import br.edu.universo.ling6.facelets.to.AlunoTO;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;


public class AlunoDAO{

    
    public AlunoDAO () throws Exception {
        
        
    }
    
    public String incluir(AlunoTO alunoTO) throws Exception {
        try {
            Connection conexao = Conexao.getConexao();
            //PREPARA O SQL PARA EXECUÇÃO
            PreparedStatement ps = conexao.prepareStatement("INSERT INTO ALUNO (NOME,ENDERECO,TELEFONE,ESCOLARIDADE) VALUES (?,?,?,?)");
            ps.setString(1, alunoTO.getNome());
            ps.setString(2, alunoTO.getEndereco());
            ps.setString(3, alunoTO.getTelefone());
            ps.setInt(4, alunoTO.getEscolaridade());
            ps.execute();//EXECUTA O INSERT
            conexao.close();
            return "";
        }
        catch(Exception ex){
            throw ex;
        }
    }

    public String alterar(AlunoTO alunoTO) throws Exception {
        try {
            Connection conexao = Conexao.getConexao();
            //PREPARA O SQL PARA EXECUÇÃO
            PreparedStatement ps = conexao.prepareStatement("UPDATE ALUNO SET NOME = ?, ENDERECO = ?, TELEFONE = ?, ESCOLARIDADE = ? WHERE ID = ?");
            ps.setString(1, alunoTO.getNome());//PEGA O NOME DO ALUNO DO TO
            ps.setString(2, alunoTO.getEndereco());//PEGA O ENDEREÇO DO ALUNO DO TO
            ps.setString(3, alunoTO.getTelefone());//PEGA O TELEFONE DO ALUNO DO TO
            ps.setInt(4, alunoTO.getEscolaridade());//PEGA A ESCOLARIDADE DO ALUNO DO TO
            ps.setLong(5, alunoTO.getId());//PEGA O ID DO ALUNO DO TO
            ps.execute();//EXECUTA O UPDATE
            conexao.close();
            return "";
        }
        catch(Exception ex){
            throw ex;
        }
    }

    public ArrayList <AlunoTO> consultarTodos() throws Exception {
        ArrayList <AlunoTO> alunos = new ArrayList();
        //*********************************************
        //RECUPERA TODOS OS ALUNOS DO BANCO
        //*********************************************
        ResultSet rs;
        Connection conexao = Conexao.getConexao();
    PreparedStatement ps = conexao.prepareStatement("SELECT * FROM ALUNO ORDER BY NOME");
        rs = ps.executeQuery();
        //***********************************************
        //PARA CADA ALUNO MONTA UM TO E ADICONA O MESMO AO ARRAYLIST
        //************************************************************
        while(rs.next()) {
          AlunoTO alunoTO = new AlunoTO();
          alunoTO.setId(rs.getLong("id"));
          alunoTO.setEndereco(rs.getString("endereco"));
          alunoTO.setNome(rs.getString("nome"));
          alunoTO.setTelefone(rs.getString("telefone"));
          alunoTO.setEscolaridade(rs.getInt("escolaridade"));
          alunos.add(alunoTO);
        }
        //************************************************************

       //RETORNA O ARRAYLIST PARA O BO
        conexao.close();
       return alunos;
    }

    public AlunoTO consultarID(long id) throws Exception {
        //*********************************************
        //RECUPERA TODOS OS ALUNOS DO BANCO
        //*********************************************
        ResultSet rs;
        Connection conexao = Conexao.getConexao();
        PreparedStatement ps = conexao.prepareStatement("SELECT * FROM ALUNO WHERE ID = ?");
        ps.setLong(1, id);
        rs = ps.executeQuery();
        //***********************************************
        //PARA CADA ALUNO MONTA UM TO E ADICONA O MESMO AO ARRAYLIST
        //************************************************************
        AlunoTO alunoTO = new AlunoTO();
        if (rs.next()) {

          alunoTO.setId(rs.getLong("id"));
          alunoTO.setEndereco(rs.getString("endereco"));
          alunoTO.setNome(rs.getString("nome"));
          alunoTO.setTelefone(rs.getString("telefone"));
          alunoTO.setEscolaridade(rs.getInt("escolaridade"));
        }
        //************************************************************

       //RETORNA O ARRAYLIST PARA O BO
        conexao.close();
       return alunoTO;
    }

    public void excluirID(long ID) throws Exception {

        //EXCLUI UM ALUNO DO BANCO (COM O ID PASSADO COMO PARÂMETRO)
        //************************************************************
        //ABRE CONEXAO COM O BANCO
        //************************************************************
        Connection conexao = Conexao.getConexao();
        PreparedStatement ps = conexao.prepareStatement("DELETE FROM ALUNO WHERE ID = ?");
        ps.setLong(1, ID);
        ps.execute();//EXCLUI O ALUNO
        conexao.close();
    }

}
