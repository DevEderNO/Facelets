package br.edu.universo.ling6.facelets.controle;

import br.edu.universo.ling6.facelets.negocio.AlunoBO;
import br.edu.universo.ling6.facelets.to.AlunoTO;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;

import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;

import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@ManagedBean
@SessionScoped
public class AlunoCTRL {

    private AlunoTO alunoTO;
    private AlunoTO alunoConsultaTO;
    private List<AlunoTO> alunos;
    private List<SelectItem> escolaridades;
    private String labelBtnIncAlt;
    private int operacao;
    //1 - Incluir
    //2 - Alterar
    //3 - Excluir

    public AlunoTO getAlunoTO() {
        return alunoTO;
    }

    public void setAlunoTO(AlunoTO alunoTO) {
        this.alunoTO = alunoTO;
    }

    public AlunoTO getAlunoConsultaTO() {
        return alunoConsultaTO;
    }

    public void setAlunoConsultaTO(AlunoTO alunoConsultaTO) {
        this.alunoConsultaTO = alunoConsultaTO;
    }

    public List<AlunoTO> getAlunos() {
        return alunos;
    }

    public void setAlunos(List<AlunoTO> alunos) {
        this.alunos = alunos;
    }

    public List<SelectItem> getEscolaridades() {
        return escolaridades;
    }

    public void setEscolaridades(List<SelectItem> escolaridades) {
        this.escolaridades = escolaridades;
    }

    public String getLabelBtnIncAlt() {
        return labelBtnIncAlt;
    }

    public void setLabelBtnIncAlt(String labelBtnIncAlt) {
        this.labelBtnIncAlt = labelBtnIncAlt;
    }

    public int getOperacao() {
        return operacao;
    }

    public void setOperacao(int operacao) {
        this.operacao = operacao;
    }

    public AlunoCTRL() {
        try {
            alunoTO = new AlunoTO();
            alunoConsultaTO = new AlunoTO();
            carregaEscolaridade();
            setLabelBtnIncAlt("Incluir");
        } catch (Exception ex) {
            exibeMensagem(new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
        }
    }

    public String manterAluno() {
        String ret = "";
        if (operacao == 1) {
            incluirAluno();

        } else {
            if (operacao == 2) {
                ret = alterarAluno();
                if (ret.equals("")) {

                    return consultarTodos();
                } else {
                    exibeMensagem(new FacesMessage(FacesMessage.SEVERITY_ERROR, ret, ""));
                }
            } else {
                if (operacao == 3) {
                    excluir();
                    return consultarTodos();
                }
            }
        }
        return "";
    }

    public String incluirAluno() {
        try {
            AlunoBO alunoBO = new AlunoBO();
            String ret = alunoBO.incluir(alunoTO);
            if (ret != "") {
                exibeMensagem(new FacesMessage(FacesMessage.SEVERITY_ERROR, ret, ""));
            } else {
                exibeMensagem(new FacesMessage(FacesMessage.SEVERITY_INFO, "Aluno incluído com sucesso!", ""));
                limpaTela();
            }

            return "";
        } catch (Exception ex) {
            exibeMensagem(new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
        }
        return "";
    }

    public String alterarAluno() {
        try {
            AlunoBO alunoBO = new AlunoBO();
            String ret = alunoBO.alterar(alunoTO);
            if (ret != "") {

                return ret;
            } else {
                limpaTela();
                return "";
            }
        } catch (Exception ex) {
            exibeMensagem(new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
        }
        return "";
    }

    public String consultarTodos() {

        try {
            AlunoBO alunoBO = new AlunoBO();
            alunos = alunoBO.consultarTodos();
        } catch (Exception ex) {
            exibeMensagem(new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
        }
        return "/paginas/alunos/consulta";
    }

    public String excluir() {
        try {
            AlunoBO alunoBO = new AlunoBO();
            alunoBO.excluirID(alunoTO.getId());
            alunos = alunoBO.consultarTodos();
            limpaTela();
        } catch (Exception ex) {
            exibeMensagem(new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
        }

        return "/paginas/alunos/consulta";
    }

    private void limpaTela() {
        alunoTO.setNome("");
        alunoTO.setEndereco("");
        alunoTO.setTelefone("");
        alunoTO.setId(0);
        setLabelBtnIncAlt("Incluir");
    }

    private void exibeMensagem(FacesMessage facesMessage) {
        FacesContext contexto = FacesContext.getCurrentInstance();
        contexto.addMessage("AlunoCTRL", facesMessage);

    }

    private void carregaEscolaridade() {
        escolaridades = new LinkedList<SelectItem>();
        escolaridades.add(new SelectItem(1, "Analfabeto"));
        escolaridades.add(new SelectItem(2, "Ensino Fundamental"));
        escolaridades.add(new SelectItem(3, "Ensino Médio"));
        escolaridades.add(new SelectItem(4, "Graduação"));
        escolaridades.add(new SelectItem(5, "Especialização"));
        escolaridades.add(new SelectItem(6, "Mestrado"));
        escolaridades.add(new SelectItem(7, "Doutorado"));
        escolaridades.add(new SelectItem(8, "Pós Doutorado"));
       
    }

    public String emiteRelatorio() {

        try {

            AlunoBO alunoBO = new AlunoBO();
            List dados = alunoBO.consultarTodos();

            FacesContext contexto = FacesContext.getCurrentInstance();
            ServletContext servletContext = (ServletContext) contexto.getExternalContext().getContext();
            String pathRelatorio = servletContext.getRealPath("/relatorios");
            JasperPrint relTermo = JasperFillManager.fillReport(pathRelatorio + File.separator + "alunos.jasper", null, new JRBeanCollectionDataSource(dados));
            byte[] arq = JasperExportManager.exportReportToPdf(relTermo);
            apresentaRelatorioPDF(arq, "alunos.pdf", contexto);
        } catch (Exception ex) {
            exibeMensagem(new FacesMessage(FacesMessage.SEVERITY_ERROR, ex.getMessage(), ""));
        }
        return "";
    }

    public void apresentaRelatorioPDF(byte[] bytes, String fileName, FacesContext contexto) throws Exception {

        HttpServletResponse response = (HttpServletResponse) FacesContext.getCurrentInstance().getExternalContext().getResponse();
        if (bytes != null && bytes.length > 0) {
            ServletOutputStream outputStream = null;
            try {
                response.setContentType("application/pdf");
                response.setHeader("Content-disposition", "attachment;filename=" + fileName);
                response.setContentLength(bytes.length);
                outputStream = response.getOutputStream();
                outputStream.write(bytes, 0, bytes.length);
                outputStream.flush();
                outputStream.close();
                contexto.responseComplete();

            } catch (Exception e) {
                throw e;
            }
        }
    }

    public String voltar() {
        alunos.clear();
        limpaTela();
        return "/paginas/alunos/cadastro";
    }

    public String editarAluno() {
        setLabelBtnIncAlt("Alterar");
        setOperacao(2);
        return "/paginas/alunos/cadastro";
    }

    public String exibirCadastro() {
        limpaTela();
        setLabelBtnIncAlt("Incluir");
        setOperacao(1);  //Operação de Inclusão
        return "/paginas/alunos/cadastro";
    }

    public String excluirAluno() {
        setLabelBtnIncAlt("Excluir");
        setOperacao(3);
        return "/paginas/alunos/cadastro";
    }
}
