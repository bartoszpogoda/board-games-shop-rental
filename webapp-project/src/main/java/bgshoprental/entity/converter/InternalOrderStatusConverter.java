package bgshoprental.entity.converter;

import javax.persistence.AttributeConverter;

import bgshoprental.entity.InternalOrderStatus;

public class InternalOrderStatusConverter implements AttributeConverter<InternalOrderStatus, String> {

	@Override
	public String convertToDatabaseColumn(InternalOrderStatus attribute) {
		switch (attribute) {
		case CREATED:
			return "Utworzone";
		case READY:
			return "Do odbioru";
		case REALIZED:
			return "Zrealizowane";
		case CANCELED:
			return "Anulowane";
		}
		return "";
	}

	@Override
	public InternalOrderStatus convertToEntityAttribute(String dbData) {
		if (dbData.equalsIgnoreCase("Utworzone")) {
			return InternalOrderStatus.CREATED;
		} else if (dbData.equalsIgnoreCase("Do odbioru")) {
			return InternalOrderStatus.READY;
		} else if (dbData.equalsIgnoreCase("Zrealizowane")) {
			return InternalOrderStatus.REALIZED;
		} else if (dbData.contains("Anulowane")) {
			return InternalOrderStatus.CANCELED;
		}
		return null;
	}

}
