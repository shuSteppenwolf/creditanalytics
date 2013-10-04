
package org.drip.state.representation;

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
 * LatentState exposes the functionality to manipulate the hidden Variable's Latent State.
 *
 * @author Lakshmi Krishnamurthy
 */

public interface LatentState {

	/**
	 * Retrieve the Array of the LSMM
	 * 
	 * @return Array of the LSMM
	 */

	public abstract org.drip.state.representation.LatentStateMetricMeasure[] lsmm();

	/**
	 * Create a LatentState Instance from the Manifest Measure Parallel Shift
	 * 
	 * @param dblShift Parallel shift of the Manifest Measure
	 * 
	 * @return New LatentState Instance corresponding to the Parallel Shifted Manifest Measure
	 */

	public abstract LatentState parallelShiftManifestMeasure (
		final double dblShift);

	/**
	 * Create a LatentState Instance from the Shift of the Specified Manifest Measure
	 * 
	 * @param iSpanIndex Index into the Span that identifies the Instrument
	 * @param dblShift Shift of the Manifest Measure
	 * 
	 * @return New LatentState Instance corresponding to the Shift of the Specified Manifest Measure
	 */

	public abstract LatentState shiftManifestMeasure (
		final int iSpanIndex,
		final double dblShift);

	/**
	 * Create a LatentState Instance from the Manifest Measure Tweak Parameters
	 * 
	 * @param rvtp Manifest Measure Tweak Parameters
	 * 
	 * @return New LatentState Instance corresponding to the Tweaked Manifest Measure
	 */

	public abstract LatentState customTweakManifestMeasure (
		final org.drip.param.definition.ResponseValueTweakParams rvtp);

	/**
	 * Create a LatentState Instance from the Quantification Metric Parallel Shift
	 * 
	 * @param dblShift Parallel shift of the Quantification Metric
	 * 
	 * @return New LatentState Instance corresponding to the Parallel Shifted Quantification Metric
	 */

	public abstract LatentState parallelShiftQuantificationMetric (
		final double dblShift);

	/**
	 * Create a LatentState Instance from the Quantification Metric Tweak Parameters
	 * 
	 * @param rvtp Quantification Metric Tweak Parameters
	 * 
	 * @return New LatentState Instance corresponding to the Tweaked Quantification Metric
	 */

	public abstract LatentState customTweakQuantificationMetric (
		final org.drip.param.definition.ResponseValueTweakParams rvtp);
}