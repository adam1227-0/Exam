
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Subject;

public class SubjectCdDao extends Dao {
	private String baseSql = "select DISTINCT * from SUBJECT where SCHOOL_CD=?";
	public List<Subject> filter(School school) throws Exception{
		// リストを初期化
		List<Subject> list = new ArrayList<>();
		// コネクションを確立
		Connection connection = getConnection();
		// プリペアードステートメント
		PreparedStatement statement = null;
		// リザルトセット
		ResultSet rSet = null;
		try {
			// プリペアードステートメントにSQL文をセット
			statement = connection.prepareStatement(baseSql);
			// プリペアードステートメントに学校コードをバインド
			statement.setString(1, school.getCd());
			// プライベートステートメントを実行
			rSet = statement.executeQuery();
			// リストへの格納処理を実行
			try {
				// リザルトセットを全権走査
				while (rSet.next()) {
					Subject subject = new Subject();
					// インスタンスに検索結果をセット
					subject.setCd(rSet.getString("CD"));
					subject.setName(rSet.getString("NAME"));
					subject.setSchool(school);
					list.add(subject);
					// リストに追加
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
}
