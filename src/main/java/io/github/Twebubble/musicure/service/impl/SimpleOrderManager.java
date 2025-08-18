package io.github.Twebubble.musicure.service.impl;

import io.github.Twebubble.musicure.model.domain.Order;
import io.github.Twebubble.musicure.service.OrderManager;
import jakarta.mail.Message;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;

//关于邮箱信息发送

@Service
public class SimpleOrderManager implements OrderManager {

    @Value("${mail.address}")
    private String sendAddress;

    @Autowired
    private JavaMailSender mailSender;

    public void setMailSender(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    //发送密码邮件
    //TODO 改良：避免明文传输密码，改成点击链接重置密码
    public void sendPassword(Order order, String receiveAddress) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(@NotNull MimeMessage mimeMessage) throws Exception {
                //添加主题
                mimeMessage.setSubject("账号密码通知");
                //设置收件人
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(receiveAddress));
                //设置发件人
                mimeMessage.setFrom(new InternetAddress(sendAddress));
                //设置纯文本内容
                mimeMessage.setText("Dear " + order.getName() + "\n" +
                        "Your  password is :" + order.getPassword() + ".");
            }
        };

        //异常处理
        //TODO 改良：log.error("邮件发送失败: {}", ex.getMessage());（见项目备忘.txt）
        try {
            this.mailSender.send(preparator);
        } catch (MailException ex) {

            System.err.println(ex.getMessage());
        }
    }

    //发送验证码邮件
    public void sendCode(String code, String receiveAddress) {
        MimeMessagePreparator preparator = new MimeMessagePreparator() {
            public void prepare(@NotNull MimeMessage mimeMessage) throws Exception {
                //添加主题
                mimeMessage.setSubject("账号密码通知");
                mimeMessage.setRecipient(Message.RecipientType.TO,
                        new InternetAddress(receiveAddress));
                mimeMessage.setFrom(new InternetAddress(sendAddress));
                // TODO 改成HTML内容
                mimeMessage.setText("Dear you code is " + code);
            }
        };
        this.mailSender.send(preparator);
    }
}
