/**
 * Created by meghanaharidev
 */

package com.aconex.writer;

import com.aconex.entity.Node;
import com.aconex.exception.GEDCOMException;
import com.aconex.parser.NodeParser;
import com.aconex.utils.Common;
import org.junit.*;

import java.io.IOException;

/**
 * Test writer XMLWriter
 */
public class XMLWriterTestCases extends Assert {

    private static Node node;
    private XMLWriter xmlWriter;
    private String outputFile;
    private String expectedFile;

    private static NodeParser nodeParser;

    @BeforeClass
    public static void BeforeClass() {
        nodeParser = new NodeParser(Common.DELIMITER);
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testIdParentNode() throws GEDCOMException, IOException {
        System.out.println("Running XMLWriterTestCases::testIdParentNode");

        node = nodeParser.extractInfo("0 @I0001@ INDI");
        outputFile = Common.RESOURCES_ROOT + "IdParentNodeOutput.xml";
        expectedFile = Common.RESOURCES_ROOT + "IdParentNodeExpected.xml";

        xmlWriter = new XMLWriter(outputFile);
        xmlWriter.parentNode(node);
        Common.fileCompare(outputFile, expectedFile);
    }

    @Test
    public void testTagParentNode() throws GEDCOMException, IOException {
        System.out.println("Running XMLWriterTestCases::testTagParentNode");

        node = nodeParser.extractInfo("0 TRLR");
        outputFile = Common.RESOURCES_ROOT + "TagParentNodeOutput.xml";
        expectedFile = Common.RESOURCES_ROOT + "TagParentNodeExpected.xml";

        xmlWriter = new XMLWriter(outputFile);
        xmlWriter.parentNode(node);
        Common.fileCompare(outputFile, expectedFile);
    }


    @Test
    public void testChildNode() throws GEDCOMException, IOException {
        System.out.println("Running XMLWriterTestCases::testChildNode");

        node = nodeParser.extractInfo("1 NAME Elizabeth Alexandra Mary /Windsor/");
        outputFile = Common.RESOURCES_ROOT + "ChildNodeOutput.xml";
        expectedFile = Common.RESOURCES_ROOT + "ChildNodeExpected.xml";

        xmlWriter = new XMLWriter(outputFile);
        xmlWriter.childNode(node);
        Common.fileCompare(outputFile, expectedFile);
    }

    @Test
    public void testValueChildNode() throws GEDCOMException, IOException {
        System.out.println("Running XMLWriterTestCases::testValueChildNode");

        node = nodeParser.extractInfo("1 FAMS @F0004@");
        outputFile = Common.RESOURCES_ROOT + "ValueChildNodeOutput.xml";
        expectedFile = Common.RESOURCES_ROOT + "ValueChildNodeExpected.xml";

        xmlWriter = new XMLWriter(outputFile);
        xmlWriter.childNode(node);
        Common.fileCompare(outputFile, expectedFile);
    }

    @Test
    public void testInnerChildNode() throws GEDCOMException, IOException {
        System.out.println("Running XMLWriterTestCases::testValueChildNode");

        node = nodeParser.extractInfo("2 DATE 1865");
        outputFile = Common.RESOURCES_ROOT + "InnerChildNodeOutput.xml";
        expectedFile = Common.RESOURCES_ROOT + "InnerChildNodeExpected.xml";

        xmlWriter = new XMLWriter(outputFile);
        xmlWriter.childNode(node);
        Common.fileCompare(outputFile, expectedFile);
    }

    @Test
    public void testIndentChildNode() throws GEDCOMException, IOException {
        System.out.println("Running XMLWriterTestCases::testIndentChildNode");

        node = nodeParser.extractInfo("4 CHAN");
        outputFile = Common.RESOURCES_ROOT + "IndentChildNodeOutput.xml";
        expectedFile = Common.RESOURCES_ROOT + "IndentChildNodeExpected.xml";

        xmlWriter = new XMLWriter(outputFile);
        xmlWriter.childNode(node);
        Common.fileCompare(outputFile, expectedFile);
    }

    @After
    public void tearDown() throws Exception {
        xmlWriter.flush();
    }

    @AfterClass
    public static void AfterClass() {

    }

}
