package com.daniele.bank.nfeuploadservice.repository;

import com.daniele.bank.nfeuploadservice.model.NotaFiscalDuplicata;
import org.springframework.data.repository.CrudRepository;

public interface NfeDuplicataRepository extends CrudRepository<NotaFiscalDuplicata, Integer> {
    void deleteByNotaFiscalId(int id);
}