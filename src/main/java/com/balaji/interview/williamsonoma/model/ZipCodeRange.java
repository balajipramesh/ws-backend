package com.balaji.interview.williamsonoma.model;

/**
 * Data model that represents zipcode minimum and maximum range
 * 
 * @author Balaji Ramesh
 * @since  2017-2-10
 *
 */
public class ZipCodeRange
{
    private int minRange;
    private int maxRange;
	
    /**
     * Constructor
     * @param minRange (Required) minimum zipcode range that the items cannot be shipped to
     * @param maxRange (Required) maximum zipcode range that the items cannot be shipped to
     */
    public ZipCodeRange(int minRange, int maxRange) {
    	this.minRange = Math.min(minRange, maxRange);
    	this.maxRange = Math.max(minRange, maxRange);
    }
    
    /**
     * Get minimum zipcode range
     * @return returns minimum zipcode range
     */
    public int getMinRange() {
		return minRange;
	}
    /**
     * Set minimum zipcode range
     * @param minRange 5-digit zipcode
     */
	public void setMinRange(int minRange) {
		this.minRange = minRange;
	}
	/**
	 * Get maximum zipcode range
	 * @return return maximum zipcode range
	 */
	public int getMaxRange() {
		return maxRange;
	}
	/**
	 * Set maximum zipcode range
	 * @param maxRange 5-digit zipcode
	 */
	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}

	@Override
    public String toString() {
		return ("[" + getMinRange() + ", " + getMaxRange() + "]");
	}
}
