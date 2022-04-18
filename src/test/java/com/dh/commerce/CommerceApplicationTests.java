package com.dh.commerce;

import com.dh.commerce.dto.ProductCartDTO;
import com.dh.commerce.dto.ProductLongCategoryDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
class CommerceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Test
	void testGetAllProducts() throws Exception {
		MvcResult  mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/products"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.content[0].title").value("Teclado Mecânico Gamer"))
				.andReturn();

		Assertions.assertEquals("application/json", mvcResult.getResponse().getContentType());
	}

	@Test
	void testGetProductsById() throws Exception {
		MvcResult  mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/products/{id}", 1))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Teclado Mecânico Gamer"))
				.andReturn();

		Assertions.assertEquals("application/json", mvcResult.getResponse().getContentType());
	}

	@Test
	void testGetCategoriesInArray() throws Exception {
		MvcResult  mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.get("/products/categories"))
				.andDo(print()).andExpect(status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[0]").value("Periféricos"))
				.andReturn();

		Assertions.assertEquals("application/json", mvcResult.getResponse().getContentType());
	}

	@Test
	void testPostProduct() throws Exception {
		ProductLongCategoryDTO payloadDTO = new ProductLongCategoryDTO("Mouse para jogo Fortrek Crusader preto",
				61.06, "Para trabalhar desde casa com o computador ou aproveitar momentos de lazer, você precisa de " +
				"conforto e facilidade de movimento.", "https://http2.mlstatic.com/D_NQ_NP_630548-MLA46389425443_062021-O.webp",
				1L);

		ObjectWriter objectWriter = new ObjectMapper()
				.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
				.writer().withDefaultPrettyPrinter();

		String payloadJson = objectWriter.writeValueAsString(payloadDTO);

		this.mockMvc.perform(MockMvcRequestBuilders.post("/products")
				.contentType(MediaType.APPLICATION_JSON)
				.content(payloadJson))
				.andDo(print())
				.andExpect(status().isCreated())
				.andExpect(content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.title")
						.value("Mouse para jogo Fortrek Crusader preto"));
	}

	@Test
	void testPutCategory() throws Exception {
		ProductLongCategoryDTO payloadDTO = new ProductLongCategoryDTO(23L, 61.06,"Mouse para jogo Fortrek",
				 "Para trabalhar desde casa com o computador ou aproveitar momentos de lazer, você precisa de " +
				"conforto e facilidade de movimento.", "https://http2.mlstatic.com/D_NQ_NP_630548-MLA46389425443_062021-O.webp",
				1L);

		ObjectWriter objectWriter = new ObjectMapper()
				.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
				.writer().withDefaultPrettyPrinter();

		String payloadJson = objectWriter.writeValueAsString(payloadDTO);

		this.mockMvc.perform(MockMvcRequestBuilders.put("/products")
						.contentType(MediaType.APPLICATION_JSON)
						.content(payloadJson))
				.andDo(print())
				.andExpect(status().isAccepted())
				.andExpect(content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.title")
						.value("Mouse para jogo Fortrek"));
	}

	@Test
	void testDeleteProductById() throws Exception {
		MvcResult  mvcResult = this.mockMvc.perform(MockMvcRequestBuilders.delete("/products/{id}", 24))
				.andDo(print()).andExpect(status().isOk())
				.andReturn();

		Assertions.assertEquals(200, mvcResult.getResponse().getStatus());
	}

/*
	@Test
	void testCartProductResponse() throws Exception {
		ProductCartDTO payloadDTO = new ProductCartDTO(1L, 4);

		ObjectWriter objectWriter = new ObjectMapper()
				.configure(SerializationFeature.WRAP_ROOT_VALUE, false)
				.writer().withDefaultPrettyPrinter();

		String payloadJson = objectWriter.writeValueAsString(payloadDTO);

		this.mockMvc.perform(MockMvcRequestBuilders.get("/products/productCart")
						.contentType(MediaType.APPLICATION_JSON)
						.content(payloadJson))
				.andDo(print())
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(MockMvcResultMatchers.jsonPath("$.price")
						.value(1199.6));
	}
*/


}
