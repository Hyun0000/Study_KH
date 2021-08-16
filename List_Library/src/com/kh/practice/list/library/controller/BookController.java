package com.kh.practice.list.library.controller;

import java.util.ArrayList;
import java.util.List;

import com.kh.practice.list.library.model.vo.Book;

public class BookController {
	private List<Book> list = new ArrayList<Book>();
	
	public BookController() {
		list.add(new Book("자바의 정석", "남궁성", "기타", 20000));
		list.add(new Book("쉽게 배우는 알고리즘", "문병로", "기타", 15000));
		list.add(new Book("대화의 기술", "강보람", "인문", 17500));
		list.add(new Book("암 정복기", "박신우", "의료", 21000));
	}
	
	public void insertBook(Book bk) {
		list.add(bk);
	}
	
	public List<Book> selectList() {
		// 해당 bookList의 주소 값 반환
		return list;
	}
//	
	public List<Book> searchBook(String keyword) {
		List<Book> searchBook = new ArrayList<Book>();
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTitle().contains(keyword)) {
				searchBook.add(list.get(i));
			}
		}
		return searchBook;
	}
//	
	public Book deleteBook (String title, String author) {
		Book removeBook = null;
		
		for (int i = 0; i < list.size(); i++) {
			if (list.get(i).getTitle().contains(title) && list.get(i).getAuthor().contains(author)) {
				removeBook = list.get(i);
				list.remove(i);
			}
		}
		return removeBook;
	}
			
	public int ascBook() {
		// 책 이름으로 오름차순 후 1 반환
		
		return 0;
	}
}
