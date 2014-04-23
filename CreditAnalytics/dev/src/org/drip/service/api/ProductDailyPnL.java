
package org.drip.service.api;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2014 Lakshmi Krishnamurthy
 * Copyright (C) 2013 Lakshmi Krishnamurthy
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
 * ProductDailyPnL contains the following daily measures computed:
 * 	- 1D Carry, Roll Down, Curve Shift, and Full Return PnL
 * 	- 3D Carry and Roll Down PnL
 * 	- 3M Carry and Roll Down PnL
 * 	- Current DV01
 * 
 * @author Lakshmi Krishnamurthy
 */

public class ProductDailyPnL {
	private int _i1DFixedAccrualDays = 0;
	private int _i1DFloatingAccrualDays = 0;
	private double _dblDV01 = java.lang.Double.NaN;
	private double _dbl1DCarry = java.lang.Double.NaN;
	private double _dbl1MCarry = java.lang.Double.NaN;
	private double _dbl3MCarry = java.lang.Double.NaN;
	private double _dbl1DFixedDCF = java.lang.Double.NaN;
	private double _dbl1MFixedDCF = java.lang.Double.NaN;
	private double _dbl3MFixedDCF = java.lang.Double.NaN;
	private double _dbl1DRollDown = java.lang.Double.NaN;
	private double _dbl1MRollDown = java.lang.Double.NaN;
	private double _dbl3MRollDown = java.lang.Double.NaN;
	private double _dbl1DCleanPnL = java.lang.Double.NaN;
	private double _dbl1DDirtyPnL = java.lang.Double.NaN;
	private double _dbl1DTotalPnL = java.lang.Double.NaN;
	private double _dbl1DCurveShift = java.lang.Double.NaN;
	private double _dbl1DFloatingDCF = java.lang.Double.NaN;
	private double _dbl1MFloatingDCF = java.lang.Double.NaN;
	private double _dbl3MFloatingDCF = java.lang.Double.NaN;
	private double _dblCleanFixedDV01 = java.lang.Double.NaN;
	private double _dblCleanFloatDV01 = java.lang.Double.NaN;
	private double _dblDV01WithFixing = java.lang.Double.NaN;
	private double _dblPeriodFixedRate = java.lang.Double.NaN;
	private double _dblBaselineSwapRate = java.lang.Double.NaN;
	private double _dblFloatingRateUsed = java.lang.Double.NaN;
	private double _dbl1DRolldownSwapRate = java.lang.Double.NaN;
	private double _dbl1MRolldownSwapRate = java.lang.Double.NaN;
	private double _dbl3MRolldownSwapRate = java.lang.Double.NaN;
	private double _dbl1DCleanPnLWithFixing = java.lang.Double.NaN;
	private double _dbl1DDirtyPnLWithFixing = java.lang.Double.NaN;
	private double _dbl1DTotalPnLWithFixing = java.lang.Double.NaN;
	private double _dbl1DCurveShiftSwapRate = java.lang.Double.NaN;
	private double _dblPeriodCurveFloatingRate = java.lang.Double.NaN;
	private double _dblCleanFloatDV01WithFixing = java.lang.Double.NaN;
	private double _dblPeriodProductFloatingRate = java.lang.Double.NaN;

	/**
	 * ProductDailyPnL constructor
	 * 
	 * @param dbl1DTotalPnL 1D Total PnL
	 * @param dbl1DCleanPnL 1D Clean PnL
	 * @param dbl1DDirtyPnL 1D Dirty PnL
	 * @param dbl1DTotalPnLWithFixing 1D Total PnL With Fixing
	 * @param dbl1DCleanPnLWithFixing 1D Clean PnL With Fixing
	 * @param dbl1DDirtyPnLWithFixing 1D Dirty PnL With Fixing
	 * @param dbl1DCarry 1D Carry PnL
	 * @param dbl1DRollDown 1D Roll Down PnL
	 * @param dbl1DCurveShift 1D Curve Shift PnL
	 * @param dbl1MCarry 1M Carry PnL
	 * @param dbl1MRollDown 1M Roll Down PnL
	 * @param dbl3MCarry 3M Carry PnL
	 * @param dbl3MRollDown 3M Roll Down PnL
	 * @param dblDV01 DV01
	 * @param dblDV01WithFixing DV01 With Fixing
	 * @param dblCleanFixedDV01 Clean Fixed DV01
	 * @param dblCleanFloatDV01 Clean Float DV01
	 * @param dblCleanFloatDV01WithFixing Clean Float DV01 With Fixing
	 * @param dblBaselineSwapRate Baseline Par Swap Rate
	 * @param dbl1DRolldownSwapRate 1D Curve Roll down implied Par Swap rate
	 * @param dbl1MRolldownSwapRate 1M Curve Roll down implied Par Swap rate
	 * @param dbl3MRolldownSwapRate 3M Curve Roll down implied Par Swap rate
	 * @param dbl1DCurveShiftSwapRate 1D Day-to-Day Curve Shift implied Par Swap rate
	 * @param dblPeriodFixedRate The Period Fixed Rate
	 * @param dblPeriodCurveFloatingRate The Period Curve Floating Rate
	 * @param dblPeriodProductFloatingRate The Period Product Floating Rate
	 * @param dblFloatingRateUsed Period Floating Rate Used
	 * @param dbl1DFixedDCF 1D Fixed Coupon DCF
	 * @param dbl1DFloatingDCF 1D Floating Coupon DCF
	 * @param dbl1MFixedDCF 1M Fixed Coupon DCF
	 * @param dbl1MFloatingDCF 1M Floating Coupon DCF
	 * @param dbl3MFixedDCF 3M Fixed Coupon DCF
	 * @param dbl3MFloatingDCF 3M Floating Coupon DCF
	 * 
	 * @throws java.lang.Exception Thrown if inputs are invalid
	 */

