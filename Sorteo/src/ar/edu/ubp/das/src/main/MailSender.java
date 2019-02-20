package ar.edu.ubp.das.src.main;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import ar.edu.ubp.das.src.passencriptor.GeneratePlainPassword;



public class MailSender {
	
	public void envioMailNotificacion(String dni, String nombre, String email){
	
	Properties mailServerProperties;
	 Session getMailSession;
	 MimeMessage generateMailMessage;
	 GeneratePlainPassword decriptor = new GeneratePlainPassword();
	 
	 String passwordEncriptado = "6138E3D0FEDFF9E19CE5C06D9557A10C";


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
			/*   Aca iria el mail de lo clientes   */
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("maralcaraz.13@gmail.com"));
		} catch (MessagingException e) {
			System.out.println("[MailSender]Error: "+e.getMessage());
		}
		try {
			generateMailMessage.setSubject("Notificacion PlanSorteAR");
		} catch (MessagingException e) {
			System.out.println("[MailSender]Error: "+e.getMessage());
		}
		String emailBody = "El participante " + nombre + ", dni: " + dni + " ha ganado el sorteo!. " + "<br><br> Su plan sera cancelado, <br>Portal Gobierno";
		try {
			generateMailMessage.setContent(emailBody, "text/html");
		} catch (MessagingException e) {
			System.out.println("[MailSender]Error: "+e.getMessage());
		}
		System.out.println("Mail Session has been created successfully..");

		// Step3
		System.out.println("\n\n 3rd ===> Get Session and Send mail");
		Transport transport = null;
		try {
			transport = getMailSession.getTransport("smtp");
		} catch (NoSuchProviderException e) {
			System.out.println("[MailSender]Error: "+e.getMessage());
		}

		// Enter your correct gmail UserID and Password
		// if you have 2FA enabled then provide App Specific Password
		try {
			transport.connect("smtp.gmail.com", "PlanSorteAR@gmail.com", decriptor.mainDecriptor(passwordEncriptado));
		} catch (MessagingException | InvalidKeyException | NoSuchAlgorithmException | NoSuchPaddingException | IllegalBlockSizeException | BadPaddingException | IOException e) {
			System.out.println("[MailSender]Error: "+e.getMessage());
		}
		try {
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
		} catch (MessagingException e) {
			System.out.println("[MailSender]Error: "+e.getMessage());
		}
		try {
			transport.close();
		} catch (MessagingException e) {
			System.out.println("[MailSender]Error: "+e.getMessage());
		}
		
	}
}
