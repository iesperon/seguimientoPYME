package es.udc.fi.tfg.seguimiento.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.MailSender;

@Service
public class EmailAPI {

	@Autowired
	private MailSender mailSender;
	
	public void enviarEmail (String toAddress, String fromAddress, String subject, String msgBody) {
 
		SimpleMailMessage crunchifyMsg = new SimpleMailMessage();
		crunchifyMsg.setFrom(fromAddress);
		crunchifyMsg.setTo(toAddress);
		crunchifyMsg.setSubject(subject);
		crunchifyMsg.setText(msgBody);
		mailSender.send(crunchifyMsg);
	}
}
