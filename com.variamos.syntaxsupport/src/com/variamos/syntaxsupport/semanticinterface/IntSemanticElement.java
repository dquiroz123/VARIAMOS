package com.variamos.syntaxsupport.semanticinterface;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import com.variamos.syntaxsupport.metamodelsupport.AbstractAttribute;

/**
 * An interface for SemanticElement class, required to avoid cyclic
 * references in projects. Part of PhD work at University of Paris 1
 * 
 * @author Juan C. Mu�oz Fern�ndez <jcmunoz@gmail.com>
 * @version 1.1
 * @since 2014-11-24
 * @see com.variamos.refas.core.sematicsmetamodel.SemanticElement
 */
public interface IntSemanticElement extends Serializable {

	String getIdentifier();

	AbstractAttribute getSemanticAttribute(String attributeName);

	void setIdentifier(String value);
	
	public Map<String,AbstractAttribute> getSemanticAttributes();

	IntSemanticElement getParent();

}