	public ProductDailyPnL (
		final double dbl1DTotalPnL,
		final double dbl1DCleanPnL,
		final double dbl1DDirtyPnL,
		final double dbl1DTotalPnLWithFixing,
		final double dbl1DCleanPnLWithFixing,
		final double dbl1DDirtyPnLWithFixing,
		final double dbl1DCarry,
		final double dbl1DRollDown,
		final double dbl1DCurveShift,
		final double dbl1MCarry,
		final double dbl1MRollDown,
		final double dbl3MCarry,
		final double dbl3MRollDown,
		final double dblDV01,
		final double dblDV01WithFixing,
		final double dblCleanFixedDV01,
		final double dblCleanFloatDV01,
		final double dblCleanFloatDV01WithFixing,
		final double dblBaselineSwapRate,
		final double dbl1DRolldownSwapRate,
		final double dbl1MRolldownSwapRate,
		final double dbl3MRolldownSwapRate,
		final double dbl1DCurveShiftSwapRate,
		final double dblPeriodFixedRate,
		final double dblPeriodCurveFloatingRate,
		final double dblPeriodProductFloatingRate,
		final double dblFloatingRateUsed,
		final int i1DFixedAccrualDays,
		final int i1DFloatingAccrualDays,
		final double dbl1DFixedDCF,
		final double dbl1DFloatingDCF,
		final double dbl1MFixedDCF,
		final double dbl1MFloatingDCF,
		final double dbl3MFixedDCF,
		final double dbl3MFloatingDCF)
		throws java.lang.Exception
	{
		if (!org.drip.quant.common.NumberUtil.IsValid (_dbl1DTotalPnL = dbl1DTotalPnL) ||
			!org.drip.quant.common.NumberUtil.IsValid (_dbl1DCleanPnL = dbl1DCleanPnL) ||
				!org.drip.quant.common.NumberUtil.IsValid (_dbl1DDirtyPnL = dbl1DDirtyPnL) ||
					!org.drip.quant.common.NumberUtil.IsValid (_dbl1DTotalPnLWithFixing =
						dbl1DTotalPnLWithFixing) || !org.drip.quant.common.NumberUtil.IsValid
							(_dbl1DCleanPnLWithFixing = dbl1DCleanPnLWithFixing) ||
								!org.drip.quant.common.NumberUtil.IsValid (_dbl1DDirtyPnLWithFixing =
									dbl1DDirtyPnLWithFixing) || !org.drip.quant.common.NumberUtil.IsValid
										(_dbl1DCarry = dbl1DCarry) ||
											!org.drip.quant.common.NumberUtil.IsValid (_dbl1DRollDown =
												dbl1DRollDown) || !org.drip.quant.common.NumberUtil.IsValid
													(_dbl1DCurveShift = dbl1DCurveShift) ||
														!org.drip.quant.common.NumberUtil.IsValid
															(_dbl1MCarry = dbl1MCarry) ||
																!org.drip.quant.common.NumberUtil.IsValid
																	(_dbl1MRollDown = dbl1MRollDown) ||
																		!org.drip.quant.common.NumberUtil.IsValid
			(_dbl3MCarry = dbl3MCarry) || !org.drip.quant.common.NumberUtil.IsValid (_dbl3MRollDown =
				dbl3MRollDown) || !org.drip.quant.common.NumberUtil.IsValid (_dblDV01 = dblDV01) ||
					!org.drip.quant.common.NumberUtil.IsValid (_dblDV01WithFixing = dblDV01WithFixing) ||
						!org.drip.quant.common.NumberUtil.IsValid (_dblCleanFixedDV01 = dblCleanFixedDV01) ||
							!org.drip.quant.common.NumberUtil.IsValid (_dblCleanFloatDV01 =
								dblCleanFloatDV01) || !org.drip.quant.common.NumberUtil.IsValid
									(_dblCleanFloatDV01WithFixing = dblCleanFloatDV01WithFixing) ||
										!org.drip.quant.common.NumberUtil.IsValid (_dblBaselineSwapRate =
											dblBaselineSwapRate) || !org.drip.quant.common.NumberUtil.IsValid
												(_dbl1DRolldownSwapRate = dbl1DRolldownSwapRate) ||
													!org.drip.quant.common.NumberUtil.IsValid
														(_dbl1MRolldownSwapRate = dbl1MRolldownSwapRate) ||
															!org.drip.quant.common.NumberUtil.IsValid
																(_dbl3MRolldownSwapRate =
																	dbl3MRolldownSwapRate) ||
																		!org.drip.quant.common.NumberUtil.IsValid
			(_dbl1DCurveShiftSwapRate = dbl1DCurveShiftSwapRate) || !org.drip.quant.common.NumberUtil.IsValid
				(_dblPeriodFixedRate = dblPeriodFixedRate) || !org.drip.quant.common.NumberUtil.IsValid
					(_dblPeriodCurveFloatingRate = dblPeriodCurveFloatingRate) ||
						!org.drip.quant.common.NumberUtil.IsValid (_dblPeriodProductFloatingRate =
							dblPeriodProductFloatingRate) || !org.drip.quant.common.NumberUtil.IsValid
								(_dblFloatingRateUsed = dblFloatingRateUsed) ||
									!org.drip.quant.common.NumberUtil.IsValid (_dbl1DFixedDCF =
										dbl1DFixedDCF) || !org.drip.quant.common.NumberUtil.IsValid
											(_dbl1DFloatingDCF = dbl1DFloatingDCF) ||
												!org.drip.quant.common.NumberUtil.IsValid (_dbl1MFixedDCF =
													dbl1MFixedDCF) ||
														!org.drip.quant.common.NumberUtil.IsValid
															(_dbl1MFloatingDCF = dbl1MFloatingDCF) ||
																!org.drip.quant.common.NumberUtil.IsValid
																	(_dbl3MFixedDCF = dbl3MFixedDCF) ||
																		!org.drip.quant.common.NumberUtil.IsValid
			(_dbl3MFloatingDCF = dbl3MFloatingDCF))
			throw new java.lang.Exception ("ProductDailyPnL ctr: Invalid Inputs!");

		_i1DFixedAccrualDays = i1DFixedAccrualDays;
		_i1DFloatingAccrualDays = i1DFloatingAccrualDays;
	}

