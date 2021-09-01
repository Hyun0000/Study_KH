package com.kh.practice.set.controller;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeSet;

import com.kh.practice.set.model.vo.Lottery;

public class LotteryController {
	// HashSet --> 순서 X, 중복 X, 인덱스 X
	
	// 추첨 대상을 담을 HashSet 객체
	private HashSet<Lottery> lottery = new HashSet<Lottery>();
	
	// 당첨 대상을 담을 HashSet 객체
	private HashSet<Lottery> win = new HashSet<Lottery>();
//============================================================
	public boolean insertObject(Lottery l) {
		if (!lottery.contains(l)) {
			lottery.add(l);
			return true;
		} else {
			return false;
		}
	}
//============================================================
//	public boolean deleteObject(Lottery l) {
//		 다시 해야됨, win이 어떠한 성질인지 알아야한다.
//		
//		if (lottery.contains(l)) {
//			lottery.remove(l);
//			return true;
//		} else {
//			return false;
//		}
//	}
//============================================================
	public HashSet winObject() {
		// 모든 당첨자를 출력하는 메소드
		ArrayList<Lottery> arrayList = new ArrayList<Lottery>(lottery);
		
		for (int i = 0; i < 4; i++) {
			win.add(arrayList.get(i));
		}
		
		return win;
	}
//============================================================
//	public TreeSet<E> sortedWinObject() {
//		
//	}
//============================================================
//	public boolean searchWinner(Lottery l) {
//		
//	}
}