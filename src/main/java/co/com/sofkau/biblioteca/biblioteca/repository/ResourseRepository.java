package co.com.sofkau.biblioteca.biblioteca.repository;

import co.com.sofkau.biblioteca.biblioteca.model.Resource;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ResourseRepository extends MongoRepository<Resource, String> {
    List<Resource> findByThematic(String thematic);
    List<Resource> findByType(String type);
}
