package ssm.controller;
/**
 * create byd tan on 2018-05-03
 * 业务逻辑的实现
 * */

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.ThreadContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.entity.User;
import ssm.service.UserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@Controller
@RequestMapping("/user")
public class UserController {

    //private final String PAGE_PATH = "/admin/";
    private static Logger logger = LogManager.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    // 根据ID查询用户信息
    @RequestMapping(value = "/getUserById", method = RequestMethod.GET)
    @ResponseBody
    public String getUserById(int id) {
        try {
            if(userService.getUserById(id) == null) {
                return "false";
            } else {
                return "true";
            }
        } catch(Exception e) {
            return "false";
        }
    }

    // 根据ID查询用户信息
    @RequestMapping(value = "/getUserById1", method = RequestMethod.GET)
    @ResponseBody
    public String getUserById1(int id) {
        try {
            List<User> user = userService.getUserById1(id);
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("total", user.size());
            map.put("rows", user);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(map);
            return json;
        } catch(Exception e) {
            return "";
        }
    }


    // 根据ID和密码查询用户信息
    @RequestMapping(value = "/getUserByIdAndPwd", method = RequestMethod.GET)
    @ResponseBody
    public String getUserByIdAndPwd(int id, String password) {
        try {
            if(userService.getUserByIdAndPwd(id, password) == null) {
                return "false";
            } else {
                return "true";
            }
        } catch(Exception e) {
            return "false";
        }
    }

    @RequestMapping(value = "/toPage")
    public String getMenu(int id) {
        ThreadContext.put("userId", String.valueOf(id));
        ThreadContext.put("userName", userService.getUserById(id).getUsername());
        logger.info("登录成功");
        return "admin/menu";
    }

    @RequestMapping(value = "/toPage1")
    public String getMenu1() {
        return "pages/password";
    }

    @RequestMapping(value = "/toPage2")
    public String getMenu2() {
        return "pages/information";
    }

    // 查询所有用户信息
    @RequestMapping(value = "/getAllUser",  produces = "application/json; charset=utf-8", method = RequestMethod.GET)
    @ResponseBody
    public String getAllUser() {
        try {
            List<User> user = userService.getAllUser();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("total", user.size());
            map.put("rows", user);
            ObjectMapper mapper = new ObjectMapper();
            String json = mapper.writeValueAsString(map);
            return json;
        } catch(Exception e) {
            return "";
        }
    }

    // 添加用户信息
    @RequestMapping(value = "/addUser", method = RequestMethod.POST)
    @ResponseBody
    public String addUser(User user) {
        try {
            userService.addUser(user);
            return "success";
        } catch(Exception e) {
            return "fail";
        }
    }

    // 删除用户信息
    @RequestMapping(value = "/delUser")
    @ResponseBody
    public String delUser(int id) {
        try {
            ThreadContext.put("userId", String.valueOf(id));
            ThreadContext.put("userName", userService.getUserById(id).getUsername());
            logger.info("删除信息成功");
            userService.deleteUser(id);
            return "success";
        } catch(Exception e) {
            return "fail";
        }
    }

    // 更新用户信息
    @RequestMapping(value = "/updateUser", method = RequestMethod.POST)
    @ResponseBody
    public String updateUser(User user, int id) {
        try {
            user.setUserid(id);
            userService.updateUser(user);
            return "success";
        } catch(Exception e) {
            return "fail";
        }
    }

    // 更新用户密码
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ResponseBody
    public String updatePassword(User user, int id,  String password) {
        try {
            user.setUserid(id);
            user.setPassword(password);
            userService.updatePassword(user);
            ThreadContext.put("userId", String.valueOf(id));
            ThreadContext.put("userName", userService.getUserById(id).getUsername());
            logger.info("修改密码成功");
            return "success";
        } catch(Exception e) {
            return "fail";
        }
    }

}
