import com.mysql.cj.result.SqlDateValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class D1 {
    public static  List<Student> student;
    public static Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement?serverTimezone=UTC", "root", "root");
        return conn;
    }

    public ResultSet getResultSet(PreparedStatement ps) throws ClassNotFoundException, SQLException {
        ResultSet rs = ps.executeQuery();
        return rs;
    }
    public static void Display(ResultSet rs){
        try{
            ResultSetMetaData rsm = rs.getMetaData();
            int col_num = rsm.getColumnCount();
            for (int i = 1; i <= col_num; i++)
                System.out.print(rsm.getColumnLabel(i) + "       ");
            System.out.println("");
            while (rs.next()) {
                for (int i = 1; i <= col_num; i++)
                    System.out.print(rs.getString(i) + "        ");
                System.out.println("");
            }
        } catch (Exception e) {
            System.out.println(e);
        }
    }
    private static Student mapResultSetToStudent(ResultSet rs) throws ClassNotFoundException, SQLException {

        int ID = rs.getInt(1);
        String Name = rs.getString(2);
        float Math = rs.getFloat(3);
        float Phys = rs.getFloat(4);
        float Chem = rs.getFloat(5);
        float Aver = rs.getFloat(6);
        return new Student(ID, Name, Math, Phys, Chem, Aver);
    }
    public static void updateMultipleStudent(List<Student> student) throws ClassNotFoundException , SQLException{
        Connection conn = getConnection();
        String query =  "";
        query += "UPDATE student SET ";
        query += " name = ? ,";
        query += " math = ? ,";
        query += " phys = ? ,";
        query += " chem = ? ,";
        query += " aver = ? ";
        query += "where id = ?";

        PreparedStatement ps = conn.prepareStatement(query);
        for (Student student1 : student ) {
            ps.setString(1, student1.getName());
            ps.setFloat(2, student1.getMath());
            ps.setFloat(3, student1.getPhys());
            ps.setFloat(4, student1.getChem());
            ps.setFloat(5, student1.getAver());
            ps.setInt(6, student1.getID());
            ps.executeUpdate();
        }

    }
    private static void addMultipleStudents(List<Student> student) throws  ClassNotFoundException , SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO student VALUES (?,?,?,?,?,?)");
        for(Student student1 : student ) {
            ps.setInt(1, student1.getID());
            ps.setString(2, student1.getName());
            ps.setFloat(3, student1.getMath());
            ps.setFloat(4, student1.getPhys());
            ps.setFloat(5, student1.getChem());
            ps.setFloat(6, (student1.getMath() + student1.getPhys() + student1.getChem()) / 3);
            ps.executeUpdate();
        }
    }
    private static void deleteMultipleStudents(List<Integer> studentID) throws ClassNotFoundException , SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("Delete table student where id = ?");
        for( Student student1 : student) {
            ps.setInt(1, student1.getID());
            ps.executeUpdate();
        }
    }
    private static void displayStudents(List<Student> studentID) throws  ClassNotFoundException, SQLException{
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT *FROM student WHERE id = ?");
        for( Student student1 : student) {
            ps.setInt(1, student1.getID());
            ps.executeUpdate();
        }
        ResultSet rs = ps.executeQuery();
        Display(rs);
    }



}