package test.dhw;

import java.util.ArrayList;
import java.util.List;

class Email{
	private String addr;

	public String getAddr() {
		return addr;
	}

	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	
}

public class TestListIsEmpty {

	public static void main(String args[]) {
		List<Email> testList = null;

		testList = new ArrayList<>();
		
		if(testList.isEmpty())
			System.out.println("2 test list is empty");
		
		Email email = new Email();
		email.setAddr("Test");
		
		Email email2 = null;
		
		testList.add(email2);
		
		if(testList.isEmpty())
			System.out.println("3 test list is empty");
		
		System.out.println("Done");
	}
}
