package springboard.service;

import org.springframework.ui.Model;

public interface IBoardService
{
	//추상메서드 : 하위 클래스에서는 무조건 오버라이딩 해야한다.
	void execute(Model model);
}
