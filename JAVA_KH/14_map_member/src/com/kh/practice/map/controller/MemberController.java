package com.kh.practice.map.controller;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

import com.kh.practice.map.model.vo.Member;

public class MemberController {
	private HashMap<String, Member> map = new HashMap<String, Member>();
	
	public boolean joinMembership(String id, Member m) {
		if (map.containsKey(id)) {
			return false;
		} else {
			map.put(id, m);
			return true;
		}
	}
	
	public String logIn(String id, String password) {
		// 로그인하는 메소드
		if (map.containsKey(id)) {
			if (map.get(id).getPassword().equals(password)) {
				return map.get(id).getName();
			}
		} 
		return null;
	}
	
	public boolean changePassword(String id, String oldPw, String newPw) {
		
		if (map.containsKey(id) && map.get(id).getPassword().equals(oldPw)) {
					map.get(id).setPassword(newPw);
				return true;
			} else {
				return false;
			}
	}
	
	public void changeName(String id, String newName) {
		// 이름을 	변경하는 메소드
		// 전달 받은 id를 통해 Member의 이름을 새로 입력한 이름으로 변경
		if (map.containsKey(id)) {
			map.get(id).setName(newName);
		} 
	}
	
	public TreeMap<String, String> sameName(String name) {
		// 같은 이름을 가진 사람을 뽑아내는 메소드
		Iterator<String> iterKey = map.keySet().iterator();
		
		TreeMap<String, String> sameMemberName = new TreeMap<String, String>();
		
		while (iterKey.hasNext()) {
			if(map.containsValue(name)) {
			String memberName = iterKey.next();
			sameMemberName.put(map.get(memberName).getName(), memberName);
			}
		}
		
		return sameMemberName;		
	}
}
