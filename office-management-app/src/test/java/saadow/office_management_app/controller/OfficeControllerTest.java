package saadow.office_management_app.controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

import java.util.List;

import javax.sql.DataSource;
import javax.ws.rs.core.Response.Status;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;

import saadow.office_management_app.entity.Office;

@RunWith(SpringRunner.class)
@SpringBootTest
@TestPropertySource("classpath:test.properties")
public class OfficeControllerTest {
	
	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private DataSource dataSource;
	
	private MockMvc mockMvc;
	private ObjectMapper mapper = new ObjectMapper();

	@Before
	public void setup() {
		Resource initSchema = new ClassPathResource("scripts\\schema.sql");
		Resource data = new ClassPathResource("scripts\\data.sql");
		DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema, data);
		DatabasePopulatorUtils.execute(databasePopulator, dataSource);
		this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testGetAllOffices() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/office")).andReturn();
		assertEquals(Status.OK.getStatusCode(), mvcResult.getResponse().getStatus());
		List<Office> offices = mapper.readValue(mvcResult.getResponse().getContentAsString(),
				mapper.getTypeFactory().constructCollectionType(List.class, Office.class));
		assertEquals(2, offices.size());	
	}
	
	@Test
	public void testGetOfficeByIdNotExist() throws Exception {
		MvcResult mvcResult = mockMvc.perform(get("/office/{id}", "-1")).andDo(print()).andReturn();
		assertEquals(Status.OK.getStatusCode(), mvcResult.getResponse().getStatus());
		assertTrue(mvcResult.getResponse().getContentAsString().length() == 0);
	}

	@Test
	public void testAddOffice() throws Exception {
		String json = mapper.writeValueAsString(new Office());
		MvcResult mvcResult = mockMvc.perform(post("/office").contentType(MediaType.APPLICATION_JSON).content(json))
				.andDo(print()).andReturn();

		assertEquals(Status.OK.getStatusCode(), mvcResult.getResponse().getStatus());
	}

	@Test
	public void testDeleteOffice() throws Exception {
		MvcResult mvcResult = mockMvc.perform(delete("/office/{id}", "98")).andDo(print()).andReturn();
		assertEquals(Status.OK.getStatusCode(), mvcResult.getResponse().getStatus());
	}

	@Test
	public void testUpdateOffice() throws Exception {
		MvcResult mvcResult = mockMvc.perform(put("/office/{id}", "98").param("sales", "1101")).andDo(print())
				.andReturn();
		assertEquals(Status.OK.getStatusCode(), mvcResult.getResponse().getStatus());
	}
}
