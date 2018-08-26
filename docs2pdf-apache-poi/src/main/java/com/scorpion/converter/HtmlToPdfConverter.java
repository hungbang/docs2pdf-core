package com.scorpion.converter;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.tool.xml.XMLWorkerHelper;
import com.scorpion.converter.utils.FileHelper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by KAI on 8/25/18.
 * Copyright 2018 by docs-to-pdf-parrent
 * All rights reserved.
 */
public class HtmlToPdfConverter {
    public static final Logger LOGGER = LoggerFactory.getLogger(HtmlToPdfConverter.class);

    private String path;
    private static HtmlToPdfConverter htmlToPdfConverter = null;
    private InputStream inputStream;

    public static HtmlToPdfConverter asConverter() {
        if (htmlToPdfConverter == null)
            return new HtmlToPdfConverter();
        return htmlToPdfConverter;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public HtmlToPdfConverter path(String path) {
        this.path = path;
        return this;
    }

    public void convert() throws IOException, DocumentException {
        this.inputStream = new FileInputStream(this.path);
        Document document = new Document();
        PdfWriter writer = PdfWriter.getInstance(document,
                new FileOutputStream(FileHelper.generateDestinationFileName(this.path, "pdf")));
        document.open();
        XMLWorkerHelper.getInstance().parseXHtml(writer, document,this.inputStream);
        document.close();
    }

}
