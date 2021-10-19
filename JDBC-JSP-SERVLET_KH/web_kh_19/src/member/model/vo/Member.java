package member.model.vo;

import java.io.Serializable;

public class Member implements Serializable {
	// Serializable --> 둘 중 아무것이나 써도 무방하다.
	
	// 제네레이트
	// private static final long serialVersionUID = 5480554413198861990L;
	
	// 디플트
	private static final long serialVersionUID = 1L;

	private String id;
	private String passwd;
	private String name;
	private String email;
	
	public Member() {}

	public Member(String id, String passwd, String name, String email) {
		super();
		this.id = id;
		this.passwd = passwd;
		this.name = name;
		this.email = email;
	}

	@Override
	public String toString() {
		return "Member [id=" + id + ", passwd=" + passwd + ", name=" + name + ", email=" + email + "]";
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPasswd() {
		return passwd;
	}

	public void setPasswd(String passwd) {
		this.passwd = passwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
}