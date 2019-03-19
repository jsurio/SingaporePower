package sp.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sp.exam.dto.RequestDTO;
import sp.exam.dto.ResponseDTO;
import sp.exam.repository.RepositorySimulator;
import sp.exam.service.FriendService;

@RestController
@RequestMapping("/friend")
public class FriendController {

	@Autowired
	private FriendService service;
	
	/**
	 * Links two friends together.
	 * <p> 
	 * This method virtually adds each parameter to 
	 * another in its list of friends.
	 */
	@RequestMapping(value = "/add")
	public ResponseDTO addFriend(@RequestBody RequestDTO request) {
		
		return service.add(request);
	}
	
	/**
	 * Get friend list.
	 * <p> 
	 * Given the id of the requestor, this method gets
	 * the friend list of the requestor along with the count
	 * of how many friend he has.
	 */
	@RequestMapping(value = "/list")
	public ResponseDTO listFriend(@RequestBody RequestDTO request) {
		
		return service.list(request);
	}
	
	/**
	 * Get common friends.
	 * <p>
	 * Gets only the common friends between the two parameter.
	 */
	@RequestMapping(value = "/common")
	public ResponseDTO commonFriend(@RequestBody RequestDTO request) {
		
		return service.common(request);
	}
	
	/**
	 * Subscribe to someone.
	 * 
	 * Using the requestor and the target as a parameter,
	 * requestor will be added to the target's subscriber list 
	 * and will be notified once the target notified everyone.
	 */
	@RequestMapping(value = "/subscribe")
	public ResponseDTO subscribe(@RequestBody RequestDTO request) {
		
		return service.subscribe(request);
	}
}
