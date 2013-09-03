
package org.drip.math.spline;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2013 Lakshmi Krishnamurthy
 * 
 * This file is part of CreditAnalytics, a free-software/open-source library for fixed income analysts and
 * 		developers - http://www.credit-trader.org
 * 
 * CreditAnalytics is a free, full featured, fixed income credit analytics library, developed with a special
 * 		focus towards the needs of the bonds and credit products community.
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
 * SegmentInelasticParams implements basis per-segment elastics parameter set. Currently it contains Ck, the
 *  roughness penalty derivative order, and the segment specific constraints.
 *
 * @author Lakshmi Krishnamurthy
 */

public class SegmentInelasticParams {
	private int _iCk = -1;
	private int _iRoughnessPenaltyDerivativeOrder = -1;
	private org.drip.math.spline.SegmentNodeWeightConstraint[] _aSNWC = null;

	/**
	 * Create the C2 Segment Elastic Parameters
	 * 
	 * @param aSNWC Array of Segment Constraints
	 * 
	 * @return SegmentElasticParams instance
	 */

	public static final SegmentInelasticParams MakeC2SegmentElasticParams (
		final org.drip.math.spline.SegmentNodeWeightConstraint[] aSNWC)
	{
		try {
			return new SegmentInelasticParams (2, 2, aSNWC);
		} catch (java.lang.Exception e) {
			e.printStackTrace();
		}

		return null;
	}

	/**
	 * ElasticParams constructor
	 * 
	 * @param iCk Continuity Order
	 * @param iRoughnessPenaltyDerivativeOrder Roughness Penalty Derivative Order
	 * @param aSNWC Array of Segment Constraints
	 * 
	 * @throws java.lang.Exception Thrown if the inputs are invalid
	 */

	public SegmentInelasticParams (
		final int iCk,
		final int iRoughnessPenaltyDerivativeOrder,
		final org.drip.math.spline.SegmentNodeWeightConstraint[] aSNWC)
		throws java.lang.Exception
	{
		_aSNWC = aSNWC;

		if (0 > (_iCk = iCk) || 0 >= (_iRoughnessPenaltyDerivativeOrder = iRoughnessPenaltyDerivativeOrder))
			throw new java.lang.Exception ("SegmentInelasticParams ctr: Invalid Inputs");
	}

	/**
	 * Retrieve the Roughness Penalty Derivative Order
	 * 
	 * @return The Roughness Penalty Derivative Order
	 */

	public int getRoughnessPenaltyDerivativeOrder()
	{
		return _iRoughnessPenaltyDerivativeOrder;
	}

	/**
	 * Retrieve the Continuity Order
	 * 
	 * @return The Continuity Order
	 */

	public int getCk()
	{
		return _iCk;
	}

	/**
	 * Retrieve the Array of Linear Constraints
	 * 
	 * @return Array of Linear Constraints
	 */

	public org.drip.math.spline.SegmentNodeWeightConstraint[] getLinearConstraint()
	{
		return _aSNWC;
	}
}