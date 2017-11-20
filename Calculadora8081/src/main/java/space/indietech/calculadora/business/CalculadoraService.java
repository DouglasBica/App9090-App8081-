package space.indietech.calculadora.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import space.indietech.calculadora.dao.AppDao;
import space.indietech.calculadora.dao.CalculadoraDao;
import space.indietech.calculadora.dao.CalculadoraEntity;

@Component
public class CalculadoraService {

	private AppDao appDao;
	private CalculadoraDao calculadoraDao;

	@Autowired
	public CalculadoraService(AppDao appDao, CalculadoraDao calculadoraDao) {
		this.appDao = appDao;
		this.calculadoraDao = calculadoraDao;
	}

	public CalculadoraBo calcular(CalculadoraBo bo) {
		CalculadoraEntity valorDoBanco = calculadoraDao.pegaCalculo(bo.getExpressao());

		if (valorDoBanco == null) {
			double resultado = appDao.calcular(bo.getExpressao());
			bo.setResultado(resultado);
			// gravar no banco

			CalculadoraEntity calcEntity = new CalculadoraEntity(); // instancia
			
			calcEntity.setExpressao(bo.getExpressao()); 			// set expressao
			
			calcEntity.setValor(bo.getResultado()); 				// set valor
			
			calculadoraDao.adicionaCalculo(calcEntity); 			// adicionando o calculo no banco

		} else {
			// colocar no retorno
			bo.setResultado(valorDoBanco.getValor());
		}
		return bo;
	}
}
