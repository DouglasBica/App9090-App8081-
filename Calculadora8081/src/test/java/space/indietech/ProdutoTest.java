package space.indietech;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class ProdutoTest {

	@Autowired
	private MockMvc mockMvc;
	
	private String location;

	@Test
	public void crudProduto() throws Exception{
		getListaProdutoVazio();
		criaProduto();
		getProduto();
		criaProduto();
		getListaProdutoPopulada();
		deletaProduto();
		getProdutoComDesconto();
	}

	private void getProdutoComDesconto() throws Exception {
		location = "/produtos/1/20";
		mockMvc.perform(get(location).header("Content-Type", "application/json"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.codigo").value(1))
			.andExpect(jsonPath("$.nome").value("Abacaxi"))
			.andExpect(jsonPath("$.valor").value(5))
			.andExpect(jsonPath("$.desconto").value(20))
			.andExpect(jsonPath("$.valorComDesconto").value(4));
	}
	private void deletaProduto() throws Exception {
		location = "/produtos/2";
		mockMvc.perform(delete(location).header("Content-Type", "application/json"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$[1]").doesNotExist());
	}

	private void getListaProdutoPopulada() throws Exception{
		location = "/produtos";
		mockMvc.perform(get(location).header("Content-Type", "application/json"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$[1]").exists());
		
	}

	private void getProduto() throws Exception {
		location = "/produtos/1";
		mockMvc.perform(get(location).header("Content-Type", "application/json"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.codigo").value(1))
			.andExpect(jsonPath("$.nome").value("Abacaxi"))
			.andExpect(jsonPath("$.valor").value(5));
	}

	private void criaProduto() throws Exception {
		location = "/produtos";
		String json = "{\"nome\": \"Abacaxi\", \"valor\": 5}";
		mockMvc.perform(put(location).header("Content-Type", "application/json")
			.content(json)).andExpect(status().isNoContent());
	}

	private void getListaProdutoVazio() throws Exception{
		location ="/produtos";
		mockMvc.perform(get(location).header("Content-Type", "application/json"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$[0]").doesNotExist());
	}

}
