
package org.drip.service.env;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2014 Lakshmi Krishnamurthy
 * Copyright (C) 2013 Lakshmi Krishnamurthy
 * Copyright (C) 2012 Lakshmi Krishnamurthy
 * Copyright (C) 2011 Lakshmi Krishnamurthy
 * 
 *  This file is part of DRIP, a free-software/open-source library for fixed income analysts and developers -
 * 		http://www.credit-trader.org/Begin.html
 * 
 *  DRIP is a free, full featured, fixed income rates, credit, and FX analytics library with a focus towards
 *  	pricing/valuation, risk, and market making.
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

/**
 * StaticBACurves that creates the closing curves from custom/user defined marks for a given EOD and
 *  populates them onto the MPC. It builds the following:
 *  - Discount Curve (from cash/future/swap - typical sequence) and TSY Curve
 *  - Credit Curve from CDS quotes
 *  - On-the-run TSY yield quotes
 * 
 * @author Lakshmi Krishnamurthy
 */

public class StaticBACurves {
	private static final org.drip.product.definition.Bond CreateTSYBond (
		final java.lang.String strTSYName,
		final java.lang.String strCurrency,
		final double dblCoupon,
		final org.drip.analytics.date.JulianDate dt,
		int iNumYears)
	{
		if (null == strTSYName || strTSYName.isEmpty() || null == strCurrency || strCurrency.isEmpty() ||
			null == dt || java.lang.Double.isNaN (dblCoupon))
			return null;

		org.drip.product.credit.BondComponent bondTSY = new org.drip.product.credit.BondComponent();

		org.drip.product.params.IdentifierSet idParams = new org.drip.product.params.IdentifierSet
			(strTSYName, strTSYName, strTSYName, strCurrency + "TSY");

		if (!idParams.validate()) return null;

		bondTSY.setIdentifierSet (idParams);

		org.drip.product.params.CouponSetting cpnParams = new org.drip.product.params.CouponSetting
			(null, "", dblCoupon, java.lang.Double.NaN, java.lang.Double.NaN);

		if (!cpnParams.validate()) return null;

		bondTSY.setCouponSetting (cpnParams);

		org.drip.product.params.CurrencySet ccyParams = org.drip.product.params.CurrencySet.Create
			(strCurrency + "TSY");

		if (!ccyParams.validate()) return null;

		bondTSY.setCurrencySet (ccyParams);

		bondTSY.setFloaterSetting (null);

		org.drip.product.params.QuoteConvention mktConv = new org.drip.product.params.QuoteConvention
			(null, "", dt.julian(), java.lang.Double.NaN, 3, strCurrency,
				org.drip.analytics.daycount.Convention.DR_MOD_FOLL);

		if (!mktConv.validate()) return null;

		bondTSY.setMarketConvention (mktConv);

		org.drip.product.params.RatesSetting bondIRValParams = new
			org.drip.product.params.RatesSetting (strCurrency, strCurrency, strCurrency,
				strCurrency);

		if (!bondIRValParams.validate()) return null;

		bondTSY.setRatesSetting (bondIRValParams);

		org.drip.product.params.CreditSetting crValParams = new
			org.drip.product.params.CreditSetting (30, java.lang.Double.NaN, false, "",
				true);

		if (!crValParams.validate()) return null;

		bondTSY.setCreditSetting (crValParams);

		org.drip.product.params.TerminationSetting cfteParams = new
			org.drip.product.params.TerminationSetting (false, false, false);

		if (!cfteParams.validate()) return null;

		bondTSY.setTerminationSetting (cfteParams);

		org.drip.product.params.PeriodGenerator periodParams = null;

		try {
			periodParams = new org.drip.product.params.PeriodGenerator (dt.addYears (iNumYears).julian(),
				dt.julian(), java.lang.Double.NaN, dt.julian(), dt.julian(), 2, dblCoupon, "30/360",
					"30/360", null, null, null, null, null, null, null, null, "", false, strCurrency,
						strCurrency, null, null);
		} catch (java.lang.Exception e) {
			e.printStackTrace();

			return null;
		}

		if (!periodParams.validate()) return null;

		bondTSY.setPeriodSet (periodParams);

		org.drip.product.params.NotionalSetting notlParams = new
			org.drip.product.params.NotionalSetting (null, 100.,
				org.drip.product.params.NotionalSetting.PERIOD_AMORT_AT_START, false);

		if (!notlParams.validate()) return null;

		bondTSY.setNotionalSetting (notlParams);

		return bondTSY;
	}

