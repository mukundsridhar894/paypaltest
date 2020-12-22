package org.CustomAnnotator;

import com.fasterxml.jackson.databind.JsonNode;
import com.sun.codemodel.JDefinedClass;
import com.sun.codemodel.JFieldVar;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import org.jsonschema2pojo.AbstractAnnotator;

public class CustomizedAnnotator extends AbstractAnnotator {

    private static final String DATE_OF_BIRTH = "dateOfBirth";
	private static final String ADDRESS = "address";
	private static final String ID = "id";
	private static final String ENTITY = "entity";
	private static final String CASCADE = "cascade";
	private static final String STRATEGY = "strategy";

	@Override
    public void propertyField(JFieldVar field, JDefinedClass clazz, String propertyName, JsonNode propertyNode) {
        super.propertyField(field, clazz, propertyName, propertyNode);
        // Note: does not have to be the propertyName, could be the field or propertyNode that is verified.

        if (propertyName.equals(ID) && propertyNode.has(ENTITY)) {
        	clazz.annotate(Entity.class);
        	field.annotate(Id.class);
        	field.annotate(GeneratedValue.class).param(STRATEGY, GenerationType.IDENTITY);
        }
        if (propertyName.equals(ADDRESS) || propertyName.equals(DATE_OF_BIRTH)) {
        	field.annotate(OneToOne.class).param(CASCADE, CascadeType.ALL);
        }
    }
}