package nati.flutter.repos;

import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface CoursesRepository extends MongoRepository<Course, String> {
    Course findCourseBy_id(String _id);
    List<Course> findCourseBySemesterAndYearGreaterThanAndPrerequisiteNotContains(int semester, int year, List<String> prerequisite);
    List<Course> findCourseBySemesterAndYearGreaterThanEqualAndPrerequisiteContains(int semester, int year, List<String> prerequisite);
    List<Course> findCourseByYearLessThanEqualAndSemesterLessThanEqual(int year,int semester);
}
