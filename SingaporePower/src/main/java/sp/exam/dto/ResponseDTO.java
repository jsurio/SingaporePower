package sp.exam.dto;

import java.util.List;
import java.util.Set;

public class ResponseDTO {

	private boolean success;
	private int count;
	private List<String> friends;
	private List<String> recipients;
	
	public ResponseDTO(boolean success, int count, List<String> friends, List<String> recipients) {
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
	
	public List<String> getFriends() {
		return friends;
	}
	
	public void setFriends(List<String> friends) {
		this.friends = friends;
	}
	
	public List<String> getRecipients() {
		return recipients;
	}
	
	public void setRecipients(List<String> recipients) {
		this.recipients = recipients;
	}
}
