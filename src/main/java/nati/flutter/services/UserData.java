package nati.flutter.services;


import nati.flutter.repos.CoursesRepository;
import nati.flutter.repos.User;
import nati.flutter.repos.UserRepository;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserData {
    private final UserRepository userRepository;
    private final CoursesRepository coursesRepository;
    private final MongoTemplate mongoTemplate;
    public UserData(UserRepository userRepository, CoursesRepository coursesRepository,MongoTemplate mongoTemplate) {
        this.userRepository = userRepository;
        this.coursesRepository = coursesRepository;
        this.mongoTemplate=mongoTemplate;
    }


    public User addUserInfo(
            User newUser
    ){
        int year;
        if (userRepository.existsBy_idEqualsIgnoreCase(newUser.get_id())){

            year= newUser.getYear();
            Query q=new Query(Criteria.where("_id").is(newUser.get_id()));

            Update update =new Update().set(
                    "coursesTaken",
                    coursesRepository
                            .findCourseByYearLessThanEqualAndSemesterLessThanEqual(year, newUser.getSemester())
                            .stream()
                            .map(course -> course._id)
                            .toList()
            );
            
            mongoTemplate.updateFirst(
                    q,
                    update,
                    User.class
            );
        } else {
            throw new NullPointerException("Couldn't find User");
        }
        return  newUser;
    }
}
