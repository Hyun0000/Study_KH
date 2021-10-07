package member.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import member.model.service.MemberService;
import member.model.vo.Member;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login.lo") // ajax
// 해당 servlet이 ajax를 통해 들어온 페이지라는 것을 표시하는 것이다.
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	
	// 교재에는 이거 자체가 없다.
	//	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	//			throws ServletException, IOException {
	//	}
	// doGet은 아예 이용할 수 없다.
	// doGet자체가 없으면 HTTP에서 405번 오류가 화면에 출력된다. 근데 doGet이 있기만 하고 아무 내용도 없으면 빈 화면만 출력된다.
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
//==========================simple-json============================================================
//==========================simple-json============================================================
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		System.out.println("(\"/login.lo\") 진입");
//		
//		// 사용자의 정보를 JSON 형식으로 전달하기 위해 ContentType 변경
//		response.setContentType("application/json;charset=UTF-8");
//		
//		MemberService mservice = new MemberService();
//		String id = request.getParameter("id");
//		// [ id : $("#userid").val() ]로 작성했으니 request.getParameter("id");라 적는다.
//		// ajax의 data(key)에 실은 value인 object(객체)에서 (id & passwd) key를 이용했으니까
//		// 꺼낼때도 object(객체)의 key를 이용해 key에 해당하는 value를 꺼낸다.
//		// 기존에는 HTML <input> 태그의 name 속성을 위와 같이 이용했다.
//		String passwd = request.getParameter("passwd");
//		// [ passwd : $("#userpwd").val() ]로 작성했으니 request.getParameter("passwd");라 적는다.
//		// ajax의 data(key)에 실은 value인 object(객체)에서 (id & passwd) key를 이용했으니까
//		// 꺼낼때도 object(객체)의 key를 이용해 key에 해당하는 value를 꺼낸다.
//		// 기존에는 HTML <input> 태그의 name 속성을 위와 같이 이용했다.
//		PrintWriter out = response.getWriter();
//		System.out.println("id : " + id);
//		System.out.println("passwd : " + passwd);
//		
//		// 현재 이 page(servlet)는 ajax를 통해서 들어온 page이다.
//		// 따라서 아래의 코드를 쳐도 소용이 없다. 어차피 작동을 하지 않는다.
//		// request.getRequestDispatcher("다른페이지.jsp").forward(request, response);
//		// 반드시 ajax가 코드가 있는 page로 돌아가게 돼있다.
//		
//		// ﻿org.json.simple-0.4 라이브러리 이용
//		JSONObject job = new JSONObject(); // job{object} 형태가 만들어졌다.
//
//		// 입력받은 사용자의 ID와 pw를 인자로 하여 Service의 loginMember() 호출
//		Member m = mservice.loginMember(id, passwd); // db 갔다오는 코드
//		
//		if (m != null) { // 로그인 성공
//			System.out.println("로그인 성공");
//			
//			HttpSession session = request.getSession();
//			session.setAttribute("member", m);
//			
//			// DB로부터 전달받은 data를 JSON 포맷에 맞추는 작업을 진행한다.(여기서는 java 문법을 따라야한다.)
//			job.put("result", "ok");
//			job.put("name", m.getName());
//			// put() method --> job{object}에 key & value 한 쌍을 넣어라
//			// job.put("result", "ok");라고 넣으면 {"result" : "ok"} 형태로 만들어준다는 의미
//			// 또 다시 job.put("name", m.getName());라고 넣으면 아래의 형태가 된다.
//			// { 
//			// "result" : "ok",
//			// "name" : m.getName()
//			//	}
//			// 위의 모양은 JSON object 모양이다. 이 모양을 그대로 전달하면 안 되고 String 모양으로 바꿔서 전달해야한다. --> out.println(job.toJSONString());
//			// 쉽게 말해서 JSON object인 { "result" : "ok", "name" : m.getName() }의 모양을 toJSONString() 모양으로 바꾼다는 의미 
//			
//			// 위와 같이 간단한거 말고 다른 형태의 값도 넣을 수 있다.
//			job.put("memberInfo", m);
//			// VO인 Member의 instance인 m 자체를  put()을 통해 실겠다는 의미이다.
//			
//			// 위에서는 instance를 하나만 실어보냈지만 List<vo> 등을 이용해
//			// 여러개의 instance를 한꺼번에 보낼 수도 있다.
//			// 단, 해당 작업은 simple-json(﻿org.json.simple-0.4﻿)으로는 불가능하다.
//			
//			
//		} else { // 로그인 실패
//			System.out.println("로그인 실패");
//			job.put("result", "fail");
//		}
//		System.out.println("job : " + job);
//		System.out.println("job.toJSONString() : " + job.toJSONString());
//		 out.println(job.toJSONString());
//		// 화면에다가 job을 JSONString() 모양으로 바꿔서 뿌리겠다는 의미
//		
//		// ajax는 servlet에서 페이지 이동을 하지 않고 다시 자신을 호출한 ajax 코드에서 (success 혹은 error)가 발생할 때까지 기다리고 있다.
//		// 언제까지 기다리는가? --> 화면에 뿌려지는게 끝날 때까지 (success, error가 발생할 때까지) 기다린다.
//		// 그 기다림이 끝나는 시점이 바로 out.flush();가 발생하는 시점이다.
//		
//		// 이때 (success 혹은 error)는 데이터를 request.setAttribute(name, o); 와 같은 방식으로 받는 것이아니라
//		// out.println(job.toJSONString());를 통해 '화면에 뿌려진 데이터'를 ajax가 직접 수집해
//		// (success : function(data) or error : function(request,status,error))에 해당 데이터를 넣어준다.
//		
//		// 의문점
//		// MVC 모델에서는 controller가 화면에 뿌리는 역할을 하지 않는다.
//		// 단, ajax에서는 화면에 뿌려진게 실제로는  ajax 코드의 
//		// (success : function(data) or error : function(request,status,error))에 실려서 이벤트를 발생시킨다.
//		
//		out.flush();
//		// flush()가 실행되면 (success, error)를 발생시키면서 out.println(job.toJSONString());을 통해
//		// 화면에 뿌려진 부분을 (success : function(data) or error : function(request,status,error))에 채워준다.
//		
//		// 정리
//		// 1. 갔다가 반드시 나에게로 다시 돌아온다.
//		// 2. 돌아오는 것을 success든 error든 화면에 뿌려지는게 끝날때까지 기다린다. by flush();
//		// 3. 이때 화면에 뿌려진것이 (success : function(data) / error : function(request,status,error))의
//		// data로 실려 들어간다.(매개인자로 들어간다는 의미)
//
//		// ex)
//		// out.print("aaa");
//		// 이것을 통해 데이터를 실어서 보내준다.
//		// 위와 같이 화면에 "aaa"라고 뿌리면 그 값("aaa")이 ajax의 success(key)에 해당하는 function(data)에 실려서 이벤트가 발생한다.
//		out.close();
//	}
	
