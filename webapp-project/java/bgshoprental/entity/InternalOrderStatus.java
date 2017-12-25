package bgshoprental.entity;

public enum InternalOrderStatus {
	CREATED("Utworzone"), READY("Gotowe do odbioru"), REALIZED("Zrealizowane"), CANCELED("Anulowane");
	
	private String polishTranslation;

	private InternalOrderStatus(String polishTranslation) {
		this.polishTranslation = polishTranslation;
	}
	
	public String getPolishTranslation() {
		return this.polishTranslation;
	}
}
