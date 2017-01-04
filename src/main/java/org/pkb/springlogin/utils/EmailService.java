package org.pkb.springlogin.utils;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class EmailService {

	private String username;

	private String password;

	private String host;

	private String port;

	/**
	 * @param username
	 * @param password
	 * @param host
	 * @param port
	 */
	public EmailService(String username, String password, String host, String port) {
		super();
		this.username = username;
		this.password = password;
		this.host = host;
		this.port = port;
	}

	public void sendEmail(String fromEmail, String fromName, String toEmail, String subject, String messageBody)
			throws Exception {

		Properties props = new Properties();
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.starttls.enable", "true");
		props.put("mail.smtp.host", host);
		props.put("mail.smtp.port", port);

		Session session = Session.getInstance(props, new javax.mail.Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(username, password);
			}
		});

		try {

			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(fromEmail, fromName));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
			message.setSubject(subject);
			message.setContent(messageBody, "text/html");
			Transport.send(message);
			System.out.println("Email Sent");

		} catch (MessagingException e) {
			throw new Exception(e);
		}
	}

}
