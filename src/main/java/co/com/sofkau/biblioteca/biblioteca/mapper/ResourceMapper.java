package co.com.sofkau.biblioteca.biblioteca.mapper;

import co.com.sofkau.biblioteca.biblioteca.dto.ResourceDTO;
import co.com.sofkau.biblioteca.biblioteca.model.Resource;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ResourceMapper {

    public Resource fromDTO(ResourceDTO dto) {
        Resource resource = new Resource();
        resource.setId(dto.getId());
        resource.setName(dto.getName());
        resource.setState(dto.getState());
        resource.setLoanDate(dto.getLoanDate());
        resource.setType(dto.getType());
        resource.setThematic(dto.getThematic());
        return resource;
    }

    public ResourceDTO fromCollection(Resource collection) {
        ResourceDTO resource = new ResourceDTO();
        resource.setId(collection.getId());
        resource.setName(collection.getName());
        resource.setState(collection.getState());
        resource.setLoanDate(collection.getLoanDate());
        resource.setType(collection.getType());
        resource.setThematic(collection.getThematic());
        return resource;
    }

    public List<ResourceDTO> fromCollectionList(List<Resource> collection) {

        if(collection == null) return null;
        List<ResourceDTO> list = new ArrayList<>(collection.size());
        Iterator listTracts = collection.iterator();

        while (listTracts.hasNext()){
            Resource resource = (Resource) listTracts.next();
            list.add(fromCollection(resource));
        }

        return list;

    }

}
