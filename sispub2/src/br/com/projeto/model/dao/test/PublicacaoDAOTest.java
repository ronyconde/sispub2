package br.com.projeto.model.dao.test;

import static org.junit.Assert.fail;

import java.util.Calendar;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Test;

import br.com.projeto.model.bean.Categoria;
import br.com.projeto.model.bean.Organizacao;
import br.com.projeto.model.bean.Publicacao;
import br.com.projeto.model.dao.CategoriaDAO;
import br.com.projeto.model.dao.OrganizacaoDAO;
import br.com.projeto.model.dao.PublicacaoDAO;
import br.com.projeto.model.dao.JPAUtil;

public class PublicacaoDAOTest {

	@Test
	public void testCadastrar() {
		
		
		// Solicitacao de conexao ao SGBD
		EntityManager entityManager = JPAUtil.getEntityManager();
		PublicacaoDAO dao = new PublicacaoDAO(entityManager);
		// Inicio da transacao
		entityManager.getTransaction().begin();
		// Criacao de um novo produto
		
		Publicacao publicacao = new Publicacao();
		publicacao.setNumero(100);
		publicacao.setAssunto("teste");
		publicacao.setPendencia("teste");
		publicacao.setDataCadastro(Calendar.getInstance());
		
		Categoria categoria = new Categoria();
		CategoriaDAO catDAO = new CategoriaDAO(entityManager);
		Organizacao organizacao = new Organizacao();
		CategoriaDAO orgDAO = new CategoriaDAO(entityManager);
		
		categoria.setId(2L);
		organizacao.setId(1L);		
		
		publicacao.setCategoria(categoria);
		publicacao.setOrganizacao(organizacao);
		
			
		//Execucao do cadastro
		dao.cadastrar(publicacao);
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
