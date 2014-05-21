package br.com.projeto.control.mb;

import java.io.InputStream;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;


import java.util.List;

import br.com.projeto.model.bean.Categoria;
import br.com.projeto.model.bean.Organizacao;
import br.com.projeto.model.bean.Publicacao;
import br.com.projeto.model.dao.CategoriaDAO;
import br.com.projeto.model.dao.JPAUtil;
import br.com.projeto.model.dao.OrganizacaoDAO;
import br.com.projeto.model.dao.PublicacaoDAO;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;


@ManagedBean
public class RelatorioMB {

	public String geraRelatorio(){
		EntityManager em = JPAUtil.getEntityManager();
		PublicacaoDAO dao = new PublicacaoDAO(em);
		em.getTransaction().begin();
		
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletResponse response = (HttpServletResponse) context
				.getExternalContext().getResponse();
		ServletOutputStream responseStream = response.getOutputStream();
		// caminho do jrxml
		InputStream pathjrxml = getClass().getResourceAsStream(
				"../jrxml/Relatorio_NPA.jrxml");
		response.setContentType("application/pdf");
		response.setHeader("Content-Disposition",
				"attachment; filename=\"relatorio_npa.pdf\"");
		JasperReport pathReport = JasperCompileManager
				.compileReport(pathjrxml);
		JasperPrint print = JasperFillManager.fillReport(pathReport, null,
				new JRBeanCollectionDataSource(lista));
		JasperExportManager.exportReportToPdfStream(print, responseStream);

		responseStream.flush();
		responseStream.close();
		context.renderResponse();
		context.responseComplete();
		System.out.println("Relatorio gerado"); 

	}
}
