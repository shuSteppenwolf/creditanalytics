
package org.drip.product.definition;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2013 Lakshmi Krishnamurthy
 * Copyright (C) 2012 Lakshmi Krishnamurthy
 * Copyright (C) 2011 Lakshmi Krishnamurthy
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
 * This interface provides stubs for component name, IR curve, credit curve, TSY curve, and EDSF curve needed
 * 	to value the component.
 *
 * @author Lakshmi Krishnamurthy
 */

public interface ComponentMarketParamRef {

	/**
	 * Gets the component name
	 * 
	 * @return The component name
	 */

	public abstract java.lang.String getComponentName();

	/**
	 * Gets the IR curve name
	 * 
	 * @return The IR curve name
	 */

	public abstract java.lang.String getIRCurveName();

	/**
	 * Gets the component name
	 * 
	 * @return The component name
	 */

	public abstract java.lang.String getRatesForwardCurveName();

	/**
	 * Gets the credit curve name
	 * 
	 * @return The credit curve name
	 */

	public abstract java.lang.String getCreditCurveName();

	/**
	 * Gets the treasury curve name
	 * 
	 * @return The treasury curve name
	 */

	public abstract java.lang.String getTreasuryCurveName();

	/**
	 * Gets the EDSF curve name
	 * 
	 * @return The EDSF curve name
	 */

	public abstract java.lang.String getEDSFCurveName();
}