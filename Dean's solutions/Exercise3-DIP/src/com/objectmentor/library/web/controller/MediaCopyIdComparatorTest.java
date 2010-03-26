package com.objectmentor.library.web.controller;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import junit.framework.TestCase;

import com.objectmentor.library.models.Media;
import com.objectmentor.library.models.MediaCopy;

public class MediaCopyIdComparatorTest extends TestCase {
	List copyList;
	String[] sortedIntegerIds = new String[] { "1", "2", "10", "21", "100" };
	String[] sortedStringIds  = new String[] { "I1", "I10", "I100", "I2", "I21" };
	
	protected void setUp() throws Exception {
		super.setUp();
		copyList = new ArrayList();
	}

	private void setUpCopyList(String prefix) {
		copyList.add(new MediaCopy(new Media("Media 4", "400", "Me4", Media.BOOK), prefix + "21"));
		copyList.add(new MediaCopy(new Media("Media 2", "200", "Me2", Media.BOOK), prefix + "2"));
		copyList.add(new MediaCopy(new Media("Media 1", "100", "Me1", Media.BOOK), prefix + "1"));
		copyList.add(new MediaCopy(new Media("Media 5", "500", "Me5", Media.BOOK), prefix + "100"));
		copyList.add(new MediaCopy(new Media("Media 3", "300", "Me3", Media.BOOK), prefix + "10"));
	}
	
	public void testSortsIntegers() {
		setUpCopyList("");
		MediaCopyIdComparator comparator = new MediaCopyIdComparator();
		Collections.sort(copyList, comparator);
		for (int i=0; i<copyList.size(); i++) {
			String id = ((MediaCopy) copyList.get(i)).getId();
			assertEquals(sortedIntegerIds[i], sortedIntegerIds[i], id);
		}
	}
	public void testSortsArbitraryStrings() {
		setUpCopyList("I");
		MediaCopyIdComparator comparator = new MediaCopyIdComparator();
		Collections.sort(copyList, comparator);
		for (int i=0; i<copyList.size(); i++) {
			String id = ((MediaCopy) copyList.get(i)).getId();
			assertEquals(sortedStringIds[i], sortedStringIds[i], id);
		}
	}
}
