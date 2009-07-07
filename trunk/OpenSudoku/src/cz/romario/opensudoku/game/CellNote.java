package cz.romario.opensudoku.game;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

/**
 * Note attached to cell.
 * 
 * @author romario
 *
 */
public class CellNote {
	private Set<Integer> mNotedNumbers;
	
	public CellNote() {
		mNotedNumbers = new HashSet<Integer>();
	}
	
	private CellNote(Set<Integer> notedNumbers) {
		mNotedNumbers = notedNumbers;
	}
	
	/**
	 * Creates instance from given string (string which has been 
	 * created by {@link #toStringBuilder(StringBuilder)} or {@link #toString()} method).
	 * earlier.
	 * 
	 * @param note
	 */
	public static CellNote fromString(String note) {
		// TODO: optimalization: CellNote object don't have to be created for empty note
		
		Set<Integer> notedNumbers = new HashSet<Integer>();
		if (note != null && !note.equals("")) {
			StringTokenizer tokenizer = new StringTokenizer(note, ",");
	        while (tokenizer.hasMoreTokens()) {
	        	String value = tokenizer.nextToken();
	        	if (!value.equals("-")) {
		        	notedNumbers.add(Integer.parseInt(value));
	        	}
	        }
		}
		
		return new CellNote(notedNumbers);
	}
	
	/**
	 * Creates note instance from given <code>Integer</code> array.
	 * 
	 * @param notedNums Array of integers, which should be part of note.
	 * @return New note instance.
	 */
	public static CellNote fromIntArray(Integer[] notedNums) {
		Set<Integer> notedNumbers = new HashSet<Integer>();
		
		for (Integer n : notedNums) {
			notedNumbers.add(n);
		}

		return new CellNote(notedNumbers);
	}

	/**
	 * Appends string representation of this object to the given <code>StringBuilder</code>.
	 * You can later recreate object from this string by calling {@link #fromString}.
	 * 
	 * @param data
	 */	
	public void toStringBuilder(StringBuilder data) {
		if (mNotedNumbers.size() == 0) {
			data.append("-");
		} else {
			for (Integer num : mNotedNumbers) {
				data.append(num).append(",");
			}
		}
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		toStringBuilder(sb);
		return sb.toString();
	}

	/**
	 * TODO: find out how big performance impact would be to create new collection here
	 * 
	 * Returns reference to the data (for performance reasons, it's called from
	 * SudokuBoardView's onDraw). Do not change
	 * anything through this reference, use CellNote's methods !
	 * 
	 * @return
	 */
	public Collection<Integer> getNotedNumbers() {
		return mNotedNumbers;		
	}
	
	/**
	 * Clears note.
	 */
	public void clear() {
		mNotedNumbers.clear();
	}
	
	/**
	 * Creates deep copy of object's instance.
	 */
	public CellNote clone() {
		Set<Integer> copy = new HashSet<Integer>();
		for (Integer n : mNotedNumbers) {
			copy.add(n);
		}
		return new CellNote(copy);
	}
	
	/**
	 * Toggles noted number: if number is already noted, it will be removed otherwise it will be added. 
	 * 
	 * @param number Number to toggle.
	 */
	public void toggleNumber(int number) {
		if (number < 1 || number > 9)
			throw new IllegalArgumentException("Number must be between 1-9.");

		Integer n = new Integer(number);
		if (mNotedNumbers.contains(n)) {
			mNotedNumbers.remove(n);
		} else {
			mNotedNumbers.add(n);
		}
	}
	
	/**
	 * Returns true, if note is empty.
	 * 
	 * @return True if note is empty.
	 */
	public boolean isEmpty() {
		return mNotedNumbers.size() == 0;
	}

}
