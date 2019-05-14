package kr.co.bookking.util;

public class EncapsuleRoleTest {
	public static void main(String[] args) {
		Role role = Role.getInstance();
		System.out.println(role.adminList());
		
		String[] list = {"user"};
		// role이 가지고있는 객체의 주소 참조
		list = role.getRoles();
		
		System.out.println("list = " + list);
		
		// role의  private member 변수가 변경됨
		list[0] = "member";
		
		for (String str : list) {
			System.out.println(str);
		}
		
		for (String str : role.getRoles()) {
			System.out.println(str);
		}
		
	}
}
