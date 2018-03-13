package com.springboot;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.springboot.controller.RestAPIController;
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration()
public class RestAPITestClient {

	@InjectMocks 
	private RestAPIController controller;
	
	private MockMvc mockMvc;

	@Before
    public void setUp() throws Exception{
		controller = new RestAPIController();
       mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }
	
	@Test
	public void testFibbonacciNumber() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/Fibbonacci").param("n", "10"))
    	.andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isOk());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/Fibbonacci").param("n", "6.7"))
    	.andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/Fibbonacci").param("n", "-5"))
    	.andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/Fibbonacci").param("n", "ABC"))
    	.andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/Fibbonacci").param("m", "10"))
    	.andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
		
	@Test
    public void testReverseWords() throws Exception {
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ReverseWords").param("sentence", "Controller"))
        	.andDo(MockMvcResultHandlers.print())
        	.andExpect(MockMvcResultMatchers.status().isOk());
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ReverseWords").param("sentence", ""))
    	.andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isOk());
        
        mockMvc.perform(MockMvcRequestBuilders.get("/api/ReverseWords").param("s", ""))
    	.andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isBadRequest());
        	
    }
	
	@Test
	public void testTriangleType() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.get("/api/TriangleType").param("a", "10").param("b", "7").param("c", "8"))
    	.andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isOk());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/TriangleType").param("a", "A").param("b", "").param("c", "8"))
    	.andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/TriangleType").param("a", "0").param("b", "0").param("c", "0"))
    	.andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/TriangleType").param("a", "4").param("b", "-6").param("c", "8"))
    	.andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/TriangleType").param("a", "6").param("b", "7"))
    	.andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isBadRequest());
		
		mockMvc.perform(MockMvcRequestBuilders.get("/api/TriangleType").param("a", "0").param("b", "7").param("c", "8"))
    	.andDo(MockMvcResultHandlers.print())
    	.andExpect(MockMvcResultMatchers.status().isBadRequest());
	}
	
	@Test
	public void testArray() throws Exception {
		
		String jsonContent = "{ \"Array1\":[1,2,3,4], \"Array2\":[3,4,5,6], \"Array3\":[6,1,3,11] }";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/makeonearray").contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON).content(jsonContent))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		String jsonContent1 = "{ \"Array1\":[1,2,3,4], \"Array2\":[3,4,5,6], \"Array3\": }";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/makeonearray").contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON).content(jsonContent1))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andReturn();
		
		String jsonContent2 = " \"Array1\":[1,2,3,4], \"Array2\":[3,4,5,6], \"Array3\":[6,1,3,11] ";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/makeonearray").contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON).content(jsonContent2))
				.andExpect(MockMvcResultMatchers.status().isBadRequest())
				.andReturn();
		
		String jsonContent3 = "{ \"Array1\":[1,2,3], \"Array2\":[3,4,5,6], \"Array3\":[6,1,3,11,20] }";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/makeonearray").contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON).content(jsonContent3))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
		String jsonContent4 = "{ \"Array1\":[1,2,3,4], \"Array2\":[3,4,5,6], \"Array3\":[6,1,3,11], \"Array4\":[6,10,3,11,17] }";
		mockMvc.perform(MockMvcRequestBuilders.post("/api/makeonearray").contentType(MediaType.APPLICATION_JSON)
	            .accept(MediaType.APPLICATION_JSON).content(jsonContent4))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andReturn();
		
	}
	
	
}
