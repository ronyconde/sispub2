package br.com.projeto.interceptor;

import java.io.Serializable;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;

import org.apache.log4j.Logger;

@SuppressWarnings("serial")
@Interceptor
@Transactional
public class TransactionInterceptor implements Serializable {

	@Inject
	private EntityManager em;
	
	@Inject
	private transient Logger logger;
	
	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception{
		logger.info("TransactionInterceptor.intercept(): Abertura de transação "+em.getTransaction());
		em.getTransaction().begin();
		
		Object result = context.proceed();
		
		em.getTransaction().commit();
		logger.info("TransactionInterceptor.intercept(): Encerramento de transação "+em.getTransaction());
		return result;
	}
}
