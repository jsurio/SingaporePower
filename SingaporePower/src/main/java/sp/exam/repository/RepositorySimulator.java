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
	
	private Map<String, Set<String>> friendMap = new HashMap<String, Set<String>>();
	
	public Set<String> getFriendListByFriend(String friend) {
		
		Set<String> friendList = null;
		if (friendMap.containsKey(friend)) {
			friendList = friendMap.get(friend);
		} else {
			friendList = new HashSet<>();
			friendMap.put(friend, friendList);
		}
		
		return friendList;
		
	}
	
	public boolean addAsFriend(String sourceFriend, String targetFriend) {
		
		Set<String> friendList = getFriendListByFriend(sourceFriend);
		return friendList.add(targetFriend);
		
	}
}
