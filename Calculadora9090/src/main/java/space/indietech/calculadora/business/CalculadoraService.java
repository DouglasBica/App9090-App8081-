package space.indietech.calculadora.business;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import space.indietech.calculadora.conversor.CalculadoraConversor;
import space.indietech.calculadora.dao.CalculadoraDao;
import space.indietech.calculadora.dao.CalculadoraEntity;

@Component
public class CalculadoraService {

	private CalculadoraDao cDAO;

	@Autowired
	public CalculadoraService(CalculadoraDao cDAO) {
		this.cDAO = cDAO;
	}

	public CalculadoraBo calcular(CalculadoraBo cBo) {
		CalculadoraEntity ce = cDAO.pegaCalculo(cBo.getExpressao());
		CalculadoraBo calculadoraAux = CalculadoraConversor.convertEntityToBo(ce);
		if (calculadoraAux != null) {
			return calculadoraAux;
		}
		return casoInfoNaoSalvaNoBanco(cBo);
		
	}

	private CalculadoraBo casoInfoNaoSalvaNoBanco(CalculadoraBo cBo) {
		boolean calculou = false;
		CalculadoraCalculo calculo = new CalculadoraCalculo();
		if (expressaoSoma(cBo)) {
			cBo = calculo.calcularResultado(cBo, "\\+");
			calculou = true;
		} else if (expressaoSub(cBo)) {
			cBo = calculo.calcularResultado(cBo, "\\-");
			calculou = true;
		} else if (expressaoMult(cBo)) {
			cBo = calculo.calcularResultado(cBo, "\\*");
			calculou = true;
		} else if (expressaoDiv(cBo)) {
			cBo = calculo.calcularResultado(cBo, "\\/");
			calculou = true;
		}
		if(calculou) {
			salvaBanco(cBo);
			return cBo;
		}
		throw new RuntimeException();
	}

	private void salvaBanco(CalculadoraBo cBo) {
		cDAO.adicionaCalculo(CalculadoraConversor.convertBoToEntity(cBo));
	}

	private boolean expressaoDiv(CalculadoraBo cBo) {
		return cBo.getExpressao().contains("/");
	}

	private boolean expressaoMult(CalculadoraBo cBo) {
		return cBo.getExpressao().contains("*");
	}

	private boolean expressaoSub(CalculadoraBo cBo) {
		return cBo.getExpressao().contains("-");
	}

	private boolean expressaoSoma(CalculadoraBo cBo) {
		return cBo.getExpressao().contains("+");
	}

}