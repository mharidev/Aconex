/**
 * Created by meghanaharidev
 */

package com.aconex.entity;

import com.aconex.utils.Common;

/**
 * Entity represents a Node with LEVEL, ID, TAG and VALUE
 */
public class Node {

    protected int level;
    protected String id;
    protected String tag;
    protected String value;

    public Node() {
        this.level = 0;
        this.id = null;
        this.tag = null;
        this.value = null;
    }

    public Node(int level, String id, String tag, String value) {
        this.level = level;
        this.id = id;
        this.tag = tag;
        this.value = value;
    }
    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getValue()
    {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public boolean isRootNode() {
        return level == Common.ROOT_LEVEL;
    }

}