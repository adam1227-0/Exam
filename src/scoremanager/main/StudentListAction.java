package scoremanager.main;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.ClassNumDao;
import dao.StudentDao;
import tool.Action;
public class StudentListAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//TODO 自動生成されたメソッド·スタブ
		HttpSession session=request.getSession();//セッション
		Teacher teacher=(Teacher)session.getAttribute("user");

		String entYearStr=request.getParameter("f1");//入力された入学年度
		String classNum=request.getParameter("f2");//入力されたクラス番号
		boolean isAttend = "t".equals(request.getParameter("f3"));	// チェックボックスの値が "t" の場合に true を設定する
		int entYear=0;//入学年度

		List<Student>students=null;//学生リスト
		LocalDate todaysDate=LocalDate.now();//LocolDateインスタンスを取得
		int year=todaysDate.getYear();//現在の年を取得
		StudentDao sDao=new StudentDao();//学生dao
		ClassNumDao cNumDao=new ClassNumDao();//クラス番号Daoを初期化
		Map<String, String>errors=new HashMap<>();//エラーメッセージ



		List<String>list=cNumDao.filter(teacher.getSchool());
		if (entYearStr!=null){
			//数値に変換
			entYear=Integer.parseInt(entYearStr);
		}

		if (entYear!=0 && !classNum.equals("0")){
			System.out.println("entYear: " + entYear);
			System.out.println("classNum: " + classNum);
			System.out.println("isAttend: " + isAttend);
			//入学年度とクラス番号を指定
			students=sDao.filter(teacher.getSchool(),entYear,classNum,isAttend);

		}else if (entYear !=0 && classNum.equals("0")){
			//入学年度のみ指定
			students=sDao.filter(teacher.getSchool(),entYear,isAttend);

		}else if(entYear==0 && classNum==null || entYear==0 && classNum.equals("0")){
			//指定なしの場合
			//全学生情報を取得
			students=sDao.filter(teacher.getSchool(),isAttend);

		}else{
			errors.put("f1","クラスを指定する場合は入学年度も指定してください");
			request.setAttribute("errors", errors);

			//全学生情報取得
			students=sDao.filter(teacher.getSchool(),isAttend);

		}


		//リス地初期化
		List<Integer>entYearSet=new ArrayList<>();
		//１０年前から１年後までのリストを追加
		for (int i=year-10;i<year+1;i++){
			entYearSet.add(i);
		}
		//レスポンス値をセット　6
		//リクエストに入学年度をセット
		request.setAttribute("f1", entYear);
		//リクエストにクラス番号をセット
		request.setAttribute("f2",classNum);
		//在学フラグが送信されていた場合
//		if (isAttendStr!=null){
//			//在学フラグを立てる
//			isAttend=true;
//			//リクエストに在学フラグをセット
//			request.setAttribute("f3",isAttendStr);
//		}
		//リクエストに学生リストをセット
		request.setAttribute("students",students);
		//リクエストにデータをセット
		request.setAttribute("class_num_set",list);
		request.setAttribute("ent_year_set",entYearSet);
		System.out.println("カモン");
		//JSPにフォワード 7
		request.getRequestDispatcher("student_list.jsp").forward(request,response);

	}

}