package com.scorpion;

import com.scorpion.converter.MammothDocs2PdfConverter;
import com.scorpion.converter.cloudmersive.CloudmersiveDocs2PdfConverter;
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
        CloudmersiveDocs2PdfConverter.asConverter().path(input).convert();
    }

}
