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
// 현재 servlet이 ajax를 통해 들어온 페이지라는 것을 표시하는 것이다.
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
	
//	protected void doPost(HttpServletRequest request, HttpServletResponse response)
//			throws ServletException, IOException {
//		System.out.println("(\"/login.lo\") 진입");
//		
//		response.setContentType("application/json;charset=UTF-8");
//		MemberService mservice = new MemberService();
//		String id = request.getParameter("id");
//		String passwd = request.getParameter("passwd");
//		
//		PrintWriter out = response.getWriter();
//		System.out.println("id : " + id);
//		System.out.println("passwd : " + passwd);
//		
//		// 다형성을 이용
//		// 이거 어떻게 만든거라고?
//		Gson gob = new GsonBuilder().create();
//		
//		// 콘솔에 이쁘게 찍어달라는 의미
//		// Gson gob2 = new GsonBuilder().setPrettyPrinting().create();
//		
//		String gobStr = "";
//		List<Member> voList = new ArrayList<Member>();
//		Member m = mservice.loginMember(id, passwd);
//		
//		if (m != null) { // 로그인 성공
//			System.out.println("로그인 성공");
//			
//			HttpSession session = request.getSession();
//			session.setAttribute("member", m);
//			voList.add(m);
//			voList.add(m);
////			gobStr  = gob.toJson(voList);
////			gobStr = gob.toJson(m);
//			// m을 제이슨 모양으로 바꿔줘
//			// 이게 있으면 JsonString 같은 거 안해도 된다. 이걸로 끝
//			
//			// 궁극적으로 원하는 것
//			/*{
//			 *		 
//			 * "result" : "ok",
//			 * "name" : "사용자1",
//			 * "memberInfo" :
//			 * [ 
//			 * 	{
//			 * 		"id":"user33",
//			 * 		"passwd":"pass33",
//			 * 		"name":"사용자3",
//			 * 		"email":"user22@test.or.kr"
//			 * },
//			 * 
//			 * { 이건 그냥 곁다리로 붙은것
//			 * 		"id":"user33",
//			 * 		"passwd":"pass33",
//			 * 		"name":"사용자3",
//			 * 		"email":"user22@test.or.kr"} 
//			 * ]
//			 * }
//			 */
//			// (com.google.gson.JsonObject)
//			// Map 형태로 만들고 거기에 담는다(자바 문법)
//			// 그리고 그 Map을 json에 넣어준다.
//			// 니가 만들고 싶은 모양으로 만들어놔, 그리고 그걸 넣으면 내가 json 모양으로 맞춰줄게
//			JsonObject gjob = new JsonObject();
////			Map<String, Object>
//			gjob.addProperty("result", "ok");
//			gjob.addProperty("name", m.getName());
//			
//			// 이 메소드로는 자바 클래스 객체 모양을 넣어 줄 수 없다.
////			 gjob.addProperty("memberInfo", m);
//			
//			// 블로그
//			List<Map<String, Object>> listMap = new ArrayList<Map<String,Object>>();
//			Map<String, Object> map3 = new HashMap<String, Object>();
////			map3.put("result, "")
//			// ....
//			
//			
//		} else { // 로그인 실패
//			System.out.println("로그인 실패");
//		}
//		System.out.println("gobStr : " + gobStr);
//		out.print(gobStr);
//		out.flush();
//		out.close();
//	}
// ==================================================================================================================================
// ==================================================================================================================================
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("(\"/login.lo\") 진입");
		
		// 사용자의 정보를 JSON 형식으로 전달하기 위해 ContentType 변경
		response.setContentType("application/json;charset=UTF-8");
		
		MemberService mservice = new MemberService();
		String id = request.getParameter("id");
		// [ id : $("#userid").val() ]로 작성했으니 request.getParameter("id");라 적는다.
		// ajax의 data(key)에 실은 value인 object(객체)에서 (id & passwd) key를 이용했으니까
		// 꺼낼때도 object(객체)의 key를 이용해 key에 해당하는 value를 꺼낸다.
		// 기존에는 HTML <input> 태그의 name 속성을 위와 같이 이용했다.
		String passwd = request.getParameter("passwd");
		// [ passwd : $("#userpwd").val() ]로 작성했으니 request.getParameter("passwd");라 적는다.
		// ajax의 data(key)에 실은 value인 object(객체)에서 (id & passwd) key를 이용했으니까
		// 꺼낼때도 object(객체)의 key를 이용해 key에 해당하는 value를 꺼낸다.
		// 기존에는 HTML <input> 태그의 name 속성을 위와 같이 이용했다.
		PrintWriter out = response.getWriter();
		System.out.println("id : " + id);
		System.out.println("passwd : " + passwd);
		
		// 현재 이 page(servlet)는 ajax를 통해서 들어온 page이다.
		// 따라서 아래의 코드를 쳐도 소용이 없다. 어차피 작동을 하지 않는다.
		// request.getRequestDispatcher("aaa.jsp").forward(request, response);
		// ajax가 코드가 있는 페이지로 반드시 돌아가게 돼있다.
		
		JSONObject job = new JSONObject();
		
		// 입력받은 사용자의 ID와 비밀번호를 인자로 하여 Service의 loginMember() 호출
		// db 갔다오는 코드
		Member m = mservice.loginMember(id, passwd);
		
		if (m != null) { // 로그인 성공
			System.out.println("로그인 성공");
			
			HttpSession session = request.getSession();
			session.setAttribute("member", m);
			
			// json 포맷에 맞추는 작업
			// 여기서는 자바 문법을 따라야한다.
			// job.put("result", "ok");
			// job.put("name", m.getName());
			// 이렇게 간단한거 말고 다른 것도 넣을 수 있다.
			// job.put("memberInfo", m);
			// m에서는 m.getEmail() / m.getName()도 가능
			// 즉, vo 자체를 put()에 실는다는 의미이다.
			// 지금은 vo하나만 보내지만
			// vo 모양 여러개를 보낼 수도 있따. List<vo>를 실어서 보낸다는 의미
			
			// simple-json 바로 변환 불가 --> job.put("memberInfo", m);
			// {"result":"ok","memberInfo":Member [id=user33, passwd=pass33, name=사용자3, email=user22@test.or.kr],"name":"사용자3"}
			
			// 이런 모양이 돼야한다.
			// {"result":"ok","memberInfo": {id="user33", passwd="pass33", name="사용자3", email="user22@test.or.kr"},"name":"사용자3"}
			// d.memberInfo.name으로 값을 꺼내야한다. --> 사용자3이 값으로 나온다.
			// 근데 simple-json에서는 위와 같은 모양으로 변환이 불가능하다.
			// [] 형태로 넣을 수는 있긴한대 문제는 []는 키:밸류 형태로 값을 넣을 수가 없다.
			// job.put("id", m.getId());
			// job.put("id", m.getPasswd());
			// 따라서 이렇게 한땀한땀 넣을 수 밖에 없다.
			// 아니면 map 형태로 put에 넣어줄수는 있다.
			// 근데 그러면 애초에 list를 map 모양으로 바꿔줘야한다.
			// 한 마디로 ㅈ 같다. 지손을 사용한다.
			
			
		} else { // 로그인 실패
			System.out.println("로그인 실패");
			// job.put("result", "fail");
		}
		System.out.println("job : " + job);
		System.out.println("job.toJSONString() : " + job.toJSONString());
		// out.println(job.toJSONString());
		// 화면에다가 job을 JSONString() 모양으로 바꿔서 뿌리겠다는 의미
		
		// ajax는 servlet에서 페이지 이동을 하지 않고 다시 자신을 호출한 ajax 코드에서 (success 혹은 error)가 발생할 때까지 기다리고 있다.
		// 언제까지 기다리는가? --> 화면에 뿌려지는게 끝날 때까지 (success, error가 발생할 때까지) 기다린다.
		// 그 기다림이 끝나는 시점이 바로 out.flush();가 발생하는 시점이다.
		
		// 이때 (success 혹은 error)는 데이터를 request.setAttribute(name, o); 와 같은 방식으로 받는 것이아니라
		// out.println(job.toJSONString());를 통해 '화면에 뿌려진 데이터'를 ajax가 직접 수집해
		// (success : function(data) or error : function(request,status,error))에 해당 데이터를 넣어준다.
		
		// 의문점
		// MVC 모델에서는 controller가 화면에 뿌리는 역할을 하지 않는다.
		// 단, ajax에서는 화면에 뿌려진게 실제로는  ajax 코드의 
		// (success : function(data) or error : function(request,status,error))에 실려서 이벤트를 발생시킨다.
		
		out.flush();
		// flush()가 실행되면 (success, error)를 발생시키면서 out.println(job.toJSONString());을 통해
		// 화면에 뿌려진 부분을 (success : function(data) or error : function(request,status,error))에 채워준다.
		
		// 정리
		// 1. 갔다가 반드시 나에게로 다시 돌아온다.
		// 2. 돌아오는 것을 success든 error든 화면에 뿌려지는게 끝날때까지 기다린다. by flush();
		// 3. 이때 화면에 뿌려진것이 (success : function(data) / error : function(request,status,error))의
		// data로 실려 들어간다.(매개인자로 들어간다는 의미)

		// ex)
//		out.print("aaa");
		// 이것을 통해 데이터를 실어서 보내준다.
		// 위와 같이 화면에 "aaa"라고 뿌리면 그 값("aaa")이 ajax의 success(key)에 해당하는 function(data)에 실려서 이벤트가 발생한다.
		out.close();
	}
}