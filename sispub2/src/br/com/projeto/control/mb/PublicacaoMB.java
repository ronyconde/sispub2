package br.com.projeto.control.mb;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;

import br.com.projeto.model.bean.Categoria;
import br.com.projeto.model.bean.Organizacao;
import br.com.projeto.model.bean.Publicacao;
import br.com.projeto.model.dao.CategoriaDAO;
import br.com.projeto.model.dao.JPAUtil;
import br.com.projeto.model.dao.OrganizacaoDAO;
import br.com.projeto.model.dao.PublicacaoDAO;

@ViewScoped
@ManagedBean
public class PublicacaoMB {
		
	//Atributos devem ser iniciados
	private Publicacao publicacao = new Publicacao();
	
	public Publicacao getPublicacao() {
		return publicacao;
	}
	
	public void setPublicacao(Publicacao publicacao) {
		this.publicacao = publicacao;
	}
	
	//Atributo que guarda a colecao produtos armazenados em BD
	private int categoriaSelecionada;
	private int organizacaoSelecionada;
		
	public List<SelectItem> categoriaSelect;
	public List<SelectItem> organizacaoSelect;
	
	public List<Publicacao> listaPublicacoes = new ArrayList<Publicacao>();
	
	public List<Publicacao> getListaPublicacoes() {
		return listaPublicacoes;
	}	

	//Metodo invocado apos a classe ser carregada pelo servidor
	@PostConstruct
	public List<SelectItem> getCategoriaSelect() {
		
		EntityManager em = JPAUtil.getEntityManager();
		PublicacaoDAO dao = new PublicacaoDAO(em);
		em.getTransaction().begin();
		
		if(this.categoriaSelect == null){
			categoriaSelect = new ArrayList<SelectItem>();
			Categoria categoria = new Categoria();
			CategoriaDAO categoriaDAO = new CategoriaDAO(em);
			List<Categoria> listaCategorias = categoriaDAO.listar();
			
			if(listaCategorias != null && !listaCategorias.isEmpty()){
				SelectItem item;
				for ( Categoria cat : listaCategorias){
					item = new SelectItem(cat, cat.getSigla());
					this.categoriaSelect.add(item);
				}
			}
		}
		return categoriaSelect;
	}
@PostConstruct	
public List<SelectItem> getOrganizacaoSelect() {
		
		EntityManager em = JPAUtil.getEntityManager();
		em.getTransaction().begin();
		if(this.organizacaoSelect == null){
			organizacaoSelect = new ArrayList<SelectItem>();
			Organizacao organizacao = new Organizacao();
			OrganizacaoDAO organizacaoDAO = new OrganizacaoDAO(em);
			List<Organizacao> listaOrganizacoes = organizacaoDAO.listar();
			
			if(listaOrganizacoes != null && !listaOrganizacoes.isEmpty()){
				SelectItem item;
				for ( Organizacao org : listaOrganizacoes){
					item = new SelectItem(org, org.getDescricao());
					this.organizacaoSelect.add(item);
				}
			}
		}
		return organizacaoSelect;
	}
	@PostConstruct
	public void carregarPublicacoes(){
		EntityManager em = JPAUtil.getEntityManager();
		PublicacaoDAO dao = new PublicacaoDAO(em);
		listaPublicacoes = dao.listar();
		em.close();
	}
	
	public void excluir(){
		EntityManager em = JPAUtil.getEntityManager();
		PublicacaoDAO dao = new PublicacaoDAO(em);
		em.getTransaction().begin();
		dao.excluir(publicacao);
		em.getTransaction().commit();
		em.close();
		carregarPublicacoes();
	}

	public void salvar(){
		EntityManager em = JPAUtil.getEntityManager();
		PublicacaoDAO dao = new PublicacaoDAO(em);
		em.getTransaction().begin();
		publicacao.setDataCadastro(Calendar.getInstance());
				
		Categoria categoria = new Categoria();
		categoria.setId((long) categoriaSelecionada);
		//publicacao.setCategoria(categoriaSelecionada);
		Organizacao organizacao = new Organizacao();
		organizacao.setId((long) organizacaoSelecionada);
		//publicacao.setOrganizacao(organizacaoSelecionada);
		
		if(publicacao.getId()!=null){
			dao.alterar(publicacao);
		}else{
			dao.cadastrar(publicacao);
		}
		em.getTransaction().commit();
		em.close();
		publicacao  = new Publicacao();
		carregarPublicacoes();
	}

	public int getCategoriaSelecionada() {
		return categoriaSelecionada;
	}

	public void setCategoriaSelecionada(int categoriaSelecionada) {
		this.categoriaSelecionada = categoriaSelecionada;
	}

	public int getOrganizacaoSelecionada() {
		return organizacaoSelecionada;
	}

	public void setOrganizacaoSelecionada(int organizacaoSelecionada) {
		this.organizacaoSelecionada = organizacaoSelecionada;
	}
	


}
