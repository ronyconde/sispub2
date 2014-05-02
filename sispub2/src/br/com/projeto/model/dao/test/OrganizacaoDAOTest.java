package br.com.projeto.model.dao.test;

import static org.junit.Assert.*;

import java.util.Calendar;

import javax.persistence.EntityManager;

import junit.framework.Assert;

import org.junit.Test;

import br.com.projeto.model.bean.Organizacao;
import br.com.projeto.model.dao.JPAUtil;
import br.com.projeto.model.dao.OrganizacaoDAO;

public class OrganizacaoDAOTest {

	@Test
	public void testCadastrar() {
		// Solicitacao de conexao ao SGBD
		EntityManager entityManager = JPAUtil.getEntityManager();
		OrganizacaoDAO dao = new OrganizacaoDAO(entityManager);
		// Inicio da transacao
		entityManager.getTransaction().begin();
		// Criacao de um novo produto
		Organizacao organizacao = new Organizacao();
		organizacao.setSigla("SERENG");
		organizacao.setDescricao("Serviço de Engenharia");
		organizacao.setAreaAtuacao("engenharia");
		organizacao.setDataCadastro(Calendar.getInstance());
		
		//Execucao do cadastro
		dao.cadastrar(organizacao);
		//Fechamento da conexao
		entityManager.getTransaction().commit();
		entityManager.close();
		
		//Realização do teste de cadastro
		Assert.assertNotNull(organizacao.getId());
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
