package bgshoprental.entity;

public enum ExternalOrderStatus {
	CREATED("Utworzone"), REALIZED("Zrealizowane"), CANCELED("Anulowane");
	
	private String polishTranslation;

	private ExternalOrderStatus(String polishTranslation) {
		this.polishTranslation = polishTranslation;
	}
	
	public String getPolishTranslation() {
		return this.polishTranslation;
	}
}