	/**
	 * Retrieve the 1D Clean PnL
	 * 
	 * @return The 1D Clean PnL
	 */

	public double clean1DPnL()
	{
		return _dbl1DCleanPnL;
	}

	/**
	 * Retrieve the 1D Dirty PnL
	 * 
	 * @return The 1D Dirty PnL
	 */

	public double dirty1DPnL()
	{
		return _dbl1DDirtyPnL;
	}

	/**
	 * Retrieve the 1D Total PnL
	 * 
	 * @return The 1D Total PnL
	 */

	public double total1DPnL()
	{
		return _dbl1DTotalPnL;
	}

	/**
	 * Retrieve the 1D Clean PnL With Fixing
	 * 
	 * @return The 1D Clean PnL With Fixing
	 */

	public double clean1DPnLWithFixing()
	{
		return _dbl1DCleanPnLWithFixing;
	}

	/**
	 * Retrieve the 1D Dirty PnL With Fixing
	 * 
	 * @return The 1D Dirty PnL With Fixing
	 */

	public double dirty1DPnLWithFixing()
	{
		return _dbl1DDirtyPnLWithFixing;
	}

	/**
	 * Retrieve the 1D Total PnL With Fixing
	 * 
	 * @return The 1D Total PnL With Fixing
	 */

	public double total1DPnLWithFixing()
	{
		return _dbl1DTotalPnLWithFixing;
	}

	/**
	 * Retrieve the 1D Carry
	 * 
	 * @return The 1D Carry
	 */

	public double carry1D()
	{
		return _dbl1DCarry;
	}

