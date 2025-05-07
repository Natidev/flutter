package nati.flutter.controllers;

import nati.flutter.repos.Course;
import nati.flutter.repos.CoursesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/courses")
public class Api {
    CoursesRepository repo;

    public Api(CoursesRepository repo) {
        this.repo = repo;
    }
    @GetMapping
    public List<Course> all(){
        return repo.findAll();
    }
    @PostMapping("/add")
    @ResponseBody
    public Course addAcourse(
            @RequestBody Course data
    ){
        return repo.save(data);
    }

    @PostMapping("/add/list")
    @ResponseBody
    public List<Course> addCourses(
            @RequestBody List<Course> cs
    ){
        return repo.saveAll(cs);
    }


    @GetMapping("/all")
    public Page<Course> getCourses(
            @PageableDefault(size = 30) Pageable pageable
    ) {
        return repo.findAll(pageable);
    }


    @GetMapping("/prerequisite")
    public List<Course> getPrerequisites(
            @RequestParam String _id
    ){
        var course=repo.findCourseBy_id(_id);
        return course.getPrerequisite().stream().map(i-> repo.findCourseBy_id(i)).toList();
    }
@PostMapping("/available")
public List<Course> getAvailableCourses(
        @RequestBody PrerequisiteData data
){
        return repo.findCourseBySemesterAndYearGreaterThanAndPrerequisiteNotContains(data.semester(), data.year(), data.notTaken());
}
    @PostMapping("/notavailable")
    public List<Course> getNotAvailableCourses(
            @RequestBody PrerequisiteData data
    ){
        return repo.findCourseBySemesterAndYearGreaterThanEqualAndPrerequisiteContains(data.semester(), data.year(), data.notTaken());
    }
@GetMapping("/{id}")
    public  Course getCourse(
            @PathVariable String id
){
        return repo.findCourseBy_id(id);
}
}
