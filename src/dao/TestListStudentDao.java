package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Student;
import bean.TestListStudent;
 
public class TestListStudentDao extends Dao {

	private String basesql = "SELECT SUBJECT_CD, s.NAME, NO, POINT from TEST as t left join SUBJECT as s on t.SUBJECT_CD = s.CD ";
 
	private List<TestListStudent> postFilter(ResultSet rSet) throws Exception {

		// リストを初期化

		List<TestListStudent> list = new ArrayList<>();

		// リストへの格納処理を実行

		while (rSet.next()) {

			// 学生別インスタンスを初期化

			TestListStudent testLS = new TestListStudent();
 
			// 学生別インスタンスに結果をセット

			testLS.setSubjectCd(rSet.getString("SUBJECT_CD"));

			testLS.setSubjectName(rSet.getString("NAME"));

			testLS.setNum(rSet.getInt("NO"));

			testLS.setPoint(rSet.getInt("POINT"));
 
			list.add(testLS);

		}

		return list;

	}
 
	public List<TestListStudent> filter(Student student) throws Exception {

		List<TestListStudent> list = new ArrayList<>();

		// コネクションを確立

		Connection connection = getConnection();

		// プリペアードステートメント

		PreparedStatement statement = null;

		// リザルトセット

		ResultSet rSet = null;

		// SQL文の条件

		String condition = "where t.STUDENT_NO=?";

		// SQL文のソート

		String order = " order by SUBJECT_CD asc";
 
		try {

			// プリペアードステートメントにSQL文をセット

			statement = connection.prepareStatement(basesql + condition + order);

			// プリペアードステートメントに学生コードをバインド

			statement.setString(1, student.getNo());

			// プライベートステートメントを実行

			rSet = statement.executeQuery();
 
			list = postFilter(rSet);
 
 
		} catch (Exception e) {

			throw e;

		} finally {

			// プリペアードステートメントを閉じる

			if (statement != null) {

				try {

					statement.close();

				} catch (SQLException sqle) {

					throw sqle;

				}

			}

			// コネクションを閉じる

			if (connection != null) {

				try {

					connection.close();

				} catch (SQLException sqle) {

					throw sqle;

				}

			}

		}

		return list;

	}

}