	/**
	 * Retrieve the 1D Roll Down
	 * 
	 * @return The 1D Roll Down
	 */

	public double rollDown1D()
	{
		return _dbl1DRollDown;
	}

	/**
	 * Retrieve the 1D Curve Shift
	 * 
	 * @return The 1D Curve Shift
	 */

	public double curveShift1D()
	{
		return _dbl1DCurveShift;
	}

	/**
	 * Retrieve the 1M Carry
	 * 
	 * @return The 1M Carry
	 */

	public double carry1M()
	{
		return _dbl1MCarry;
	}

	/**
	 * Retrieve the 1M Roll Down
	 * 
	 * @return The 1M Roll Down
	 */

	public double rollDown1M()
	{
		return _dbl1MRollDown;
	}

	/**
	 * Retrieve the 3M Carry
	 * 
	 * @return The 3M Carry
	 */

	public double carry3M()
	{
		return _dbl3MCarry;
	}

	/**
	 * Retrieve the 3M Roll Down
	 * 
	 * @return The 3M Roll Down
	 */

	public double rollDown3M()
	{
		return _dbl3MRollDown;
	}

	/**
	 * Retrieve the DV01
	 * 
	 * @return The DV01
	 */

	public double DV01()
	{
		return _dblDV01;
	}

	/**
	 * Retrieve the DV01 With Fixing
	 * 
	 * @return The DV01 With Fixing
	 */

	public double DV01WithFixing()
	{
		return _dblDV01WithFixing;
	}

	/**
	 * Retrieve the Clean Fixed DV01
	 * 
	 * @return The Clean Fixed DV01
	 */

	public double cleanFixedDV01()
	{
		return _dblCleanFixedDV01;
	}

	/**
	 * Retrieve the Clean Float DV01
	 * 
	 * @return The Clean Float DV01
	 */

	public double cleanFloatDV01()
	{
		return _dblCleanFloatDV01;
	}

	/**
	 * Retrieve the Clean Float DV01 With Fixing
	 * 
	 * @return The Clean Float DV01 With Fixing
	 */

	public double cleanFloatDV01WithFixing()
	{
		return _dblCleanFloatDV01WithFixing;
	}

	/**
	 * Retrieve the Baseline Swap Rate
	 * 
	 * @return The Baseline Swap Rate
	 */

	public double baselineSwapRate()
	{
		return _dblBaselineSwapRate;
	}

	/**
	 * Retrieve the 1D Roll Down Swap Rate
	 * 
	 * @return The 1D Roll Down Swap Rate
	 */

	public double rolldownSwapRate1D()
	{
		return _dbl1DRolldownSwapRate;
	}

	/**
	 * Retrieve the 1M Roll Down Swap Rate
	 * 
	 * @return The 1M Roll Down Swap Rate
	 */

	public double rolldownSwapRate1M()
	{
		return _dbl1MRolldownSwapRate;
	}

	/**
	 * Retrieve the 3M Roll Down Swap Rate
	 * 
	 * @return The 3M Roll Down Swap Rate
	 */

	public double rolldownSwapRate3M()
	{
		return _dbl3MRolldownSwapRate;
	}

	/**
	 * Retrieve the 1D Curve Shift Swap Rate
	 * 
	 * @return The 1D Curve Shift Swap Rate
	 */

	public double curveShiftSwapRate1D()
	{
		return _dbl1DCurveShiftSwapRate;
	}

	/**
	 * Retrieve the Period Fixed Rate
	 * 
	 * @return The Period Fixed Rate
	 */

	public double periodFixedRate()
	{
		return _dblPeriodFixedRate;
	}

	/**
	 * Retrieve the Period Curve Floating Rate
	 * 
	 * @return The Period Curve Floating Rate
	 */

	public double periodCurveFloatingRate()
	{
		return _dblPeriodCurveFloatingRate;
	}

	/**
	 * Retrieve the Period Product Floating Rate
	 * 
	 * @return The Period Product Floating Rate
	 */

	public double periodProductFloatingRate()
	{
		return _dblPeriodProductFloatingRate;
	}

	/**
	 * Retrieve the Floating Rate Used
	 * 
	 * @return The Floating Rate Used
	 */

	public double floatingRateUsed()
	{
		return _dblFloatingRateUsed;
	}

	/**
	 * Retrieve the 1D Fixed Accrual Period
	 * 
	 * @return The 1D Fixed Accrual Period
	 */

	public int fixed1DAccrualDays()
	{
		return _i1DFixedAccrualDays;
	}

	/**
	 * Retrieve the 1D Floating Accrual Period
	 * 
	 * @return The 1D Floating Accrual Period
	 */

