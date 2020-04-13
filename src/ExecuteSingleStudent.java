import java.sql.*;

public class ExecuteSingleStudent {
    public Connection getConnection() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement?serverTimezone=UTC", "root", "root");
    }

    public ResultSet getResultSet(PreparedStatement ps) throws SQLException {
        return ps.executeQuery();
    }

    public PreparedStatement getPreparedStatement(String sql) throws SQLException, ClassNotFoundException {
        Connection conn = getConnection();
        return conn.prepareStatement(sql);
    }

    private Student getStudentByID(int id) throws ClassNotFoundException, SQLException {
        PreparedStatement ps = getPreparedStatement("SELECT  *from student where id = ?");
        ps.setFloat(1, id);
        ResultSet rs = getResultSet(ps);
        if (rs.next()) {
            return mapResultSetToStudent(rs);
        } else {
            return null;
        }
    }


    public void updateStudentByID(Student student) throws Exception {
        String query = "UPDATE student SET name = ? ,math = ? ,phys = ? ,chem = ? ,aver = ? where id = ?";
        PreparedStatement ps = getPreparedStatement(query);
        ps.setString(1, student.getName());
        ps.setFloat(2, student.getMath());
        ps.setFloat(3, student.getPhys());
        ps.setFloat(4, student.getChem());
        ps.setFloat(5, student.getAver());
        ps.setInt(6, student.getID());
        ps.executeUpdate();
    }

    public void addStudent(Student student) throws ClassNotFoundException, SQLException {
        PreparedStatement ps = getPreparedStatement("INSERT INTO student VALUES (?,?,?,?,?,?)");
        ps.setInt(1, student.getID());
        ps.setString(2, student.getName());
        ps.setFloat(3, student.getMath());
        ps.setFloat(4, student.getPhys());
        ps.setFloat(5, student.getChem());
        ps.setFloat(6, (student.getMath() + student.getPhys() + student.getChem()) / 3);
        ps.executeUpdate();
    }

    public void deleteStudentById(int id) throws ClassNotFoundException, SQLException {
        PreparedStatement ps = getPreparedStatement("Delete table student where id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();

    }

    public Student mapResultSetToStudent(ResultSet rs) throws SQLException {
        int ID = rs.getInt(1);
        String Name = rs.getString(2);
        float Math = rs.getFloat(3);
        float Phys = rs.getFloat(4);
        float Chem = rs.getFloat(5);
        float Aver = rs.getFloat(6);
        return new Student(ID, Name, Math, Phys, Chem, Aver);
    }

    public static void main(String[] args) throws Exception {
        ExecuteSingleStudent c = new ExecuteSingleStudent();
        Student student = new Student();
        student.setID(1);
        student.setName("dat");
        student.setMath(10);
        student.setPhys(10);
        student.setChem(10);
        student.setAver(10);
        c.updateStudentByID(student);
    }
}
