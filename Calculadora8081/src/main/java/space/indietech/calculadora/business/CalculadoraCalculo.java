package space.indietech.calculadora.business;

public class CalculadoraCalculo {
	private double valor1;
	private double valor2;

	public CalculadoraBo calcularResultado(CalculadoraBo cBo, String expressaoRegular) {
		String[] expressao = cBo.getExpressao().split(expressaoRegular);
		if (expressao.length == 2) {
			try {
				this.valor1 = Double.parseDouble(expressao[0]);
				this.valor2 = Double.parseDouble(expressao[1]);
				return calculo(cBo, expressaoRegular);
			} catch (Exception e) {
				throw new RuntimeException();
			}
		}
		throw new RuntimeException();
	}

	private CalculadoraBo calculo(CalculadoraBo cBo, String expressaoRegular) {
		if (expressaoRegular.endsWith("+")) {
			cBo.setResultado(valor1 + valor2);
		} else if (expressaoRegular.endsWith("-")) {
			cBo.setResultado(valor1 - valor2);
		} else if (expressaoRegular.endsWith("*")) {
			cBo.setResultado(valor1 * valor2);
		} else {
			if (this.valor2 == 0) {
				throw new RuntimeException();
			}
			cBo.setResultado(valor1 / valor2);
		}
		return cBo;
	}
}
