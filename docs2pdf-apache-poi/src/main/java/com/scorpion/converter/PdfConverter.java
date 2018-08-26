package com.scorpion.converter;

import com.scorpion.converter.utils.FileHelper;
import org.apache.poi.xwpf.converter.pdf.PdfOptions;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;

/**
 * Created by KAI on 8/25/18.
 * Copyright 2018 by docs-to-pdf-parrent
 * All rights reserved.
 */
public class PdfConverter {

    public static final Logger LOGGER = LoggerFactory.getLogger(DocsToPdfConverter.class);

    private String path;
    private static PdfConverter pdfConverter = null;
    private InputStream inputStream;


    public static PdfConverter asConverter() {
        if (pdfConverter == null)
            return new PdfConverter();
        return pdfConverter;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public PdfConverter path(String path) {
        this.path = path;
        return this;
    }


    public void convert() throws IOException {
        this.inputStream = new FileInputStream(new File(this.path));
        // Load DOCX into XWPFDocument
        try (XWPFDocument document = new XWPFDocument(this.inputStream)) {
            // Prepare Pdf options
            PdfOptions options = PdfOptions.create();
            // Convert XWPFDocument to Pdf
            try (OutputStream out = new FileOutputStream(new File(
                    FileHelper.generateDestinationFileName(this.path, "pdf")))) {
                org.apache.poi.xwpf.converter.pdf.PdfConverter.getInstance().convert(document, out, options);
            }
        }

    }

}
