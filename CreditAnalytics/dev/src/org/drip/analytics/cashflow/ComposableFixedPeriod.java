
package org.drip.analytics.cashflow;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2014 Lakshmi Krishnamurthy
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
 * ComposableFixedPeriod contains the fixed cash flow periods' composable sub period details. Currently it
 *  holds the accrual start date, the accrual end date, the fixed coupon, the basis spread, coupon and
 *  accrual day counts, and the EOM adjustment flags.
 *
 * @author Lakshmi Krishnamurthy
 */

public class ComposableFixedPeriod extends org.drip.analytics.cashflow.ComposablePeriod {
	private double _dblBasis = java.lang.Double.NaN;
	private double _dblFixedCoupon = java.lang.Double.NaN;

	/**
	 * The ComposableFixedPeriod constructor
	 * 
	 * @param dblAccrualStartDate Accrual Start Date
	 * @param dblAccrualEndDate Accrual End Date
	 * @param strCouponDC Coupon Day Count
	 * @param bCouponEOMAdjustment Coupon EOM Adjustment Flag
	 * @param strAccrualDC Accrual Day Count
	 * @param bAccrualEOMAdjustment Accrual EOM Adjustment Flag
	 * @param strCalendar Calendar
	 * @param dblFullCouponDCF The Period's Full Coupon DCF
	 * @param dblNotional The Period Notional
	 * @param dblFixedCoupon Fixed Coupon (Annualized)
	 * @param dblBasis Basis over the Fixed Coupon in the same units
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public ComposableFixedPeriod (
		final double dblAccrualStartDate,
		final double dblAccrualEndDate,
		final java.lang.String strCouponDC,
		final boolean bCouponEOMAdjustment,
		final java.lang.String strAccrualDC,
		final boolean bAccrualEOMAdjustment,
		final java.lang.String strCalendar,
		final double dblFullCouponDCF,
		final double dblNotional,
		final double dblFixedCoupon,
		final double dblBasis)
		throws java.lang.Exception
	{
		super (dblAccrualStartDate, dblAccrualEndDate, strCouponDC, bCouponEOMAdjustment, strAccrualDC,
			bAccrualEOMAdjustment, strCalendar, dblFullCouponDCF, dblNotional);

		if (!org.drip.quant.common.NumberUtil.IsValid (_dblFixedCoupon = dblFixedCoupon) ||
			!org.drip.quant.common.NumberUtil.IsValid (_dblBasis = dblBasis))
			throw new java.lang.Exception ("ComposableFixedPeriod ctr: Invalid Inputs");
	}

	/**
	 * Retrieve the Fixed Coupon
	 * 
	 * @return The Fixed Coupon
	 */

	public double fixedCoupon()
	{
		return _dblFixedCoupon;
	}

	/**
	 * Retrieve the Basis
	 * 
	 * @return The Basis
	 */

	public double basis()
	{
		return _dblBasis;
	}

	/**
	 * Get the Accrued01 to an accrual end date
	 * 
	 * @param dblAccrualEnd Accrual End Date
	 * 
	 * @return The Accrued01
	 * 
	 * @exception Thrown if inputs are invalid, or if the date does not lie within the period
	 */

	public double accrued01 (
		final double dblAccrualEnd)
		throws java.lang.Exception
	{
		return 0.0001 * notional() * accrualDCF (dblAccrualEnd);
	}

	@Override public double accrued (
		final double dblAccrualEnd,
		final org.drip.param.market.CurveSurfaceQuoteSet csqs)
		throws java.lang.Exception
	{
		return notional() * (_dblFixedCoupon + _dblBasis) * accrualDCF (dblAccrualEnd);
	}

	@Override public double fullCouponAccrued (
		final org.drip.param.market.CurveSurfaceQuoteSet csqs)
	{
		return notional() * (_dblFixedCoupon + _dblBasis) * fullCouponDCF();
	}
}
