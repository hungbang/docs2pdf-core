package com.scorpion.converter;

import com.scorpion.exception.Docs2PdfConverterException;
import org.docx4j.openpackaging.exceptions.Docx4JException;

import java.io.IOException;

/**
 * Created by KAI on 8/26/18.
 * Copyright 2018 by docs-to-pdf-parrent
 * All rights reserved.
 */
public interface Converter {
    String convert() throws Docs2PdfConverterException;
}
