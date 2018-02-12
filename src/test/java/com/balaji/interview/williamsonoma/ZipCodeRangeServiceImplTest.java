package com.balaji.interview.williamsonoma;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;


import com.balaji.interview.williamsonoma.api.ZipCodeRangeService;
import com.balaji.interview.williamsonoma.impl.ZipCodeRangeServiceImpl;
import com.balaji.interview.williamsonoma.model.ZipCodeRange;


/**
 * Unit test for simple ZipCodeRangeServiceImpl.
 */
public class ZipCodeRangeServiceImplTest {
	private static final Logger LOG = Logger.getLogger(ZipCodeRangeServiceImplTest.class);

	@Before
    public void setUp() {
    	System.out.println("@Before - setUp");
    }
    
    @After
    public void tearDown() {
        System.out.println("@After - tearDown");
    }
	
    @Test
    public void testZipCodeRangeWithNullRange() {
    	ZipCodeRangeService svc = new ZipCodeRangeServiceImpl();
		List<ZipCodeRange> minRangeList = svc.getMinimumZipCodeRanges(null);
		assertEquals(0, minRangeList.size());
		
		LOG.info("Test: testZipCodeRangeWithNullRange \n" + minRangeList.toString());		
    }
    
    @Test
    public void testZipCodeRangeWithLessThanTwoRange() {
    	ZipCodeRangeService svc = new ZipCodeRangeServiceImpl();
		List<ZipCodeRange> minRangeList = svc.getMinimumZipCodeRanges(new ArrayList<ZipCodeRange>());
		assertEquals(0, minRangeList.size());
		
		List<ZipCodeRange> rangeList = new ArrayList<ZipCodeRange>();
		rangeList.add(new ZipCodeRange(3, 8));
		
		minRangeList = svc.getMinimumZipCodeRanges(rangeList);
		assertEquals(1, minRangeList.size());
		assertEquals(rangeList.toString(), minRangeList.toString());
		
		LOG.info("Test: testZipCodeRangeWithLessThanTwoRange \n" + minRangeList.toString());		
    }
    
	@Test
    public void testZipCodeWithinAndOverlapRange() {
		
		List<ZipCodeRange> rangeList = new ArrayList<ZipCodeRange>();
		rangeList.add(new ZipCodeRange(5, 8));
		rangeList.add(new ZipCodeRange(3, 9));
		rangeList.add(new ZipCodeRange(3, 8));
		rangeList.add(new ZipCodeRange(2, 5));
		rangeList.add(new ZipCodeRange(1, 1));
		rangeList.add(new ZipCodeRange(8, 10));
		rangeList.add(new ZipCodeRange(5, 11));
		rangeList.add(new ZipCodeRange(12, 12));
		
		ZipCodeRangeService svc = new ZipCodeRangeServiceImpl();
		List<ZipCodeRange> expectedRangeList = new ArrayList<ZipCodeRange>();
		expectedRangeList.add(new ZipCodeRange(1, 12));

		List<ZipCodeRange> minRangeList = svc.getMinimumZipCodeRanges(rangeList);		
		
		assertEquals(expectedRangeList.size(), minRangeList.size());
		assertEquals(expectedRangeList.toString(), minRangeList.toString());
		
		LOG.info("Test: testZipCodeWithinAndOverlapRange \n" + minRangeList.toString());		
    }
	
	@Test
    public void testZipCodePartialOverlapRange() {
		List<ZipCodeRange> rangeList = new ArrayList<ZipCodeRange>();
		rangeList.add(new ZipCodeRange(94133,94133));
		rangeList.add(new ZipCodeRange(94200,94299));
		rangeList.add(new ZipCodeRange(94226,94399));
		
		List<ZipCodeRange> expectedRangeList = new ArrayList<ZipCodeRange>();
		expectedRangeList.add(new ZipCodeRange(94133,94133));
		expectedRangeList.add(new ZipCodeRange(94200,94399));
		
		ZipCodeRangeService svc = new ZipCodeRangeServiceImpl();
		List<ZipCodeRange> minRangeList = svc.getMinimumZipCodeRanges(rangeList);
		
		assertEquals(expectedRangeList.size(), minRangeList.size());	
		assertEquals(expectedRangeList.toString(), minRangeList.toString());
		
		LOG.info("Test: testZipCodePartialOverlapRange \n" + minRangeList.toString());
	}
	
