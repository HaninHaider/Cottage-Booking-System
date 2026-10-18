package assignment_3;

public class Cottages {
	private int cottageNum;
	private String cottageType;
	private int maxOccupancy;
	private double cottagePrice;
	private boolean hasSeaView;
	private boolean allowPets;
	private String eMail;

	// constructor that does everything
	public Cottages(int cottageNum, String cottageType, int maxOccupancy, double cottagePrice, boolean hasSeaView,
			boolean allowPets, String eMail) {

		this.cottageNum = cottageNum;

		this.cottageType = cottageType;

		this.maxOccupancy = maxOccupancy;

		this.cottagePrice = cottagePrice;

		this.hasSeaView = hasSeaView;

		this.allowPets = allowPets;

		this.eMail = eMail;

	}// getter and setters below

	public int getCottageNum() {

		return cottageNum;

	}

	public void setCottageNum(int cottageNum) {

		this.cottageNum = cottageNum;

	}

	public String getCottageType() {

		return cottageType;

	}

	public void setCottageType(String cottageType) {

		this.cottageType = cottageType;

	}

	public int getMaxOccupancy() {

		return maxOccupancy;

	}

	public void setMaxOccupancy(int maxOccupancy) {

		this.maxOccupancy = maxOccupancy;

	}

	public double getCottagePrice() {

		return cottagePrice;

	}

	public void setCottagePrice(double cottagePrice) {

		this.cottagePrice = cottagePrice;

	}

	public boolean getHasSeaView() {

		return hasSeaView;

	}

	public void setHasSeaView(boolean hasSeaView) {

		this.hasSeaView = hasSeaView;

	}

	public boolean getAllowPets() {

		return allowPets;

	}

	public void setAllowPets(boolean allowPets) {

		this.allowPets = allowPets;

	}

	public String geteMail() {

		return eMail;

	}

	public void seteMail(String eMail) {

		this.eMail = eMail;

	}

	// checks if the cottage if free. (makes it easier to reserve and cancel )
	public boolean isFree() {
		return eMail.equalsIgnoreCase("free");

	}

	public String toString() {
		// puts the information in the right order for the file
		String result = cottageNum + " " + cottageType + " " + maxOccupancy + " " + cottagePrice + " " + hasSeaView
				+ " " + allowPets + " " + eMail;

		return result;

	}

	public int nextBestMatch(String type, int occupancy, double price, boolean seaView, boolean pets) {
		int matchScore = 0;// score for each requirement that meet. used to find best next match
		if (cottageType.equalsIgnoreCase(type)) {
			matchScore++;

		}
		if (occupancy <= maxOccupancy) {
			matchScore++;

		}
		if (price >= cottagePrice) {
			matchScore++;
		}
		if (seaView == hasSeaView) {
			matchScore++;
		}
		if (allowPets == pets) {
			matchScore++;
		}
		return matchScore;
	}

}
