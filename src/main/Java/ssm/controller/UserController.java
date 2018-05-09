package ssm.controller;
/**
 * create byd tan on 2018-05-03
 * 业务逻辑的实现
 * */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import ssm.service.UserService;

@Controller
@RequestMapping("/user")
@Transactional
public class UserController {

    //private final String PAGE_PATH = "/admin/";

    @Autowired
    private UserService userService;

    // 用户登录校验
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public String login() {
        System.out.println("123");
        System.out.println("用户信息为：" + userService.getAllUser());
        return "index";
    }

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
    public String getMenu(String page) {
        return page;
    }

    @RequestMapping(value = "/toPage1")
    public String getMenu1() {
        return "pages/password";
    }
}
