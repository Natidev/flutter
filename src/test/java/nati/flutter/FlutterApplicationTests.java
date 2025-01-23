package nati.flutter;

import static org.assertj.core.api.Assertions.assertThat;

import nati.flutter.repos.User;
import nati.flutter.repos.UserRepository;
import nati.flutter.services.UserData;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FlutterApplicationTests {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserData userData;


    @Test
    void contextLoads() {
    }
    @Test
    void doesthisRun(){
        User nati =new User(
                "ETS1225",
                "NATI",
                3,
                2,
                "Software"
        );
        userRepository.save(nati);

       var r= userData.addUserInfo(
               nati
        );
        assertThat(r.get_id()).isEqualTo("ETS1225");
    }

}
