
public class C1 {
    public Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/studentmanagement?serverTimezone=UTC", "root", "root");
    }

    public ResultSet getResultSet(PreparedStatement ps) throws ClassNotFoundException, SQLException {
        return ps.executeQuery();
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
        return new Student(ID, Name, Math, Phys, Chem, Aver);
    }

    private void updateStudentByID(Student student) throws Exception {
        Connection conn = getConnection();
        String query = "";
        query += "UPDATE student SET ";
        query += " name = ? ,";
        query += " math = ? ,";
        query += " phys = ? ,";
        query += " chem = ? ,";
        query += " aver = ? ";
        query += "where id = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setString(1, student.getName());
        ps.setFloat(2, student.getMath());
        ps.setFloat(3, student.getPhyc());
        ps.setFloat(4, student.getChem());
        ps.setFloat(5, student.getAver());
        ps.setInt(6, student.getId());
        ps.executeUpdate();
    }

    private void addStudent(Student student) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("INSERT INTO student VALUES (?,?,?,?,?,?)");
        ps.setInt(1, student.getId());
        ps.setString(2, student.getName());
        ps.setFloat(3, student.getMath());
        ps.setFloat(4, student.getPhyc());
        ps.setFloat(5, student.getChem());
        ps.setFloat(6, (student.getMath() + student.getPhyc() + student.getChem()) / 3);
        ps.executeUpdate();
    }

    private void deleteStudentById(int id) throws ClassNotFoundException, SQLException {
        Connection conn = getConnection();
        PreparedStatement ps = conn.prepareStatement("Delete table student where id = ?");
        ps.setInt(1, id);
        ps.executeUpdate();

    }

    public static void main(String[] args) throws Exception {
        C1 c = new C1();
        Student student = new Student();
        student.setId(1);
        student.setName("dat");
        student.setMath(10);
        student.setPhyc(10);
        student.setChem(10);
        student.setAver(10);
        c.updateStudentByID(student);
    }
}
