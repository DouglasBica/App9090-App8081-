package space.indietech.estacionamento;

import java.util.ArrayList;
import java.util.List;

public class EstacionamentoService {
	private EstacionamentoDao estacionamentoDao = new EstacionamentoDao();

	public Vaga[][] getVagas() {
		return estacionamentoDao.getVagas();
	}

	public Vaga[] getVagasDoAndar(int andar) {
		if (andar >= 1 && andar <= 4) {
			Vaga[] andarPassado = new Vaga[60];
			Vaga[][] estacio = this.getVagas();
			for (int numero = 0; numero < 60; numero++) {
				andarPassado[numero] = estacio[andar - 1][numero];
			}
			return andarPassado;
		}
		throw new RuntimeException("Andar invalido");

	}

	public List<Vaga> getVagasLivres() {
		List<Vaga> vagasLivres = new ArrayList<Vaga>();
		Vaga[][] estacionamentoLivre = this.getVagas();
		for (int andar = 0; andar < 4; andar++) {
			for (int numeroDaVagaLivre = 0; numeroDaVagaLivre < 60; numeroDaVagaLivre++) {
				if (estacionamentoLivre[andar][numeroDaVagaLivre].getStatus().equals("Livre")) {
					vagasLivres.add(estacionamentoLivre[andar][numeroDaVagaLivre]);
				}

			}
		}
		return vagasLivres;
	}

	public List<Vaga> getVagasOcupadas() {
		List<Vaga> vagasOcupadas = new ArrayList<Vaga>();
		Vaga[][] estacionamentoOcupado = this.getVagas();
		for (int andar = 0; andar < 4; andar++) {
			for (int numeroDaVagaOcupada = 0; numeroDaVagaOcupada < 60; numeroDaVagaOcupada++) {
				if (estacionamentoOcupado[andar][numeroDaVagaOcupada].getStatus().equals("Ocupada")) {
					vagasOcupadas.add(estacionamentoOcupado[andar][numeroDaVagaOcupada]);
				}
			}
		}
		return vagasOcupadas;
	}

	public boolean setStatus(int andar, int numero, String status) {
		if (andar <= 4 && andar >= 1 && numero >= 1 && numero <= 60) {
			if (status.equals("Livre") || status.equals("Ocupado")) {
				estacionamentoDao.setStatus(andar, numero, status);
				return true;
			}
			throw new IllegalArgumentException("Status invalido");
		}
		throw new RuntimeException("Andar ou numero invalido");
	}
}