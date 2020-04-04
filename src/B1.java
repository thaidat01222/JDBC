import java.sql.*;

public class B1 {
    public Connection getConnection() throws ClassNotFoundException, SQLException {

        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection conn = DriverManager
                .getConnection("jdbc:mysql://localhost:3306/studentmanagement?serverTimezone=UTC", "root", "root");
        return conn;
    }
    public void Display(int x){
        try{
            Connection conn = getConnection();
            Statement sm = conn.createStatement();
            ResultSet rs = sm.executeQuery("SELECT *from student");
            ResultSetMetaData rsm = rs.getMetaData();
            // PreparedStatement ps = conn.prepareStatement("SELECT *from student inner join teacher on student.id = teacher.id");
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
    public void getStudentByMathAndPhys(float x, float y) {
        try {

            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT  *from student where (Math>=? and Phys >=?)  ");

            ps.setFloat(1, x);
            ps.setFloat(2, y);

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            // PreparedStatement ps = conn.prepareStatement("SELECT *from student inner join teacher on student.id = teacher.id");
            int col_num = rsm.getColumnCount();
            Display(col_num);
        }
        catch (Exception e) {
            System.out.println(e);
        }

    }
    public void getStudentByAver(float x) {
        try {

            Connection conn = getConnection();
            PreparedStatement ps = conn.prepareStatement("SELECT  *from student where Aver >=?  ");

            ps.setFloat(1, x);

            ResultSet rs = ps.executeQuery();
            ResultSetMetaData rsm = rs.getMetaData();
            // PreparedStatement ps = conn.prepareStatement("SELECT *from student inner join teacher on student.id = teacher.id");
            int col_num = rsm.getColumnCount();
            Display(col_num);
            }
         catch (Exception e) {
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        B1 b = new B1();
        b.getStudentByAver(5);
        b.getStudentByMathAndPhys(3,5);

    }

}