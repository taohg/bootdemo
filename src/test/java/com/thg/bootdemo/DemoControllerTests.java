package com.thg.bootdemo;

import com.thg.bootdemo.controller.demo.DemoController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

@RunWith(SpringRunner.class)
@SpringBootTest
public class DemoControllerTests {
	private static final Logger logger = LoggerFactory.getLogger(DemoControllerTests.class);
    private MockMvc mvc;

    @Before
    public void setUp() throws Exception{
        mvc = MockMvcBuilders.standaloneSetup(new DemoController()).build();
    }

    @Test
    public void getHello() throws Exception{
    	MvcResult result;
    	ResultActions resultActions;
        
    	resultActions = mvc.perform(MockMvcRequestBuilders.get("/demo/hello").accept(MediaType.APPLICATION_JSON));
    	resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
    	
        result = resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        logger.info("---------getHello:" + result.getResponse().getContentAsString());
    }

    @Test
    public void getBean() throws Exception{
    	MvcResult result;
    	ResultActions resultActions;
    	
        resultActions = mvc.perform(MockMvcRequestBuilders.get("/demo/getBean").accept(MediaType.APPLICATION_JSON));
        resultActions.andReturn().getResponse().setCharacterEncoding("UTF-8");
        
        result = resultActions.andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andReturn();
        logger.info("----------getBean:" + result.getResponse().getContentAsString());
    }
}
