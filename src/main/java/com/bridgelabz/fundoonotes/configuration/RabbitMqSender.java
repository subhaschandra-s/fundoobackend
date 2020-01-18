package com.bridgelabz.fundoonotes.configuration;

import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.dto.MailObject;

@Component
public class RabbitMqSender 
{
	@Autowired
	private AmqpTemplate rabbitTemplate;
	
	@Value("{rabbitmq.exchange}")
	private String exchange;
	
	@Value("{rabbitmq.routingkey}")
	private String routingkey;

	public void send(MailObject mailobj) 
	{
		rabbitTemplate.convertAndSend(exchange,routingkey,mailobj);
		System.out.println("send message "+mailobj);
		
	}	

	
}
