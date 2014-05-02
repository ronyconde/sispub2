package br.com.projeto.control.mb;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.projeto.model.bean.Organizacao;
import br.com.projeto.model.dao.JPAUtil;
import br.com.projeto.model.dao.OrganizacaoDAO;

@ViewScoped
@ManagedBean
public class OrganizacaoMB {
	//Atributos devem ser iniciados
	private Organizacao organizacao = new Organizacao();
	
	public Organizacao getOrganizacao() {
		return organizacao;
	}
	
	public void setOrganizacao(Organizacao organizacao) {
		this.organizacao = organizacao;
	}
	
	//Atributo que guarda a colecao produtos armazenados em BD
	public List<Organizacao> listaOrganizacoes = new ArrayList<Organizacao>();
	
	public List<Organizacao> getListaOrganizacoes() {
		return listaOrganizacoes;
	}	

	//Metodo invocado apos a classe ser carregada pelo servidor
	@PostConstruct
	public void carregarOrganizacoes(){
		EntityManager em = JPAUtil.getEntityManager();
		OrganizacaoDAO dao = new OrganizacaoDAO(em);
		listaOrganizacoes = dao.listar();
		em.close();
	}
	
	public void excluir(){
		EntityManager em = JPAUtil.getEntityManager();
		OrganizacaoDAO dao = new OrganizacaoDAO(em);
		em.getTransaction().begin();
		dao.excluir(organizacao);
		em.getTransaction().commit();
		em.close();
		carregarOrganizacoes();
	}

	public void salvar(){
		EntityManager em = JPAUtil.getEntityManager();
		OrganizacaoDAO dao = new OrganizacaoDAO(em);
		em.getTransaction().begin();
		organizacao.setDataCadastro(Calendar.getInstance());
		if(organizacao.getId()!=null){
			dao.alterar(organizacao);
		}else{
			dao.cadastrar(organizacao);
		}
		em.getTransaction().commit();
		em.close();
		organizacao  = new Organizacao();
		carregarOrganizacoes();
	}
}
