package day_0304;

/**
 * Singleton pattern이 도입된 클래스 
 */
public class Singleton {
	//1.클래스외부에서 객체 생성을 막기위해 생성자를 private로 설정한다
	private static Singleton single;
	private Singleton() {
		
	}
	
	public static Singleton getInstance() {
		//객체를 하나로 유지하면서 생성하는 코드 작성.
		if(single == null) {
			single = new Singleton();
		}
		return single;
	}
}
