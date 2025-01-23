package nati.flutter.controllers;

import nati.flutter.repos.Course;
import nati.flutter.repos.CoursesRepository;
import nati.flutter.repos.User;
import nati.flutter.repos.UserRepository;
import nati.flutter.services.UserData;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("user")
public class UserController {
    UserRepository repo;
    CoursesRepository coursesRepository;
    UserData userData;
    public UserController(UserRepository repo,UserData userData,CoursesRepository coursesRepository1) {
        this.repo = repo;
        this.userData=userData;
        this.coursesRepository=coursesRepository1;
    }

    @PostMapping("/create")
    @ResponseBody
    public User registerUser(
            @RequestBody User user
    ){

        return userData.addUserInfo(
                repo.save(user)
        );
    }
    @GetMapping("check/{id}")
    @ResponseBody
    boolean checkUser(@PathVariable String id ){
        return repo.existsBy_idEqualsIgnoreCase(id);
    }
    @GetMapping("/all")

    List<User> allUsers(){
     return repo.findAll();
    }
    @GetMapping("/{user}")
    @ResponseBody
    Optional<User> getUser(
            @PathVariable String user
    ){
        return repo.findById(user);
    }
    @GetMapping("/courses")
    @ResponseBody
    List<Course> getCoursesTaken(
            @RequestParam String user_id
    ){

        var r=repo.findById(user_id);
        if(r.isPresent())
        return r.get()
                .getCoursesTaken()
                .stream()
                .map( id->coursesRepository.findCourseBy_id(id)).toList();
        else
            throw new NullPointerException();
    }
}
