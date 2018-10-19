package ssm.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import ssm.dao.PasswordDAO;
import ssm.entity.User;
import ssm.util.RandomUtil;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * create by 1311230692@qq.com on 2018/10/17 13:51
 * 密码相关操作业务逻辑层实现
 **/
@Service("PasswordService")
public class PasswordServiceImpl implements PasswordService{

    @Resource
    private PasswordDAO passwordDAO;

    @Resource(name = "mailSender")
    private JavaMailSender mailSender;

    @Value("${qq.mail.username}")
    private String from;

    @Override
    public User getUserByEmail(String email) {
        return passwordDAO.getUserByEmail(email);
    }

    @Override
    public User getUserByTel(String tel) {
        return passwordDAO.getUserByTel(tel);
    }

    @Override
    public ConcurrentHashMap<String, Object> sendHtmlEmail(String to) {
        ConcurrentHashMap<String, Object> map = new ConcurrentHashMap<>();
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        int checkCode = RandomUtil.getRandNum();
        String subject = "找回密码邮件";
        String content = "<html>\n" + "<body>\n" +
                "<h3>尊敬的用户，您好!</h3>\n" + "&nbsp;&nbsp;&nbsp;&nbsp;校验码为：" + checkCode +  ",30 分钟内有效！" +
                "</body>\n" + "</html>\n";
        try {
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8"); // 设置字符编码，避免中文乱码
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content, true);

            mailSender.send(mimeMessage);
            map.put("checkEmailCode", checkCode);
            System.out.println("HTML 格式邮件发送成功");
        } catch(MessagingException e) {
            System.out.println("HTML 格式邮件发送异常");
            e.printStackTrace();
        }
        return map;
    }

    @Override
    public void updatePassword(User user) {
        passwordDAO.updatePassword(user);
    }
}
