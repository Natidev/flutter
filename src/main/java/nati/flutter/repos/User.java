package nati.flutter.repos;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.MongoId;
import java.time.LocalDate;
import java.util.List;

@Document("Users")
public class User {
    @MongoId
    String _id;
    String name;
    int year;
    int semester;
    String department;
    LocalDate created;

    public List<String> getCoursesTaken() {
        return coursesTaken;
    }

    public void setCoursesTaken(List<String> coursesTaken) {
        this.coursesTaken = coursesTaken;
    }

    List<String> coursesTaken;
    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public LocalDate getCreated() {
        return created;
    }

    public void setCreated(LocalDate created) {
        this.created = created;
    }

    public User(String _id, String name, int year, int semester, String department) {
        this._id = _id;
        this.name = name;
        this.year = year;
        this.semester = semester;
        this.department = department;
        this.created = LocalDate.now();
    }
}
