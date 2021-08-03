package com.kh.practice.list.library.controller;

import java.util.ArrayList;
import java.util.List;

import com.kh.practice.list.library.model.vo.Book;

public class BookController {
	private List<Book> bookList = new ArrayList<Book>();
	
	public void BookController() {
		bookList.add(new Book("자바의 정석", "남궁성", "기타", 20000));
		bookList.add(new Book("쉽게 배우는 알고리즘", "문병로", "기타", 15000));
		bookList.add(new Book("대화의 기술", "강보람", "인문", 17500));
		bookList.add(new Book("암 정복기", "박신우", "의료", 21000));
	}
	
	public void insertBook(Book bk) {
		bookList.add(bk);
	}
	
	public ArrayList<Book> selectList() {
		}
	}
	
	public ArrayList<Book> searchBook (String keyword) {
		
	}
	
	public Book deleteBook (String title, String author) {
		
	}
	
	public int ascBook() {
		
	}
}
