package ssm.test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ssm.controller.UserController;

/**
 * create by tan on 2018-05-03
 * 用于测试功能
 * */
@RunWith(SpringJUnit4ClassRunner.class)//此处调用Spring单元测试类
@ContextConfiguration(locations = {"classpath:applicationContext.xml", "classpath:spring/springmvc-servlet.xml"})//加载spring容器
public class UserControllerTest {
    // 注入UserController
    @Autowired
    private UserController userController;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
    }

    // 测试查找所有用户信息
    @Test
    public void test() throws Exception{
        ResultActions resultActions = this.mockMvc.perform(MockMvcRequestBuilders.get("/user/getUserById1?id=" + 1));
        MvcResult mvcResult = resultActions.andReturn();
        String result = mvcResult.getResponse().getContentAsString();
        System.out.println(".........所有用户信息..........");
        System.out.println(result);
    }
}
