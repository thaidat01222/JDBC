import com.sun.xml.internal.bind.v2.model.core.ID;

import javax.naming.Name;
import java.sql.*;

public class C1 {
    public Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement?serverTimezone=UTC", "root", "root");
        return conn;
    }

    public ResultSet getResultSet(PreparedStatement ps) throws ClassNotFoundException, SQLException {
        ResultSet rs = ps.executeQuery();
        return rs;
    }

    private Student getStudentByID(int id) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("SELECT  *from student where id = ?");
        ps.setFloat(1, id);
        ResultSet rs = getResultSet(ps);
        if (rs.next()) {
            return mapResultSetToStudent(rs);
        } else {
            return null;
        }
    }

    private Student mapResultSetToStudent(ResultSet rs) throws ClassNotFoundException, SQLException {

        int ID = rs.getInt(1);
        String Name = rs.getString(2);
        float Math = rs.getFloat(3);
        float Phys = rs.getFloat(4);
        float Chem = rs.getFloat(5);
        float Aver = rs.getFloat(6);
        Student s = new Student(ID, Name, Math, Phys, Chem, Aver);
        return s;
    }

    private void updateStudentByID(int id, String x , String y) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("UPDATE student SET ? = ? where id = ?");
        ps.setString(1,x);
        ps.setString(2,y);
        ps.setInt(3,id);
        ResultSet rs = getResultSet(ps);
        mapResultSetToStudent(rs);
    }

    private void addStudent(int x, String y, float z, float t, float q)throws ClassNotFoundException, SQLException {

        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO student VALUES (?,?,?,?,?,?)");
        ps.setInt(1,x);
        ps.setString(2,y);
        ps.setFloat(3,z);
        ps.setFloat(4,t);
        ps.setFloat(5,q);
        ps.setFloat(6,(z+t+q)/3);
        ps.executeUpdate();
        ResultSet rs = getResultSet(ps);
        //mapResultSetToStudent(rs);
    }
    private void deleteStudentById(int id) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("Delete table student where id = ?");
        ps.setInt(1,id);
        ps.executeUpdate();
        
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        C1 c = new C1();
        //Student c1 = c.getStudentByID(1);
        //c1.Display();
    }
}
