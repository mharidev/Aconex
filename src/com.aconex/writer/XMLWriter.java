/**
 * Created by meghanaharidev
 */

package com.aconex.writer;

import com.aconex.entity.Node;
import com.aconex.utils.Common;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

public class XMLWriter implements com.aconex.writer.BaseWriter<Node> {

    private Writer out;

    /**
     * Constructor - initializes output writer
     * @param outputFile
     *          Output filename
     * @throws IOException
     *
     */
    public XMLWriter(String outputFile) throws IOException {
        if ( outputFile != null ) {
            out = new BufferedWriter(new FileWriter(outputFile));
        }
    }

    /**
     * Writes parent node details to outputFile
     * @param node
     *          Parent node with tag - could also have id, value
     * @throws IOException
     *
     */    
    public void parentNode(Node node) throws IOException {
        beautify(node);
        out.write("<" + node.getTag());

        if( node.getId() != null ) {
            out.write(" id=\"" + node.getId() + "\"");
        }
        if( node.getValue() != null ) {
            out.write(" value=\"" + node.getValue()+ "\"");
        }
        out.write(">\n");
    }

    /**
     * Writes child node details to outputFile
     * @param node
     *          Child node with tag - could also have value
     * @throws IOException
     *
     */    
    public void childNode(Node node) throws IOException {
        beautify(node);
        out.write("<" + node.getTag() + ">");

        if ( node.getValue() != null ) {
            out.write(node.getValue());
        }
        out.write("</" + node.getTag() + ">\n");
    }

    /**
     * Writes end node tag
     * @param node
     *          Last node
     * @throws IOException
     *
     */   
    public void endNode(Node node) throws IOException {
        beautify(node);
        out.write("</" + node.getTag() + ">\n");
        out.flush();
    }

    /**
     * Flush output
     * @throws IOException
     */
    public void flush() throws IOException {
        out.flush();
    }
    /**
     * Beautify/indent XML line based on node level
     * @param node
     *          Node to be indented
     * @throws IOException
     *
     */
    protected void beautify(Node node) throws IOException {
        for ( int i = 0 ; i<= node.getLevel(); i++ ) {
            out.write (Common.INDENT);
        }
    }
}