	@Test
    public void testZipCodeNonOverlapRange() {
		List<ZipCodeRange> rangeList = new ArrayList<ZipCodeRange>();
		rangeList.add(new ZipCodeRange(94133,94133));
		rangeList.add(new ZipCodeRange(94200,94299));
		rangeList.add(new ZipCodeRange(94600,94699));
		
		List<ZipCodeRange> expectedRangeList = new ArrayList<ZipCodeRange>();
		expectedRangeList.add(new ZipCodeRange(94133,94133));
		expectedRangeList.add(new ZipCodeRange(94200,94299));
		expectedRangeList.add(new ZipCodeRange(94600,94699));
		
		ZipCodeRangeService svc = new ZipCodeRangeServiceImpl();
		List<ZipCodeRange> minRangeList = svc.getMinimumZipCodeRanges(rangeList);
		
		assertEquals(expectedRangeList.size(), minRangeList.size());
		assertEquals(expectedRangeList.toString(), minRangeList.toString());
		
		LOG.info("Test: testZipCodeNonOverlapRange \n" + minRangeList.toString());
	}
	
	@Test
    public void testZipCodeConsecutiveRange() {
		
		List<ZipCodeRange> rangeList = new ArrayList<ZipCodeRange>();
		rangeList.add(new ZipCodeRange(1, 1));
		rangeList.add(new ZipCodeRange(2, 2));
		rangeList.add(new ZipCodeRange(3, 8));
		
		ZipCodeRangeService svc = new ZipCodeRangeServiceImpl();
		List<ZipCodeRange> expectedRangeList = new ArrayList<ZipCodeRange>();
		expectedRangeList.add(new ZipCodeRange(1, 8));
		
		List<ZipCodeRange> minRangeList = svc.getMinimumZipCodeRanges(rangeList);

		assertEquals(expectedRangeList.size(), minRangeList.size());
		assertEquals(expectedRangeList.toString(), minRangeList.toString());
		
		LOG.info("Test: testZipCodeConsecutiveRange \n" + minRangeList.toString());
    }
	
	@Test
    public void testZipCodeArbitraryOrderAndConsecutive() {
		
		List<ZipCodeRange> rangeList = new ArrayList<ZipCodeRange>();
		rangeList.add(new ZipCodeRange(4, 7));
		rangeList.add(new ZipCodeRange(2, 3));
		rangeList.add(new ZipCodeRange(12, 13));
		rangeList.add(new ZipCodeRange(14, 17));
		
		List<ZipCodeRange> expectedRangeList = new ArrayList<ZipCodeRange>();
		expectedRangeList.add(new ZipCodeRange(2, 7));
		expectedRangeList.add(new ZipCodeRange(12, 17));
		
		ZipCodeRangeService svc = new ZipCodeRangeServiceImpl();
		List<ZipCodeRange> minRangeList = svc.getMinimumZipCodeRanges(rangeList);
		
		assertEquals(expectedRangeList.size(), minRangeList.size());
		assertEquals(expectedRangeList.toString(), minRangeList.toString());
		
		LOG.info("Test: testZipCodeArbitraryOrderAndConsecutive \n" + minRangeList.toString());
    }
	
	@Test
    public void testZipCodeMaxMinSwapped() {
		
		List<ZipCodeRange> rangeList = new ArrayList<ZipCodeRange>();
		rangeList.add(new ZipCodeRange(4, 1));
		rangeList.add(new ZipCodeRange(9, 7));
		rangeList.add(new ZipCodeRange(8, 1));
		
		List<ZipCodeRange> expectedRangeList = new ArrayList<ZipCodeRange>();
		expectedRangeList.add(new ZipCodeRange(1, 9));
		
		ZipCodeRangeService svc = new ZipCodeRangeServiceImpl();
		List<ZipCodeRange> minRangeList = svc.getMinimumZipCodeRanges(rangeList);
		
		assertEquals(expectedRangeList.size(), minRangeList.size());
		assertEquals(expectedRangeList.toString(), minRangeList.toString());
		
		LOG.info("Test: testZipCodeMaxMinSwapped \n" + minRangeList.toString());
    }
}