//==========================Gson============================================================
//==========================Gson============================================================
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		System.out.println("(\"/login.lo\") 진입");
//		
//		// 사용자의 정보를 JSON 형식으로 전달하기 위해 ContentType 변경
//		response.setContentType("application/json;charset=UTF-8");
//		
//		MemberService mservice = new MemberService();
//		String id = request.getParameter("id");
//		String passwd = request.getParameter("passwd");
//		PrintWriter out = response.getWriter();
//		System.out.println("id : " + id);
//		System.out.println("passwd : " + passwd);
//		
//		// cf)
//		// Gson gob = new GsonBuilder().setPrettyPrinting().create(); --> console에 이쁘게 찍어달라는 의미
//		
//		Gson gob = new GsonBuilder().create(); // 다형성 적용
//		
//		String gobStr = "";
//		List<Member> voList = new ArrayList<Member>();
//		Member m = mservice.loginMember(id, passwd); // db 갔다오는 코드
//		
//		if (m != null) { // 로그인 성공
//			System.out.println("로그인 성공");
//			
//			HttpSession session = request.getSession();
//			session.setAttribute("member", m);
//
//			voList.add(m);
//			voList.add(m);
//			gobStr = gob.toJson(voList);
//			// voList를 JSON 모양으로 바꾸라는 의미
//			// 이 method만 있으면 simple-json에서의 toJSONString() 같은 작업을 하지 않아도 된다.(이걸로 끝)
//			
//		} else { // 로그인 실패
//			System.out.println("로그인 실패");
//		}
//		System.out.println("gobStr : " + gobStr);
//		out.print(gobStr); // 화면에 뿌리는 건 똑같이 해야한다.
//		out.flush();
//		out.close();
//	}
//==============================정리============================================================	
//==============================정리============================================================	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("(\"/login.lo\") 진입");
		
		// 사용자의 정보를 JSON 형식으로 전달하기 위해 ContentType 변경
		response.setContentType("application/json;charset=UTF-8");
		
		MemberService mservice = new MemberService();
		String id = request.getParameter("id");
		String passwd = request.getParameter("passwd");
		PrintWriter out = response.getWriter();
		System.out.println("id : " + id);
		System.out.println("passwd : " + passwd);
		
		// Gson gob = new Gson(); --> 이렇게도 많이 만든다.
		Gson gob = new GsonBuilder().setPrettyPrinting().create();
		// setPrettyPrinting() --> console에 값이 이쁘게 찍히게 한다.
		
		// 아래와 같이 생성할 수도 있다.
		// Gson gob = new GsonBuilder().create();
		// GsonBuilder() --> 생성자 method, 즉, Gson 라이브러리안에 GsonBuilder class가 있다는 얘기이다.
		
		String gobStr = ""; // 화면에 뿌릴 값
