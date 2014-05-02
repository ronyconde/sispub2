package br.com.projeto.model.dao.test;

import java.util.Calendar;

import javax.persistence.EntityManager;

import org.junit.Assert;
import org.junit.Test;

import br.com.projeto.model.bean.Usuario;
import br.com.projeto.model.dao.JPAUtil;
import br.com.projeto.model.dao.UsuarioDAO;

public class UsuarioDAOTest {

	@Test
	public void testCadastrar(){
		//solicita conexao com o bd
		EntityManager em = JPAUtil.getEntityManager();
		UsuarioDAO dao = new UsuarioDAO(em);
		//inicio da transacao
		em.getTransaction().begin();
		//criacao de um novo usuario
		Usuario usuario = new Usuario();
		usuario.setLogin("user");
		usuario.setNome("Joao");
		usuario.setSenha("362514");
		usuario.setDataCadastro(Calendar.getInstance());
		
		//execucao do cadastro
		dao.cadastrar(usuario);
		//fecha conexao
		em.getTransaction().commit();
		em.close();
		
		//realizacao do teste de cadastro
		Assert.assertNotNull(usuario.getId());
	}
	
}
