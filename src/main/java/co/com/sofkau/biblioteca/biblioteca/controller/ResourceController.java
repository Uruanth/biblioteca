package co.com.sofkau.biblioteca.biblioteca.controller;

import co.com.sofkau.biblioteca.biblioteca.dto.ResourceDTO;
import co.com.sofkau.biblioteca.biblioteca.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class ResourceController {


    Logger log = Logger.getLogger("controller");

    @Autowired
    ResourceService service;

    @GetMapping
    public ResponseEntity<List<ResourceDTO>> getAll(){
        return new ResponseEntity<>(service.getAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResourceDTO> getById(@PathVariable("id") String id){
        return new ResponseEntity<>(service.getById(id), HttpStatus.OK);
    }



    @GetMapping("/thematic/{thematic}")
    public ResponseEntity<List<ResourceDTO>> categoryRecommendations(@PathVariable("thematic") String thematic){
        return new ResponseEntity<>(service.getByThematic(thematic), HttpStatus.OK);
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<List<ResourceDTO>> typeRecommendations(@PathVariable("type") String type){
        return new ResponseEntity<>(service.getByType(type), HttpStatus.OK);
    }

    @GetMapping("/avaible/{id}")
    public ResponseEntity<String> availability(@PathVariable("id") String id){
        if (service.availability(id))
            return new ResponseEntity<>("Resourse avaible", HttpStatus.OK);

        return new ResponseEntity<>("Resourse unavaible", HttpStatus.OK);
    }

    @GetMapping("/lend/{id}")
    public ResponseEntity<String> lendAResource(@PathVariable("id") String id){
        if (service.lendAResource(id))
            return new ResponseEntity<>("Successful loan", HttpStatus.OK);

        return new ResponseEntity<>("Loan failure", HttpStatus.UNAUTHORIZED);
    }
    @GetMapping("/return/{id}")
    public ResponseEntity<String> returnResource(@PathVariable("id") String id){
        if(service.returnResource(id))
            return new ResponseEntity<>("Successful return", HttpStatus.OK);

        return new ResponseEntity<>("Return failed", HttpStatus.CONFLICT);
    }

    @PostMapping
    public ResponseEntity<ResourceDTO> save(@RequestBody ResourceDTO resourceDTO){
        log.warning("resourceDTO = " + resourceDTO);
        return new ResponseEntity<>(service.save(resourceDTO), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<ResourceDTO> update(@RequestBody ResourceDTO resourceDTO){
        return new ResponseEntity<>(service.update(resourceDTO), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id){
        service.delete(id);
    }

}
