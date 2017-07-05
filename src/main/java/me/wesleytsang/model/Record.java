package me.wesleytsang.model;

// TODO: Auto-generated Javadoc
/**
 * The Class Record.
 */
public class Record {
	
	/** The code. */
	private String code = "";
	
	/** The name. */
	private String name = "";
	
	/** The date. */
	private String date = "";
	
	/** The market. */
	private String market = "";
	
	/** The sh. */
	private int sh = 0;
	
	/** The dollar. */
	private int dollar = 0;
	
	/**
	 * Instantiates a new record.
	 *
	 * @param code the code
	 * @param name the name
	 * @param date the date
	 * @param market the market
	 * @param sh the sh
	 * @param dollar the dollar
	 */
	public Record (String code, String name, String date, String market, int sh, int dollar) {
		this.code = code;
		this.name = name;
		this.date = date;
		this.market = market;
		this.sh = sh;
		this.dollar = dollar;
	}

	/**
	 * Gets the code.
	 *
	 * @return the code
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 *
	 * @param code the new code
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 *
	 * @param name the new name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the date.
	 *
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * Sets the date.
	 *
	 * @param date the new date
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * Gets the market.
	 *
	 * @return the market
	 */
	public String getMarket() {
		return market;
	}

	/**
	 * Sets the market.
	 *
	 * @param market the new market
	 */
	public void setMarket(String market) {
		this.market = market;
	}

	/**
	 * Gets the sh.
	 *
	 * @return the sh
	 */
	public int getSh() {
		return sh;
	}

	/**
	 * Sets the sh.
	 *
	 * @param sh the new sh
	 */
	public void setSh(int sh) {
		this.sh = sh;
	}

	/**
	 * Gets the dollar.
	 *
	 * @return the dollar
	 */
	public int getDollar() {
		return dollar;
	}

	/**
	 * Sets the dollar.
	 *
	 * @param dollar the new dollar
	 */
	public void setDollar(int dollar) {
		this.dollar = dollar;
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return getCode() + getDate() + getMarket();
	}
	
}
