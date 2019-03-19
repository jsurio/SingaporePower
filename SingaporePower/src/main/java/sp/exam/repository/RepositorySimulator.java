package sp.exam.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.stereotype.Component;

@Component
public class RepositorySimulator {
	
	private Map<String, List<String>> friendMap = new HashMap<String, List<String>>();
	private Map<String, List<String>> subscriberMap = new HashMap<String, List<String>>();
	private Map<String, List<String>> blockedMap = new HashMap<String, List<String>>();
	
	public List<String> getFriendListById(String friend) {
		
		List<String> friendList = new ArrayList<String>();
		if (friendMap.containsKey(friend)) {
			friendList = friendMap.get(friend);
		} else {
			friendList =new ArrayList<String>();
			friendMap.put(friend, friendList);
		}
		
		return friendList;
		
	}
	
	public List<String> getSubscriberListById(String subscriber) {
		
		List<String> subscriberList = new ArrayList<String>();
		if (subscriberMap.containsKey(subscriber)) {
			subscriberList = subscriberMap.get(subscriber);
		} else {
			subscriberList =new ArrayList<String>();
			subscriberMap.put(subscriber, subscriberList);
		}
		
		return subscriberList;
		
	}

	public List<String> getBlockedListById(String blocker) {
	
	List<String> blockedList = new ArrayList<String>();
	if (blockedMap.containsKey(blocker)) {
		blockedList = blockedMap.get(blocker);
	} else {
		blockedList =new ArrayList<String>();
		blockedMap.put(blocker, blockedList);
	}
	
	return blockedList;
	
	}
	
	public boolean addAsFriend(String source, String target) {

		List<String> friendList = getFriendListById(source);
		
		if (!friendList.contains(target)) {
			friendList.add(target);
		}
		
		return true;
	}
	
	public boolean addSubscriber(String requestor, String target) {

		List<String> subscriberList = getSubscriberListById(target);
		
		if (!subscriberList.contains(requestor)) {
			subscriberList.add(requestor);
		}
		
		return true;
	}
	
	public boolean addToBlocked(String requestor, String target) {

		List<String> blockedList = getSubscriberListById(target);
		
		if (!blockedList.contains(requestor)) {
			blockedList.add(requestor);
		}
		
		return true;
	}
}
