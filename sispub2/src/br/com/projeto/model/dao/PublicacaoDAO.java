package br.com.projeto.model.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projeto.model.bean.Publicacao;
@SuppressWarnings("unchecked")
public class PublicacaoDAO {

	private EntityManager entityManager;
	
	public PublicacaoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void cadastrar(Publicacao publicacao){
		entityManager.persist(publicacao);
	}
	public void alterar(Publicacao publicacao){
		entityManager.merge(publicacao);
	}
	public void excluir(Publicacao publicacao){
		entityManager.remove(entityManager.merge(publicacao));
	}
	
	public Publicacao consultar(Long id){
		return entityManager.getReference(Publicacao.class, id);
	}
	
	public List<Publicacao> listar(){
		String jpql = "Select p from Publicacao p order by numero";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
	
	public List<Publicacao> listarNpa(){
		String jpql = "Select p from Publicacao p where categoria_id = 1 order by numero";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
	
	public List<Publicacao> listarPendencia(){
		String jpql = "Select p from Publicacao p where pendencia != null and pendencia != ''";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
}