	public int floating1DAccrualDays()
	{
		return _i1DFloatingAccrualDays;
	}

	/**
	 * Retrieve the Period 1D Fixed DCF
	 * 
	 * @return The Period 1D Fixed DCF
	 */

	public double fixed1DDCF()
	{
		return _dbl1DFixedDCF;
	}

	/**
	 * Retrieve the Period 1D Floating DCF
	 * 
	 * @return The Period 1D Floating DCF
	 */

	public double floating1DDCF()
	{
		return _dbl1DFloatingDCF;
	}

	/**
	 * Retrieve the Period 1M Fixed DCF
	 * 
	 * @return The Period 1M Fixed DCF
	 */

	public double fixed1MDCF()
	{
		return _dbl1MFixedDCF;
	}

	/**
	 * Retrieve the Period 1M Floating DCF
	 * 
	 * @return The Period 1M Floating DCF
	 */

	public double floating1MDCF()
	{
		return _dbl1MFloatingDCF;
	}

	/**
	 * Retrieve the Period 3M Fixed DCF
	 * 
	 * @return The Period 3M Fixed DCF
	 */

	public double fixed3MDCF()
	{
		return _dbl3MFixedDCF;
	}

	/**
	 * Retrieve the Period 3M Floating DCF
	 * 
	 * @return The Period 3M Floating DCF
	 */

	public double floating3MDCF()
	{
		return _dbl3MFloatingDCF;
	}

	/**
	 * Retrieve the Array of Metrics
	 * 
	 * @return The Array of Metrics
	 */

	public double[] toArray()
	{
		java.util.List<java.lang.Double> lsPnLMetric = new java.util.ArrayList<java.lang.Double>();

		lsPnLMetric.add (_dbl1DTotalPnL);

		lsPnLMetric.add (_dbl1DCleanPnL);

		lsPnLMetric.add (_dbl1DDirtyPnL);

		lsPnLMetric.add (_dbl1DTotalPnLWithFixing);

		lsPnLMetric.add (_dbl1DCleanPnLWithFixing);

		lsPnLMetric.add (_dbl1DDirtyPnLWithFixing);

		lsPnLMetric.add (_dbl1DCarry);

		lsPnLMetric.add (_dbl1DRollDown);

		lsPnLMetric.add (_dbl1DCurveShift);

		lsPnLMetric.add (_dbl1MCarry);

		lsPnLMetric.add (_dbl1MRollDown);

		lsPnLMetric.add (_dbl3MCarry);

		lsPnLMetric.add (_dbl3MRollDown);

		lsPnLMetric.add (_dblDV01);

		lsPnLMetric.add (_dblDV01WithFixing);

		lsPnLMetric.add (_dblCleanFixedDV01);

		lsPnLMetric.add (_dblCleanFloatDV01);

		lsPnLMetric.add (_dblCleanFloatDV01WithFixing);

		lsPnLMetric.add (_dblBaselineSwapRate);

		lsPnLMetric.add (_dbl1DRolldownSwapRate);

		lsPnLMetric.add (_dbl1MRolldownSwapRate);

		lsPnLMetric.add (_dbl3MRolldownSwapRate);

		lsPnLMetric.add (_dbl1DCurveShiftSwapRate);

		lsPnLMetric.add (_dblPeriodFixedRate);

		lsPnLMetric.add (_dblPeriodCurveFloatingRate);

		lsPnLMetric.add (_dblPeriodProductFloatingRate);

		lsPnLMetric.add (_dblFloatingRateUsed);

		lsPnLMetric.add ((double) _i1DFixedAccrualDays);

		lsPnLMetric.add ((double) _i1DFloatingAccrualDays);

		lsPnLMetric.add (_dbl1DFixedDCF);

		lsPnLMetric.add (_dbl1DFloatingDCF);

		lsPnLMetric.add (_dbl1MFixedDCF);

		lsPnLMetric.add (_dbl1MFloatingDCF);

		lsPnLMetric.add (_dbl3MFixedDCF);

		lsPnLMetric.add (_dbl3MFloatingDCF);

		int i = 0;

		double[] adblSPCA = new double[lsPnLMetric.size()];

		for (double dbl : lsPnLMetric)
			adblSPCA[i++] = dbl;

		return adblSPCA;
	}

	@Override public java.lang.String toString()
	{
		java.lang.StringBuffer sb = new java.lang.StringBuffer();

		boolean bStart = true;

		for (double dbl : toArray()) {
			if (bStart)
				bStart = false;
			else
				sb.append (",");

			sb.append (dbl);
		}

		return sb.toString();
	}
}
