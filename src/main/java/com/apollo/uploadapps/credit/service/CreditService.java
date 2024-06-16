package com.apollo.uploadapps.credit.service;

import com.apollo.uploadapps.credit.config.OtherConfig;
import com.apollo.uploadapps.credit.entity.Credit;
import com.apollo.uploadapps.credit.handler.ResourceNotFoundException;
import com.apollo.uploadapps.credit.handler.ResponseHandler;
import com.apollo.uploadapps.credit.repository.CreditRepository;
import com.apollo.uploadapps.credit.utils.ConstMessage;
import com.apollo.uploadapps.credit.utils.LoggingFile;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CreditService {


    private final CreditRepository creditRepository;

    private String [] strExceptionArr = new String[2];

    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<Object> saveUploadFile(List<Credit> listCredit,
                                                 MultipartFile multipartFile,
                                                 WebRequest request) throws Exception {

        String strMessage = ConstMessage.SUCCESS_SAVE;
        List<Credit> listCreditResult = new ArrayList<>();
        try
        {
            listCreditResult = creditRepository.saveAll(listCredit);
            if(listCreditResult.size() == 0)
            {
                strExceptionArr[1]="saveUploadFile(List<Genre> listGenre, MultipartFile multipartFile, WebRequest request) --- LINE 74";
                LoggingFile.exceptionStringz(strExceptionArr,new ResourceNotFoundException("FILE KOSONG"), OtherConfig.getFlagLogging());
                return new ResponseHandler().generateResponse(ConstMessage.ERROR_EMPTY_FILE +" -- "+multipartFile.getOriginalFilename(),
                        HttpStatus.BAD_REQUEST,null,"FI07021",request);
            }
        }
        catch (Exception e)
        {
            strMessage = e.getMessage();
            strExceptionArr[1]="saveUploadFile(List<Genre> listGenre, MultipartFile multipartFile, WebRequest request) --- LINE 83";
            LoggingFile.exceptionStringz(strExceptionArr,e, OtherConfig.getFlagLogging());
            return new ResponseHandler().generateResponse(strMessage,
                    HttpStatus.BAD_REQUEST,null,"FI07022",request);
        }
        return new ResponseHandler().generateResponse(strMessage,
                HttpStatus.CREATED,null,null,request);
    }

}
