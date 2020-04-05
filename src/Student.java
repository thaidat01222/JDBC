import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Student {
    private int ID;
    private String Name;
    private float Math;
    private float Phys;
    private float Chem;
    private float Aver;

    public Student(int ID, String Name, float Math, float Phys, float Chem, float Aver) {
        this.ID = ID;
        this.Name = Name;
        this.Math = Math;
        this.Phys = Phys;
        this.Chem = Chem;
        this.Aver = Aver;
    }

    public void setID(int id) {
        this.ID = ID;
    }

    public int getID() {
        return ID;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getName() {
        return Name;
    }

    public void setMath(float Math) {
        this.Math = Math;
    }

    public float getMath() {
        return Math;
    }

    public void setPhys(float Phys) {
        this.Phys = Phys;
    }

    public float getPhys() {
        return Phys;
    }

    public void setChem(float Chem) {
        this.Chem = Chem;
    }

    public float getChem() {
        return Chem;
    }

    public void setAver(float Aver) {
        this.Aver = Aver;
    }

    public float getAver() {
        return Aver;
    }

    public void Display() {
        System.out.println("id :" + this.getID());
        System.out.println("name :" + this.getName());
        System.out.println("math :" + this.getMath());
        System.out.println("phys :" + this.getPhys());
        System.out.println("Chem :" + this.getChem());
        System.out.println("Aver :" + this.getAver());
    }

}
