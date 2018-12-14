package ar.edu.ubp.das.src.main;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class MailSender {
	
	public void envioMailNotificacion(String dni, String nombre, String email){
	
	Properties mailServerProperties;
	 Session getMailSession;
	 MimeMessage generateMailMessage;


		// Step1
		System.out.println("\n 1st ===> setup Mail Server Properties..");
		mailServerProperties = System.getProperties();
		mailServerProperties.put("mail.smtp.port", "587");
		mailServerProperties.put("mail.smtp.auth", "true");
		mailServerProperties.put("mail.smtp.starttls.enable", "true");
		System.out.println("Mail Server Properties have been setup successfully..");

		// Step2
		System.out.println("\n\n 2nd ===> get Mail Session..");
		getMailSession = Session.getDefaultInstance(mailServerProperties, null);
		generateMailMessage = new MimeMessage(getMailSession);
		try {
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("InsertarMailParaEnviar"));
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			generateMailMessage.setSubject("Notificacion PlanSorteAR");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String emailBody = "El participante " + nombre + ", dni: " + dni + " ha ganado el sorteo!. " + "<br><br> Su plan sera cancelado, <br>Portal Gobierno";
		try {
			generateMailMessage.setContent(emailBody, "text/html");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("Mail Session has been created successfully..");

		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = null;
		try {
			transport = getMailSession.getTransport("smtp");
		} catch (NoSuchProviderException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		try {
			transport.connect("smtp.gmail.com", "PlanSorteAR@gmail.com", "Password");
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			transport.close();
		} catch (MessagingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