	/**
	 * Add custom treasuries to the org.drip.param.definition.MarketParams
	 * 
	 * @param mpc org.drip.param.definition.MarketParams to be added to
	 * 
	 * @return Success (true), or failure (false)
	 */

	public static final boolean AddTSYToMPC (
		final org.drip.param.definition.ScenarioMarketParams mpc)
	{
		if (null == mpc) return false;

		try {
			org.drip.param.definition.ProductQuote cq2YON =
				org.drip.param.creator.ProductQuoteBuilder.CreateProductQuote();

			cq2YON.addQuote ("Yield", org.drip.param.creator.QuoteBuilder.CreateQuote ("mid", 0.02,
				java.lang.Double.NaN), true);

			mpc.addTSYQuote ("2YON", cq2YON);

			org.drip.param.definition.ProductQuote cq3YON =
				org.drip.param.creator.ProductQuoteBuilder.CreateProductQuote();

			cq3YON.addQuote ("Yield", org.drip.param.creator.QuoteBuilder.CreateQuote ("mid", 0.025,
				java.lang.Double.NaN), true);

			mpc.addTSYQuote ("3YON", cq3YON);

			org.drip.param.definition.ProductQuote cq5YON =
				org.drip.param.creator.ProductQuoteBuilder.CreateProductQuote();

			cq5YON.addQuote ("Yield", org.drip.param.creator.QuoteBuilder.CreateQuote ("mid", 0.03,
				java.lang.Double.NaN), true);

			mpc.addTSYQuote ("5YON", cq5YON);

			org.drip.param.definition.ProductQuote cq7YON =
				org.drip.param.creator.ProductQuoteBuilder.CreateProductQuote();

			cq7YON.addQuote ("Yield", org.drip.param.creator.QuoteBuilder.CreateQuote ("mid", 0.0325,
				java.lang.Double.NaN), true);

			mpc.addTSYQuote ("7YON", cq7YON);

			org.drip.param.definition.ProductQuote cq10YON =
				org.drip.param.creator.ProductQuoteBuilder.CreateProductQuote();

			cq10YON.addQuote ("Yield", org.drip.param.creator.QuoteBuilder.CreateQuote ("mid", 0.0375,
				java.lang.Double.NaN), true);

			mpc.addTSYQuote ("10YON", cq10YON);

			org.drip.param.definition.ProductQuote cq30YON =
				org.drip.param.creator.ProductQuoteBuilder.CreateProductQuote();

			cq30YON.addQuote ("Yield", org.drip.param.creator.QuoteBuilder.CreateQuote ("mid", 0.04,
				java.lang.Double.NaN), true);

			mpc.addTSYQuote ("30YON", cq30YON);

			org.drip.param.definition.ProductQuote cqBRA_5_00_21 =
				org.drip.param.creator.ProductQuoteBuilder.CreateProductQuote();

			org.drip.param.definition.Quote qPxBRA_5_00_21 = org.drip.param.creator.QuoteBuilder.CreateQuote
				("bid", 0.74, java.lang.Double.NaN);

			qPxBRA_5_00_21.setSide ("mid", 0.75, java.lang.Double.NaN);

			qPxBRA_5_00_21.setSide ("ask", 0.76, java.lang.Double.NaN);

			cqBRA_5_00_21.addQuote ("Price", qPxBRA_5_00_21, true);

			return true;
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}

		return false;
	}

	/**
	 * Build the treasury curve from custom/user defined marks and adds it to the MarketParams for the given
	 *  EOD and currency
	 *  
	 * @param mpc org.drip.param.definition.MarketParams to which the treasury is to be added to 
	 * @param dt EOD JulianDate
	 * @param strCurrency Currency string
	 * 
	 * @return Success (true) or failure (false)
	 */

