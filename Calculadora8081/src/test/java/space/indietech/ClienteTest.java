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
public class ClienteTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void crud1ClientePF() throws Exception {
		createClientePF();
		
		updateClientePF();
		
		String location = "/clientes/67147255585";
		
		mockMvc.perform(get(location).header("Content-Type", "application/json"))
			.andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.nome").value("Miriam Silva Moura"))
			.andExpect(jsonPath("$.telefone").value("(51) 992828292"))
			.andExpect(jsonPath("$.email").value("miriam2@gmail.com"));

		location = "/clientes";
		
		mockMvc.perform(get(location).header("Content-Type", "application/json")).andExpect(status().is2xxSuccessful());
		
		location = "/clientes/67147255585";
		mockMvc.perform(delete(location).header("Content-Type", "application/json")).andExpect(status().is2xxSuccessful());
		
		mockMvc.perform(get(location).header("Content-Type", "application/json")).andExpect(status().isNotFound());
	}
	
	private void updateClientePF() throws Exception {
		String location = "/clientes/67147255585";
		
		String json = "{\"nome\": \"Miriam Silva Moura\", \"telefone\": \"(51) 992828292\", \"email\":\"miriam2@gmail.com\"}";

		mockMvc.perform(put(location).header("Content-Type", "application/json")
			.content(json)).andExpect(status().is2xxSuccessful())
			.andExpect(jsonPath("$.email").value("miriam2@gmail.com"));
	}
	
	private void createClientePF() throws Exception {
		String location = "/clientes/67147255585";
		
		String json = "{ \"nome\": \"Miriam Silva Moura\", \"telefone\": \"(51) 992828292\", \"email\":\"miriam@gmail.com\" }";

		mockMvc.perform(put(location).header("Content-Type", "application/json")
				.content(json)).andExpect(status().is2xxSuccessful());
	}


}