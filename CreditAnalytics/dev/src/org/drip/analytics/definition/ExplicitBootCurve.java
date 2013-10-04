
package org.drip.analytics.definition;

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
 * In ExplicitBootCurve, the segment boundaries explicitly line up with the instrument maturity boundaries.
 * 	This feature is exploited in building a boot-strappable curve.
 *
 * @author Lakshmi Krishnamurthy
 */

public interface ExplicitBootCurve extends org.drip.analytics.definition.Curve {

	/**
	 * Set the Value/Slope at the Node specified by the Index
	 * 
	 * @param iIndex Node Index
	 * @param dblValue Node Value
	 * 
	 * @return Success (true), failure (false)
	 */

	public abstract boolean setNodeValue (
		final int iIndex,
		final double dblValue);

	/**
	 * Bump the node value at the node specified the index by the value
	 * 
	 * @param iIndex node index
	 * @param dblValue node bump value
	 * 
	 * @return Success (true), failure (false)
	 */

	public abstract boolean bumpNodeValue (
		final int iIndex,
		final double dblValue);

	/**
	 * Set the flat value across all the nodes
	 * 
	 * @param dblValue node value
	 * 
	 * @return Success (true), failure (false)
	 */

	public abstract boolean setFlatValue (
		final double dblValue);
}
