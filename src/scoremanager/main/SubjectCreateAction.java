package scoremanager.main;

import java.time.LocalDate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class SubjectCreateAction extends Action{
	
	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception{
		// ローカル変数の宣言

				HttpSession session = req.getSession(); // セッション情報を取得

				Teacher teacher = (Teacher)session.getAttribute("user");

				LocalDate todaysDate = LocalDate.now();	// LocalDateインスタンスを取得

				int year = todaysDate.getYear();	// 現在の年を取得

				ClassNumDao cNumDao = new ClassNumDao();	// クラス番号Daoをインスタンス化

	}
}