	public static final boolean BuildTSYCurve (
		org.drip.param.definition.ScenarioMarketParams mpc,
		final org.drip.analytics.date.JulianDate dt,
		final java.lang.String strCurrency)
	{
		if (null == mpc || null == dt || null == strCurrency || strCurrency.isEmpty()) return false;

		org.drip.product.definition.CalibratableFixedIncomeComponent aCompCalib[] = new
			org.drip.product.definition.CalibratableFixedIncomeComponent[6];
		java.lang.String astrCalibMeasure[] = new java.lang.String[6];
		double adblCompCalibValue[] = new double[6];
		org.drip.param.definition.ScenarioDiscountCurve irscTSY = null;
		adblCompCalibValue[0] = .0200;
		adblCompCalibValue[1] = .0250;
		adblCompCalibValue[2] = .0300;
		adblCompCalibValue[3] = .0325;
		adblCompCalibValue[4] = .0375;
		adblCompCalibValue[5] = .0400;

		long lStart = System.nanoTime();

		if (null == (aCompCalib[0] = CreateTSYBond (strCurrency + "2YON", strCurrency, 0.02, dt, 2)))
			return false;

		if (null == (aCompCalib[1] = CreateTSYBond (strCurrency + "3YON", strCurrency, 0.025, dt, 3)))
			return false;

		if (null == (aCompCalib[2] = CreateTSYBond (strCurrency + "5YON", strCurrency, 0.03, dt, 5)))
			return false;

		if (null == (aCompCalib[3] = CreateTSYBond (strCurrency + "7YON", strCurrency, 0.0325, dt, 7)))
			return false;

		if (null == (aCompCalib[4] = CreateTSYBond (strCurrency + "10YON", strCurrency, 0.0375, dt, 10)))
			return false;

		if (null == (aCompCalib[5] = CreateTSYBond (strCurrency + "30YON", strCurrency, 0.04, dt, 30)))
			return false;

		for (int i = 0; i < 6; ++i)

		try {
			if (!(irscTSY = org.drip.param.creator.ScenarioDiscountCurveBuilder.FromIRCSG (strCurrency + "TSY",
				org.drip.state.creator.DiscountCurveBuilder.BOOTSTRAP_MODE_CONSTANT_FORWARD,
					aCompCalib)).cookScenarioDC (new org.drip.param.valuation.ValuationParams (dt,
						dt.addBusDays (3, strCurrency), strCurrency), null, adblCompCalibValue, 0.0001,
							astrCalibMeasure, mpc.fixings(), null, 15)) {
				System.out.println ("Cannot cook " + strCurrency + "TSY curve!");

				return false;
			}
		} catch (java.lang.Exception e) {
			e.printStackTrace();

			return false;
		}

		System.out.println (strCurrency + "TSYDC Cooked in: " + (System.nanoTime() - lStart) * 1.e-09 +
			" sec");

		if (!mpc.addScenDC (strCurrency + "TSY", irscTSY)) {
			System.out.println ("Cannot add scenario CC!");

			return false;
		}

		return true;
	}

	/**
	 * Build the EDSF curve from custom/user defined marks and adds it to the MarketParams for the given EOD
	 *  and currency
	 *  
	 * @param mpc org.drip.param.definition.MarketParams to which the treasury is to be added to 
	 * @param dt EOD JulianDate
	 * @param strCurrency Currency string
	 * 
	 * @return Success (true) or failure (false)
	 */

