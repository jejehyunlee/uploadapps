package com.apollo.uploadapps.credit.controller;

import com.apollo.uploadapps.credit.config.OtherConfig;
import com.apollo.uploadapps.credit.entity.Credit;
import com.apollo.uploadapps.credit.handler.ResponseHandler;
import com.apollo.uploadapps.credit.service.CreditService;
import com.apollo.uploadapps.credit.utils.ConstMessage;
import com.apollo.uploadapps.credit.utils.CsvReader;
import com.apollo.uploadapps.credit.utils.LoggingFile;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/apollo/")
public class CreditController {


    private final CreditService creditService;

    private final List<Credit> lsCPUpload = new ArrayList<Credit>();

    private final String [] strExceptionArr = new String[2];

    @PostMapping("v1/credit/upload")
    public ResponseEntity<Object> uploadMasterCsv(
            @Valid @RequestParam("csvMasterCredit")
            MultipartFile multipartFile,
            WebRequest webRequest
    )throws Exception{

        if(CsvReader.isCsv(multipartFile))
        {
            return creditService.saveUploadFile(
                    csvToGenre(
                            multipartFile.getInputStream()),multipartFile,webRequest);

        } else if (multipartFile.isEmpty()) {
            return new ResponseHandler().generateResponse(ConstMessage.ERROR_EMPTY_FILE
                    , HttpStatus.BAD_REQUEST
                    ,null
                    ,"FI07021"
                    ,webRequest);
        }
        else {
            return new ResponseHandler().generateResponse(ConstMessage.ERROR_NOT_CSV_FILE+" -- "+multipartFile.getOriginalFilename()
                    , HttpStatus.NOT_ACCEPTABLE
                    ,null
                    ,"FI01001"
                    , webRequest);
        }
    }


    public List<Credit> csvToGenre(InputStream inputStream) throws Exception {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
        CSVParser csvParser = new CSVParser(bufferedReader,
                CSVFormat.DEFAULT.withFirstRecordAsHeader().
                        withIgnoreHeaderCase()
        );
        lsCPUpload.clear();
        int intCatchErrorRecord = 1;
        try {
            Iterable<CSVRecord> iterRecords = csvParser.getRecords();
            for (CSVRecord record : iterRecords) {
                Credit credit = new Credit();
                credit.setAsalRek(record.get("RekAsal"));
                credit.setNamaAsalRek(record.get("NamaRekAsal"));
                credit.setRekTujuan(record.get("RekTujuan"));
                credit.setNamaRekTujuan(record.get("NamaRekTujuan"));
                credit.setJumlah(Integer.parseInt(record.get("Jumlah")));
                credit.setKeterangan(record.get("Keterangan"));
                credit.setStatus(record.get("Status"));
                credit.setAlasan(record.get("Alasan"));
                credit.setTglEfektif(record.get("TanggalEfektif"));
                lsCPUpload.add(credit);
                intCatchErrorRecord++;
            }

        } catch (Exception ex) {
            strExceptionArr[1]="csvToPersonal(InputStream inputStream) --- LINE 175"+ex.getMessage()+" Error Record at Line "+intCatchErrorRecord;
            LoggingFile.exceptionStringz(strExceptionArr,ex, OtherConfig.getFlagLogging());
            throw new Exception(ex.getMessage()+" Error Record at Line "+intCatchErrorRecord);
        }
        finally {
            if (!csvParser.isClosed()) {
                csvParser.close();
            }
        }
        return lsCPUpload;
    }

}
