package bgshoprental.util;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;
import org.springframework.web.context.WebApplicationContext;

import bgshoprental.entity.InternalOrderElement;

@Component
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class InternalOrderBuilder {
	private List<InternalOrderElement> elements;
	
	public InternalOrderBuilder() {
		elements = new ArrayList<>();
	}
	
	public void addElement(InternalOrderElement elementToAdd) {
		elements.add(elementToAdd);
	}

	public List<InternalOrderElement> getElements() {
		return elements;
	}
	
}
