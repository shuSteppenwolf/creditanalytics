
package org.drip.analytics.holset;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*
 *    GENERATED on Wed Jul 04 22:35:36 EDT 2012 ---- DO NOT DELETE
 */

/*!
 * Copyright (C) 2012 Lakshmi Krishnamurthy
 * Copyright (C) 2011 Lakshmi Krishnamurthy
 *
 * This file is part of CreditAnalytics, a free-software/open-source library for
 *		fixed income analysts and developers - http://www.credit-trader.org
 *
 * CreditAnalytics is a free, full featured, fixed income credit analytics library, developed with a special focus
 * 		towards the needs of the bonds and credit products community.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *   	you may not use this file except in compliance with the License.
 *
 *  You may obtain a copy of the License at
 *  	http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  	distributed under the License is distributed on an "AS IS" BASIS,
 *  	WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  
 *  See the License for the specific language governing permissions and
 *  	limitations under the License.
 */

public class NLGHoliday implements org.drip.analytics.holset.LocationHoliday {
	public NLGHoliday()
	{
	}

	public java.lang.String getHolidayLoc()
	{
		return "NLG";
	}

	public org.drip.analytics.holiday.Locale getHolidaySet()
	{
		org.drip.analytics.holiday.Locale lh = new
			org.drip.analytics.holiday.Locale();

		lh.addStaticHoliday ("01-JAN-1998", "New Years Day");

		lh.addStaticHoliday ("10-APR-1998", "Good Friday");

		lh.addStaticHoliday ("13-APR-1998", "Easter Monday");

		lh.addStaticHoliday ("30-APR-1998", "Queens Birthday");

		lh.addStaticHoliday ("21-MAY-1998", "Ascension Day");

		lh.addStaticHoliday ("01-JUN-1998", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-1998", "Christmas Day");

		lh.addStaticHoliday ("01-JAN-1999", "New Years Day");

		lh.addStaticHoliday ("02-APR-1999", "Good Friday");

		lh.addStaticHoliday ("05-APR-1999", "Easter Monday");

		lh.addStaticHoliday ("30-APR-1999", "Queens Birthday");

		lh.addStaticHoliday ("13-MAY-1999", "Ascension Day");

		lh.addStaticHoliday ("24-MAY-1999", "Whit Monday");

		lh.addStaticHoliday ("21-APR-2000", "Good Friday");

		lh.addStaticHoliday ("24-APR-2000", "Easter Monday");

		lh.addStaticHoliday ("01-MAY-2000", "Queens Birthday Observed");

		lh.addStaticHoliday ("05-MAY-2000", "Liberation Day");

		lh.addStaticHoliday ("01-JUN-2000", "Ascension Day");

		lh.addStaticHoliday ("12-JUN-2000", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2000", "Christmas Day");

		lh.addStaticHoliday ("26-DEC-2000", "Boxing Day");

		lh.addStaticHoliday ("01-JAN-2001", "New Years Day");

		lh.addStaticHoliday ("13-APR-2001", "Good Friday");

		lh.addStaticHoliday ("16-APR-2001", "Easter Monday");

		lh.addStaticHoliday ("01-MAY-2001", "Queens Birthday Observed");

		lh.addStaticHoliday ("24-MAY-2001", "Ascension Day");

		lh.addStaticHoliday ("04-JUN-2001", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2001", "Christmas Day");

		lh.addStaticHoliday ("26-DEC-2001", "Boxing Day");

		lh.addStaticHoliday ("01-JAN-2002", "New Years Day");

		lh.addStaticHoliday ("29-MAR-2002", "Good Friday");

		lh.addStaticHoliday ("01-APR-2002", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2002", "Queens Birthday");

		lh.addStaticHoliday ("09-MAY-2002", "Ascension Day");

		lh.addStaticHoliday ("20-MAY-2002", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2002", "Christmas Day");

		lh.addStaticHoliday ("26-DEC-2002", "Boxing Day");

		lh.addStaticHoliday ("01-JAN-2003", "New Years Day");

		lh.addStaticHoliday ("18-APR-2003", "Good Friday");

		lh.addStaticHoliday ("21-APR-2003", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2003", "Queens Birthday");

		lh.addStaticHoliday ("29-MAY-2003", "Ascension Day");

		lh.addStaticHoliday ("09-JUN-2003", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2003", "Christmas Day");

		lh.addStaticHoliday ("26-DEC-2003", "Boxing Day");

		lh.addStaticHoliday ("01-JAN-2004", "New Years Day");

		lh.addStaticHoliday ("09-APR-2004", "Good Friday");

		lh.addStaticHoliday ("12-APR-2004", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2004", "Queens Birthday");

		lh.addStaticHoliday ("20-MAY-2004", "Ascension Day");

		lh.addStaticHoliday ("31-MAY-2004", "Whit Monday");

		lh.addStaticHoliday ("25-MAR-2005", "Good Friday");

		lh.addStaticHoliday ("28-MAR-2005", "Easter Monday");

		lh.addStaticHoliday ("05-MAY-2005", "Liberation Day");

		lh.addStaticHoliday ("16-MAY-2005", "Whit Monday");

		lh.addStaticHoliday ("26-DEC-2005", "Boxing Day");

		lh.addStaticHoliday ("14-APR-2006", "Good Friday");

		lh.addStaticHoliday ("17-APR-2006", "Easter Monday");

		lh.addStaticHoliday ("25-MAY-2006", "Ascension Day");

		lh.addStaticHoliday ("05-JUN-2006", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2006", "Christmas Day");

		lh.addStaticHoliday ("26-DEC-2006", "Boxing Day");

		lh.addStaticHoliday ("01-JAN-2007", "New Years Day");

		lh.addStaticHoliday ("06-APR-2007", "Good Friday");

		lh.addStaticHoliday ("09-APR-2007", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2007", "Queens Birthday");

		lh.addStaticHoliday ("17-MAY-2007", "Ascension Day");

		lh.addStaticHoliday ("28-MAY-2007", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2007", "Christmas Day");

		lh.addStaticHoliday ("26-DEC-2007", "Boxing Day");

		lh.addStaticHoliday ("01-JAN-2008", "New Years Day");

		lh.addStaticHoliday ("21-MAR-2008", "Good Friday");

		lh.addStaticHoliday ("24-MAR-2008", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2008", "Queens Birthday");

		lh.addStaticHoliday ("01-MAY-2008", "Ascension Day");

		lh.addStaticHoliday ("12-MAY-2008", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2008", "Christmas Day");

		lh.addStaticHoliday ("26-DEC-2008", "Boxing Day");

		lh.addStaticHoliday ("01-JAN-2009", "New Years Day");

		lh.addStaticHoliday ("10-APR-2009", "Good Friday");

		lh.addStaticHoliday ("13-APR-2009", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2009", "Queens Birthday");

		lh.addStaticHoliday ("21-MAY-2009", "Ascension Day");

		lh.addStaticHoliday ("01-JUN-2009", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2009", "Christmas Day");

		lh.addStaticHoliday ("01-JAN-2010", "New Years Day");

		lh.addStaticHoliday ("02-APR-2010", "Good Friday");

		lh.addStaticHoliday ("05-APR-2010", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2010", "Queens Birthday");

		lh.addStaticHoliday ("05-MAY-2010", "Liberation Day");

		lh.addStaticHoliday ("13-MAY-2010", "Ascension Day");

		lh.addStaticHoliday ("24-MAY-2010", "Whit Monday");

		lh.addStaticHoliday ("22-APR-2011", "Good Friday");

		lh.addStaticHoliday ("25-APR-2011", "Easter Monday");

		lh.addStaticHoliday ("02-JUN-2011", "Ascension Day");

		lh.addStaticHoliday ("13-JUN-2011", "Whit Monday");

		lh.addStaticHoliday ("26-DEC-2011", "Boxing Day");

		lh.addStaticHoliday ("06-APR-2012", "Good Friday");

		lh.addStaticHoliday ("09-APR-2012", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2012", "Queens Birthday");

		lh.addStaticHoliday ("17-MAY-2012", "Ascension Day");

		lh.addStaticHoliday ("28-MAY-2012", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2012", "Christmas Day");

		lh.addStaticHoliday ("26-DEC-2012", "Boxing Day");

		lh.addStaticHoliday ("01-JAN-2013", "New Years Day");

		lh.addStaticHoliday ("29-MAR-2013", "Good Friday");

		lh.addStaticHoliday ("01-APR-2013", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2013", "Queens Birthday");

		lh.addStaticHoliday ("09-MAY-2013", "Ascension Day");

		lh.addStaticHoliday ("20-MAY-2013", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2013", "Christmas Day");

		lh.addStaticHoliday ("26-DEC-2013", "Boxing Day");

		lh.addStaticHoliday ("01-JAN-2014", "New Years Day");

		lh.addStaticHoliday ("18-APR-2014", "Good Friday");

		lh.addStaticHoliday ("21-APR-2014", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2014", "Queens Birthday");

		lh.addStaticHoliday ("29-MAY-2014", "Ascension Day");

		lh.addStaticHoliday ("09-JUN-2014", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2014", "Christmas Day");

		lh.addStaticHoliday ("26-DEC-2014", "Boxing Day");

		lh.addStaticHoliday ("01-JAN-2015", "New Years Day");

		lh.addStaticHoliday ("03-APR-2015", "Good Friday");

		lh.addStaticHoliday ("06-APR-2015", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2015", "Queens Birthday");

		lh.addStaticHoliday ("05-MAY-2015", "Liberation Day");

		lh.addStaticHoliday ("14-MAY-2015", "Ascension Day");

		lh.addStaticHoliday ("25-MAY-2015", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2015", "Christmas Day");

		lh.addStaticHoliday ("01-JAN-2016", "New Years Day");

		lh.addStaticHoliday ("25-MAR-2016", "Good Friday");

		lh.addStaticHoliday ("28-MAR-2016", "Easter Monday");

		lh.addStaticHoliday ("05-MAY-2016", "Ascension Day");

		lh.addStaticHoliday ("16-MAY-2016", "Whit Monday");

		lh.addStaticHoliday ("26-DEC-2016", "Boxing Day");

		lh.addStaticHoliday ("14-APR-2017", "Good Friday");

		lh.addStaticHoliday ("17-APR-2017", "Easter Monday");

		lh.addStaticHoliday ("25-MAY-2017", "Ascension Day");

		lh.addStaticHoliday ("05-JUN-2017", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2017", "Christmas Day");

		lh.addStaticHoliday ("26-DEC-2017", "Boxing Day");

		lh.addStaticHoliday ("01-JAN-2018", "New Years Day");

		lh.addStaticHoliday ("30-MAR-2018", "Good Friday");

		lh.addStaticHoliday ("02-APR-2018", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2018", "Queens Birthday");

		lh.addStaticHoliday ("10-MAY-2018", "Ascension Day");

		lh.addStaticHoliday ("21-MAY-2018", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2018", "Christmas Day");

		lh.addStaticHoliday ("26-DEC-2018", "Boxing Day");

		lh.addStaticHoliday ("01-JAN-2019", "New Years Day");

		lh.addStaticHoliday ("19-APR-2019", "Good Friday");

		lh.addStaticHoliday ("22-APR-2019", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2019", "Queens Birthday");

		lh.addStaticHoliday ("30-MAY-2019", "Ascension Day");

		lh.addStaticHoliday ("10-JUN-2019", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2019", "Christmas Day");

		lh.addStaticHoliday ("26-DEC-2019", "Boxing Day");

		lh.addStaticHoliday ("01-JAN-2020", "New Years Day");

		lh.addStaticHoliday ("10-APR-2020", "Good Friday");

		lh.addStaticHoliday ("13-APR-2020", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2020", "Queens Birthday");

		lh.addStaticHoliday ("05-MAY-2020", "Liberation Day");

		lh.addStaticHoliday ("21-MAY-2020", "Ascension Day");

		lh.addStaticHoliday ("01-JUN-2020", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2020", "Christmas Day");

		lh.addStaticHoliday ("01-JAN-2021", "New Years Day");

		lh.addStaticHoliday ("02-APR-2021", "Good Friday");

		lh.addStaticHoliday ("05-APR-2021", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2021", "Queens Birthday");

		lh.addStaticHoliday ("13-MAY-2021", "Ascension Day");

		lh.addStaticHoliday ("24-MAY-2021", "Whit Monday");

		lh.addStaticHoliday ("15-APR-2022", "Good Friday");

		lh.addStaticHoliday ("18-APR-2022", "Easter Monday");

		lh.addStaticHoliday ("26-MAY-2022", "Ascension Day");

		lh.addStaticHoliday ("06-JUN-2022", "Whit Monday");

		lh.addStaticHoliday ("26-DEC-2022", "Boxing Day");

		lh.addStaticHoliday ("07-APR-2023", "Good Friday");

		lh.addStaticHoliday ("10-APR-2023", "Easter Monday");

		lh.addStaticHoliday ("18-MAY-2023", "Ascension Day");

		lh.addStaticHoliday ("29-MAY-2023", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2023", "Christmas Day");

		lh.addStaticHoliday ("26-DEC-2023", "Boxing Day");

		lh.addStaticHoliday ("01-JAN-2024", "New Years Day");

		lh.addStaticHoliday ("29-MAR-2024", "Good Friday");

		lh.addStaticHoliday ("01-APR-2024", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2024", "Queens Birthday");

		lh.addStaticHoliday ("09-MAY-2024", "Ascension Day");

		lh.addStaticHoliday ("20-MAY-2024", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2024", "Christmas Day");

		lh.addStaticHoliday ("26-DEC-2024", "Boxing Day");

		lh.addStaticHoliday ("01-JAN-2025", "New Years Day");

		lh.addStaticHoliday ("18-APR-2025", "Good Friday");

		lh.addStaticHoliday ("21-APR-2025", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2025", "Queens Birthday");

		lh.addStaticHoliday ("05-MAY-2025", "Liberation Day");

		lh.addStaticHoliday ("29-MAY-2025", "Ascension Day");

		lh.addStaticHoliday ("09-JUN-2025", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2025", "Christmas Day");

		lh.addStaticHoliday ("26-DEC-2025", "Boxing Day");

		lh.addStaticHoliday ("01-JAN-2026", "New Years Day");

		lh.addStaticHoliday ("03-APR-2026", "Good Friday");

		lh.addStaticHoliday ("06-APR-2026", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2026", "Queens Birthday");

		lh.addStaticHoliday ("14-MAY-2026", "Ascension Day");

		lh.addStaticHoliday ("25-MAY-2026", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2026", "Christmas Day");

		lh.addStaticHoliday ("01-JAN-2027", "New Years Day");

		lh.addStaticHoliday ("26-MAR-2027", "Good Friday");

		lh.addStaticHoliday ("29-MAR-2027", "Easter Monday");

		lh.addStaticHoliday ("30-APR-2027", "Queens Birthday");

		lh.addStaticHoliday ("06-MAY-2027", "Ascension Day");

		lh.addStaticHoliday ("17-MAY-2027", "Whit Monday");

		lh.addStaticHoliday ("14-APR-2028", "Good Friday");

		lh.addStaticHoliday ("17-APR-2028", "Easter Monday");

		lh.addStaticHoliday ("25-MAY-2028", "Ascension Day");

		lh.addStaticHoliday ("05-JUN-2028", "Whit Monday");

		lh.addStaticHoliday ("25-DEC-2028", "Christmas Day");

		lh.addStaticHoliday ("26-DEC-2028", "Boxing Day");

		lh.addStandardWeekend();

		return lh;
	}
}
