package com.daniele.bank.nfeuploadservice.controller;

import com.daniele.bank.nfeuploadservice.model.NotaFiscal;
import com.daniele.bank.nfeuploadservice.service.NfeService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@Log4j2
@RequestMapping(path = "/nfe")
public class NfeController {

    @Autowired
    private NfeService nfeService;

    @PostMapping
    public ResponseEntity<Void> addNfe(@RequestParam("file") MultipartFile file) {

        try {
            nfeService.createNfe(file);
            log.info("xml file created with success {}", file.getOriginalFilename());

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }

    }

    @GetMapping
    public List<NotaFiscal> getAllNfes() {
        try {
            return nfeService.getNfes();
        } catch (Exception e) {
            log.error(e.getMessage());
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteNfe(@PathVariable int id) {
        try {
            if (nfeService.deleteNfe(id)) {
                return ResponseEntity.ok().build();
            }

            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

}
