/**
 * Created by meghanaharidev
 */

package com.aconex.transformer;

import com.aconex.exception.GEDCOMException;
import com.aconex.utils.Common;
import org.junit.*;

import java.io.*;

public class GEDCOMToXMLTransformerTestCases extends Assert {

    private static String inputFile;
    private static String outputFile;
    private static String resultFile;
    private static File fileOutput;

    public static void convertValidate( String iFile,
                                        String oFile,
                                        String rFile ) throws IOException, GEDCOMException {

        inputFile = Common.RESOURCES_ROOT + iFile;
        outputFile = Common.RESOURCES_ROOT + oFile;
        resultFile = Common.RESOURCES_ROOT + rFile;

         fileOutput = new File(outputFile);

        // Delete output xml file if it already exists
        if (fileOutput.exists()) {
            fileOutput.delete();
        }

        // Conver from GEDCOM input file to XML output file
        GEDCOMToXMLTransformer gedcomToXMLTransformer = new GEDCOMToXMLTransformer(inputFile, outputFile);
        gedcomToXMLTransformer.transform();

        assertTrue("Files should be same", Common.fileCompare(outputFile,resultFile));

    }



    @BeforeClass
    public static void BeforeClass() {

    }

    @Before
    public void setUp() throws Exception {

    }


    @Test
    public void testFileExistsCanRead() throws FileNotFoundException {
        System.out.println("Running GEDCOMToXMLTransformerTestCases::testFileExistsCanRead");

        BufferedReader reader = new BufferedReader(new FileReader(Common.RESOURCES_ROOT + "GEDCOMParserSample.txt"));
        assertNotNull(reader);
    }

    /**
     * Blank lines should be ignored - requirement. Verify for Empty file
     * @throws IOException
     * @throws GEDCOMException
     */
    @Test
    public void testEmptyFile() throws IOException, GEDCOMException {
        System.out.println("Running GEDCOMToXMLTransformerTestCases::testEmptyFile");

        convertValidate("EmptyFileInput.txt","EmptyFileOutput.xml","EmptyFileExpected.xml");
    }

    /**
     * Blank lines should be ignored - requirement. Verify for Empty file
     * @throws IOException
     * @throws Exception
     */
    @Test
    public void testBlankLines() throws IOException, Exception {
        System.out.println("Running GEDCOMToXMLTransformerTestCases::testBlankLines");

        convertValidate("BlankLinesInput.txt","BlankLinesOutput.xml","BlankLinesExpected.xml");
    }

    /**
     * Variable White Space between Level and Tag which should be ignored - requirement
     * @throws IOException
     * @throws Exception
     */
    @Test
    public void testVariableWhiteSpaces() throws IOException, Exception {
        System.out.println("Running GEDCOMToXMLTransformerTestCases::testVariableWhiteSpaces");

        convertValidate("VariableWhiteSpacesInput.txt",
                        "VariableWhiteSpacesOutput.xml",
                        "VariableWhiteSpacesExpected.xml");
    }

    /**
     * Test Example Input given in requirement
     * @throws IOException
     * @throws Exception
     */
    @Test
    public void testValues() throws IOException, Exception {
        System.out.println("Running GEDCOMToXMLTransformerTestCases::testValues");

        convertValidate("ExampleDataInput.txt","ExampleDataOutput.xml","ExampleDataExpected.xml");
    }

    /**
     * Test Sample GEDCOM data fiel included in challenge
     * @throws IOException
     * @throws Exception
     */
    @Test
    public void testConvertGEDCOMToXML() throws IOException, Exception {
        System.out.println("Running GEDCOMToXMLTransformerTestCases::testConvertGEDCOMToXML");

        convertValidate("GEDCOMParserSample.txt","GEDCOMParserOutput.xml","GEDCOMParserExpected.xml");
    }

    @After
    public void tearDown() throws Exception {

        System.out.println("Created output GEDCOM XML file here: " + fileOutput.getAbsolutePath());
    }

    @AfterClass
    public static void AfterClass() {
    }

}
