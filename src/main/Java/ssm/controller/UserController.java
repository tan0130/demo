package ssm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
//import org.apache.logging.log4j.Logger;
//import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.ThreadContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.entity.User;
import ssm.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * create by 1311230692@qq.com on 2018/10/15 15:40
 * 用户操作控制层实现
 * 采用状态码来表示返回信息，规则如下：
 * 00001：成功
 * 00002：失败
 * 00021：用户不存在
 * 00022：密码错误
 * 00023: 邮箱未绑定
 * 00024：手机号格式错误
 * 00025：手机号未绑定
 * 00026: 验证码错误
 * 00003：服务器错误
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    // 使用 slf4j 日志记录
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    // 注入 Service
    @Resource(name = "UserService")
    private UserService userService;

    /**
     * 查询所有用户信息
     * */
    @RequestMapping(value = "/getAllUser", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getAllUser() throws Exception{
        // concurrentHashMap 在多线程的情况下线程安全
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<User> userList = userService.getAllUser();
            if (userList.size() == 0) {
                map.put("result", "00002");
            } else {
                map.put("result", "00001");
                map.put("total", userList.size());
                map.put("rows", userList);
            }
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            map.put("result", "00003");
            return objectMapper.writeValueAsString(map);
        }
    }

    /**
     * 根据 id 查询用户信息，判断 id 是否在数据库中
     * @param id 要查询的 id
     * */
    @RequestMapping(value = "/getUserById", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getUserById(int id) throws Exception{
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
           if (null == userService.getUserById(id)) {
               map.put("result", "00021");
           } else {
               map.put("result", "00001");
           }
           return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            map.put("result", "00003");
            return objectMapper.writeValueAsString(map);
        }
    }

    /**
     * 根据 id 查询用户信息，返回 list
     * @param id 要查询的 id
     * */
    @RequestMapping(value = "/getUserByIdList", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getUserByIdList(int id) throws Exception{
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            List<User> userList = userService.getUserByIdList(id);
            if (userList.size() == 0) {
                map.put("result", "00002");
            } else {
                map.put("result", "00001");
                map.put("total", userList.size());
                map.put("rows", userList);
            }
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            map.put("result", "00003");
            return objectMapper.writeValueAsString(map);
        }
    }

    /**
     * 根据 id 和 密码查询用户信息，用于登录验证判断密码是否正确
     * @param id 要查询的 id
     * @param password 要查询的密码
     * */
    @RequestMapping(value = "/getUserByIdAndPwd", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getUserByIdAndPwd(int id, String password, HttpServletRequest request) throws Exception{
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            if (null == userService.getUserById(id)) {
                map.put("result", "00021");
            } else if (null == userService.getUserByIdAndPwd(id, password)) {
                map.put("result", "00022");
            } else {
                map.put("result", "00001");
                request.getSession().setAttribute("loginId", id);
            }
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            map.put("result", "00003");
            return objectMapper.writeValueAsString(map);
        }
    }

    /**
     * 添加用户信息
     * @param user 要添加的用户实体
     * */
    @RequestMapping(value = "/addUser", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String addUser(User user) throws Exception{
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("user:" + user);
        try {
            if (null == user) {
                map.put("result", "00002");
            } else {
//                System.out.println("添加用户");
                userService.addUser(user);
//                System.out.println("添加成功");
                map.put("result", "00001");
            }
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            map.put("result", "00003");
//            System.out.println("添加失败");
            return objectMapper.writeValueAsString(map);
        }
    }

    /**
     * 删除用户信息
     * @param id 要删除用户的 id
     * */
    @RequestMapping(value = "/deleteUser", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String deletUser(int id) throws Exception{
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            if (null == userService.getUserById(id)) {
                map.put("result", "00021");
            } else {
                userService.deleteUser(id);
                map.put("result", "00001");
            }
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            map.put("result", "00003");
            return objectMapper.writeValueAsString(map);
        }
    }

    /**
     * 修改用户信息
     * @param user 要更新的用户实体
     * @param id 要更新的用户 id
     * */
    @RequestMapping(value = "/updateUser", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updateUser(User user ,int id) throws Exception{
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            if (null == userService.getUserById(id)) {
                map.put("result", "00021");
            } else {
                user.setId(id);
                userService.updateUser(user);
                map.put("result", "00001");
            }
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            map.put("result", "00003");
            return objectMapper.writeValueAsString(map);
        }
    }

    /**
     * 修改用户密码
     * @param oldPassword 旧密码
     * @param user_password 新密码
     * */
    @RequestMapping(value = "/updatePassword", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updatePassword(String oldPassword, String user_password, HttpServletRequest request) throws Exception{
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        // 获取登录 id
        int id = (int) request.getSession().getAttribute("loginId");
        User user = new User();
//        System.out.println("id:" + id);
        try {
            if (null == userService.getUserById(id)) {
                map.put("result", "00021");
            } else if (null == userService.getUserByIdAndPwd(id, oldPassword)) {
                map.put("result", "00022");
            } else {
                user.setId(id);
                user.setUser_password(user_password);
                System.out.println("user:" + user);
                userService.updatePassword(user);
                map.put("result", "00001");
            }
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            map.put("result", "00003");
            return objectMapper.writeValueAsString(map);
        }
    }

    /**
     * 登录验证
     * @param id 登录 id
     * @param password 登录密码
     * */
    @RequestMapping(value = "/login", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String login(int id, String password, int remFlag, HttpServletRequest request, HttpServletResponse response) throws Exception{
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (null == userService.getUserById(id)) {
                map.put("result", "00021");
            } else if (null == userService.getUserByIdAndPwd(id, password)) {
                map.put("result", "00022");
            } else {
                map.put("result", "00001");

                // 将记录写入数据库中
//                ThreadContext.put("userId", String.valueOf(id));
                ThreadContext.put("userName", userService.getUserById(id).getUser_name());

                // 调用函数，将 Cookie 保存到
                addCookie(id, password, remFlag, response,request);

                logger.info("用户登录成功");
                System.out.println("登录日志写入成功");
                // 将登录 id 保存 session 中
                request.getSession().setAttribute("loginId", id);
            }
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            map.put("result", "00003");
            System.out.println("controller login 异常");
            return objectMapper.writeValueAsString(map);
        }
    }



    /**
     * 获取登录用户对应的用户名，用于前端菜单页面显示
     * */
    @RequestMapping(value = "/name", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getName(HttpServletRequest request) throws Exception{
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        try {
           int loginId = (int)request.getSession().getAttribute("loginId");
           String user_name = userService.getUserById(loginId).getUser_name();
           map.put("result", "00001");
           map.put("user_name", user_name);
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            map.put("result", "00003");
            return objectMapper.writeValueAsString(map);
        }
    }

    /**
     * 获取 Cookie中的信息
     * @param request 请求
     */
    @RequestMapping(value="/getCookie")
    @ResponseBody
    public String getCookie(HttpServletRequest request)throws IOException {
        String loginAccount = "";
        String loginPassword = "";
        Cookie[] cookies = request.getCookies();
        if(cookies != null && cookies.length > 0){
            //遍历Cookie
            for (int i = 0;i < cookies.length;i++){
                Cookie cookie = cookies[i];
                //此处类似与Map有
                if("loginAccount".equals(cookie.getName())){
                    loginAccount = cookie.getValue();
                }
                if("loginPassword".equals(cookie.getName())){
                    loginPassword = cookie.getValue();
                }
            }
        }
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        map.put("loginAcount", loginAccount);
        map.put("loginPassword", loginPassword);
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(map);
    }

    private void addCookie(int loginAccount, String loginPassword, int remFlag, HttpServletResponse response, HttpServletRequest request)
            throws UnsupportedEncodingException {
        if(loginAccount != 0 && !loginPassword.equals("")){
            //创建  Cookie
            Cookie loginAccountCookie = new Cookie("loginAccount",String.valueOf(loginAccount));
            Cookie loginPasswordCookie = new Cookie("loginPassword",loginPassword);
            //设置Cookie的父路经
//            loginAccountCookie.setPath(request.getContextPath()+"/");
//            loginPasswordCookie.setPath(request.getContextPath()+"/");
            //获取是否保存Cookie（例如：复选框的值）
//            String rememberMe = request.getParameter("rememberMe");
            if(0 == remFlag){
                //不保存Cookie
                loginAccountCookie.setMaxAge(0);
                loginPasswordCookie.setMaxAge(0);
            } else if (1 == remFlag) {
                //保存Cookie的时间长度，单位为秒
                loginAccountCookie.setMaxAge(7 * 24 * 60 * 60);
                loginPasswordCookie.setMaxAge(7 * 24 * 60 * 60);
            } else {

            }
            //加入Cookie到响应头
            response.addCookie(loginAccountCookie);
            response.addCookie(loginPasswordCookie);
        }
    }
}
