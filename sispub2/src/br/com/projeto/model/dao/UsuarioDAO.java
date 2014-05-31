package br.com.projeto.model.dao;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.projeto.model.bean.Usuario;
@SuppressWarnings("unchecked")
public class UsuarioDAO {

	private EntityManager entityManager;
	
	public UsuarioDAO(EntityManager entityManager) {
		this.entityManager = entityManager;
	}
	public void cadastrar(Usuario usuario){
		entityManager.persist(usuario);
	}
	public void alterar(Usuario usuario){
		entityManager.merge(usuario);
	}
	public void excluir(Usuario usuario){
		entityManager.remove(entityManager.merge(usuario));
	}
	
	public Usuario consultar(Long id){
		return entityManager.getReference(Usuario.class, id);
	}
	
	public List<Usuario> listar(){
		String jpql = "Select p from Usuario p order by nome";
		Query query = entityManager.createQuery(jpql);
		return query.getResultList();
	}
	
	public Usuario efetuarLogin(String login, String senha) {
		String jpql = "from Usuario u where u.login=:pLogin and u.senha=:pSenha";
		Query query = entityManager.createQuery(jpql);
		query.setParameter("pLogin", login);
		query.setParameter("pSenha", senha);
		if (!query.getResultList().isEmpty()) {
			return (Usuario) query.getResultList().get(0);
		}
		return null;
	}
}
