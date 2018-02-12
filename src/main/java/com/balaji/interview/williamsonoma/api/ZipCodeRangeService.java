package com.balaji.interview.williamsonoma.api;

import java.util.List;

import com.balaji.interview.williamsonoma.model.ZipCodeRange;

/**
 * This interface provides services to manipulate on zipcode ranges
 *  
 * @author Balaji Ramesh
 * @since 2018-2-10
 *
 */
public interface ZipCodeRangeService {
	
	/**
	 * Given a collection of 5-digit ZIP code range the method produces minimum number of ranges required to represent the same restrictions as the input
	 * <p>
	 * Example:
	 * If the input = [94133,94133] [94200,94299] [94226,94399] 
	 * Then the output would be = [94133,94133] [94200,94399]
	 * @param rangeList List of 5-digit zip code ranges (each range includes both their upper and lower bounds)
	 * @return Minimum number of ranges required to represent the same restrictions as the input
	 * @see ZipCodeRange
	 */
	List<ZipCodeRange> getMinimumZipCodeRanges(List<ZipCodeRange> rangeList);
}
