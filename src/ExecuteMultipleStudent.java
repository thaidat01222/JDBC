import java.sql.*;
import java.util.List;

public class ExecuteMultipleStudent {

    public void Display(List<Student> students) {
        for (Student s : students) {
            s.display();
        }
    }

    public void updateMultipleStudent(List<Student> students) throws Exception {
        ExecuteSingleStudent executeSingleStudent = new ExecuteSingleStudent();
        for (Student s : students) {
            executeSingleStudent.updateStudentByID(s);
        }
    }

    public void addMultipleStudents(List<Student> students) throws ClassNotFoundException, SQLException {
        ExecuteSingleStudent executeSingleStudent = new ExecuteSingleStudent();
        for (Student s : students) {
            executeSingleStudent.addStudent(s);
        }
    }

    public void deleteMultipleStudents(List<Integer> studentID) throws ClassNotFoundException, SQLException {
        ExecuteSingleStudent executeSingleStudent = new ExecuteSingleStudent();
        for (Integer id : studentID) {
            executeSingleStudent.deleteStudentById(id);
        }
    }

    public void displayStudents(List<Student> students) {
        for (Student s : students) {
            s.display();
        }
    }


}