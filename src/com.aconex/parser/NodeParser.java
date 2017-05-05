/**
 * Created by meghanaharidev
 */

package com.aconex.parser;

import com.aconex.entity.Node;
import com.aconex.exception.GEDCOMException;
import com.aconex.utils.Common;

/**
 * Implements the logic for parsing input line to node info with validation
 */
public class NodeParser implements BaseParser<Node> {

    protected String delimiter;
    protected Node node;

    public NodeParser(String delimiter) {
        this.delimiter = delimiter;
    }

    /**
     * Extracts level, id, tag, value from given line to a node
     * @param line
     *          Single line to parse
     * @return Node
     *          New node with populated values
     * @throws GEDCOMException
     *
     */
    public Node extractInfo(String line) throws GEDCOMException
    {
        int level;
        String tag;
        node = new Node();

        if (line == null || line.isEmpty()) {
            return node;
        }

        // Ignoring whitespaces and extracting the tokens
        String[] tokens = line.split(delimiter, 3);

        // Validate level
        if(!tokens[0].matches(Common.LEVEL_PATTERN)) {
            throw new GEDCOMException("Invalid level");
        }

        // Get level
        level = Integer.parseInt(tokens[0]);

        // Validate level is not less than/equal to root level
        if(level <= Common.ROOT_LEVEL ) {
            throw new GEDCOMException("Invalid level");
        }

        // Set level after validation
        node.setLevel(level);

        // Extract id, tag, value
        if(tokens[1].startsWith("@") && tokens[1].endsWith("@")) {
            node.setId(tokens[1]);
            tag = tokens[2];
        }
        else if(tokens.length == 3) {
            // We have LEVEL, ID/TAG and VALUE here
            tag = tokens[1];
            node.setValue(tokens[2]);
        }
        else  {
            tag = tokens[1];
        }

        //Trim
        tag = tag.trim();

        // Validate tag
        if (!isValidTag(tag)) {
            throw new GEDCOMException("Invalid Tag");
        }

        // Set tag after validation
        node.setTag(tag.toLowerCase());
        return node;
    }

    /**
     * Validates the tag
     * @param tag
     * @return boolean
     * @throws GEDCOMException
     *
     */
    public boolean isValidTag(String tag) {
            //Validating for letters, ensuring length of 3 or 4
            return (tag.matches(Common.TAG_PATTERN));
    }
}
