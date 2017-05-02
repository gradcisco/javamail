/**
 * 
 */
package email;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import java.util.Properties;

/**
 * @author cisco
 *
 */
public class SendMail {
	
	 public static void main(String [] args)
	   { 
	//prepare arguments
	   String username = "mutegison2@gmail.com";
	   String pwd = "somi28889475";
	   String to = "mutegison2@gmail.com";
	   String from = "mutegison2@gmail.com";
	   String hostname = "smtp.googlemail.com";
	   int port =465;
	   
	   System.out.println("Trying to send an email using apache commons...");
	   //sendmail(username,pwd,from,to,hostname,993);
	   System.out.println("Trying to send an email generically...");
	  // sendGenericMail(username, pwd, from, to, hostname, port);
	   //sendGenericMailWithAttachment
	   sendGenericMailWithAttachment(username, pwd, from, to, hostname, port);
	   }
	 
	 /**
	    * Sends an email using java mail api and activation api
	    * @param username
	    * @param pwd
	    * @param from
	    * @param to
	    * @param hostname
	    * @param port
	    */
	public static void sendGenericMail(String username,String pwd,String from,String to,String hostname,int port){

		      Properties properties = System.getProperties();

		      // Setup mail server
		      properties.setProperty("mail.smtp.host", hostname);
		      properties.put("mail.smtp.starttls.enable", "true");
		      properties.put("mail.smtp.auth", "true");

		      // Get the default Session object.
		      System.out.println("Obtain session...");
		      Session session = Session.getInstance(properties,
		              new javax.mail.Authenticator() {
		                  protected PasswordAuthentication getPasswordAuthentication() {
		                      return new PasswordAuthentication(from, pwd);
		                  }
		              });

		      System.out.println("Preparing message to send...");
		      try{
		         // Create a default MimeMessage object.
		    	  System.out.println("Step 1...");
		         MimeMessage message = new MimeMessage(session);

		         // Set From: header field of the header.
		         System.out.println("Step 2...");
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         System.out.println("Step 3...");
		         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		         // Set Subject: header field
		         System.out.println("Step 4...");
		         message.setSubject("Your subject");

		         // Now set the actual message
		         message.setText("This is actual message");

		         // Send message
		         System.out.println("now sending...");
		        Transport.send(message);
		         System.out.println("Sent message successfully....");
		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
		   }
	
	
	/**
	    * Sends an email using java mail api and activation api
	    * @param username
	    * @param pwd
	    * @param from
	    * @param to
	    * @param hostname
	    * @param port
	    */
	public static void sendGenericMailWithAttachment(String username,String pwd,String from,String to,String hostname,int port){

		      Properties properties = System.getProperties();

		      // Setup mail server
		      properties.setProperty("mail.smtp.host", hostname);
		      properties.put("mail.smtp.starttls.enable", "true");
		      properties.put("mail.smtp.auth", "true");

		      // Get the default Session object.
		      System.out.println("Obtain session...");
		      Session session = Session.getInstance(properties,
		              new javax.mail.Authenticator() {
		                  protected PasswordAuthentication getPasswordAuthentication() {
		                      return new PasswordAuthentication(from, pwd);
		                  }
		              });

		      System.out.println("Preparing message to send...");
		      try{
		         // Create a default MimeMessage object.
		    	  System.out.println("Step 1...");
		         MimeMessage message = new MimeMessage(session);

		         // Set From: header field of the header.
		         System.out.println("Step 2...");
		         message.setFrom(new InternetAddress(from));

		         // Set To: header field of the header.
		         System.out.println("Step 3...");
		         message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

		         // Set Subject: header field
		         System.out.println("Step 4...");
		         message.setSubject("Your subject");

		         // Create the message part
		         BodyPart messageBodyPart = new MimeBodyPart();

		         // Now set the actual message
		         messageBodyPart.setText("This is message body");

		         // Create a multipar message
		         Multipart multipart = new MimeMultipart();

		         // Set text message part
		         multipart.addBodyPart(messageBodyPart);

		         // Part two is attachment
		         messageBodyPart = new MimeBodyPart();
		         String filename = "softwares.txt";
		         DataSource source = new FileDataSource(filename);
		         messageBodyPart.setDataHandler(new DataHandler(source));
		         messageBodyPart.setFileName(filename);
		         multipart.addBodyPart(messageBodyPart);

		         // Send the complete message parts
		         message.setContent(multipart);

		         // Send message
		         Transport.send(message);
		         System.out.println("Sent message successfully with attachment....");
		      }catch (MessagingException mex) {
		         mex.printStackTrace();
		      }
		   }

}
