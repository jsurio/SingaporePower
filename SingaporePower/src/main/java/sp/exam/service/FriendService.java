package sp.exam.service;

import org.springframework.stereotype.Service;

import sp.exam.dto.RequestDTO;
import sp.exam.dto.ResponseDTO;

@Service
public interface FriendService {
	
	ResponseDTO addFriend(RequestDTO request);
	ResponseDTO listFriend(RequestDTO request);
	ResponseDTO commonFriend(RequestDTO request);
	
}
