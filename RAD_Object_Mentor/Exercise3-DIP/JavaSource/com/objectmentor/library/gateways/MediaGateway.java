package com.objectmentor.library.gateways;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import com.objectmentor.library.models.LoanReceipt;
import com.objectmentor.library.models.Media;
import com.objectmentor.library.models.MediaCopy;

public class MediaGateway {

	private Map<String, List<MediaCopy>> bookCopies = new HashMap<String, List<MediaCopy>>();
	private long lastCopyId = 0;

	// FOR TESTING
	public MediaCopy addedBookCopy;

	public MediaGateway() {
		super();
	}

	public MediaCopy addCopy(Media book) {
		lastCopyId += 1;
		MediaCopy bookCopy = new MediaCopy(book, "" + lastCopyId);

		// FOR TESTING
		addedBookCopy = bookCopy;

		String bookId = book.getId();
		List<MediaCopy> copies = bookCopies.get(bookId);
		if (copies == null) {
			copies = new LinkedList<MediaCopy>();
			bookCopies.put(bookId, copies);
		}
		copies.add(bookCopy);
		return bookCopy;
	}

	protected String getLastId() {
		return "" + lastCopyId;
	}

	public List<MediaCopy> addCopies(Media book, int numberOfNewCopies) {
		ArrayList<MediaCopy> list = new ArrayList<MediaCopy>();
		for (int i = 0; i < numberOfNewCopies; i++)
			list.add(addCopy(book));
		return list;
	}

	public MediaCopy findBookCopy(String isbn) {
		List<MediaCopy> copies = this.bookCopies.get(isbn);
		if (copies != null) {
			for (Iterator<MediaCopy> i = copies.iterator(); i.hasNext();) {
				MediaCopy copy = (MediaCopy) i.next();
				if (copy.isBookCopy())
					return copy;
			}
			return null;
		} else
			return null;
	}

	public MediaCopy findCdCopy(String barCode) {
		List<MediaCopy> copies = this.bookCopies.get(barCode);
		if (copies != null) {
			for (Iterator<MediaCopy> i = copies.iterator(); i.hasNext();) {
				MediaCopy copy = (MediaCopy) i.next();
				if (copy.isCdCopy())
					return copy;
			}
			return null;
		} else
			return null;
	}

	public List<LoanReceipt> findAllLoanReceiptsFor(String patronId) {
		List<LoanReceipt> receipts = new LinkedList<LoanReceipt>();
		Collection<List<MediaCopy>> copyLists = bookCopies.values();
		for (Iterator<List<MediaCopy>> i = copyLists.iterator(); i.hasNext();) {
			List<MediaCopy> copyList = i.next();
			for (int j = 0; j < copyList.size(); j++) {
				MediaCopy mediaCopy = (MediaCopy) copyList.get(j);
				LoanReceipt receipt = mediaCopy.getLoanReceipt();
				if (receipt != null
						&& receipt.getBorrower().getId().equals(patronId))
					receipts.add(receipt);
			}
		}
		return receipts;
	}

	public void clear() {
		lastCopyId = 0;
		bookCopies.clear();
	}

	public int mediaCount() {
		return bookCopies.size();
	}

	public List<MediaCopy> findAllCopies(String isbn) {
		List<MediaCopy> copies = this.bookCopies.get(isbn);
		if (copies == null)
			return new ArrayList<MediaCopy>();
		return copies;
	}

	public boolean contains(String id) {
		return bookCopies.containsKey(id);
	}

	public MediaCopy findAvailableCopy(String id) {
		List<MediaCopy> copies = findAllCopies(id);
		for (int i = 0; i < copies.size(); i++) {
			MediaCopy copy = (MediaCopy) copies.get(i);
			if (!copy.isLoaned())
				return copy;
		}
		return null;
	}

	public MediaCopy findCopyById(String copyId) {
		Collection<List<MediaCopy>> listsOfCopies = bookCopies.values();
		for (Iterator<List<MediaCopy>> i = listsOfCopies.iterator(); i.hasNext();) {
			List<MediaCopy> copies = i.next();
			for (int j = 0; j < copies.size(); j++) {
				MediaCopy mediaCopy = (MediaCopy) copies.get(j);
				if (mediaCopy.getId().equals(copyId))
					return mediaCopy;
			}
		}
		return null;
	}

	public void delete(MediaCopy copy) {
		for (Iterator<List<MediaCopy>> iter = bookCopies.values().iterator(); iter
				.hasNext();) {
			List<MediaCopy> list = iter.next();
			if (list.remove(copy)) {
				return;
			}
		}
	}

	public List<String> findAllISBNs() {
		return findAllKeysForValuesOfType(Media.BOOK);
	}

	public int copyCount() {
		int count = 0;
		for (Iterator<List<MediaCopy>> iter = bookCopies.values().iterator(); iter
				.hasNext();) {
			List<MediaCopy> list = iter.next();
			count += list.size();
		}
		return count;
	}

	public Map<String, String> findAllISBNsAndTitles() {
		List<String> isbns = findAllKeysForValuesOfType(Media.BOOK);
		Map<String, String> map = new HashMap<String, String>();
		for (Iterator<String> iter = isbns.iterator(); iter.hasNext();) {
			String isbn = iter.next();
			List<MediaCopy> copies = bookCopies.get(isbn);
			if (copies.size() > 0) {
				String title = ((MediaCopy) copies.get(0)).getMedia()
						.getTitle();
				map.put(isbn, title);
			}
		}
		return map;
	}

	public List<List<MediaCopy>> findAllCDs() {
		return findAllCollectionsOfType(Media.COMPACT_DISC);
	}

	protected List<String> findAllKeysForValuesOfType(int type) {
		List<String> list = new ArrayList<String>();
		for (Iterator<String> iter = bookCopies.keySet().iterator(); iter
				.hasNext();) {
			String key = iter.next();
			Media media = getFirstInList(bookCopies.get(key));
			if (media != null && media.getTypeCode() == type)
				list.add(key);
		}
		return list;
	}

	protected List<List<MediaCopy>> findAllCollectionsOfType(int type) {
		List<List<MediaCopy>> list = new ArrayList<List<MediaCopy>>();
		List<String> keys = findAllKeysForValuesOfType(type);
		for (Iterator<String> iter = keys.iterator(); iter.hasNext();) {
			String key = iter.next();
			list.add(bookCopies.get(key));
		}
		return list;
	}

	protected Media getFirstInList(List<MediaCopy> list) {
		return list.size() > 0 ? ((MediaCopy) list.get(0)).getMedia() : null;
	}

	public List<MediaCopy> findAllCopies() {
		List<MediaCopy> allCopies = new ArrayList<MediaCopy>();
		for (Iterator<List<MediaCopy>> i = bookCopies.values().iterator(); i.hasNext();) {
			List<MediaCopy> copies = i.next();
			allCopies.addAll(copies);
		}
		return allCopies;
	}

}