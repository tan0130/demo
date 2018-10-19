package ssm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ssm.service.UserService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * create by 1311230692@qq.com on 2018/10/16 09:02
 * 页面跳转控制器实现
 **/
@Controller
@RequestMapping("/page")
public class PageController {

    /**
     * 登录成功跳转 menu 菜单页面
     *
     * */
    @RequestMapping(value = "/toMenu")
    public String toMenuPage(int id) {
        return "admin/menu";
    }

    /**
     * 跳转 information 查询所有人信息页面
     * */
    @RequestMapping(value = "/toInformation")
    public String toInformationPage() {
        return "page/information";
    }

    /**
     * 跳转 password 密码修改页面
     * */
    @RequestMapping(value = "/toPassword")
    public String toPasswordPage() {
        return "page/password";
    }

    /**
     * 跳转 login 登录页面
     * */
    @RequestMapping(value = "/login")
    public void toLoginPage(HttpServletRequest request, HttpServletResponse response) throws Exception{
        request.getRequestDispatcher("/login.html").forward(request, response);
        System.out.println("跳转登录界面");
    }

    /**
     * 跳转 check 验证方式页面
     * */
    @RequestMapping(value = "/toCheck")
    public String toCheckPage() {
        return "check/check";
    }

    /**
     * 跳转 email 邮箱验证页面
     * */
    @RequestMapping(value = "/toEmail")
    public String toEmailPage() {
        return "check/email";
    }

    /**
     * 跳转 message 短信验证页面
     * */
    @RequestMapping(value = "/toMessage")
    public String toMessagePage() {
        return "check/message";
    }

    /**
     * 跳转 password1 密码修改页面
     * */
    @RequestMapping(value = "/toPassword1")
    public String toPassword1Page() {
        return "check/password1";
    }
}
