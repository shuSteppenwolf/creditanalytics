
package org.drip.learning.kernel;

/*
 * -*- mode: java; tab-width: 4; indent-tabs-mode: nil; c-basic-offset: 4 -*-
 */

/*!
 * Copyright (C) 2015 Lakshmi Krishnamurthy
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
 * IntegralOperatorEigenComponent holds the Eigen-Function Space and the Eigenvalue Functions/Spaces of the
 *  R^x L2 -> R^x L2 Kernel Linear Integral Operator defined by:
 * 
 * 		T_k [f(.)] := Integral Over Input Space {k (., y) * f(y) * d[Prob(y)]}
 *  
 *  The References are:
 *  
 *  1) Ash, R. (1965): Information Theory, Inter-science New York.
 *  
 *  2) Konig, H. (1986): Eigenvalue Distribution of Compact Operators, Birkhauser, Basel, Switzerland. 
 *  
 *  3) Smola, A. J., A. Elisseff, B. Scholkopf, and R. C. Williamson (2000): Entropy Numbers for Convex
 *  	Combinations and mlps, in: Advances in Large Margin Classifiers, A. Smola, P. Bartlett, B. Scholkopf,
 *  	and D. Schuurmans - editors, MIT Press, Cambridge, MA.
 *
 * @author Lakshmi Krishnamurthy
 */

public class IntegralOperatorEigenComponent {
	private double _dblEigenValue = java.lang.Double.NaN;
	private org.drip.learning.kernel.EigenFunction _eigenFunction = null;
	private org.drip.spaces.RxToR1.NormedRdToNormedR1 _rkhsFeatureMap = null;

	/**
	 * IntegralOperatorEigenComponent Constructor
	 * 
	 * @param eigenFunction Normed R^d -> Normed L2 R^1 Eigen-Function
	 * @param dblEigenValue The Eigenvalue
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are Invalid
	 */

	public IntegralOperatorEigenComponent (
		final org.drip.learning.kernel.EigenFunction eigenFunction,
		final double dblEigenValue)
		throws java.lang.Exception
	{
		if (null == (_eigenFunction = eigenFunction) || !org.drip.quant.common.NumberUtil.IsValid
			(_dblEigenValue = dblEigenValue))
			throw new java.lang.Exception ("IntegralOperatorEigenComponent ctr: Invalid Inputs");

		final org.drip.function.deterministic.RdToR1 eigenFuncRdToR1 = _eigenFunction.function();

		if (null != eigenFuncRdToR1) {
			org.drip.function.deterministic.RdToR1 rkhsFeatureMapRdToR1 = new
				org.drip.function.deterministic.RdToR1 (null) {
				@Override public double evaluate (
					final double[] adblX)
					throws java.lang.Exception
				{
					return java.lang.Math.sqrt (_dblEigenValue) * eigenFuncRdToR1.evaluate (adblX);
				}
			};

			org.drip.spaces.metric.RealMultidimensionalNormedSpace rmnsInput = _eigenFunction.input();

			org.drip.spaces.metric.RealUnidimensionalNormedSpace runsOutput = _eigenFunction.output();

			org.drip.spaces.metric.ContinuousRealUnidimensional cru =
				org.drip.spaces.metric.ContinuousRealUnidimensional.Standard (runsOutput.leftEdge(),
					runsOutput.rightEdge(), runsOutput.borelSigmaMeasure(), 2);

			_rkhsFeatureMap = rmnsInput instanceof
				org.drip.spaces.metric.CombinatorialRealMultidimensionalBanach ? new
					org.drip.spaces.RxToR1.NormedRdCombinatorialToR1Continuous (rkhsFeatureMapRdToR1,
						(org.drip.spaces.metric.CombinatorialRealMultidimensionalBanach) rmnsInput, cru) :
							new org.drip.spaces.RxToR1.NormedRdContinuousToR1Continuous
								(rkhsFeatureMapRdToR1,
									(org.drip.spaces.metric.ContinuousRealMultidimensionalBanach) rmnsInput,
										cru);
		}
	}

	/**
	 * Retrieve the Eigen-Function
	 * 
	 * @return The Eigen-Function
	 */

	public org.drip.learning.kernel.EigenFunction eigenFunction()
	{
		return _eigenFunction;
	}

	/**
	 * Retrieve the Eigenvalue
	 * 
	 * @return The Eigenvalue
	 */

	public double eigenvalue()
	{
		return _dblEigenValue;
	}

	/**
	 * Retrieve the Feature Map Space represented via the Reproducing Kernel Hilbert Space
	 * 
	 * @return The Feature Map Space representation using the Reproducing Kernel Hilbert Space
	 */

	public org.drip.spaces.RxToR1.NormedRdToNormedR1 rkhsFeatureMap()
	{
		return _rkhsFeatureMap;
	}

	/**
	 * Retrieve the RKHS Feature Map Parallelepiped Agnostic Upper Bound Length
	 * 
	 * @return The RKHS Feature Map Parallelepiped Agnostic Upper Bound Length
	 */

	public double rkhsFeatureParallelepipedLength()
	{
		return 2. * _eigenFunction.agnosticUpperBound() * java.lang.Math.sqrt (_dblEigenValue);
	}

	/**
	 * Compute the Eigen-Component Contribution to the Kernel Value
	 * 
	 * @param adblX The X Variate Array
	 * @param adblY The Y Variate Array
	 * 
	 * @return The Eigen-Component Contribution to the Kernel Value
	 * 
	 * @throws java.lang.Exception Thrown if the Inputs are invalid
	 */

	public double evaluate (
		final double[] adblX,
		final double[] adblY)
		throws java.lang.Exception
	{
		org.drip.function.deterministic.RdToR1 eigenFuncRdToR1 = _eigenFunction.function();

		return eigenFuncRdToR1.evaluate (adblX) * eigenFuncRdToR1.evaluate (adblY) * _dblEigenValue;
	}
}
