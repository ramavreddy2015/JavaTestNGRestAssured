package helpers;

import java.util.Comparator;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.TextNode;

public class JSONComparator implements Comparator<JsonNode> 
{
    public int compare(JsonNode o1, JsonNode o2) {
        if (o1.equals(o2)) {
            return 0;
        }
        if ((o1 instanceof TextNode) && (o2 instanceof TextNode)) {
            String s1 = ((TextNode) o1).asText();
            String s2 = ((TextNode) o2).asText();
            if (s1.equalsIgnoreCase(s2)) {
                return 0;
            }
        }
        return 1;
    }
}
