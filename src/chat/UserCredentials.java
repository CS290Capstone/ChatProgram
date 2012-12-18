package chat;

import java.io.Serializable;

public class UserCredentials implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -6489156545872614076L;
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

	public String toString(){
		return String.format("[IP=\"%s\";USERNAME=\"%s\";NAME=\"%s\";]", ip, username, name);
	}
}
