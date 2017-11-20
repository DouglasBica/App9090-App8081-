package space.indietech.calculadora.dao;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class CalculadoraDao {

	private EntityManager em;

	@Autowired
	public CalculadoraDao(EntityManager em) {
		this.em = em;
	}

	@Transactional
	public void adicionaCalculo(CalculadoraEntity ce) {
		em.merge(ce);
	}

	public CalculadoraEntity pegaCalculo(String expressao) {
		CalculadoraEntity ce = new CalculadoraEntity();
		ce = em.find(CalculadoraEntity.class, expressao);
		return ce;
	}

}