	public static final boolean BuildEDSFCurve (
		org.drip.param.definition.ScenarioMarketParams mpc,
		final org.drip.analytics.date.JulianDate dt,
		final java.lang.String strCurrency)
	{
		if (null == mpc || null == dt || null == strCurrency || strCurrency.isEmpty()) return false;

		java.lang.String astrCalibMeasure[] = new java.lang.String[8];
		double adblCompCalibValue[] = new double[8];
		org.drip.product.definition.CalibratableFixedIncomeComponent[] aCompCalib = null;
		org.drip.param.definition.ScenarioDiscountCurve irsc = null;
		adblCompCalibValue[0] = .0027;
		adblCompCalibValue[1] = .0032;
		adblCompCalibValue[2] = .0041;
		adblCompCalibValue[3] = .0054;
		adblCompCalibValue[4] = .0077;
		adblCompCalibValue[5] = .0104;
		adblCompCalibValue[6] = .0134;
		adblCompCalibValue[7] = .0160;
		astrCalibMeasure[0] = "Rate";
		astrCalibMeasure[1] = "Rate";
		astrCalibMeasure[2] = "Rate";
		astrCalibMeasure[3] = "Rate";
		astrCalibMeasure[4] = "Rate";
		astrCalibMeasure[5] = "Rate";
		astrCalibMeasure[6] = "Rate";
		astrCalibMeasure[7] = "Rate";

		long lStart = System.nanoTime();

		if (null == (aCompCalib = org.drip.product.creator.SingleStreamComponentBuilder.GenerateFuturesPack (dt, 8,
			strCurrency)) || 8 != aCompCalib.length)
			return false;

		try {
			if (!(irsc = org.drip.param.creator.ScenarioDiscountCurveBuilder.FromIRCSG (strCurrency + "EDSF",
				org.drip.state.creator.DiscountCurveBuilder.BOOTSTRAP_MODE_CONSTANT_FORWARD,
					aCompCalib)).cookScenarioDC (new org.drip.param.valuation.ValuationParams (dt,
						dt.addBusDays (3, strCurrency), strCurrency), null, adblCompCalibValue, 0.0001,
							astrCalibMeasure, mpc.fixings(), null, 15)) {
				System.out.println ("Cannot cook " + strCurrency + " curve!");

				return false;
			}
		} catch (java.lang.Exception e) {
			System.out.println ("Cannot calib EDSF curve: " + e.getMessage());

			return false;
		}

		System.out.println (strCurrency + "EDSFDC Cooked in: " + (System.nanoTime() - lStart) * 1.e-09 +
			" sec");

		if (!mpc.addScenDC (strCurrency + "EDSF", irsc)) {
			System.out.println ("Cannot add " + strCurrency + "EDSF scen curve to MPC!");

			return false;
		}

		return true;
	}

	/**
	 * Build the full IR curve from custom/user defined marks and adds it to the MarketParams for the given
	 *  EOD and currency
	 *  
	 * @param mpc org.drip.param.definition.MarketParams to which the treasury is to be added to 
	 * @param dt EOD JulianDate
	 * @param strCurrency Currency string
	 * 
	 * @return Success (true) or failure (false)
	 */

