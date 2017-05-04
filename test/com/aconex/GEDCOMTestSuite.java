/**
 * Created by meghanaharidev
 */

package com.aconex;

import com.aconex.entity.NodeTestCases;
import com.aconex.parser.NodeParserTestCases;
import com.aconex.transformer.GEDCOMToXMLTransformerTestCases;
import com.aconex.writer.XMLWriterTestCases;
import org.junit.runner.JUnitCore;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        NodeTestCases.class,
        NodeParserTestCases.class,
        XMLWriterTestCases.class,
        GEDCOMToXMLTransformerTestCases.class
})

public class GEDCOMTestSuite {

    public static void main(String[] args) {

        JUnitCore.runClasses(new Class[] { GEDCOMTestSuite.class });
    }
}