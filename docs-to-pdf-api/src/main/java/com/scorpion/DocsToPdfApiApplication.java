package com.scorpion;

import com.scorpion.converter.DocsToHtmlConverter;
import com.scorpion.converter.DocsToPdfConverter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DocsToPdfApiApplication {

    public static void main(String[] args) throws Exception {
        convertWithDocsToPdf();
        SpringApplication.run(DocsToPdfApiApplication.class, args);
    }

    public static void convertWithDocsToPdf() throws Exception {
        String input = "/Users/KAI/Downloads/temp14_20-Aug-2018_09-49-22-497.docx";
        String path = DocsToHtmlConverter.asConverter().path(input).convert();
        DocsToPdfConverter.asConverter().path(path).convert();
    }




}
