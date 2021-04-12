package com.daniele.bank.nfeuploadservice.service;

import com.daniele.bank.nfeuploadservice.enums.StatusNfe;
import com.daniele.bank.nfeuploadservice.model.NotaFiscal;
import com.daniele.bank.nfeuploadservice.model.NotaFiscalStatus;
import com.daniele.bank.nfeuploadservice.repository.NfeDuplicataRepository;
import com.daniele.bank.nfeuploadservice.repository.NfeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.transaction.Transactional;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class NfeService {

    @Autowired
    private NfeRepository nfeRepository;

    @Autowired
    private NfeDuplicataRepository nfeDuplicataRepository;

    @Value("${file.input.path}")
    private String inputPath;

    public void createNfe(MultipartFile file) throws IOException {
        String fileName = getFileName();

        NotaFiscal notaFiscal = NotaFiscal.builder()
                .arquivoId(fileName)
                .nomeArquivoOriginal(file.getOriginalFilename())
                .status(NotaFiscalStatus.builder().id(StatusNfe.AGUARDANDO_PROCESSAMENTO.getStatusId()).build())
                .build();

        nfeRepository.save(notaFiscal);

        createFile(file, fileName);
    }

    public List<NotaFiscal> getNfes() {
        List<NotaFiscal> notaFiscalList = new ArrayList<>();
        nfeRepository.findAll().forEach(notaFiscalList::add);
        return notaFiscalList;
    }

    @Transactional
    public boolean deleteNfe(int id) {
        if (nfeRepository.existsById(id)) {
            nfeDuplicataRepository.deleteByNotaFiscalId(id);
            nfeRepository.deleteById(id);
            return true;
        }
        return false;
    }

    private String getFileName() {
        return "nfe_".concat(UUID.randomUUID().toString()).concat(".xml");
    }

    private void createFile(MultipartFile file, String fileName) throws IOException {
        Files.copy(file.getInputStream(), Paths.get(inputPath).resolve(fileName));
    }
}
