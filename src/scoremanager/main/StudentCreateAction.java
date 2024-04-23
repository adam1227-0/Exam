package scoremanager.main;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Teacher;
import dao.ClassNumDao;
import tool.Action;

public class StudentCreateAction extends Action {

	@Override

	public void execute(HttpServletRequest req, HttpServletResponse res) throws Exception {

		// ローカル変数の宣言

		HttpSession session = req.getSession(); // セッション情報を取得

		Teacher teacher = (Teacher)session.getAttribute("user");

		LocalDate todaysDate = LocalDate.now();	// LocalDateインスタンスを取得

		int year = todaysDate.getYear();	// 現在の年を取得

		ClassNumDao cNumDao = new ClassNumDao();	// クラス番号Daoをインスタンス化


		//リクエストパラメータ―の取得 2

		// 無し

		//DBからデータ取得 3

		// ログインユーザーの学校コードをもとにクラス番号の一覧を取得

		List<String> list = cNumDao.filter(teacher.getSchool());

		//ビジネスロジック 4

		List<Integer> entYearSet = new ArrayList<>();	// リストを初期化

		for (int i = year - 10; i < year + 1; i++) {	// 10年前から1年後まで年をリストに追加

			entYearSet.add(i);

		}

		//DBへデータ保存 5

		//なし

		//レスポンス値をセット 6

		req.setAttribute("class_num_set", list);	// リクエストにデータをセット

		req.setAttribute("ent_year_set", entYearSet);//↓↓↓  同じく  ↓↓↓

		//JSPへフォワード 7

		req.getRequestDispatcher("student_create.jsp").forward(req, res); // 学生一覧まで画面遷移

	}

}
