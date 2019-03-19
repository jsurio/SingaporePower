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

	@Autowired
	private RepositorySimulator repo;
	
	@Override
	public ResponseDTO addFriend(RequestDTO request) {
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
	public ResponseDTO listFriend(RequestDTO request) {
		if (ObjectUtils.isEmpty(request) 
				|| StringUtils.isEmpty(request.getEmail())) {
			return null;
		}
		
		ResponseDTO response = new ResponseDTO();
		List<String> friendList = repo.getFriendListByFriend(request.getEmail());
		response.setFriends(friendList);
		response.setSuccess(true);
		response.setCount(friendList.size());
		
		return response;
	}

	@Override
	public ResponseDTO commonFriend(RequestDTO request) {
		if (ObjectUtils.isEmpty(request) 
				|| CollectionUtils.isEmpty(request.getFriends())) {
			return null;
		}
		
		ResponseDTO response = new ResponseDTO();
		
		String friend1 = request.getFriends().get(0);
		List<String> friend1List = repo.getFriendListByFriend(friend1);
		List<String> friend1ListCopy = new ArrayList<String>(friend1List);
		Collections.copy(friend1ListCopy, friend1List);
		
		String friend2 = request.getFriends().get(1);
		List<String> friend2List = repo.getFriendListByFriend(friend2);
		List<String> friend2ListCopy = new ArrayList<String>(friend2List);
		Collections.copy(friend2ListCopy, friend2List);
		
		//friend1ListCopy will serve as the holder of the return value
		response.setSuccess(friend1ListCopy.retainAll(friend2ListCopy));
		response.setFriends(friend1ListCopy);
		response.setCount(friend1ListCopy.size());
		
		return response;
	}

}
