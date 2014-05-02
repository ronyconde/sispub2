package br.com.projeto.model.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projeto.model.bean.Organizacao;
@SuppressWarnings("unchecked")
public class OrganizacaoDAO {

	private EntityManager entityManager;
	
	public OrganizacaoDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void cadastrar(Organizacao organizacao){
		entityManager.persist(organizacao);
	}
	public void alterar(Organizacao organizacao){
		entityManager.merge(organizacao);
	}
	public void excluir(Organizacao organizacao){
		entityManager.remove(entityManager.merge(organizacao));
	}
	
	public Organizacao consultar(Long id){
		return entityManager.getReference(Organizacao.class, id);
	}
	
	public List<Organizacao> listar(){
		String jpql = "Select p from Organizacao p order by sigla";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
}
