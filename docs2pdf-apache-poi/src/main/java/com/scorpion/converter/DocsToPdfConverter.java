package com.scorpion.converter;

import com.scorpion.converter.utils.FileHelper;
import org.docx4j.Docx4J;
import org.docx4j.convert.out.HTMLSettings;
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
public class DocsToPdfConverter {
    public static final Logger LOGGER = LoggerFactory.getLogger(DocsToPdfConverter.class);

    private String path;
    private static DocsToPdfConverter docsToPdfConverter = null;
    private InputStream inputStream;

    public static DocsToPdfConverter asConverter() {
        if (docsToPdfConverter == null)
            return new DocsToPdfConverter();
        return docsToPdfConverter;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public DocsToPdfConverter path(String path) {
        this.path = path;
        return this;
    }

    public void convert() throws Docx4JException, IOException {
        this.inputStream = new FileInputStream(new File(this.path));
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage
                .load(this.inputStream);
        String outputPdf = FileHelper.generateDestinationFileName(this.path, "pdf");
        String outputHtml = FileHelper.generateDestinationFileName(this.path, "html");
        try (OutputStream outputStream = new FileOutputStream(new File(FileHelper.baseDir + File.separator + outputPdf))) {
            Docx4J.toPDF(wordMLPackage, outputStream);
        }

        HTMLSettings htmlSettings = new HTMLSettings();
        htmlSettings.setWmlPackage(wordMLPackage);
        try (OutputStream outputStream = new FileOutputStream(new File(FileHelper.baseDir + File.separator + outputHtml))) {
            Docx4J.toHTML(htmlSettings, outputStream, 2);
        }
        LOGGER.info(FileHelper.baseDir + File.separator + outputPdf);
    }
}
