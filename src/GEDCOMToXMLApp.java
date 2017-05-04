/**
 * Created by meghanaharidev
 */

import com.aconex.exception.GEDCOMException;
import com.aconex.transformer.GEDCOMToXMLTransformer;

import java.io.File;
import java.io.IOException;

/**
 * Application to run the GEDCOMToXMLTransformer on provided sample input file
 */
public class GEDCOMToXMLApp
{
    public GEDCOMToXMLApp() {}

    public static void main(String[] args) throws IOException, GEDCOMException {
        if(args.length < 2)
        {
            System.out.println("Usage: java GEDCOMToXMLApp <GEDCOMInputFilename.txt> <XMLOutputFilename.xml>");
            System.exit(0);
        }

        GEDCOMToXMLTransformer gedcomToXMLTransformer = new GEDCOMToXMLTransformer(args[0], args[1]);
        if (gedcomToXMLTransformer.transform()) {

            File output = new File(args[1]);
            System.out.println("Created output GEDCOM XML file here: " + output.getAbsolutePath());
        }
    }
}