package com.scorpion.factory;

import main.java.com.scorpion.docstopdfconverter.Converter;
import main.java.com.scorpion.docstopdfconverter.DocToPDFConverter;
import main.java.com.scorpion.docstopdfconverter.DocxToPDFConverter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by KAI on 8/25/18.
 * Copyright 2018 by docs-to-pdf-parrent
 * All rights reserved.
 */
public class ConverterFactory {
    public static final Logger LOGGER = LoggerFactory.getLogger(ConverterFactory.class);

    private static ConverterFactory converterFactory = null;
    private InputStream inStream;
    private OutputStream outStream;
    private boolean shouldShowMessages;
    private DocType docType;

    public static ConverterFactory asConverter() {
        if (converterFactory == null)
            return new ConverterFactory();
        return converterFactory;
    }

    public Converter build() {
        Converter converter;

        switch (docType) {
            case DOC:
                converter = new DocToPDFConverter(inStream, outStream, shouldShowMessages, true);
                break;
            case DOCX:
                converter = new DocxToPDFConverter(inStream, outStream, shouldShowMessages, true);
                break;
            default:
                converter = null;
                break;
        }
        return converter;
    }

    public ConverterFactory setInStream(InputStream inStream) {
        this.inStream = inStream;
        return this;
    }

    public ConverterFactory setOutStream(OutputStream outStream) {
        this.outStream = outStream;
        return this;
    }

    public ConverterFactory setShouldShowMessages(boolean shouldShowMessages) {
        this.shouldShowMessages = shouldShowMessages;
        return this;
    }

    public ConverterFactory setDocType(DocType docType) {
        this.docType = docType;
        return this;
    }
}