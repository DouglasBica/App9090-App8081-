package space.indietech;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.orm.jpa.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
public class CalculadoraTest {

	@Autowired
	private MockMvc mockMvc;
	private String location = "/calculadora";

	@Test
	public void deveSomarComValoresIguais() throws Exception {
		calcular("2+2", 4);
	}

	@Test
	public void deveSubtrairComValoresIguais() throws Exception {
		calcular("2-2", 0);
	}

	@Test
	public void deveMultiplicarComValoresIguais() throws Exception {
		calcular("5*5", 25);
	}

	@Test
	public void deveDividirComValoresIguais() throws Exception {
		calcular("2/2", 1);
	}

	@Test
	public void deveSomarComValoresDiferentes() throws Exception {
		calcular("2+3", 5);
	}

	@Test
	public void deveSubtrairComValoresDiferentes() throws Exception {
		calcular("5-2", 3);
	}

	@Test
	public void deveMultiplicarComValoresDiferentes() throws Exception {
		calcular("5*2", 10);
	}

	@Test
	public void deveDividirComValoresDiferentes() throws Exception {
		calcular("12/2", 6);
	}

	@Test
	public void calcularComOperacaoRepetidaDeSoma() throws Exception {
		calculoComOperadorRepetido("3++3");
	}

	@Test
	public void calcularComOperacaoRepetidaDeSubtracao() throws Exception {
		calculoComOperadorRepetido("3--5");
	}

	@Test
	public void calcularComOperacaoRepetidaDeMultiplicacao() throws Exception {
		calculoComOperadorRepetido("7**2");
	}

	@Test
	public void calcularComOperacaoRepetidaDeDivisao() throws Exception {
		calculoComOperadorRepetido("8//5");
	}

	@Test
	public void calcularValorUnico() throws Exception {
		String json = "{ \"calculo\" : \"3\"}";
		mockMvc.perform(put(location).header("Content-type", "application/json").content(json))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void calculoComMaisDe2Valores() throws Exception {
		String json = "{ \"calculo\" : \"3+3*8\"}";
		mockMvc.perform(put(location).header("Content-type", "application/json").content(json))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void DivisaoPorZero() throws Exception {
		String json = "{ \"calculo\" : \"5/0\"}";
		mockMvc.perform(put(location).header("Content-type", "application/json").content(json))
				.andExpect(status().isBadRequest());
	}

	@Test
	public void calcularComLetraNaEquacao() throws Exception {
		String json = "{ \"calculo\" : \"2a+25c\"}";
		mockMvc.perform(put(location).header("Content-type", "application/json").content(json))
				.andExpect(status().isBadRequest());
	}

	private void calculoComOperadorRepetido(String expressao) throws Exception {
		String json = "{ \"calculo\" : \"" + expressao + "\"}";
		mockMvc.perform(put(location).header("Content-type", "application/json").content(json))
				.andExpect(status().isBadRequest());
	}

	private void calcular(String expressao, double resultado) throws Exception {
		String json = "{ \"calculo\" : \"" + expressao + "\"}";
		mockMvc.perform(put(location).header("Content-type", "application/json").content(json))
				.andExpect(status().isOk()).andExpect(jsonPath("$.resultado").value(resultado));
	}

}
