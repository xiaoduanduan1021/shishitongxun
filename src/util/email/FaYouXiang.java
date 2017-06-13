package util.email;


import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Properties;

import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.apache.log4j.Logger;


public class FaYouXiang {

	private Logger log = Logger.getLogger(FaYouXiang.class);
	/**
	 * @param args
	 *            邮件发送
	 * @throws UnsupportedEncodingException
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws UnsupportedEncodingException, InterruptedException {
		String content = "邮箱测试";
		String jieguo = new FaYouXiang().youjianfasong("1021567205@qq.com", "【天猫】", content);
		//log.info(jieguo);
		if(jieguo.equals("Invalid Addresses")){
			//log.info("无效地址");
		}
	}
	// 邮件发送方法
	public String youjianfasong(String shoujianren,String biaoti,String content) {
		try {
			String from = "2439532880@qq.com"; // 发件人
			String to = shoujianren; // 收件人
			String subject = biaoti; // 主题
			String messageText = "";// 内容
			String sendHtml = content;// 邮件正文html
			String password = "twzmxmmfhrgueabb"; // 密码
			String mailserver = "smtp.qq.com";
			// 建立邮件会话
			Properties pro = new Properties();
			pro.put("mail.smtp.host", mailserver);
			pro.put("mail.smtp.auth", "true");
			pro.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			pro.setProperty("mail.smtp.port", "465");
			pro.setProperty("mail.smtp.socketFactory.port", "465");
			Session sess = Session.getInstance(pro);
			sess.setDebug(true);
			// 新建一个消息对象
			MimeMessage message = new MimeMessage(sess);
			// 设置发件人
			InternetAddress from_mail = new InternetAddress(from);
			message.setFrom(from_mail);
			// 设置收件人
			InternetAddress to_mail = new InternetAddress(to);
			message.setRecipient(Message.RecipientType.TO, to_mail);
			// 设置主题
			message.setSubject(subject);
			// 设置内容
			message.setText(messageText);
			// 设置发送时间
			message.setSentDate(new Date());

			// 以下为添加附件内容
			// 向multipart对象中添加邮件的各个部分内容，包括文本内容和附件
			Multipart multipart = new MimeMultipart();

			// 添加邮件正文
			BodyPart contentPart = new MimeBodyPart();
			contentPart.setContent(sendHtml, "text/html;charset=UTF-8");
			multipart.addBodyPart(contentPart);
			message.setContent(multipart);

			// 发送邮件
			message.saveChanges(); // 保证报头域同会话内容保持一致
			Transport transport = sess.getTransport("smtp");
			transport.connect(mailserver, from, password);
			transport.sendMessage(message, message.getAllRecipients());
			transport.close();

			log.info("邮件发送成功");
			return("ok");
		} catch (Exception e) {
			log.info("发送邮件产生的错误：" + e.getMessage());
			return(e.getMessage());
		}
	}


}
