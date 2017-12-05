package bgshoprental.entity.converter;

import javax.persistence.AttributeConverter;

import bgshoprental.entity.RentalStatus;

public class RentalStatusConverter implements AttributeConverter<RentalStatus, String> {

	@Override
	public String convertToDatabaseColumn(RentalStatus attribute) {
		switch (attribute) {
		case CREATED:
			return "Utworzone";
		case READY:
			return "Do odbioru";
		case RECEIVED:
			return "Odebrane";
		case REALIZED:
			return "Zrealizowane";
		case CANCELED:
			return "Anulowane";
		}
		return null;
	}

	@Override
	public RentalStatus convertToEntityAttribute(String dbData) {
		if (dbData.equalsIgnoreCase("Utworzone")) {
			return RentalStatus.CREATED;
		} else if (dbData.equalsIgnoreCase("Do odbioru")) {
			return RentalStatus.READY;
		} else if (dbData.equalsIgnoreCase("Odebrane")) {
			return RentalStatus.RECEIVED;
		} else if (dbData.equalsIgnoreCase("Zrealizowane")) {
			return RentalStatus.REALIZED;
		} else if (dbData.equalsIgnoreCase("Anulowane")) {
			return RentalStatus.CANCELED;
		}
		return null;
	}

}
