package com.qubikapp.app.ws.shared.dto;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailService;
import com.amazonaws.services.simpleemail.AmazonSimpleEmailServiceClientBuilder;
import com.amazonaws.services.simpleemail.model.Body;
import com.amazonaws.services.simpleemail.model.Content;
import com.amazonaws.services.simpleemail.model.Destination;
import com.amazonaws.services.simpleemail.model.Message;
import com.amazonaws.services.simpleemail.model.SendEmailRequest;

public class AmazonSES {
	final String FROM="jovan.lazic.10@gmail.com";
	final String SUBJECT="Verifikacija naloga";
	final String HTMLBODY="<h1>Verifikujte nalog</h1>"
	+"<p>Za potvrdu registracije kliknite na sledeci link:</p>"
	+"<a href='http://localhost:8080/verification-service/email-verification.html?token=$tokenValue'>Kliknite ovde...</a>";
//	+"<a href='http://ec2-18-197-183-171.eu-central-1.compute.amazonaws.com:8080/verification-service/email-verification.html?token=$tokenValue'>Kliknite ovde...</a>";
	final String TEXTBODY="Verifikujte vas nalog za AutobuskaApp"
	+"Hvala za registrovanje na nasoj aplikaciji. Jos samo korak vas deli do uspesnog koriscenja usluga nase aplikacije."
			+"Za potvrdu registracije otvorite sledeci link:"
	+"http://localhost:8080/verification-service/email-verification.html?token=$tokenValue"
	+ "Hvala! Ocekujemo vas na nasoj aplikaciji!";
	
	public void verifyEmail(UserDto userDto) {
		System.setProperty("aws.accessKeyId", "AKIAX5BZXRB6E3LXRWV3"); 	
		System.setProperty("aws.secretKey", "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"); 			
		AmazonSimpleEmailService client = AmazonSimpleEmailServiceClientBuilder.standard().withRegion(Regions.US_EAST_1).build();
		String htmlBodyWithTokent=HTMLBODY.replace("$tokenValue", userDto.getEmailVerificationToken());
		String textBodyWithToken=TEXTBODY.replace("$tokenValue", userDto.getEmailVerificationToken());
		SendEmailRequest request = new SendEmailRequest().withDestination(new Destination().withToAddresses(userDto.getEmail())).withMessage(new Message()
				.withBody(new Body().withHtml(new Content().withCharset("UTF-8").withData(htmlBodyWithTokent)).withText(new Content().withCharset("UTF-8")
						.withData(textBodyWithToken))).withSubject(new Content().withCharset("UTF-8")
						.withData(SUBJECT))).withSource(FROM);
		client.sendEmail(request);
		System.out.println("Email sent!");
	}
}
