package br.com.projeto.control.mb;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.persistence.EntityManager;

import br.com.projeto.model.bean.Categoria;
import br.com.projeto.model.dao.CategoriaDAO;
import br.com.projeto.model.dao.JPAUtil;

@ViewScoped
@ManagedBean
public class CategoriaMB {
	//Atributos devem ser iniciados
	private Categoria categoria = new Categoria();
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
	
	//Atributo que guarda a colecao produtos armazenados em BD
	public List<Categoria> listaCategorias = new ArrayList<Categoria>();
	
	public List<Categoria> getListaCategorias() {
		return listaCategorias;
	}	

	//Metodo invocado apos a classe ser carregada pelo servidor
	@PostConstruct
	public void carregarCategorias(){
		EntityManager em = JPAUtil.getEntityManager();
		CategoriaDAO dao = new CategoriaDAO(em);
		listaCategorias = dao.listar();
		em.close();
	}
	
	public void excluir(){
		EntityManager em = JPAUtil.getEntityManager();
		CategoriaDAO dao = new CategoriaDAO(em);
		em.getTransaction().begin();
		dao.excluir(categoria);
		em.getTransaction().commit();
		em.close();
		carregarCategorias();
	}

	public void salvar(){
		EntityManager em = JPAUtil.getEntityManager();
		CategoriaDAO dao = new CategoriaDAO(em);
		em.getTransaction().begin();
		categoria.setDataCadastro(Calendar.getInstance());
		if(categoria.getId()!=null){
			dao.alterar(categoria);
		}else{
			dao.cadastrar(categoria);
		}
		em.getTransaction().commit();
		em.close();
		categoria  = new Categoria();
		carregarCategorias();
	}
}
