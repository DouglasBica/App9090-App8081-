package space.indietech.calculadora.conversor;

import java.util.Date;

import space.indietech.calculadora.business.CalculadoraBo;
import space.indietech.calculadora.dao.CalculadoraEntity;
import space.indietech.calculadora.web.CalculadoraDTODevolve;
import space.indietech.calculadora.web.CalculadoraDTORecebe;

public class CalculadoraConversor {

	public static CalculadoraBo convertDTORecebetoBo(CalculadoraDTORecebe cDTO) {
		CalculadoraBo bo = new CalculadoraBo();
		bo.setExpressao(cDTO.getCalculo());
		return bo;
	}

	public static CalculadoraDTODevolve converterBoToDTODevolve(CalculadoraBo bo) {
		CalculadoraDTODevolve cDto = new CalculadoraDTODevolve();
		cDto.setResultado(bo.getResultado());
		return cDto;
	}

	public static CalculadoraBo convertEntityToBo(CalculadoraEntity ce) {
		if (ce != null) {
			CalculadoraBo bo = new CalculadoraBo();
			bo.setExpressao(ce.getExpressao());
			bo.setResultado(ce.getValor());
			return bo;
		}
		return null;
	}

	public static CalculadoraEntity convertBoToEntity(CalculadoraBo bo) {
		CalculadoraEntity ce = new CalculadoraEntity();
		ce.setCriado_em(new Date());
		ce.setExpressao(bo.getExpressao());
		ce.setValor(bo.getResultado());
		return ce;
	}

}
