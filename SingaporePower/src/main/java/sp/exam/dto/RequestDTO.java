package sp.exam.dto;

import java.util.List;

public class RequestDTO {
	
	private String email;
	private String requestor;
	private String target;
	private List<String> friends;
	private String sender;
	private String text;
	

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRequestor() {
		return requestor;
	}
	
	public void setRequestor(String requestor) {
		this.requestor = requestor;
	}
	
	public String getTarget() {
		return target;
	}
	
	public void setTarget(String target) {
		this.target = target;
	}
	
	public List<String> getFriends() {
		return friends;
	}
	
	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
	
	public String getSender() {
		return sender;
	}
	
	public void setSender(String sender) {
		this.sender = sender;
	}
	
	public String getText() {
		return text;
	}
	
	public void setText(String text) {
		this.text = text;
	}
	
}
