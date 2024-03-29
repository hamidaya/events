package org.music.events.controllers;


import org.music.events.dtos.CIModuleDto;
import org.music.events.services.CIModuleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// Dit is de Controller klasse van de CIModule entiteit en heeft vergelijkbare methodes als de TelevisionController
@RestController
public class CIModuleController {
    private final CIModuleService ciModuleService;

    public CIModuleController(CIModuleService ciModuleService){
        this.ciModuleService = ciModuleService;
    }


    @GetMapping("/cimodules")
    public ResponseEntity<List<CIModuleDto>> getAllCIModules() {

        List<CIModuleDto> dtos = ciModuleService.getAllCIModules();

        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/cimodules/{id}")
    public ResponseEntity<CIModuleDto> getCIModule(@PathVariable("id") Long id) {

        CIModuleDto ciModuleDto = ciModuleService.getCIModule(id);

        return ResponseEntity.ok(ciModuleDto);
    }

    @PostMapping("/cimodules")
    public ResponseEntity<CIModuleDto> addCIModule(@RequestBody CIModuleDto dto) {
        CIModuleDto ciModuleDto = ciModuleService.addCIModule(dto);
        return ResponseEntity.created(null).body(ciModuleDto);
    }

    @DeleteMapping("/cimodules/{id}")
    public ResponseEntity<Object> deleteCIModule(@PathVariable("id") Long id) {

        ciModuleService.deleteCIModule(id);

        return ResponseEntity.noContent().build();
    }

    @PutMapping("/cimodules/{id}")
    public ResponseEntity<CIModuleDto> updateCIModule(@PathVariable("id") Long id, @RequestBody CIModuleDto ciModuleDto) {
        ciModuleService.updateCIModule(id, ciModuleDto);
        return ResponseEntity.ok(ciModuleDto);
    }

}