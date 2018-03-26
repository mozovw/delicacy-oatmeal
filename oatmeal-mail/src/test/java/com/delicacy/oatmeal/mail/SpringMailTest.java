package com.delicacy.oatmeal.mail;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootApplication.class)
public class SpringMailTest {

	@Autowired
	private JavaMailSender mailSender;

	@Test
	public void sendSimpleMail()  {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom("zhangyutao@tomato.shop");
		message.setTo("yutao.xz@qq.com");
		message.setSubject("主题：简单邮件");
		message.setText("测试邮件内容");
		mailSender.send(message);
	}

}
