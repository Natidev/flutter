package nati.flutter.repos;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface UserRepository extends MongoRepository<User,String> {
    boolean existsBy_idEqualsIgnoreCase(String _id);


}