//====================================방법 0 - 이건 애초에 로그인이 안 된다.=====================================================================
//====================================방법 0 - 이건 애초에 로그인이 안 된다.=====================================================================
//		List<Member> voList = new ArrayList<Member>();
//		Member m = mservice.loginMember(id, passwd); // db 갔다오는 코드
//		
//		if (m != null) { // 로그인 성공
//			System.out.println("로그인 성공");
//			
//			HttpSession session = request.getSession();
//			session.setAttribute("member", m);
//
//			voList.add(m);
//			voList.add(m);
//			gobStr = gob.toJson(voList);
//		} else { // 로그인 실패
//			System.out.println("로그인 실패");
//		}
//		System.out.println("gobStr : " + gobStr);
//		out.print(gobStr); // 화면에 뿌리는 건 똑같이 해야한다.
//		out.flush();
//		out.close();
		
		// 결과
//		("/login.lo") 진입
//		id : user33
//		passwd : pass33
//		로그인 성공
//		gobStr : [
//		          {"id":"user33","passwd":"pass33","name":"사용자3","email":"user22@test.or.kr"},
//		          {"id":"user33","passwd":"pass33","name":"사용자3","email":"user22@test.or.kr"}
//		          ]
//	}
//====================================방법 1 - map만 이용========================================================================
//====================================방법 1 - map만 이용========================================================================
		Map<String, Object> map1 = new HashMap<String, Object>();
		Member m = mservice.loginMember(id, passwd); // db 갔다오는 코드
		
		if (m != null) { // 로그인 성공
			System.out.println("로그인 성공");
			
			HttpSession session = request.getSession();
			session.setAttribute("member", m);
			
			map1.put("result", "ok");
			map1.put("name", m.getName());
			map1.put("memberInfo", m);
			gobStr = gob.toJson(map1);
			// 우선 Map에다가 data를 key & value 형태로 넣는다.(당연히 java 문법을 따른다.)
			// 그리고 그 Map 자체를 json 형태로 포맷한다.
			// "일단 java 코드로 니가 만들고 싶은 모양을 만들어놔, 그러면 내가 그걸 json 모양으로 맞춰줄게"

		} else { // 로그인 실패
			System.out.println("로그인 실패");
			map1.put("result", "fail");
			gobStr = gob.toJson(map1);
		}
		System.out.println("gobStr : " + gobStr);
		out.print(gobStr); // 화면에 뿌리는 건 똑같이 해야한다.
		out.flush();
		out.close();
		
		// 결과
//		("/login.lo") 진입
//		id : user33
//		passwd : pass33
//		로그인 성공
//		gobStr : {
//		  "result": "ok",
//		  "memberInfo": {
//		    "id": "user33",
//		    "passwd": "pass33",
//		    "name": "사용자3",
//		    "email": "user22@test.or.kr"
//		  },
//		  "name": "사용자3"
//		}
//====================================방법 2 - map과 list 이용========================================================================
//====================================방법 2 - map과 list 이용========================================================================
//		List<Member> voList = new ArrayList<Member>();
//		Member m = mservice.loginMember(id, passwd); // db 갔다오는 코드
//		Map<String, Object> map2 = new HashMap<String, Object>();
//		
//		if (m != null) { // 로그인 성공
//			System.out.println("로그인 성공");
//			
//			HttpSession session = request.getSession();
//			session.setAttribute("member", m);
//			
//			voList.add(m);
//			voList.add(m);
//			map2.put("result", "ok");
//			map2.put("name", m.getName());
//			map2.put("memberInfo", voList);
//			gobStr = gob.toJson(map2);
//		} else { // 로그인 실패
//			System.out.println("로그인 실패");
//		}
//		System.out.println("gobStr : " + gobStr);
//		out.print(gobStr); // 화면에 뿌리는 건 똑같이 해야한다.
//		out.flush();
//		out.close();
		
		// 결과값
