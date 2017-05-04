/**
 * Created by meghanaharidev
 */

package com.aconex.transformer;

import com.aconex.exception.GEDCOMException;
import com.aconex.parser.BaseParser;
import com.aconex.writer.BaseWriter;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Stack;

/**
 * Abstract base class for Transformer - extend for customized writer, parser
 * @param <T>
 */
public abstract class BaseTransformer<T>
{
    protected BufferedReader reader;
    protected BaseWriter<T> writer ;
    protected BaseParser<T> parser;
    protected Stack<T> stack;

    /**
     * Constructor - initializing reader, writer, parser and creating stack of entity type T
     * @throws IOException
     *
     */
    protected BaseTransformer(String inputFile, BaseWriter writer, BaseParser parser) throws IOException {
        this.reader = new BufferedReader(new FileReader(inputFile));
        this.writer = writer;
        this.parser = parser;
        this.stack =  new Stack<T>();
    }

    public BufferedReader getReader() {
        return reader ;
    }

    public BaseWriter<T> getWriter() {
        return writer;
    }

    public BaseParser<T> getParser() {
        return parser;
    }

    public Stack<T> getStack() {
        return stack;
    }

    /**
     * Transforms from X to Y format - override for customized implementation
     * @return
     * @throws IOException
     * @throws GEDCOMException
     */
    abstract boolean transform() throws IOException, GEDCOMException;
}
