/**
 * Created by meghanaharidev
 */

package com.aconex.parser;
import com.aconex.exception.GEDCOMException;

/**
 * Base interface for Parser - implement for customized parser behaviour and entity type T
 * @param <T>
 */
public interface BaseParser<T> {

    T extractInfo(String line) throws GEDCOMException;
}
