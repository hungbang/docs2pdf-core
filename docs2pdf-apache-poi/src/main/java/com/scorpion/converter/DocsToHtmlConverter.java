package com.scorpion.converter;

import com.scorpion.converter.utils.FileHelper;
import com.scorpion.exception.Docs2PdfConverterException;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.convert.out.common.Exporter;
import org.docx4j.convert.out.html.HTMLExporterVisitor;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by KAI on 8/25/18.
 * Copyright 2018 by docs-to-pdf-parrent
 * All rights reserved.
 */
public class DocsToHtmlConverter implements Converter{
    public static final Logger LOGGER = LoggerFactory.getLogger(DocsToHtmlConverter.class);

    private String path;
    public InputStream inputStream;
    private static DocsToHtmlConverter docsToHtmlConverter = null;

    public static DocsToHtmlConverter asConverter(){
        if(docsToHtmlConverter == null)
            return new DocsToHtmlConverter();
        return docsToHtmlConverter;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public DocsToHtmlConverter path(String path) throws FileNotFoundException {
        this.path = path;
        return this;
    }

    public InputStream getInputStream() {
        return inputStream;
    }

    public void convert() throws Docs2PdfConverterException {
       try{
           this.inputStream = new FileInputStream(new File(this.path));
           WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                   .load(this.inputStream);
           // 2) Prepare HTML settings
           HTMLSettings htmlSettings = new HTMLSettings();
           Exporter<HTMLSettings> exporter =  HTMLExporterVisitor.getInstance();
           htmlSettings.setWmlPackage(wordMLPackage);
           String output = FileHelper.generateDestinationFileName(this.path, "html");

           try (OutputStream outputStream = new FileOutputStream(new File(FileHelper.baseDir + File.separator + output))) {
               exporter.export(htmlSettings, outputStream);
           }
       }catch (IOException | Docx4JException e){
           LOGGER.error(getClass().getSimpleName(), e);
           throw new Docs2PdfConverterException();
       }
    }



}
