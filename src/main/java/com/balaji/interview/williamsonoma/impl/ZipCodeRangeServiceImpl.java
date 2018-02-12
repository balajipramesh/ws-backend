package com.balaji.interview.williamsonoma.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Stack;

import com.balaji.interview.williamsonoma.api.ZipCodeRangeService;
import com.balaji.interview.williamsonoma.model.ZipCodeRange;

/**
 * Implementation for ZipCodeRangeService
 * 
 * @author rameshb
 * @since 2018-2-10
 * @see ZipCodeRangeService
 *
 */
public class ZipCodeRangeServiceImpl implements ZipCodeRangeService {

	/**
	 * Comparison function that compares on minRange property of the ZipCodeRange
	 * 
	 * @see ZipCodeRange
	 */
	private static final Comparator<ZipCodeRange> zipCodeRangeComparator = new Comparator<ZipCodeRange>(){
		@Override
		public int compare(ZipCodeRange compared, ZipCodeRange base) {
			return Integer.compare(compared.getMinRange(), base.getMinRange());
		}		
	};
	
	public List<ZipCodeRange> getMinimumZipCodeRanges(List<ZipCodeRange> rangeList) {
		ArrayList<ZipCodeRange> minRangeList = new ArrayList<ZipCodeRange>();
		
		// validate for empty or null input range
		if (rangeList == null){
			return minRangeList;
		} else if (rangeList.size() <=1 ) {
			return rangeList;
		}
		
		// Sort zipcode range based on the minimum range value
		// Time complexity for sorting = nlogn
		Collections.sort(rangeList, zipCodeRangeComparator);
		Stack<ZipCodeRange> stack = new Stack<ZipCodeRange>();
		
		// Time complexity for iterating through arraylist = n
		for (ZipCodeRange range : rangeList) {
			if (stack.isEmpty()){
				stack.push(range);
			} else {
				ZipCodeRange top = stack.peek();
				if(canMerge(range, top)) {
					merge(range, top);
				} else {
					stack.push(range);
				}
			}
		}
		// total time complexity nlogn + n ~ nlogn
		return new ArrayList<ZipCodeRange>(stack);
	}
	
	/**
	 * Method to check if the two input arguments can be merged from one to other
	 * @param from ZipCodeRange object that gets merged into the other object
	 * @param to ZipCodeRange object which shall consume the other object
	 * @return true - if the objects can be merged; false - if the objects can not be merged  
	 */
	private boolean canMerge(ZipCodeRange from, ZipCodeRange to) {
		return to.getMaxRange()+1 >= from.getMinRange();
	}
	
	/**
	 * Method that merges two ZipCodeRange objects
	 * @param from ZipCodeRange object that will be merged into the other object
	 * @param to ZipCodeRange object that consumes the other object
	 */
	private void merge(ZipCodeRange from, ZipCodeRange to) {
		// set the max range to whichever object has the bigger value
		to.setMaxRange(Math.max(to.getMaxRange(), from.getMaxRange()));
	}
}