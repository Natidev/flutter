package nati.flutter.controllers;

import nati.flutter.repos.Course;
import nati.flutter.repos.CoursesRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class Api {
    CoursesRepository repo;

    public Api(CoursesRepository repo) {
        this.repo = repo;
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
    @GetMapping("/courses/all")
    public Page<Course> getCourses(
            @PageableDefault(size = 5) Pageable pageable
    ) {
//        Sort sort=Sort.by(Sort.Direction.ASC,"year");
//        pageable= PageRequest.of(pageable.getPageNumber(),pageable.getPageSize(),sort);
        return repo.findAll(pageable);
    }



}
