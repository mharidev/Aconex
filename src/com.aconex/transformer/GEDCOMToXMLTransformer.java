/**
 * Created by meghanaharidev
 */

package com.aconex.transformer;

import com.aconex.entity.Node;
import com.aconex.exception.GEDCOMException;
import com.aconex.parser.NodeParser;
import com.aconex.utils.Common;
import com.aconex.writer.XMLWriter;

import java.io.IOException;

public class GEDCOMToXMLTransformer extends BaseTransformer<Node>
{

    /**
     * Constructor - initialization oof reader, writer, parser
     * @throws IOException
     *
     */
    public GEDCOMToXMLTransformer(String inputFile, String outputFile) throws IOException {
        super(inputFile, new XMLWriter(outputFile), new NodeParser(Common.DELIMITER));
    }

    /**
     * Overridden to convert to XML using Node entities
     * @return
     * @throws GEDCOMException
     * @throws IOException
     */
    @Override
    public boolean transform() throws GEDCOMException, IOException
    {
        Node curr;
        String line = null;
        Boolean isSuccess = false;

        //Start by creating new root node
        Node prev = new Node(Common.ROOT_LEVEL, null, Common.ROOT_TAG, null);

        while (true) {
            // Read line from input file
            line = getReader().readLine() ;

            if ( line != null && line.trim().compareTo(Common.EMPTY_STRING) == 0 ) {
                // Skip blank lines
                continue ;
            }

            if ( line == null ) {
                // End of file - condition to complete the current node and break from loop
                writer.childNode(prev);
                break;
            }

            //Parse line to get node info
            curr = parser.extractInfo(line);

            // Previous Level is < Current Level
            if ( getStack().empty() || prev.getLevel() < curr.getLevel() ) {
                // Process as parent node and push on to stack as we need to close the tag as parent later
                writer.parentNode(prev);
                getStack().push(prev);
            }
            else if ( prev.getLevel() == curr.getLevel() ) {
                // Previous Level is == Current Level
                // Process as child node, no need to push to stack
                writer.childNode(prev);
            }
            else if ( prev.getLevel() > curr.getLevel() ) {
                // Previous Level is > Current Level
                // Process as child node
                writer.childNode(prev);

                // Process the nodes on the stack till current level <= level of node on top of stack
                while ( (!getStack().empty() &&
                        (curr.getLevel() <= getStack().peek().getLevel())) ) {
                    writer.endNode(getStack().pop());
                }
            }
            // Make curr line as prev
            prev = curr ;
        }

        // Process all nodes on the stack till empty to close all parent tags
        while(!getStack().empty())
        {
            writer.endNode(getStack().pop());
        }

        isSuccess = true;
        return isSuccess;
    }
}