	public static boolean setDC (
		org.drip.param.definition.ScenarioMarketParams mpc,
		final org.drip.analytics.date.JulianDate dt,
		final java.lang.String strCurrency)
	{
		if (null == mpc || null == dt || null == strCurrency || strCurrency.isEmpty()) return false;

		org.drip.product.definition.CalibratableFixedIncomeComponent aCompCalib[] = new
			org.drip.product.definition.CalibratableFixedIncomeComponent[30];
		java.lang.String astrCalibMeasure[] = new java.lang.String[30];
		double adblCompCalibValue[] = new double[30];
		org.drip.param.definition.ScenarioDiscountCurve irsc = null;
		double adblDate[] = new double[30];
		adblCompCalibValue[0] = .0013;
		adblCompCalibValue[1] = .0017;
		adblCompCalibValue[2] = .0017;
		adblCompCalibValue[3] = .0018;
		adblCompCalibValue[4] = .0020;
		adblCompCalibValue[5] = .0023;
		adblCompCalibValue[6] = .0026;
		org.drip.product.definition.CalibratableFixedIncomeComponent[] aEDF = null;

		// First 7 instruments - cash calibration

		try {
			adblDate[0] = dt.addDays (3).julian(); // ON

			adblDate[1] = dt.addDays (4).julian(); // 1D (TN)

			adblDate[2] = dt.addDays (9).julian(); // 1W

			adblDate[3] = dt.addDays (16).julian(); // 2W

			adblDate[4] = dt.addDays (32).julian(); // 1M

			adblDate[5] = dt.addDays (62).julian(); // 2M

			adblDate[6] = dt.addDays (92).julian(); // 3M
		} catch (java.lang.Exception e) {
			e.printStackTrace();

			return false;
		}

		for (int i = 0; i < 7; ++i) {
			astrCalibMeasure[i] = "Rate";

			try {
				aCompCalib[i] = org.drip.product.creator.SingleStreamComponentBuilder.CreateDeposit
					(dt.addDays (2), new org.drip.analytics.date.JulianDate (adblDate[i]), null,
						strCurrency);
			} catch (java.lang.Exception e) {
				e.printStackTrace();

				return false;
			}
		}

		// Next 8 instruments - EDF calibration

		if (null == (aEDF = org.drip.product.creator.SingleStreamComponentBuilder.GenerateFuturesPack (dt, 8,
			strCurrency)) || 8 != aEDF.length)
			return false;

		adblCompCalibValue[7] = .0027;
		adblCompCalibValue[8] = .0032;
		adblCompCalibValue[9] = .0041;
		adblCompCalibValue[10] = .0054;
		adblCompCalibValue[11] = .0077;
		adblCompCalibValue[12] = .0104;
		adblCompCalibValue[13] = .0134;
		adblCompCalibValue[14] = .0160;

		for (int i = 0; i < 8; ++i) {
			aCompCalib[i + 7] = aEDF[i];
			astrCalibMeasure[i + 7] = "Rate";
			}

		// Final 15 instruments - IRS calibration

		org.drip.analytics.date.JulianDate dtMatIRS = dt.addDays ((int)(365.25 * 4 + 2)); // 4Y

		if (null == dtMatIRS) return false;

		adblDate[15] = dtMatIRS.julian();

		if (null == (dtMatIRS = dt.addDays ((int)(365.25 * 5 + 2)))) return false; // 5Y

		adblDate[16] = dtMatIRS.julian();

		if (null == (dtMatIRS = dt.addDays ((int)(365.25 * 6 + 2)))) return false; // 6Y

		adblDate[17] = dtMatIRS.julian();

		if (null == (dtMatIRS = dt.addDays ((int)(365.25 * 7 + 2)))) return false; // 7Y

		adblDate[18] = dtMatIRS.julian();

		if (null == (dtMatIRS = dt.addDays ((int)(365.25 * 8 + 2)))) return false; // 8Y

		adblDate[19] = dtMatIRS.julian();

		if (null == (dtMatIRS = dt.addDays ((int)(365.25 * 9 + 2)))) return false; // 9Y

		adblDate[20] = dtMatIRS.julian();

		if (null == (dtMatIRS = dt.addDays ((int)(365.25 * 10 + 2)))) return false; // 10Y

		adblDate[21] = dtMatIRS.julian();

		if (null == (dtMatIRS = dt.addDays ((int)(365.25 * 11 + 2)))) return false; // 11Y

		adblDate[22] = dtMatIRS.julian();

		if (null == (dtMatIRS = dt.addDays ((int)(365.25 * 12 + 2)))) return false; // 12Y

		adblDate[23] = dtMatIRS.julian();

		if (null == (dtMatIRS = dt.addDays ((int)(365.25 * 15 + 2)))) return false; // 15Y

		adblDate[24] = dtMatIRS.julian();

		if (null == (dtMatIRS = dt.addDays ((int)(365.25 * 20 + 2)))) return false; // 20Y

		adblDate[25] = dtMatIRS.julian();

		if (null == (dtMatIRS = dt.addDays ((int)(365.25 * 25 + 2)))) return false; // 25Y

		adblDate[26] = dtMatIRS.julian();

		if (null == (dtMatIRS = dt.addDays ((int)(365.25 * 30 + 2)))) return false; // 30Y

		adblDate[27] = dtMatIRS.julian();

		if (null == (dtMatIRS = dt.addDays ((int)(365.25 * 40 + 2)))) return false; // 40Y

		adblDate[28] = dtMatIRS.julian();

		if (null == (dtMatIRS = dt.addDays ((int)(365.25 * 50 + 2)))) return false; // 50Y

		adblDate[29] = dtMatIRS.julian();

		adblCompCalibValue[15] = .0166;
		adblCompCalibValue[16] = .0206;
		adblCompCalibValue[17] = .0241;
		adblCompCalibValue[18] = .0269;
		adblCompCalibValue[19] = .0292;
		adblCompCalibValue[20] = .0311;
		adblCompCalibValue[21] = .0326;
		adblCompCalibValue[22] = .0340;
		adblCompCalibValue[23] = .0351;
		adblCompCalibValue[24] = .0375;
		adblCompCalibValue[25] = .0393;
		adblCompCalibValue[26] = .0402;
		adblCompCalibValue[27] = .0407;
		adblCompCalibValue[28] = .0409;
		adblCompCalibValue[29] = .0409;
		org.drip.param.period.CompositePeriodSetting cpsFixed = null;
		org.drip.param.period.CompositePeriodSetting cpsFloating = null;
		org.drip.param.period.UnitCouponAccrualSetting ucasFixed = null;
		org.drip.param.period.ComposableFixedUnitSetting cfusFixed = null;
		org.drip.param.period.UnitCouponAccrualSetting ucasFloating = null;
		org.drip.param.period.ComposableFloatingUnitSetting cfusFloating = null;

		try {
			ucasFloating = new org.drip.param.period.UnitCouponAccrualSetting (4, "Act/360", false,
				"Act/360", false, strCurrency, true);

			ucasFixed = new org.drip.param.period.UnitCouponAccrualSetting (2, "Act/360", false, "Act/360",
				false, strCurrency, true);

			cfusFloating = new org.drip.param.period.ComposableFloatingUnitSetting ("3M",
				org.drip.analytics.support.CompositePeriodBuilder.EDGE_DATE_SEQUENCE_SINGLE, null,
					org.drip.state.identifier.ForwardLabel.Standard (strCurrency + "-LIBOR-3M"),
						org.drip.analytics.support.CompositePeriodBuilder.REFERENCE_PERIOD_IN_ADVANCE, null,
							0.);

			cfusFixed = new org.drip.param.period.ComposableFixedUnitSetting ("6M",
				org.drip.analytics.support.CompositePeriodBuilder.EDGE_DATE_SEQUENCE_REGULAR, null, 0., 0.,
					strCurrency);

			cpsFloating = new org.drip.param.period.CompositePeriodSetting (4, "3M", strCurrency, null,
				org.drip.analytics.support.CompositePeriodBuilder.ACCRUAL_COMPOUNDING_RULE_GEOMETRIC, -1., null,
					null, null, null);

			cpsFixed = new org.drip.param.period.CompositePeriodSetting (2, "6M", strCurrency, null,
				org.drip.analytics.support.CompositePeriodBuilder.ACCRUAL_COMPOUNDING_RULE_GEOMETRIC, 1., null,
					null, null, null);
		} catch (java.lang.Exception e) {
			e.printStackTrace();

			return false;
		}

		for (int i = 0; i < 15; ++i) {
			astrCalibMeasure[i + 15] = "Rate";

			try {
				java.util.List<java.lang.Double> lsFixedStreamEdgeDate =
					org.drip.analytics.support.CompositePeriodBuilder.BackwardEdgeDates (dt, new
						org.drip.analytics.date.JulianDate (adblDate[i + 15]), "6M", null,
							org.drip.analytics.support.CompositePeriodBuilder.SHORT_STUB);

				java.util.List<java.lang.Double> lsFloatingStreamEdgeDate =
					org.drip.analytics.support.CompositePeriodBuilder.BackwardEdgeDates (dt, new
						org.drip.analytics.date.JulianDate (adblDate[i + 15]), "3M", null,
							org.drip.analytics.support.CompositePeriodBuilder.SHORT_STUB);

				org.drip.product.rates.Stream floatingStream = new org.drip.product.rates.Stream
					(org.drip.analytics.support.CompositePeriodBuilder.FloatingCompositeUnit
						(lsFloatingStreamEdgeDate, cpsFloating, ucasFloating, cfusFloating));

				org.drip.product.rates.Stream fixedStream = new org.drip.product.rates.Stream
					(org.drip.analytics.support.CompositePeriodBuilder.FixedCompositeUnit
						(lsFixedStreamEdgeDate, cpsFixed, ucasFixed, cfusFixed));

				aCompCalib[i + 15] = new org.drip.product.rates.FixFloatComponent (fixedStream,
					floatingStream, null);
			} catch (java.lang.Exception e) {
				e.printStackTrace();

				return false;
			}
		}

		long lStart = System.nanoTime();

		if (!mpc.addFixings (dt.addDays (2), org.drip.state.identifier.ForwardLabel.Standard (strCurrency +
			"-LIBOR-6M"), 0.0042))
			return false;

		try {
			if (!mpc.addFixings (org.drip.analytics.date.JulianDate.CreateFromYMD (2010, 12, 14),
				org.drip.state.identifier.ForwardLabel.Standard ("USD-LIBOR-3M"), 0.0042))
				return false;

			(irsc = org.drip.param.creator.ScenarioDiscountCurveBuilder.FromIRCSG (strCurrency,
				org.drip.state.creator.DiscountCurveBuilder.BOOTSTRAP_MODE_CONSTANT_FORWARD,
					aCompCalib)).cookScenarioDC (new org.drip.param.valuation.ValuationParams (dt,
						dt.addBusDays (3, strCurrency), strCurrency), null, adblCompCalibValue, 0.0001,
							astrCalibMeasure, mpc.fixings(), null, 15);
		} catch (java.lang.Exception e) {
			e.printStackTrace();

			return false;
		}

		System.out.println ("IRSDC[" + strCurrency + "] Cooked in: " + (System.nanoTime() - lStart) * 1.e-09
			+ " sec");

		if (!mpc.addScenDC (strCurrency, irsc)) {
			System.out.println ("Cannot add " + strCurrency + " scen curve to MPC!");

			return false;
		}

		if (!AddTSYToMPC (mpc)) System.out.println ("Cannot add TSY marks to MPC!");

		if (!BuildTSYCurve (mpc, dt, strCurrency)) {
			System.out.println ("Cannot add " + strCurrency + " TSY scen curve to MPC!");

			return false;
		}

		return BuildEDSFCurve (mpc, dt, strCurrency);
	}

