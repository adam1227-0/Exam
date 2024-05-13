
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import bean.School;
import bean.Subject;
import bean.TestListSubject;

public class TestListSubjectDao extends Dao {

	String basesql = "select s.ENT_YEAR, s.NO, s.NAME, s.CLASS_NUM, t.NO, t.POINT from TEST as t left join STUDENT as s on t.STUDENT_NO=s.NO ";

	private List<TestListSubject> postFilter(ResultSet rSet) throws Exception {

		// リストを初期化

		List<TestListSubject> list = new ArrayList<>();

		while (rSet.next()) {

			// 成績インスタンスを初期化

			TestListSubject testLS = new TestListSubject();

			Map<Integer, Integer> map = new HashMap<>();

			map.put(rSet.getInt("NO"), rSet.getInt("POINT"));

			// 成績インスタンスに結果をセット

			testLS.setEntYear(rSet.getInt("ENTYEAR"));

			testLS.setStudentNo(rSet.getString("NO"));

			testLS.setStudentName(rSet.getString("NAME"));

			testLS.setClassNum(rSet.getString("CLASS_NUM"));

			testLS.setPoints(map);

			testLS.putPoint(rSet.getInt("POINT"),rSet.getInt("NO"));

			list.add(testLS);

		}

		return list;

	}

	public List<TestListSubject> filter(int entYear, String classNum, Subject subject, School school) throws Exception {

		List<TestListSubject> list = new ArrayList<>();

		// コネクションを確立

		Connection connection = getConnection();

		// プリペアードステートメント

		PreparedStatement statement = null;

		// リザルトセット

		ResultSet rSet = null;

		// SQL文の条件

		String condition = "where t.SUBJECT_CD=? and s.ENT_YEAR=? and s.CLASS_NUM=? and s.SCHOOL=?";

		// SQL文のソート

		String order = " order by t.STUDENT_CD asc";

		try {

			// プリペアードステートメントにSQL文をセット

			statement = connection.prepareStatement(basesql + condition + order);

			// プリペアードステートメントに科目コードをバインド

			statement.setString(1, subject.getCd());

			statement.setInt(2, entYear);

			// プリペアードステートメントにクラス番号をバインド

			statement.setString(3, classNum);

			// プリペアードステートメントに学校コードをバインド

			statement.setString(4, school.getCd());

			// プライベートステートメントを実行

			rSet = statement.executeQuery();

			// リストへの格納処理を実行

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
