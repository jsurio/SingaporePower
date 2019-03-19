package sp.exam.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import sp.exam.dto.RequestDTO;
import sp.exam.dto.ResponseDTO;
import sp.exam.repository.RepositorySimulator;

@Service
public class FriendServiceImpl implements FriendService {

	private static final String WHITE_SPACE_CHARS = "\\s";
	private static final String EMAIL_PATTERN = ".+@.+\\..+";
	
	@Autowired
	private RepositorySimulator repo;
	
	@Override
	public ResponseDTO add(RequestDTO request) {
		if (ObjectUtils.isEmpty(request) 
				|| CollectionUtils.isEmpty(request.getFriends())) {
			return null;
		}
		
		ResponseDTO response = new ResponseDTO();
		for (String sourceFriend : request.getFriends()) {
			for (String targetFriend: request.getFriends()) {
				if (!sourceFriend.equals(targetFriend)) {
					boolean result = repo.addAsFriend(sourceFriend, targetFriend);
					response.setSuccess(result);
				}
			}
		}

		return response;
	}

	@Override
	public ResponseDTO list(RequestDTO request) {
		if (ObjectUtils.isEmpty(request) 
				|| StringUtils.isEmpty(request.getEmail())) {
			return null;
		}
		
		ResponseDTO response = new ResponseDTO();
		List<String> friendList = repo.getFriendListById(request.getEmail());
		response.setFriends(friendList);
		response.setSuccess(true);
		response.setCount(friendList.size());
		
		return response;
	}

	@Override
	public ResponseDTO common(RequestDTO request) {
		if (ObjectUtils.isEmpty(request) 
				|| CollectionUtils.isEmpty(request.getFriends())) {
			return null;
		}
		
		ResponseDTO response = new ResponseDTO();
		
		String friend1 = request.getFriends().get(0);
		List<String> friend1List = repo.getFriendListById(friend1);
		List<String> friend1ListCopy = new ArrayList<String>(friend1List);
		Collections.copy(friend1ListCopy, friend1List);
		
		String friend2 = request.getFriends().get(1);
		List<String> friend2List = repo.getFriendListById(friend2);
		List<String> friend2ListCopy = new ArrayList<String>(friend2List);
		Collections.copy(friend2ListCopy, friend2List);
		
		//friend1ListCopy will serve as the holder of the return value
		response.setSuccess(friend1ListCopy.retainAll(friend2ListCopy));
		response.setFriends(friend1ListCopy);
		response.setCount(friend1ListCopy.size());
		
		return response;
	}

	@Override
	public ResponseDTO subscribe(RequestDTO request) {
		if (ObjectUtils.isEmpty(request) 
				|| StringUtils.isEmpty(request.getRequestor())
				|| StringUtils.isEmpty(request.getTarget())) {
			return null;
		}
		
		ResponseDTO response = new ResponseDTO();
		response.setSuccess(repo.addSubscriber(request.getRequestor(), request.getTarget()));
		
		return response;
	}

	@Override
	public ResponseDTO block(RequestDTO request) {
		if (ObjectUtils.isEmpty(request) 
				|| StringUtils.isEmpty(request.getRequestor())
				|| StringUtils.isEmpty(request.getTarget())) {
			return null;
		}
		
		ResponseDTO response = new ResponseDTO();
		response.setSuccess(repo.addToBlocked(request.getRequestor(), request.getTarget()));
		
		return response;
	}

	@Override
	public ResponseDTO notify(RequestDTO request) {
		if (ObjectUtils.isEmpty(request) 
				|| StringUtils.isEmpty(request.getSender())
				|| StringUtils.isEmpty(request.getText())) {
			return null;
		}
		
		String sender = request.getSender();
		ArrayList<String> recipients = new ArrayList<String>();
		ResponseDTO response = new ResponseDTO();
		response.setSuccess(true);
		
		String texts = request.getText();
		for (String text : texts.split(WHITE_SPACE_CHARS)) {
			if (text.matches(EMAIL_PATTERN)) {
				recipients.add(text);
			}
		}
		recipients.addAll(repo.getFriendListById(sender));
		recipients.addAll(repo.getSubscriberListById(sender));
		
		recipients.removeAll(repo.getBlockedListById(sender));
		
		response.setRecipients(recipients);
		return response;
	}

}
