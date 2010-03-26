/**
 * 
 */
package com.objectmentor.library.web.controller;

import java.util.Comparator;

import com.objectmentor.library.models.MediaCopy;

public class MediaCopyIdComparator implements Comparator<MediaCopy> {

	public int compare(MediaCopy mc0, MediaCopy mc1) {
		try {
			int i0 = Integer.parseInt(mc0.getId());
			int i1 = Integer.parseInt(mc1.getId());
			return i0 - i1;
		} catch (NumberFormatException e) {
		}
		return mc0.getId().compareTo(mc1.getId()); // if not integers...
	}
}