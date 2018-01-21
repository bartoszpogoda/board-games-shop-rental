package bgshoprental.entity.converter;

import javax.persistence.AttributeConverter;

import bgshoprental.entity.ExternalOrderStatus;

public class ExternalOrderStatusConverter implements AttributeConverter<ExternalOrderStatus, String> {

	@Override
	public String convertToDatabaseColumn(ExternalOrderStatus attribute) {
		switch (attribute) {
		case CANCELED:
			return "Anulowane";
		case CREATED:
			return "Utworzone";
		case REALIZED:
			return "Zrealizowane";
		}
		return "";
	}

	@Override
	public ExternalOrderStatus convertToEntityAttribute(String dbData) {
		if(dbData.equalsIgnoreCase("Anulowane")) {
			return ExternalOrderStatus.CANCELED;
		} else if(dbData.equalsIgnoreCase("Utworzone")) {
			return ExternalOrderStatus.CREATED;
		} else if(dbData.equalsIgnoreCase("Zrealizowane")) {
			return ExternalOrderStatus.REALIZED;
		}
		return null;
	}

}
