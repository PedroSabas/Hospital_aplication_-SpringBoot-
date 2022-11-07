package com.backend.vegap.controller;

import com.backend.vegap.entity.Medic;
import com.backend.vegap.exception.ModelNotFoundException;
import com.backend.vegap.model.MedicDTO;
import com.backend.vegap.service.MedicService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/medics")
public class MedicController {

    @Autowired
    MedicService medicService;

    @Autowired
    private ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<MedicDTO>> findAll(){
        List<MedicDTO> list = medicService.findAll().stream().map(p -> modelMapper.map(p, MedicDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MedicDTO> findById(@PathVariable("id") Integer id){
        Medic obj = medicService.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        return new ResponseEntity<>(modelMapper.map(obj, MedicDTO.class), HttpStatus.OK);
    }

    /*@PostMapping
    public ResponseEntity<Medic> save(@RequestBody Medic medic){
        Medic obj = service.save(medic);
        return new ResponseEntity<>(obj, HttpStatus.CREATED);
    }*/

    @PostMapping
    public ResponseEntity<Void> save(@Valid @RequestBody MedicDTO dto){
        Medic obj = medicService.save(modelMapper.map(dto, Medic.class));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getIdMedic()).toUri();
        return ResponseEntity.created(location).build();
    }

    @PutMapping
    public ResponseEntity<Medic> update(@Valid @RequestBody MedicDTO dto){
        Medic obj = medicService.update(modelMapper.map(dto, Medic.class));
        return new ResponseEntity<>(obj, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Integer id){
        Medic obj = medicService.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        medicService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/hateoas/{id}")
    public EntityModel<MedicDTO> findByIdHateoas(@PathVariable("id") Integer id){
        Medic obj = medicService.findById(id);

        if(obj == null){
            throw new ModelNotFoundException("ID NOT FOUND: " + id);
        }
        EntityModel<MedicDTO> resource = EntityModel.of(modelMapper.map(obj, MedicDTO.class));
        WebMvcLinkBuilder link1 = linkTo(methodOn(this.getClass()).findById(id));
        WebMvcLinkBuilder link2 = linkTo(methodOn(this.getClass()).findById(id));
        resource.add(link1.withRel("medic-info1"));
        resource.add(link2.withRel("medic-info2"));

        return resource;
    }



    /*public MedicController(MedicService service){
        this.service = service;
    }*/

    /*@GetMapping
    public String sayHello(){
        //Medic medic = new Medic(1, "mitocode");
        return service.sayHello(null);
    }*/
}
