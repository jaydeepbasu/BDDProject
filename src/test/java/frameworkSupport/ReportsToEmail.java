package frameworkSupport;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import util.static_data;

public class ReportsToEmail 
{
	public static void reporting_email(String ReportZipPath)
	{
    	System.out.println("Preparing to send the Execution Report in mail");
        // SMTP info
        String host = "smtp.gmail.com";
        String port = "587";
        String mailFrom = "jaydeep.basu1@gmail.com";
        String password = "Jaydeeppp01011987";
 
        // message info
        String mailTo = "jaydeep.basu@gmail.com";
        String subject = "Automation Report "+static_data.today_date;
        String message = "Please find attached the Automation Report executed at : "+static_data.environment+" on "+static_data.today_date;
 
        // attachments
        String[] attachFiles = new String[1];
        attachFiles[0] = ReportZipPath;

        try 
        {
        	System.out.println("Trying to send mail with attachments..");
        	
            sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                subject, message, attachFiles);
            System.out.println("Email sent with attachments..");
        } 
        catch (Exception ex) 
        {
        	try
        	{
        		System.out.println("Re-Trying to send mail without attachments..");
        		
        		List<String> list = new ArrayList<String>(Arrays.asList(attachFiles));
        		list.clear();
        		attachFiles = list.toArray(new String[0]);
        		
        		sendEmailWithAttachments(host, port, mailFrom, password, mailTo,
                        subject, message, attachFiles);
        		
        		System.out.println("Email sent without attachments..");
        	}
        	catch(Exception exc)
        	{
                System.out.println("Could not send email..");
                exc.printStackTrace();
        	}
        }
	}
	
	
	public static void sendEmailWithAttachments(String host, String port,
            final String userName, final String password, String toAddress,
            String subject, String message, String[] attachFiles)
            throws AddressException, MessagingException 
    {
        // sets SMTP server properties
        Properties properties = new Properties();
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.user", userName);
        properties.put("mail.password", password);
 
        // creates a new session with an authenticator
        Authenticator auth = new Authenticator() 
        {
            public PasswordAuthentication getPasswordAuthentication() 
            {
                return new PasswordAuthentication(userName, password);
            }
        };
        Session session = Session.getInstance(properties, auth);
 
        // creates a new e-mail message
        Message msg = new MimeMessage(session);
 
        msg.setFrom(new InternetAddress(userName));
        InternetAddress[] toAddresses = { new InternetAddress(toAddress) };
        msg.setRecipients(Message.RecipientType.TO, toAddresses);
        msg.setSubject(subject);
        msg.setSentDate(new Date());
 
        // creates message part
        MimeBodyPart messageBodyPart = new MimeBodyPart();
        messageBodyPart.setContent(message, "text/html");
 
        // creates multi-part
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messageBodyPart);
 
        // adds attachments
        if (attachFiles != null && attachFiles.length > 0) 
        {
            for (String filePath : attachFiles) 
            {
                MimeBodyPart attachPart = new MimeBodyPart();
 
                try 
                {
                    attachPart.attachFile(filePath);
                } 
                catch (IOException ex) 
                {
                    ex.printStackTrace();
                }
 
                multipart.addBodyPart(attachPart);
            }
        }
 
        // sets the multi-part as e-mail's content
        msg.setContent(multipart);
 
        // sends the e-mail
        Transport.send(msg);
 
    }
}
