package sp.exam.dto;

import java.util.List;
import java.util.Set;

public class ResponseDTO {

	private boolean success;
	private int count;
	private Set<String> friends;
	private Set<String> recipients;
	
	public ResponseDTO(boolean success, int count, Set<String> friends, Set<String> recipients) {
		super();
		this.success = success;
		this.count = count;
		this.friends = friends;
		this.recipients = recipients;
	}
	
	public ResponseDTO() {}

	public boolean isSuccess() {
		return success;
	}
	
	public void setSuccess(boolean success) {
		this.success = success;
	}
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}
	
	public Set<String> getFriends() {
		return friends;
	}
	
	public void setFriends(Set<String> friends) {
		this.friends = friends;
	}
	
	public Set<String> getRecipients() {
		return recipients;
	}
	
	public void setRecipients(Set<String> recipients) {
		this.recipients = recipients;
	}
}