	/**
	 * Build the credit curve from a set of custom/user-defined quotes for a given EOD and loads them onto
	 *  the MPC
	 *  
	 * @param mpc org.drip.param.definition.MarketParams to be loaded into
	 * @param dt EOD Date
	 * @param strCC String identifying the credit curve
	 * @param strIR String representing the discount curve
	 * @param dblFixedCoupon fixed coupon
	 * @param dblFairPremium fair premium
	 * @param dblRecovery recovery
	 * 
	 * @return Success (true) or failure (false)
	 */

	public static boolean setCC (
		org.drip.param.definition.ScenarioMarketParams mpc,
		final org.drip.analytics.date.JulianDate dt,
		final java.lang.String strCC,
		final java.lang.String strIR,
		final double dblFixedCoupon,
		final double dblFairPremium,
		final double dblRecovery)
	{
		if (null == mpc || null == dt || null == strCC || strCC.isEmpty() || null == strIR || strIR.isEmpty()
			|| java.lang.Double.isNaN (dblFixedCoupon) || java.lang.Double.isNaN (dblFairPremium) ||
				java.lang.Double.isNaN (dblRecovery))
				return false;

		double[] adblQuotes = new double[5];
		org.drip.param.definition.ScenarioCreditCurve ccsc = null;
		java.lang.String[] astrCalibMeasure = new java.lang.String[5];
		org.drip.product.definition.CalibratableFixedIncomeComponent[] aCDS = new
			org.drip.product.definition.CreditDefaultSwap[5];

		for (int i = 0; i < 5; ++i) {
			adblQuotes[i] = dblFairPremium;
			astrCalibMeasure[i] = "FairPremium";

			try {
				if (null == (aCDS[i] = org.drip.product.creator.CDSBuilder.CreateCDS (dt,
					org.drip.analytics.date.JulianDate.CreateFromYMD (2012 + i, 6, 20), dblFixedCoupon,
						strIR, 0.40, strCC, strIR, true)))
					return false;
			} catch (java.lang.Exception e) {
				e.printStackTrace();

				return false;
			}
		}

		long lStart = System.nanoTime();

		try {
			if (!(ccsc = org.drip.param.creator.CreditScenarioCurveBuilder.CreateCCSC (aCDS)).cookScenarioCC
				(strCC, new org.drip.param.valuation.ValuationParams (dt, dt, strIR), mpc.getScenMarketParams
					(aCDS[0], "Base").fundingCurve (org.drip.state.identifier.FundingLabel.Standard
						(aCDS[0].payCurrency()[0])), null, adblQuotes, dblRecovery, astrCalibMeasure, null,
							null, false, 63)) {
				System.out.println ("CC[" + strCC + "] failed to cook");

				return false;
			}

			System.out.println ("CC[" + strCC + "] Cooked in: " + (System.nanoTime() - lStart) * 1.e-09 +
				" sec");

			if (!mpc.addScenCC (strCC, ccsc)) {
				System.out.println ("Cannot add " + strCC + " scen credit curve to MPC!");

				return false;
			}
		} catch (java.lang.Exception e) {
			System.out.println ("Cannot calib credit curve " + strCC);

			e.printStackTrace();

			return false;
		}

		return true;
	}
}
