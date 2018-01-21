package bgshoprental.entity;

public enum RentalStatus {
	CREATED("Utworzone"), READY("Gotowe do odbioru"), RECEIVED("Odebrane"), REALIZED("Zrealizowane"), CANCELED("Anulowane");
	
	private String polishTranslation;

	private RentalStatus(String polishTranslation) {
		this.polishTranslation = polishTranslation;
	}
	
	public String getPolishTranslation() {
		return this.polishTranslation;
	}
}
