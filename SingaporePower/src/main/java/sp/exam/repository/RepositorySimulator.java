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
	
	public List<String> getFriendListByFriend(String friend) {
		
		List<String> friendList = new ArrayList<String>();
		if (friendMap.containsKey(friend)) {
			friendList = friendMap.get(friend);
		} else {
			friendList =new ArrayList<String>();
			friendMap.put(friend, friendList);
		}
		
		return friendList;
		
	}
	
	public boolean addAsFriend(String sourceFriend, String targetFriend) {

		List<String> friendList = getFriendListByFriend(sourceFriend);
		
		if (!friendList.contains(targetFriend)) {
			friendList.add(targetFriend);
		}
		
		return true;
	}
}
