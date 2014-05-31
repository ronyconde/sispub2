package br.com.projeto.model.dao.test;

import static org.junit.Assert.*;

import java.util.Calendar;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Test;

import br.com.projeto.model.bean.Categoria;
import br.com.projeto.model.dao.CategoriaDAO;
import br.com.projeto.model.dao.JPAUtil;

public class CategoriaDAOTest {

	@Test
	public void testCadastrar() {
		// Solicitacao de conexao ao SGBD
		EntityManager entityManager = JPAUtil.getEntityManager();
		CategoriaDAO dao = new CategoriaDAO(entityManager);
		// Inicio da transacao
		entityManager.getTransaction().begin();
		// Criacao de um novo produto
		Categoria categoria = new Categoria();
		categoria.setSigla("TEST");
		categoria.setDescricao("TEST");
		categoria.setDataCadastro(Calendar.getInstance());
		
		//Execucao do cadastro
		dao.cadastrar(categoria);
		//Fechamento da conexao
		entityManager.getTransaction().commit();
		entityManager.close();
		
		//Realização do teste de cadastro
		Assert.assertNotNull(categoria.getId());
	}

	// Continuacao...
	@Test
	public void testAlterar() {
		fail("Not yet implemented");
	}

	@Test
	public void testExcluir() {
		fail("Not yet implemented");
	}

	@Test
	public void testConsultar() {
		fail("Not yet implemented");
	}

	@Test
	public void testListar() {
		fail("Not yet implemented");
	}

}
