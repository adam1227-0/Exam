package scoremanager.main;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import tool.Action;

public class StudentUpdateAction extends Action {

	@Override

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// ローカル変数の宣言

		HttpSession session = req.getSession(); // セッション情報を取得

		Teacher teacher = (Teacher)session.getAttribute("user");
		String no="";
		String entYearSet="";



		//DBへデータ保存 5

		//なし

		//レスポンス値をセット 6

		req.setAttribute("no", no);	// リクエストにデータをセット

		req.setAttribute("ent_year_set", entYearSet);//↓↓↓  同じく  ↓↓↓

		//JSPへフォワード 7

		req.getRequestDispatcher("student_update.jsp").forward(req, res); // 学生一覧まで画面遷移

	}

}
