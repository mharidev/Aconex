/**
 * Created by meghanaharidev
 */

package com.aconex.parser;

import com.aconex.entity.Node;
import com.aconex.exception.GEDCOMException;
import com.aconex.utils.Common;
import org.junit.*;
import org.junit.rules.ExpectedException;

/**
 * Test cases for parser NodeParser
 */
public class NodeParserTestCases extends Assert {

    @Rule
    public ExpectedException exceptions = ExpectedException.none();

    private static NodeParser nodeParser;

    @BeforeClass
    public static void BeforeClass() {
        nodeParser = new NodeParser(Common.DELIMITER);
    }

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void testEmptyLineParsing() throws GEDCOMException {
        System.out.println("Running NodeParserTestCases::testEmptyLineParsing");
        Node node = nodeParser.extractInfo("");
        assertEquals(node.getLevel(), 0);
        assertEquals(node.getId(), null);
        assertEquals(node.getTag(), null);
        assertEquals(node.getValue(), null);
    }

    @Test
    public void testIdParsing() throws GEDCOMException {
        System.out.println("Running NodeParserTestCases::testIdParsing");
        Node node = nodeParser.extractInfo("0 @I0001@ INDI");
        assertEquals(node.getLevel(), 0);
        assertEquals(node.getId(), "@I0001@");
        assertEquals(node.getTag(), "indi");
        assertEquals(node.getValue(), null);
    }

    @Test
    public void testIdWithSpacesParsing() throws GEDCOMException {
        System.out.println("Running NodeParserTestCases::testIdWithSpacesParsing");
        Node node = nodeParser.extractInfo("0        @I0001@      NOTE");
        assertEquals(node.getLevel(), 0);
        assertEquals(node.getId(), "@I0001@");
        assertEquals(node.getTag(), "note");
        assertEquals(node.getValue(), null);
    }

    @Test
    public void testTagExceedingLimitParsing()throws GEDCOMException {
        System.out.println("Running NodeParserTestCases::testTagExceedingLimitParsing");
        exceptions.expect(GEDCOMException.class);
        Node node = nodeParser.extractInfo("0 @I0001@ INDITEST");
    }

    @Test
    public void testTagExceedingLimitWithSpaceParsing()throws GEDCOMException {
        System.out.println("Running NodeParserTestCases::testTagExceedingLimitWithSpaceParsing");
        exceptions.expect(GEDCOMException.class);
        Node node = nodeParser.extractInfo("0 @I0001@ INDI TEST");
    }

    @Test
    public void testTagBelowLimitParsing()throws GEDCOMException {
        System.out.println("Running NodeParserTestCases::testTagBelowLimitParsing");
        exceptions.expect(GEDCOMException.class);
        Node node = nodeParser.extractInfo("0 @I0001@ IN");
    }

    @Test
    public void testTagExceedingLimitButWithSpaceParsing() throws GEDCOMException {
        System.out.println("Running NodeParserTestCases::testTagExceedingLimitButWithSpaceParsing");
        Node node = nodeParser.extractInfo("0     @I0001@      INDI   ");
        assertEquals(node.getLevel(), 0);
        assertEquals(node.getId(), "@I0001@");
        assertEquals(node.getTag(), "indi");
        assertEquals(node.getValue(), null);
    }

    @Test
    public void testInvalidIdTagParsing() throws GEDCOMException {
        System.out.println("Running NodeParserTestCases::testInvalidIdTagParsing");
        exceptions.expect(GEDCOMException.class);
        Node node = nodeParser.extractInfo("0 @I0001 INDI");
    }

    @Test
    public void testInvalidIntegerLevelSameAsRootParsing() throws GEDCOMException {
        System.out.println("Running NodeParserTestCases::testInvalidIntegerLevelSameAsRootParsing");
        exceptions.expect(GEDCOMException.class);
        Node node = nodeParser.extractInfo(Common.ROOT_LEVEL + " INDI");
    }

    @Test
    public void testInvalidIntegerLevelLessThanRootParsing() throws GEDCOMException {
        System.out.println("Running NodeParserTestCases::testInvalidIntegerLevelLessThanRootParsing");
        exceptions.expect(GEDCOMException.class);
        Node node = nodeParser.extractInfo((Common.ROOT_LEVEL - 1) + " INDI");
    }

    @Test
    public void testInvalidLevelParsing() throws GEDCOMException {
        System.out.println("Running NodeParserTestCases::testInvalidLevelParsing");
        exceptions.expect(GEDCOMException.class);
        Node node = nodeParser.extractInfo("X INDI");
    }

    @Test
    public void testTagParsing() throws GEDCOMException {
        System.out.println("Running NodeParserTestCases::testTagParsing");
        Node node = nodeParser.extractInfo("2 DATE 1895");
        assertEquals(node.getLevel(), 2);
        assertEquals(node.getId(), null);
        assertEquals(node.getTag(), "date");
        assertEquals(node.getValue(), "1895");
    }

    @Test
    public void testTagWithValueParsing() throws GEDCOMException {
        System.out.println("Running NodeParserTestCases::testTagWithValueParsing");
        Node node = nodeParser.extractInfo("1 FAMS @F0002@");
        assertEquals(node.getLevel(), 1);
        assertEquals(node.getId(), null);
        assertEquals(node.getTag(), "fams");
        assertEquals(node.getValue(), "@F0002@");
    }

    @Test
    public void testTagExtendedValueParsing() throws GEDCOMException {
        System.out.println("Running NodeParserTestCases::testTagExtendedValueParsing");
        Node node = nodeParser.extractInfo("1 NAME George VI");
        assertEquals(node.getLevel(), 1);
        assertEquals(node.getId(), null);
        assertEquals(node.getTag(), "name");
        assertEquals(node.getValue(), "George VI");
    }

    @Test
    public void testTagExtendedValueWithSpaceParsing()throws GEDCOMException {
        System.out.println("Running NodeParserTestCases::testTagExtendedValueWithSpaceParsing");
        Node node = nodeParser.extractInfo("1 NAME George VI   ");
        assertEquals(node.getLevel(), 1);
        assertEquals(node.getId(), null);
        assertEquals(node.getTag(), "name");
        assertEquals(node.getValue(), "George VI   ");
    }

    @Test
    public void testTagNoValueParsing() throws GEDCOMException {
        System.out.println("Running NodeParserTestCases::testTagNoValueParsing");
        Node node = nodeParser.extractInfo("1 BIRT");
        assertEquals(node.getLevel(), 1);
        assertEquals(node.getId(), null);
        assertEquals(node.getTag(), "birt");
        assertEquals(node.getValue(), null);
    }

    @After
    public void tearDown() throws Exception {
    }

    @AfterClass
    public static void AfterClass() {

    }

}
