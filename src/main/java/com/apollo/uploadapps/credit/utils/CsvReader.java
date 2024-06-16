package com.apollo.uploadapps.credit.utils;


import org.springframework.web.multipart.MultipartFile;

public class CsvReader {

    public static boolean isCsv(MultipartFile multipartFile)
    {
        return ConstMessage.CONTENT_TYPE_CSV.equals(multipartFile.getContentType());
    }
}