//		("/login.lo") 진입
//		id : user33
//		passwd : pass33
//		로그인 성공
//		gobStr : {
//		  "result": "ok",
//		  "memberInfo": [
//		    {
//		      "id": "user33",
//		      "passwd": "pass33",
//		      "name": "사용자3",
//		      "email": "user22@test.or.kr"
//		    },
//		    {
//		      "id": "user33",
//		      "passwd": "pass33",
//		      "name": "사용자3",
//		      "email": "user22@test.or.kr"
//		    }
//		  ],
//		  "name": "사용자3"
//		}
//====================================방법 3 - List안에 Map을 넣는다. - 로그인 가능하지만 복잡========================================================================
//====================================방법 3 - List안에 Map을 넣는다. - 로그인 가능하지만 복잡========================================================================
//		List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
//		Map<String, Object> map3 = new HashMap<String, Object>();
//		Member m = mservice.loginMember(id, passwd); // db 갔다오는 코드
//		
//		if (m != null) { // 로그인 성공
//			System.out.println("로그인 성공");
//			
//			HttpSession session = request.getSession();
//			session.setAttribute("member", m);
//			
//			map3.put("result", "ok");
//			map3.put("name", m.getName());
//			map3.put("memberInfo", m);
//			
//			// 결과값을 보기 위해 일부러 2번 담는다.
//			listMap.add(map3);
//			listMap.add(map3);
//			gobStr = gob.toJson(listMap);
//		} else { // 로그인 실패
//			System.out.println("로그인 실패");
//		}
//		System.out.println("gobStr : " + gobStr);
//		out.print(gobStr); // 화면에 뿌리는 건 똑같이 해야한다.
//		out.flush();
//		out.close();
		
		// 결과값
//		("/login.lo") 진입
//		id : user33
//		passwd : pass33
//		로그인 성공
//		gobStr : [  배열 형태로 map이 담겼다.
//		  {
//		    "result": "ok",
//		    "memberInfo": {
//		      "id": "user33",
//		      "passwd": "pass33",
//		      "name": "사용자3",
//		      "email": "user22@test.or.kr"
//		    },
//		    "name": "사용자3"
//		  },
//		  {
//		    "result": "ok",
//		    "memberInfo": {
//		      "id": "user33",
//		      "passwd": "pass33",
//		      "name": "사용자3",
//		      "email": "user22@test.or.kr"
//		    },
//		    "name": "사용자3"
//		  }
//		]
//====================================방법 4 - 애초에 로그인 불가(방법 0과 동일)========================================================================
//====================================방법 4 - 애초에 로그인 불가(방법 0과 동일)========================================================================
//		List<Member> volist = new ArrayList<Member>();
//		Member m = mservice.loginMember(id, passwd); // db 갔다오는 코드
//		
//		if (m != null) { // 로그인 성공
//			System.out.println("로그인 성공");
//			
//			HttpSession session = request.getSession();
//			session.setAttribute("member", m);
//			
//			volist.add(m);
//			
//			gobStr = gob.toJson(volist);
//		} else { // 로그인 실패
//			System.out.println("로그인 실패");
//		}
//		System.out.println("gobStr : " + gobStr);
//		out.print(gobStr); // 화면에 뿌리는 건 똑같이 해야한다.
//		out.flush();
//		out.close();
		
		// 결과값
//		("/login.lo") 진입
//		id : user33
//		passwd : pass33
//		로그인 성공
//		gobStr : [
//		  {
//		    "id": "user33",
//		    "passwd": "pass33",
//		    "name": "사용자3",
//		    "email": "user22@test.or.kr"
//		  }
//		]
//====================================방법 5 - 애초에 로그인 불가========================================================================
//====================================방법 5 - 애초에 로그인 불가========================================================================
//		Member m = mservice.loginMember(id, passwd); // db 갔다오는 코드
//		
//		if (m != null) { // 로그인 성공
//			System.out.println("로그인 성공");
//			
//			HttpSession session = request.getSession();
//			session.setAttribute("member", m);
//			
//			gobStr = gob.toJson(m);
//		} else { // 로그인 실패
//			System.out.println("로그인 실패");
//		}
//		System.out.println("gobStr : " + gobStr);
//		out.print(gobStr); // 화면에 뿌리는 건 똑같이 해야한다.
//		out.flush();
//		out.close();
	}
}