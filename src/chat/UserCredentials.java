package chat;

import java.io.Serializable;

public class UserCredentials implements Serializable{
	
	private String username, password, name, ip;
	private int department;
	
	public UserCredentials(String u, String p, String name, int d){
		this.username = u;
		this.password = p;
		this.name = name;
		this.department = d;
	}
	
	public void setIp(String ip){
		this.ip = ip;
	}
	
	public String getIp(){
		return ip;
	}
	
	public String getUser(){
		return username;
	}
	
	public String getPass(){
		return password;
	}
	
	public String getName(){
		return name;
	}
	
	public int getDept(){
		return department;
	}

}
