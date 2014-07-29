package com.cfm.productline.editor;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.cfm.productline.VariabilityElement;
import com.cfm.productline.Variable;
import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;

public class PaletteDatabase {
	static class PaletteElement{
		public String name;
		public String icon;
		public String styleName;
		public int width;
		public int height;
	}
	
	@SuppressWarnings("serial")
	static class ScriptedVariabilityElement extends VariabilityElement{
		@Override
		public Variable[] getEditableVariables() {
			Collection<Variable> v = vars.values();
			return v.toArray (new Variable[v.size ()]);
		}
	}
	
	static class PaletteNode extends PaletteElement{
		public ScriptedVariabilityElement prototype;
	}
	
	static class PaletteEdge extends PaletteElement{
		public Object value;
	}
	
	static class PaletteDefinition{
		public String name;
		public List<PaletteNode> nodes = new ArrayList<>();
		public List<PaletteEdge> edges = new ArrayList<>();
	}
	
	public List<PaletteDefinition> palettes = new ArrayList<>();
	
	static class NaturalDeserializer implements JsonDeserializer<Object> {
		  public Object deserialize(JsonElement json, Type typeOfT, 
		      JsonDeserializationContext context) {
		    if(json.isJsonNull()) return null;
		    else if(json.isJsonPrimitive()) return handlePrimitive(json.getAsJsonPrimitive());
		    else if(json.isJsonArray()) return handleArray(json.getAsJsonArray(), context);
		    else return handleObject(json.getAsJsonObject(), context);
		  }
		  private Object handlePrimitive(JsonPrimitive json) {
		    if(json.isBoolean())
		      return json.getAsBoolean();
		    else if(json.isString())
		      return json.getAsString();
		    else {
		      BigDecimal bigDec = json.getAsBigDecimal();
		      // Find out if it is an int type
		      try {
		        bigDec.toBigIntegerExact();
		        try { return bigDec.intValueExact(); }
		        catch(ArithmeticException e) {}
		        return bigDec.longValue();
		      } catch(ArithmeticException e) {}
		      // Just return it as a double
		      return bigDec.doubleValue();
		    }
		  }
		  private Object handleArray(JsonArray json, JsonDeserializationContext context) {
		    Object[] array = new Object[json.size()];
		    for(int i = 0; i < array.length; i++)
		      array[i] = context.deserialize(json.get(i), Object.class);
		    return array;
		  }
		  private Object handleObject(JsonObject json, JsonDeserializationContext context) {
		    Map<String, Object> map = new HashMap<String, Object>();
		    for(Map.Entry<String, JsonElement> entry : json.entrySet())
		      map.put(entry.getKey(), context.deserialize(entry.getValue(), Object.class));
		    return map;
		  }
		}
}