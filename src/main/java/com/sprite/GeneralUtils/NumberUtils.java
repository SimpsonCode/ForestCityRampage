package com.sprite.GeneralUtils;


public class NumberUtils
{

	// default to ZERO on exception
	static public int parseInt(String aText)
	{

		try
		{
			int i = Integer.parseInt(aText);
			return i;
		} catch (Exception e)
		{
			// DO NOTHING! Will return 0 on bad data
		}

		return 0;
	}
	// default to ZERO on exception
	static public double parseDouble(String aText)
	{

		try
		{
			double i = Double.parseDouble(aText);
			return i;
		} catch (Exception e)
		{
			// DO NOTHING! Will return 0 on bad data
		}

		return 0.0;
	}
}
