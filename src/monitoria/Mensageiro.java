package monitoria;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mensageiro {
    
    public static void enviarEmail(Aluno alunos, String mensagem) {

   	 Properties props = new Properties();
   	 props.put("mail.smtp.host", "smtp.gmail.com");
   	 props.put("mail.smtp.port", "587");
   	 props.put("mail.smtp.auth", "true");
   	 props.put("mail.smtp.starttls.enable", "true");

   	 Session session = Session.getInstance(props,new javax.mail.Authenticator() {
   		 protected PasswordAuthentication getPasswordAuthentication() {
   			 return new PasswordAuthentication("projetomonitores76@gmail.com", "eyrrodxsjoqsuvga");
   		 }
   	 });

   	 try {
   		 
   		 Message message = new MimeMessage(session);
   		 message.setFrom(new InternetAddress("projetomonitores@gmail.com"));
   		 message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(alunos.getEmail()));
   		 message.setSubject("Inscrição no edital de monitoria");
		 message.setText(mensagem);

   		 Transport.send(message);

   		 System.out.println("Email enviado com a confirmação da inscrição!");

   	 } catch (MessagingException e) {
   		 System.out.println("Ocorreu um erro ao enviar o email: " + e.getMessage());
   	 }
    }
}
