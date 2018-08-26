package com.scorpion.converter;

import com.scorpion.converter.utils.FileHelper;
import com.scorpion.exception.Docs2PdfConverterException;
import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zwobble.mammoth.DocumentConverter;
import org.zwobble.mammoth.Result;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import static com.scorpion.converter.utils.FileHelper.baseDir;

/**
 * Created by KAI on 8/26/18.
 * Copyright 2018 by docs-to-pdf-parrent
 * All rights reserved.
 */
public class MammothDocs2PdfConverter {
    public static final Logger LOGGER = LoggerFactory.getLogger(MammothDocs2PdfConverter.class);

    private String path;
    private static MammothDocs2PdfConverter htmlToPdfConverter = null;
    private InputStream inputStream;

    public static MammothDocs2PdfConverter asConverter() {
        if (htmlToPdfConverter == null)
            return new MammothDocs2PdfConverter();
        return htmlToPdfConverter;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public MammothDocs2PdfConverter path(String path) {
        this.path = path;
        return this;
    }

    public void convert() throws Docs2PdfConverterException {
        DocumentConverter converter = new DocumentConverter();
        converter.preserveEmptyParagraphs();
        try {
            Result<String> result = converter.convertToHtml(new File(this.path));
            FileUtils.writeStringToFile(new File(baseDir + File.separator + FileHelper.generateDestinationFileName(this.path, "html")), result.getValue(), "UTF-8");
            Set<String> warnings = result.getWarnings();
            warnings.forEach(LOGGER::info);
        } catch (IOException e) {
            LOGGER.error(getClass().getSimpleName(), e);
            throw new Docs2PdfConverterException();
        }

    }

}
