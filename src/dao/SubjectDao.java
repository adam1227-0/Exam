package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;
public class SubjectDao extends Dao{

	private String baseSql = "select * from SUBJECT where SCHOOL_CD=?";

	public Subject get(String cd, School school) throws Exception {

		// 学生インスタンスを初期化

		Subject subject = new Subject();

		// コネクションを確立

		Connection connection = getConnection();

		// プリペアードステートメント

		// SQL文の条件

		String condition = "and CD=?";

		PreparedStatement statement = null;

		try {

			// プリペアードステートメントにSQL文をセット

			statement = connection.prepareStatement(baseSql + condition);

			// プリペアードステートメントに学校コードをバインド

			statement.setString(1, school.getCd());

			statement.setString(2, cd);

			// プライアードステートメントを実行

			ResultSet rSet = statement.executeQuery();

			// 学校Daoを初期化

			SchoolDao schoolDao = new SchoolDao();

			if (rSet.next()) {

				// リザルトセットが存在する場合

				// インスタンスに検索結果をセット

				subject.setSchool(schoolDao.get(rSet.getString("school_cd")));

				subject.setCd(rSet.getString("cd"));

				subject.setName(rSet.getString("name"));

			} else {

				// リザルトセットが存在しない場合

				// 学生インスタンスにnullをセット

				subject = null;

			}

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

		return subject;

	}
//-----------------------------------------------------------------------------------------------------------------------------------------

	public List<Subject> filter(School school) throws Exception {

		// リストを初期化

		List<Subject> list = new ArrayList<>();

		// コネクションを確立

		Connection connection = getConnection();

		// プリペアードステートメント

		PreparedStatement statement = null;

		// リザルトセット

		ResultSet rSet = null;

		// SQL文のソート

		String order = " order by CD asc";

		try {

			// プリペアードステートメントにSQL文をセット

			statement = connection.prepareStatement(baseSql + order);

			// プリペアードステートメントに学校コードをバインド

			statement.setString(1, school.getCd());

			// プライベートステートメントを実行

			rSet = statement.executeQuery();

			// リストへの格納処理を実行

			try {

				// リザルトセットを全権走査

				while (rSet.next()) {

					// インスタンスを初期化

					Subject subject = new Subject();

					// インスタンスに検索結果をセット

					subject.setSchool(school);

					subject.setCd(rSet.getString("cd"));

					subject.setName(rSet.getString("name"));

					// リストに追加

					list.add(subject);

				}

			} catch (SQLException | NullPointerException e) {

				e.printStackTrace();

			}


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
//-----------------------------------------------------------------------------------------------------------------------------------------

	public boolean save(Subject subject) throws Exception {

		// コネクションを確立

		Connection connection = getConnection();

		// プリペアードステートメント

		PreparedStatement statement = null;

		// 実行件数

		int count = 0;


		try {

			// データベースから学生を取得

			Subject old = get(subject.getCd(), subject.getSchool());

			if (old == null) {

			    // 学生が存在しなかった場合

			    // プリペアードステートメントにINSERT文をセット

			    statement = connection.prepareStatement(

			            "insert into SUBJECT(SCHOOL_CD, CD, NAME) values(?, ?, ?)");

			    // プリペアードステートメントに値をバインド

			    statement.setString(1, subject.getSchool().getCd());

			    statement.setString(2, subject.getCd());

			    statement.setString(3,  subject.getName());

			} else {

			    // 学生が存在した場合

			    // プリペアードステートメントにUPDATE文をセット

			    statement = connection

			            .prepareStatement("update SUBJECT set NAME=? where CD=? and SCHOOL_CD=?");

			    // プリペアードステートメントに値をバインド

			    statement.setString(1,  subject.getName());

			    statement.setString(2, subject.getCd());

			    statement.setString(3, subject.getSchool().getCd());

			}

			// プリペアードステートメントを実行

			count = statement.executeUpdate();

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

		if (count > 0) {

			// 実行件数が1件以上ある場合

			return true;

		} else {

			// 実行件数が０件の場合

			return false;

		}

	}
//-----------------------------------------------------------------------------------------------------------------------------------------

	public boolean delete(Subject subject) throws Exception {

		// コネクションを確立

		Connection connection = getConnection();

		// プリペアードステートメント

		PreparedStatement statement = null;

		// 実行件数

		int count = 0;


		try {

			// データベースから学生を取得

			Subject old = get(subject.getCd(), subject.getSchool());

			if(old != null){

			    // 学生が存在した場合

			    // プリペアードステートメントにDELETE文をセット

			    statement = connection

			            .prepareStatement("delete SUBJECT where CD=? ");

			    // プリペアードステートメントに値をバインド

			    statement.setString(1, subject.getCd());

			}

			// プリペアードステートメントを実行

			count = statement.executeUpdate();

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

		if (count > 0) {

			// 実行件数が1件以上ある場合

			return true;

		} else {

			// 実行件数が０件の場合

			return false;

		}

	}

}
//-----------------------------------------------------------------------------------------------------------------------------------------
//