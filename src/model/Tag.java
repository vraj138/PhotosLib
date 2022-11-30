package model;

import java.io.Serializable;

public class Tag implements Serializable {
    public String tagName;
    public String value;

    public Tag(String name, String value) {
		this.tagName = name;
		this.value = value;
	}

    // we need a method to check if 2 tags are equal (both by name and by value)
    @Override
    public boolean equals(Object obj) {
        if (obj == null || obj instanceof Tag) {
            return false;
        }

        Tag t = (Tag) obj;

        return t.tagName.equals(this.tagName) && t.value.equals(this.value);
    }
}