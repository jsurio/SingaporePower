package sp.exam.service;

import java.util.List;
import java.util.Set;

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
		Set<String> friendList = repo.getFriendListByFriend(request.getEmail());
		response.setFriends(friendList);
		response.setSuccess(true);
		response.setCount(friendList.size());
		
		return response;
	}

	@Override
	public ResponseDTO commonFriend(RequestDTO request) {
		// TODO Auto-generated method stub
		return null;
	}

}
