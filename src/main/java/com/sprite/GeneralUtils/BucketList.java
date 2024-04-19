package com.sprite.GeneralUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

// DID: should just be bucket list... and used for multiple things...
// it is just a file with strings that can be parsed by The Universal Bucket
// ALSO: Not a singleton
public class BucketList {
	ArrayList<UniversalBucket> ivBucketData = new ArrayList<UniversalBucket>();

	// always call with a name
	public BucketList(String aFullFileName) {
		initFromFile(aFullFileName);
	}
	// in 2024 - init from string?!

	public BucketList() {
		// create an empty bucket list
	}

	public void addBucket(UniversalBucket aThing) {
		ivBucketData.add(aThing);
	}

	// must be a bucket in the list
	public void removeBucket(UniversalBucket aThing) {
		ivBucketData.remove(aThing);
	}

	public void insertBucketAt(UniversalBucket aThing, int inx) {
		// try to insert it where I asked.
		try {
			ivBucketData.add(inx, aThing);
		} catch (Exception e) {
			System.out.println(
					"Warning: insertBucketAt failed using inx " + inx + " against size " + ivBucketData.size());

			// if I can't then tail it on the end - and live with it
			ivBucketData.add(aThing);
		}
	}

	// does not throw - does not do anything?
	public void initFromString(String aNewLineDelimitedFile) {
		Scanner scan = new Scanner(aNewLineDelimitedFile);
		String line = "";
		while (scan.hasNext()) {
			// line = line.trim();
			line = scan.nextLine();
			if (line.length() > 0) // AND has an = ... AND has a ;
			{
				int tvOneEqual = line.indexOf("=");
				int tvOneSemi = line.indexOf(";");

				// now it's a list of Universal Buckets
				if (tvOneEqual >= 0 && tvOneSemi >= 0) {
					UniversalBucket ubLine = new UniversalBucket();
					ubLine.initializeFromString(line);

					// if we got one value out of it - should be good
					if (ubLine.getValueCount() > 0) {
						// only after passing all of the tests is it allowed in
						ivBucketData.add(ubLine);
					} // if no values are present
				} // if has no = or ;
			} // if empty string
		} // while reading lines
		scan.close();
	}

	// someday a reload feature
	// someday 2024 - load a full text file and send it through the parse String;
	private void initFromFile(String aFullFileName) {

		File aFile = new File(aFullFileName);

		BufferedReader input = null;
		String line = "";
		try {
			input = new BufferedReader(new FileReader(aFile));

			while ((line = input.readLine()) != null) {
				line = line.trim();
				if (line.length() > 0) // AND has an = ... AND has a ;
				{
					int tvOneEqual = line.indexOf("=");
					int tvOneSemi = line.indexOf(";");

					// now it's a list of Universal Buckets
					if (tvOneEqual >= 0 && tvOneSemi >= 0) {
						UniversalBucket ubLine = new UniversalBucket();
						ubLine.initializeFromString(line);

						// if we got one value out of it - should be good
						if (ubLine.getValueCount() > 0) {
							// only after passing all of the tests is it allowed in
							ivBucketData.add(ubLine);
						} // if no values are present
					} // if has no = or ;
				} // if empty string
			} // while reading lines

			input.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public ArrayList<UniversalBucket> getAllKeys(String aKey, String aStartsWith) {
		ArrayList<UniversalBucket> ubReturn = new ArrayList<UniversalBucket>();
		for (int i = 0; i < ivBucketData.size(); i++) {
			UniversalBucket ub = ivBucketData.get(i);
			String s = ub.getValue(aKey);
			if (s != null) {
				if (s.startsWith(aStartsWith)) {
					ubReturn.add(ub);
				}
			}
		}
		return ubReturn;
	}

	// returns -1 if NOT FOUND?!
	public int getFirstIndexFound(String aKey, String aStartsWith) {
		for (int i = 0; i < ivBucketData.size(); i++) {
			UniversalBucket ub = ivBucketData.get(i);
			String s = ub.getValue(aKey);
			if (s != null) {
				if (s.startsWith(aStartsWith)) {
					return i;
				}
			}
		}
		return -1;
	}


	// returns NULL if not found
	public UniversalBucket getFirstKey(String aKey, String aStartsWith) {
		for (int i = 0; i < ivBucketData.size(); i++) {
			UniversalBucket ub = ivBucketData.get(i);
			String s = ub.getValue(aKey);
			if (s != null) {
				if (s.startsWith(aStartsWith)) {
					return ub;
				}
			}
		}
		return null;
	}

	// returns NULL if not found
	public UniversalBucket getLastKey(String aKey, String aStartsWith) {
		UniversalBucket ubReturn = null;
		for (int i = 0; i < ivBucketData.size(); i++) {
			UniversalBucket ub = ivBucketData.get(i);
			String s = ub.getValue(aKey);
			if (s != null) {
				if (s.startsWith(aStartsWith)) {
					ubReturn = ub;
				}
			}
		}
		return ubReturn;
	}

	// for when I wish to save it again
	// should make a load from string tooo - for in memory things
	public String asBucketListFileData() {
		// Dump it out in an easy parse format (jSON?) nope ENDLs
		StringBuffer sbAppend = new StringBuffer(256);
		for (int i = 0; i < ivBucketData.size(); i++) {
			UniversalBucket ubLine = ivBucketData.get(i);
			// slap out a line, and slap out an end-line for that line
			sbAppend.append(ubLine.getSemiString() + "\r\n");
		}

		return sbAppend.toString();
	}

	// and now it's just an array list again
	public int getSize() {
		return ivBucketData.size();
	}

	public UniversalBucket getBucketAtIndex(int i) {
		return ivBucketData.get(i);
	}
}
