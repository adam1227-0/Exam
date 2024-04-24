package scoremanager.main;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import bean.Student;
import bean.Teacher;
import dao.StudentDao;
import tool.Action;
public class StudentUpdateExecuteAction extends Action {

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		//TODO 自動生成されたメソッド·スタブ
		HttpSession session=request.getSession();//セッション
		Teacher teacher=(Teacher)session.getAttribute("user");

		String entYearStr="";//入力された入学年度
		String no="";
		String name="";
		String classNum="";//入力されたクラス番号
		String isAttendStr="";//入力された在学フラグ
		int entYear=0;//入学年度
		boolean isAttend=false;//在学フラグ

		//List<Student>students=null;//学生リスト

		LocalDate todaysDate=LocalDate.now();//LocolDateインスタンスを取得
		int year=todaysDate.getYear();//現在の年を取得
		StudentDao sDao=new StudentDao();//学生dao
		Map<String, String>errors=new HashMap<>();//エラーメッセージ

		entYearStr=request.getParameter("f1");
		no=request.getParameter("f2");
		name=request.getParameter("f3");
		classNum=request.getParameter("f4");

		System.out.println(entYearStr);
		System.out.println(no);
		System.out.println(name);
		System.out.println(classNum);
		if (entYearStr.equals("0")){
			//入学年度とクラス番号を指定
			errors.put("f1", "入学年度を選択してください");
			request.setAttribute("no", no);
			request.setAttribute("name", name);
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("StudentCreate.action").forward(request, response);
		}else if (sDao.get(no)!=null){
			errors.put("f2", "学生番号が重複しています");
			request.setAttribute("no", no);
			request.setAttribute("name", name);
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("StudentCreate.action").forward(request, response);
		}else if (no==null){
			errors.put("f2", "学生番号を入力してください");
			request.setAttribute("no", no);
			request.setAttribute("name", name);
			request.setAttribute("errors", errors);
			request.getRequestDispatcher("StudentCreate.action").forward(request, response);
		}else{
			    isAttend=true;
			    entYear=Integer.parseInt(entYearStr);
			    Student student = new Student();
		        student.setNo(no);
		        student.setName(name);
		        student.setEntYear(entYear);//ここ？
		        student.setClassNum(classNum);
		        student.setAttend(isAttend);
		        student.setSchool(teacher.getSchool()); // 学校情報をセット

		        // StudentDaoを使って学生情報をデータベースに保存
		        sDao.save(student);
		    request.getRequestDispatcher("student_create_done.jsp").forward(request, response);
		}
	}

}

		//在学フラグが送信されていた場合
//		if (isAttendStr!=null){
//			//在学フラグを立てる
//			isAttend=true;
//			//リクエストに在学フラグをセット
//			request.setAttribute("f3",isAttendStr);
//		}
		//リクエストに学生リストをセット
//		request.setAttribute("students",students);
//		//リクエストにデータをセット
//		request.setAttribute("class_num_set",list);
//		request.setAttribute("ent_year_set",entYearSet);
//
//		//JSPにフォワード 7
//		request.getRequestDispatcher("student_list.jsp").forward(request,response);
