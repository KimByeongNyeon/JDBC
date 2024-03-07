package day_0307;

public class HomeworkVO {
	private String country, maker, model, carYear, carOption;
	private int price;

	public HomeworkVO() {

	}

	public HomeworkVO(String country, String maker, String model, String carYear, int price, String carOption) {
		super();
		this.country = country;
		this.maker = maker;
		this.model = model;
		this.carYear = carYear;
		this.price = price;
		this.carOption = carOption;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getMaker() {
		return maker;
	}

	public void setMaker(String maker) {
		this.maker = maker;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCarYear() {
		return carYear;
	}

	public void setCarYear(String carYear) {
		this.carYear = carYear;
	}

	public String getCarOption() {
		return carOption;
	}

	public void setCarOption(String carOption) {
		this.carOption = carOption;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "HomeworkVO [country=" + country + ", maker=" + maker + ", model=" + model + ", carYear=" + carYear
				+ ", price=" + price + ", carOption=" + carOption + "]";
	}
}
