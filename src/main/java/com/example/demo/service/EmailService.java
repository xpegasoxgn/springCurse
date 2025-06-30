package com.example.demo.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;

    public void enviarCorreo(String para,String asunto,String mensaje){
        SimpleMailMessage mail =new SimpleMailMessage();
        mail.setTo(para);
        mail.setSubject(asunto);
        mail.setText(mensaje);
        mail.setFrom("junito@gmail.com");

        mailSender.send(mail);
    }
}
