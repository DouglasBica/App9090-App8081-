package space.indietech.estacionamento;

import java.util.Random;

public class EstacionamentoDao {

	private final Vaga[][] estacionamento = new Vaga[4][60];

	public EstacionamentoDao() {
		for (int andar = 0; andar < 4; andar++) {
			for (int numero = 0; numero < 60; numero++) {
				Vaga vaga = new Vaga();
				vaga.setAndar(andar + 1);
				vaga.setNum(numero + 1);
				vaga.setStatus(livreOcupado());
				estacionamento[andar][numero] = vaga;
			}

		}
	}

	private String livreOcupado() {
		Random ran = new Random();
		if (ran.nextBoolean()) {
			return "Livre";
		}
		return "Ocupada";
	}

	public Vaga[][] getVagas() {
		return estacionamento;
	}

	public void setStatus(int andar, int numero, String status) {
		this.estacionamento[andar - 1][numero - 1].setStatus(status);
	}

}
