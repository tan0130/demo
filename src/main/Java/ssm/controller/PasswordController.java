package ssm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import ssm.entity.User;
import ssm.service.PasswordService;
import ssm.service.UserService;
import ssm.util.IndustrySMS;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * create by 1311230692@qq.com on 2018/10/17 13:47
 * 对密码相关操作控制器实现
 **/
@Controller
@RequestMapping("/password")
public class PasswordController {

    @Resource(name = "PasswordService")
    private PasswordService passwordService;

    @Resource(name = "UserService")
    private UserService userService;

    /**
     * 修改用户密码
     * @param user_password 新密码
     * */
    @RequestMapping(value = "/updatePassword", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String updatePassword(String user_password, HttpServletRequest request) throws Exception{
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println("修改密码");
        // 获取登录 id
        int id = (int) request.getSession().getAttribute("loginId");
        System.out.println("id:" + id);
        User user = new User();
        try {

            if (null == userService.getUserById(id)) {
                map.put("result", "00021");
            } else {
                user.setId(id);
                user.setUser_password(user_password);
                System.out.println("user:" + user);
                passwordService.updatePassword(user);
                map.put("result", "00001");
                request.getSession().removeAttribute("loginId");
//                id = (int) request.getSession().getAttribute("loginId");
//                System.out.println("移除 session 之后 id:" + id);
            }
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            map.put("result", "00003");
            return objectMapper.writeValueAsString(map);
        }
    }

    /**
     * 根据 email 查询用户信息，判断邮箱是否在数据库中
     * @param user_email 要查询的 user_email
     * */
    @RequestMapping(value = "/sendEmail", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String getUserById(String user_email, HttpServletRequest request) throws Exception{
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            if (null == passwordService.getUserByEmail(user_email)) {
                map.put("result", "00023");
            } else {
//                passwordService.sendHtmlEmail(user_email);
                map = passwordService.sendHtmlEmail(user_email);
                int checkEmailCode = (int)map.get("checkEmailCode");
                System.out.println("验证码为：" + checkEmailCode);
                map.put("result", "00001");
                int id = (int)passwordService.getUserByEmail(user_email).getId();
                System.out.println("id:" + id);
                request.getSession().setAttribute("loginId", id);
                request.getSession().setAttribute("checkEmailCode", checkEmailCode);

            }
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            map.put("result", "00003");
            return objectMapper.writeValueAsString(map);
        }
    }


    /**
     * 校验邮件中下发的验证码和用户输入的是否一致
     * @param checkCode 要查询的 checkCode
     * */
    @RequestMapping(value = "/checkEmailCode", produces = "application/json; charset=utf-8")
    @ResponseBody
    public String checkEmailCode(int checkCode, HttpServletRequest request) throws Exception{
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();
        int sessionEmailCode = (int)request.getSession().getAttribute("checkEmailCode");
        System.out.println("sessionEmailCode:" + sessionEmailCode);
        try {
            if (checkCode != sessionEmailCode) {
                map.put("result", "00026");
            } else {
                map.put("result", "00001");
                request.getSession().removeAttribute("checkEmailCode");
//                sessionEmailCode = (int)request.getSession().getAttribute("checkEmailCode");
//                System.out.println("移除 session 之后，checkEmailCode:" + sessionEmailCode);
            }
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            map.put("result", "00003");
            return objectMapper.writeValueAsString(map);
        }
    }

    /**
     * 发送短信验证码
     * 使用 秒嘀科技 第三方短信平台
     * */
    @RequestMapping(value = "/sendMsg", method = RequestMethod.GET)
    @ResponseBody
    public String send(String user_tel, HttpServletRequest request) throws Exception{
        HashMap<String, String> map = IndustrySMS.executeHash(user_tel);
        ObjectMapper objectMapper = new ObjectMapper();

        String status = map.get("respCode");
        String checkMsgCode = map.get("checkMsgCode");
        // 将验证码缓存到 session 中
        HttpSession session = request.getSession();
        session.setMaxInactiveInterval(60 * 1); // 设置 session 一分钟内有效,单位是秒

        try {
            if (status.trim().equals("00025")) {
                map.put("result", "00024");
            } else if (null == passwordService.getUserByTel(user_tel)) {
                map.put("result", "00025");
            } else if (status.trim().equals("00000")) {
                map.put("result", "00001");
                session.setAttribute("checkMsgCode",checkMsgCode);

                int id = passwordService.getUserByTel(user_tel).getId();
                System.out.println("短信验证 id:" + id);
                session.setAttribute("loginId", id);

            } else if(status.trim().equals("00141")) {
                map.put("result", "00141");
            }
            return objectMapper.writeValueAsString(map);
        } catch (Exception e) {
            map.put("result", "00003");
            return objectMapper.writeValueAsString(map);
        }
    }

    /**
     * 比对短信校验码
     * */
    @RequestMapping(value = "/checkMsgCode", method = RequestMethod.POST)
    @ResponseBody
    public String checkCode(String checkCode, HttpServletRequest request) throws Exception{
        HashMap<String, String> map = new HashMap<>();
        ObjectMapper objectMapper = new ObjectMapper();

        HttpSession session = request.getSession(); // 设置 session
        String sessionMsgCode = (String)session.getAttribute("checkMsgCode");

        if ((checkCode).equals(sessionMsgCode)) { // 和缓存比对验证码是否相同
            map.put("result", "00001");
            session.removeAttribute("checkMsgCode");
        } else {
            map.put("result", "00026");
        }
        return objectMapper.writeValueAsString(map);
    }
}
