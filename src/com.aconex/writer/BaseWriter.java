/**
 * Created by meghanaharidev
 */

package com.aconex.writer;

import java.io.IOException;

/**
 * Base interface for Writer - implement for customized behaviour for writing entity type T
 * @param <T>
 */
public interface BaseWriter<T> {

    void parentNode(T element) throws IOException;
    void childNode(T element) throws IOException;
    void endNode(T element) throws IOException;
}