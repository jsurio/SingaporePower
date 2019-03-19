package sp.exam.service;

import org.springframework.stereotype.Service;

import sp.exam.dto.RequestDTO;
import sp.exam.dto.ResponseDTO;

@Service
public interface FriendService {
	
	ResponseDTO add(RequestDTO request);
	ResponseDTO list(RequestDTO request);
	ResponseDTO common(RequestDTO request);
	ResponseDTO subscribe(RequestDTO request);
	ResponseDTO block(RequestDTO request);
	ResponseDTO notify(RequestDTO request);
	
}
