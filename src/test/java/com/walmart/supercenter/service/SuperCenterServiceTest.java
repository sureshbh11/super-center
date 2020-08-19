package com.walmart.supercenter.service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Description;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.walmart.supercenter.model.Item;
import com.walmart.supercenter.model.SuperCenter;
import com.walmart.supercenter.model.ZipCode;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SuperCenterServiceTest {

    private static final Logger log = LoggerFactory.getLogger(SuperCenterServiceTest.class);
	
    @Autowired
    private WebApplicationContext webApplicationContext;
    private MockMvc mockMvc;

    @Before
    public void setUp() {
      mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
    }

	public static String asJsonString(final Object obj) throws Exception {
        return new ObjectMapper().writeValueAsString(obj);
	}
	
	@Test()
	@Description("Test for getAllSuperCenterThatCanDeliverFrom")
	public void testGetAllSuperCenterThatCanDeliverFrom() throws Exception {

		MvcResult mvcResult = this.mockMvc.perform(get("/getAllSuperCenterThatCanDeliverFrom")
				.param("zipCode", asJsonString(new ZipCode(1,1D,1D)))
				.contentType(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON))
		 		.andExpect(status().isOk())
		 		.andReturn();
		log.info(mvcResult.toString());
		Assert.assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
	}

	@Test()
	@Description("Test for getItem")
	public void testGetItem() throws Exception {

		final UUID uuId1 = new UUID(1L, 1L);
		final UUID uuId2 = new UUID(2L, 2L);
		final ZipCode zip = new ZipCode(1,1D,1D);
		final SuperCenter sc = new SuperCenter(zip);
		final Item item1 = new Item(uuId1);
		final Item item2 = new Item(uuId2);
		sc.addItem(item1);
		sc.addItem(item2);
		
		MvcResult mvcResult = this.mockMvc.perform(get("/getItem")
				.param("superCenter", asJsonString(sc))
				.param("itemId", asJsonString(uuId1))
				.contentType(MediaType.APPLICATION_JSON)
			    .accept(MediaType.APPLICATION_JSON))
		 		.andExpect(status().isOk())
		 		.andReturn();
		
		log.info(mvcResult.toString());
		Assert.assertEquals("application/json;charset=UTF-8", mvcResult.getResponse().getContentType());
					
	}
	
}
