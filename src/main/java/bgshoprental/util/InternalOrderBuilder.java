package bgshoprental.util;

import java.math.BigDecimal;
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

	public void removeElement(int id) {
		elements.remove(id);
	}

	public List<InternalOrderElement> getElements() {
		return elements;
	}

	public BigDecimal calculateTotalPrice() {
		BigDecimal total = new BigDecimal(0);

		for (InternalOrderElement elem : elements) {
			total = total.add(elem.getBoardGame().getSellPrice().multiply(new BigDecimal(elem.getQuantity())));
		}

		return total;
	}

	public void clear() {
		elements.clear();
	}

	public boolean isEmpty() {
		return elements.isEmpty();
	}

}
