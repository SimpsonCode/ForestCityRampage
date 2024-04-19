package com.sprite.GeneralUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.StringTokenizer;

// take in a SEMI STRING and store in a hash map
// can get values - set values - as String again
// Full on Data Class - (Sequence Couple)
public class UniversalBucket
{
	private HashMap<String, String> ivBucketList = new HashMap<String, String>();

	public UniversalBucket()
	{

	}
	
	public UniversalBucket(String aSemiList)
	{
		initializeFromString(aSemiList);
	}

	// DESIGN: Perhaps Personality should just be
	//  one of these that parses by the plus sign
	public void initializeFromString(String aSemiList)
	{
		// TODO: RIGHT HERE is where I would do ESCAPES FOR EVERYONE!!!! 
		
		
		StringTokenizer st = new StringTokenizer(aSemiList, ";");

		// iterate those brains
		while (st.hasMoreTokens())
		{
			String stTrait = st.nextToken().trim();
			if (stTrait.length() > 0)
			{
				int tvEqual = stTrait.indexOf("=");
				if (tvEqual > 0)
				{
					String tvKey = stTrait.substring(0, tvEqual);
					String tvValue = stTrait.substring(tvEqual + 1);
					setValue(tvKey, tvValue);
				}
			}
		} // end of while
	}
	
	public int getValueCount()
	{
		return ivBucketList.size();
	}

	public void changeNumericValue(String aKey, int aValue)
	{
		changeValue(aKey, ""+aValue);
	}

	// not less than ZERO
	public void alterNumericValue(String aKey, int aValue)
	{
		int tvCurrent = getNumericValueWithDefault(aKey, 0);
		int tvNewValue = tvCurrent + aValue;
		if (tvNewValue<0)
		{
			tvNewValue = 0;
		}
		changeNumericValue(aKey,tvNewValue);
	}
	
	public void changeValue(String aKey, String aValue)
	{
		// if there is a value - just replace it
		if (ivBucketList.containsKey(aKey))
		{
			ivBucketList.remove(aKey);			
		}
		
		ivBucketList.put(aKey, aValue);		
	}
	
	public void removeValue(String aKey)
	{
		// if there is a value - just remove it 
		if (ivBucketList.containsKey(aKey))
		{
			ivBucketList.remove(aKey);			
		}
		
				
	}
	
	
	public void setValue(String aKey, int aValue)
	{
		setValue(aKey, ""+aValue);
	}
	
	
	public void setValue(String aKey, String aValue)
	{
		if (!ivBucketList.containsKey(aKey))
		{
			ivBucketList.put(aKey, aValue);
		}
		else
		{
			System.err.println("Warning: Key:  " + aKey + " already inside list - not adding (" + aValue +") use change value" );
			System.err.println("Already there: " + getSemiString());
		}
	}

	// returns null if does not exist - on purpose
	public String getValue(String aKey)
	{
		return ivBucketList.get(aKey);
	}
	
	
	public int getInteger(String aKey)
	{
		String s = getValue(aKey);
		if (s!=null)
		{
			// this is still not "SAFE"			
			return Integer.parseInt(s);
		}
		return 0;
	}

	// returns default if does not exist
	public String getValueWithDefault(String aKey, String aDefault)
	{
		String s = getValue(aKey);
		if (s == null)
		{
			s = aDefault;
		}
		return s;
	}

	// returns aDefault if does not exist
	public int getNumericValueWithDefault(String aKey, int aDefault)
	{
		String s = getValue(aKey);
		int i = aDefault;
		if (s == null)
		{
			return i;
		}
		
		i = NumberUtils.parseInt(s);
		return i;
	}

	
	public ArrayList<String> getKeys()
	{
		ArrayList<String> aKeySet = new ArrayList<String>();
		Iterator<String> ivIterator = ivBucketList.keySet().iterator();
		while (ivIterator.hasNext())
		{
			aKeySet.add( ivIterator.next() );
		}
		
		return aKeySet;
	}
		
	public String getSemiString()
	{
		StringBuffer sb = new StringBuffer(1024);
		Iterator<String> ivIterator = ivBucketList.keySet().iterator();
		while (ivIterator.hasNext())
		{
			String aKey = ivIterator.next();
			String aValue = ivBucketList.get(aKey);
			sb.append(aKey + "=" + aValue + ";");
		}

		return sb.toString();
	}
}
