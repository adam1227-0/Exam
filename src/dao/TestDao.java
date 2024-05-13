package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.School;
import bean.Student;
import bean.Subject;
import bean.Test;

public class TestDao extends Dao {

    // テーブルからデータを取得する基本のSQL文
    private String baseSql = "select * from test where school_cd=?";

    // 指定された条件でテスト情報を取得するメソッド
    public Test get(Student student, Subject subject, School school, int no) throws Exception {
        Test test = new Test();
        Connection connection = getConnection();
        PreparedStatement statement = null;

        try {
            statement = connection.prepareStatement("select * from test where student_no=? and subject_cd=? and school_cd=? and no=?");
            statement.setString(1, student.getNo());
            statement.setString(2, subject.getCd());
            statement.setString(3, school.getCd());
            statement.setInt(4, no);

            ResultSet rSet = statement.executeQuery();
            if (rSet.next()) {
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getInt("Point")); // ポイントをセット
                test.setStudent(student);
                test.setSubject(subject);
                test.setSchool(school);
            } else {
                test = null;
            }
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
        }

        return test;
    }

    // テスト情報をフィルタリングして取得するメソッド
    private List<Test> postFilter(ResultSet rSet, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        try {
            while (rSet.next()) {
                Test test = new Test();
                test.setNo(rSet.getInt("no"));
                test.setPoint(rSet.getInt("Point")); // ポイントをセット
                // 生徒と科目の情報を取得
                StudentDao studentDao = new StudentDao();
                SubjectDao subjectDao = new SubjectDao();
                test.setStudent(studentDao.get(rSet.getString("student_no")));
                test.setSubject(subjectDao.get(rSet.getString("subject_cd"), school));
                test.setSchool(school);
                list.add(test);
            }
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
        return list;
    }

    // 条件に基づいてテスト情報をフィルタリングして取得するメソッド
    public List<Test> filter(int entYear, String classNum, Subject subject, int num, School school) throws Exception {
        List<Test> list = new ArrayList<>();
        Connection connection = getConnection();
        PreparedStatement statement = null;
        ResultSet rSet = null;
        String condition = "and ent_year=? and class_num=? and subject_cd=? and num=?";
        String order = " order by no asc";
        try {
            statement = connection.prepareStatement(baseSql + condition + order);
            statement.setString(1, school.getCd());
            statement.setInt(2, entYear);
            statement.setString(3, classNum);
            statement.setString(4, subject.getCd());
            statement.setInt(5, num);
            rSet = statement.executeQuery();
            list = postFilter(rSet, school);
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException sqle) {
                    throw sqle;
                }
            }
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

    // テスト情報をデータベースに保存するメソッド
    public boolean save(List<Test> list) throws Exception {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            for (Test test : list) {
                if (!save(test, connection)) {
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return true;
    }

    // テスト情報をデータベースに保存するメソッド
    public boolean save(Test test, Connection connection) throws Exception {
        PreparedStatement statement = null;
        int count = 0;
        try {
            statement = connection.prepareStatement("insert into test(no, score, student_no, subject_cd, school_cd) values(?, ?, ?, ?, ?)");
            statement.setInt(1, test.getNo());
            statement.setInt(2, test.getPoint());
            statement.setString(3, test.getStudent().getNo());
            statement.setString(4, test.getSubject().getCd());
            statement.setString(5, test.getSchool().getCd());
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return count > 0;
    }

    // テスト情報をデータベースから削除するメソッド
    public boolean delete(List<Test> list) throws Exception {
        Connection connection = null;
        try {
            connection = getConnection();
            connection.setAutoCommit(false);
            for (Test test : list) {
                if (!delete(test, connection)) {
                    connection.rollback();
                    return false;
                }
            }
            connection.commit();
        } catch (Exception e) {
            if (connection != null) {
                connection.rollback();
            }
            throw e;
        } finally {
            if (connection != null) {
                connection.close();
            }
        }
        return true;
    }

    // テスト情報をデータベースから削除するメソッド
    public boolean delete(Test test, Connection connection) throws Exception {
        PreparedStatement statement = null;
        int count = 0;
        try {
            statement = connection.prepareStatement("delete from test where no=?");
            statement.setInt(1, test.getNo());
            count = statement.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            if (statement != null) {
                statement.close();
            }
        }
        return count > 0;
    }
}
//