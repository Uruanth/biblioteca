package co.com.sofkau.biblioteca.biblioteca.service;

import co.com.sofkau.biblioteca.biblioteca.dto.ResourceDTO;
import co.com.sofkau.biblioteca.biblioteca.mapper.ResourceMapper;
import co.com.sofkau.biblioteca.biblioteca.model.Resource;
import co.com.sofkau.biblioteca.biblioteca.repository.ResourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Service
public class ResourceService {

    Logger log = Logger.getLogger("ResourceService.class");

    @Autowired
    ResourseRepository repository;
    ResourceMapper mapper = new ResourceMapper();

    public List<ResourceDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(resource -> mapper.fromCollection(resource))
                .collect(Collectors.toList());
    }

    public ResourceDTO getById(String id) {
        return repository.findById(id).stream()
                .map(resource -> mapper.fromCollection(resource))
                .findFirst()
                .orElseThrow();
    }

    public List<ResourceDTO> getByThematic(String thematic) {
        repository.findByThematic(thematic)
                .stream()
                .forEach(System.out::println);

        return repository.findByThematic(thematic)
                .stream()
                .map(resource -> mapper.fromCollection(resource))
                .collect(Collectors.toList());
    }

    public List<ResourceDTO> getByType(String type) {
        return repository.findByType(type)
                .stream()
                .map(resource -> mapper.fromCollection(resource))
                .collect(Collectors.toList());
    }

    public ResourceDTO save(ResourceDTO resource) {
        Resource response = repository.save(mapper.fromDTO(resource));
        return mapper.fromCollection(response);
    }


    public ResourceDTO update(ResourceDTO resourcedto) {
        Resource resource = mapper.fromDTO(resourcedto);
        repository.findById(resource.getId()).orElseThrow(()
                -> new RuntimeException("The resource could not be found"));
        return mapper.fromCollection(repository.save(resource));
    }

    public void delete(String id) {
        repository.deleteById(id);
    }

    public Boolean availability(String id) {
        try {

            return this.getById(id).getState();
        } catch (Exception e) {
            return false;
        }
    }

    public Boolean lendAResource(String id) {
        if (availability(id)) {
            ResourceDTO resource = getById(id);
            resource.setState(false);
            resource.setLoanDate(LocalDate.now());
            update(resource);
            return true;
        }
        return false;
    }


    public Boolean returnResource(String id) {
        ResourceDTO resourceDTO = getById(id);
        if (resourceDTO.getId() != null) {
            resourceDTO.setState(true);
            this.update(resourceDTO);
            return this.availability(resourceDTO.getId());
        }
        return this.availability(resourceDTO.getId());

    }
}
