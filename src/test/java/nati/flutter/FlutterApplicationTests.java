package nati.flutter;

import static org.assertj.core.api.Assertions.assertThat;

import nati.flutter.controllers.PrerequisiteData;
import nati.flutter.repos.Course;
import nati.flutter.repos.User;
import nati.flutter.repos.UserRepository;
import nati.flutter.services.UserData;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@SpringBootTest
class FlutterApplicationTests {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserData userData;
    RestTemplate template=new RestTemplate();

    @Test
    void contextLoads() {
    }
    @Test
    void addCourse(){
        var response=template.postForEntity("http://localhost:8080/courses/add",
                new Course(List.of(),1,1,3,"test","test123"),
                Course.class);
        assertThat(Objects.requireNonNull(response.getBody())._id).isEqualTo("test123");
    }
    @Test
    void addListCourse(){

        var response=template.postForEntity("http://localhost:8080/courses/add/list",
                List.of(new Course(List.of(),1,1,3,"test","test123"),
                        new Course(List.of(),1,1,3,"test","test1234")),
        List.class);
        assertThat(Objects.requireNonNull(response.getBody()).size()).isEqualTo(2);
    }
    @Test
    void checkPrerequisite(){
        var response=template.getForEntity("http://localhost:8080/courses/prerequisite?_id=SWEG3101"
                                ,List.class);
        assertThat(Objects.requireNonNull(response.getBody()).size()).isEqualTo(1);
    }
    @Test
    void checkAvailable(){
        var response=template.postForEntity("http://localhost:8080/courses/available",
                new PrerequisiteData(List.of("Phil1009"),1,2)
                ,List.class);
        assertThat(Objects.requireNonNull(response.getBody()).size()).isGreaterThan(5);
    }
}
