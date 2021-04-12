package com.daniele.bank.nfeuploadservice.repository;

import com.daniele.bank.nfeuploadservice.model.NotaFiscal;
import org.springframework.data.repository.CrudRepository;

public interface NfeRepository extends CrudRepository<NotaFiscal, Integer> {
}
