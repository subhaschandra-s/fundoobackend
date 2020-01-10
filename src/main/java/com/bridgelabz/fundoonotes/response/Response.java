package com.bridgelabz.fundoonotes.response;

import org.springframework.stereotype.Component;

import com.bridgelabz.fundoonotes.model.UserInfo;
import com.bridgelabz.fundoonotes.utility.Jwt;

@Component
public class Response 
{
private String name;
private  int MessageCode;
private Object obj;

Jwt jwt=new Jwt();

public Response()
{
	
}

@Override
public String toString() {
	return "Response [name=" + name + ", MessageCode=" + MessageCode + ", obj=" + obj + ", jwt=" + jwt + "]";
}

public Response(String name,int error,Object obj)
{
	super();
	this.name=name;
	this.MessageCode=error;
	this.obj=obj;
	
}

public String getName() {
	return name;
}

public void setName(String name) {
	this.name = name;
}

public int getMessageCode() {
	return MessageCode;
}

public void setMessageCode(int messageCode) {
	MessageCode = messageCode;
}

public Object getObj() {
	return obj;
}

public void setObj(Object obj) {
	this.obj = obj;
}

public String getToken(UserInfo user)
{
	return  jwt.generateToken(user);
	
}



}
