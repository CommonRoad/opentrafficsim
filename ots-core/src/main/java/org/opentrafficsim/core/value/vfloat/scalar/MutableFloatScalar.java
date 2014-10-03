package org.opentrafficsim.core.value.vfloat.scalar;

import org.opentrafficsim.core.unit.SICoefficients;
import org.opentrafficsim.core.unit.SIUnit;
import org.opentrafficsim.core.unit.Unit;
import org.opentrafficsim.core.value.Absolute;
import org.opentrafficsim.core.value.Relative;
import org.opentrafficsim.core.value.ValueUtil;
import org.opentrafficsim.core.value.vfloat.FloatMathFunctions;

/**
 * MutableFloatScalar.
 * <p>
 * Copyright (c) 2013-2014 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights
 * reserved. <br>
 * BSD-style license. See <a href="http://opentrafficsim.org/node/13">OpenTrafficSim License</a>.
 * <p>
 * @version Sep 5, 2014 <br>
 * @author <a href="http://www.tudelft.nl/pknoppers">Peter Knoppers</a>
 * @param <U> the unit of the values in the constructor and for display
 */
public abstract class MutableFloatScalar<U extends Unit<U>> extends FloatScalar<U> implements FloatMathFunctions
{
    /** */
    private static final long serialVersionUID = 20140905L;

    /**
     * Construct a new MutableFloatScalar.
     * @param unit U; the unit of the new MutableFloatScalar
     */
    protected MutableFloatScalar(final U unit)
    {
        super(unit);
    }

    /**
     * @param <U> Unit
     */
    public static class Abs<U extends Unit<U>> extends MutableFloatScalar<U> implements Absolute, Comparable<Abs<U>>
    {
        /** */
        private static final long serialVersionUID = 20140905L;

        /**
         * Construct a new Absolute MutableFloatScalar.
         * @param value float; the value of the new Absolute MutableFloatScalar
         * @param unit U; the unit of the new Absolute MutableFloatScalar
         */
        public Abs(final float value, final U unit)
        {
            super(unit);
            // System.out.println("Created Abs");
            initialize(value);
        }

        /**
         * Construct a new Absolute MutableFloatScalar from an existing Absolute Immutable FloatScalar.
         * @param value FloatScalar.Abs&lt;U&gt;; the reference
         */
        public Abs(final FloatScalar.Abs<U> value)
        {
            super(value.getUnit());
            // System.out.println("Created Abs");
            initialize(value);
        }

        /**
         * Construct a new Absolute MutableFloatScalar from an existing Absolute MutableFloatScalar.
         * @param value MutableFloatScalar.Abs&lt;U&gt;; the reference
         */
        public Abs(final MutableFloatScalar.Abs<U> value)
        {
            super(value.getUnit());
            // System.out.println("Created Abs");
            initialize(value);
        }

        /** {@inheritDoc} */
        @Override
        public final MutableFloatScalar.Abs<U> mutable()
        {
            return new MutableFloatScalar.Abs<U>(this);
        }

        /** {@inheritDoc} */
        @Override
        public final FloatScalar.Abs<U> immutable()
        {
            return new FloatScalar.Abs<U>(this);
        }

        /** {@inheritDoc} */
        @Override
        public final int compareTo(final Abs<U> o)
        {
            return new Float(getValueSI()).compareTo(o.getValueSI());
        }

        /** {@inheritDoc} */
        @Override
        public final MutableFloatScalar.Abs<U> copy()
        {
            return new MutableFloatScalar.Abs<U>(this);
        }

    }

    /**
     * @param <U> Unit
     */
    public static class Rel<U extends Unit<U>> extends MutableFloatScalar<U> implements Relative, Comparable<Rel<U>>
    {
        /** */
        private static final long serialVersionUID = 20140905L;

        /**
         * Construct a new Relative MutableFloatScalar.
         * @param value float; the value of the new Relative MutableFloatScalar
         * @param unit U; the unit of the new Relative MutableFloatScalar
         */
        public Rel(final float value, final U unit)
        {
            super(unit);
            // System.out.println("Created Rel");
            initialize(value);
        }

        /**
         * Construct a new Relative MutableFloatScalar from an existing Relative Immutable FloatScalar.
         * @param value FloatScalar.Rel&lt;U&gt;; the reference
         */
        public Rel(final FloatScalar.Rel<U> value)
        {
            super(value.getUnit());
            // System.out.println("Created Rel");
            initialize(value);
        }

        /**
         * Construct a new Relative MutableFloatScalar from an existing Relative MutableFloatScalar.
         * @param value MutableFloatScalar.Rel&lt;U&gt;; the reference
         */
        public Rel(final MutableFloatScalar.Rel<U> value)
        {
            super(value.getUnit());
            // System.out.println("Created Rel");
            initialize(value);
        }

        /** {@inheritDoc} */
        @Override
        public final MutableFloatScalar.Rel<U> mutable()
        {
            return new MutableFloatScalar.Rel<U>(this);
        }

        /** {@inheritDoc} */
        @Override
        public final FloatScalar.Rel<U> immutable()
        {
            return new FloatScalar.Rel<U>(this);
        }

        /** {@inheritDoc} */
        @Override
        public final int compareTo(final Rel<U> o)
        {
            return new Float(getValueSI()).compareTo(o.getValueSI());
        }

        /** {@inheritDoc} */
        @Override
        public final MutableFloatScalar.Rel<U> copy()
        {
            return new MutableFloatScalar.Rel<U>(this);
        }

    }

    /**
     * Construct an immutable version of this MutableFloatScalar. <br>
     * The immutable version is created as a deep copy of this. Delayed copying is not worthwhile for a Scalar.
     * @return FloatScalar&lt;U&gt;; immutable version of this FloatScalar
     */
    public abstract FloatScalar<U> immutable();

    /**
     * Replace the stored value by the supplied value which is expressed in the standard SI unit.
     * @param valueSI float; the value to store (value must already be in the standard SI unit)
     */
    final void setSI(final float valueSI)
    {
        setValueSI(valueSI);
    }

    /**
     * Replace the stored value by the supplied value.
     * @param value FloatScalar&lt;U&gt;; the strongly typed value to store
     */
    final void set(final FloatScalar<U> value)
    {
        setValueSI(value.getValueSI());
    }

    /**
     * Replace the stored value by the supplied value which can be expressed in any compatible unit.
     * @param value float; the value to store
     * @param valueUnit U; the unit of the supplied value
     */
    final void setInUnit(final float value, final U valueUnit)
    {
        setValueSI((float) ValueUtil.expressAsSIUnit(value, valueUnit));
    }

    /**********************************************************************************/
    /******************************* NON-STATIC METHODS *******************************/
    /**********************************************************************************/

    /**
     * Add another value to this value. Only Relative values are allowed; adding an absolute value to an absolute value
     * is not allowed. Adding an absolute value to an existing relative value would require the result to become
     * absolute, which is a type change that is impossible. For that operation, use a static method.
     * @param value FloatScalar.Rel&lt;U&gt;; the value to add
     */
    public final void add(final FloatScalar.Rel<U> value)
    {
        setValueSI(getValueSI() + value.getValueSI());
    }

    /**
     * Subtract another value from this value. Only relative values are allowed; subtracting an absolute value from a
     * relative value is not allowed. Subtracting an absolute value from an existing absolute value would require the
     * result to become relative, which is a type change that is impossible. For that operation, use a static method.
     * @param value FloatScalar.Rel&lt;U&gt;; the value to subtract
     */
    public final void subtract(final FloatScalar.Rel<U> value)
    {
        setValueSI(getValueSI() - value.getValueSI());
    }

    /**********************************************************************************/
    /********************************* STATIC METHODS *********************************/
    /**********************************************************************************/

    /**
     * Increment the stored value by a specified amount.
     * @param increment FloatScalar&lt;?&gt;; the amount by which to increment the stored value
     * @return FloatScalar&lt;?&gt;; the modified MutableFloatScalar
     */
    protected final FloatScalar<?> incrementBy(final FloatScalar<?> increment)
    {
        setValueSI(getValueSI() + increment.getValueSI());
        return this;
    }

    /**
     * Add a number of relative values to an absolute value. Return a new instance of the value. The unit of the return
     * value will be the unit of the first argument. Due to type erasure of generics, the method cannot check whether an
     * array of arguments submitted to the varargs has a mixed-unit content at runtime.
     * @param valueAbs FloatScalar.Abs&lt;U&gt;; the absolute base value
     * @param valuesRel FloatScalar.Rel&lt;U&gt;...; zero or more relative values to add to the absolute value
     * @param <U> Unit; the unit of the parameters and the result
     * @return MutableFloatScalar.Abs&lt;U&gt;; the sum of the values as an absolute value
     */
    @SafeVarargs
    public static <U extends Unit<U>> MutableFloatScalar.Abs<U> plus(final FloatScalar.Abs<U> valueAbs,
            final FloatScalar.Rel<U>... valuesRel)
    {
        MutableFloatScalar.Abs<U> result = new MutableFloatScalar.Abs<U>(valueAbs);
        for (FloatScalar.Rel<U> v : valuesRel)
        {
            result.incrementBy(v);
        }
        return result;
    }

    /**
     * Add a number of relative values. Return a new instance of the value. Due to type erasure of generics, the method
     * cannot check whether an array of arguments submitted to the varargs has a mixed-unit content at runtime.
     * @param targetUnit U; the unit of the sum
     * @param valuesRel FloatScalar.Rel&lt;U&gt;...; zero or more relative values to add together
     * @param <U> Unit; the unit of the parameters and the result
     * @return MutableFloatScalar.Rel&lt;U&gt;; the sum of the values as a relative value
     */
    @SafeVarargs
    public static <U extends Unit<U>> MutableFloatScalar.Rel<U> plus(final U targetUnit,
            final FloatScalar.Rel<U>... valuesRel)
    {
        MutableFloatScalar.Rel<U> result = new MutableFloatScalar.Rel<U>(0.0f, targetUnit);
        for (FloatScalar.Rel<U> v : valuesRel)
        {
            result.incrementBy(v);
        }
        return result;
    }

    /**
     * Decrement the stored value by a specified amount.
     * @param decrement FloatScalar&lt;?&gt;; the amount by which to decrement the stored value
     * @return FloatScalar&lt;?&gt;; the modified MutableFloatScalar
     */
    protected final FloatScalar<?> decrementBy(final FloatScalar<?> decrement)
    {
        setValueSI(getValueSI() - decrement.getValueSI());
        return this;
    }

    /**
     * Subtract a number of relative values from an absolute value. Return a new instance of the value. The unit of the
     * return value will be the unit of the first argument. Due to type erasure of generics, the method cannot check
     * whether an array of arguments submitted to the varargs has a mixed-unit content at runtime.
     * @param valueAbs FloatScalar.Abs&lt;U&gt;; the absolute base value
     * @param valuesRel FloatScalar.Rel&lt;U&gt;...; zero or more relative values to subtract from the absolute value
     * @param <U> Unit; the unit of the parameters and the result
     * @return MutableFloatScalar.Abs&lt;U&gt;; the resulting value as an absolute value
     */
    @SafeVarargs
    public static <U extends Unit<U>> MutableFloatScalar.Abs<U> minus(final FloatScalar.Abs<U> valueAbs,
            final FloatScalar.Rel<U>... valuesRel)
    {
        MutableFloatScalar.Abs<U> result = new MutableFloatScalar.Abs<U>(valueAbs);
        for (FloatScalar.Rel<U> v : valuesRel)
        {
            result.decrementBy(v);
        }
        return result;
    }

    /**
     * Subtract a number of relative values from a relative value. Return a new instance of the value. The unit of the
     * value will be the unit of the first argument. Due to type erasure of generics, the method cannot check whether an
     * array of arguments submitted to the varargs has a mixed-unit content at runtime.
     * @param valueRel FloatScalar.Rel&lt;U&gt;; the relative base value
     * @param valuesRel FloatScalar.Rel&lt;U&gt;...; zero or more relative values to subtract from the first value
     * @param <U> Unit; the unit of the parameters and the result
     * @return MutableFloatScalar.Rel&lt;U&gt;; the resulting value as a relative value
     */
    @SafeVarargs
    public static <U extends Unit<U>> MutableFloatScalar.Rel<U> minus(final FloatScalar.Rel<U> valueRel,
            final FloatScalar.Rel<U>... valuesRel)
    {
        MutableFloatScalar.Rel<U> result = new MutableFloatScalar.Rel<U>(valueRel);
        for (FloatScalar.Rel<U> v : valuesRel)
        {
            result.decrementBy(v);
        }
        return result;
    }

    /**
     * Subtract two absolute values. Return a new instance of a relative value of the difference. The unit of the value
     * will be the unit of the first argument.
     * @param valueAbs1 FloatScalar.Abs&lt;U&gt;; value 1
     * @param valueAbs2 FloatScalar.Abs&lt;U&gt;; value 2
     * @param <U> Unit; the unit of the parameters and the result
     * @return MutableFloatScalar.Rel&lt;U&gt;; the difference of the two absolute values as a relative value
     */
    public static <U extends Unit<U>> MutableFloatScalar.Rel<U> minus(final FloatScalar.Abs<U> valueAbs1,
            final FloatScalar.Abs<U> valueAbs2)
    {
        MutableFloatScalar.Rel<U> result =
                new MutableFloatScalar.Rel<U>(valueAbs1.getValueInUnit(), valueAbs1.getUnit());
        result.decrementBy(valueAbs2);
        return result;
    }

    /**
     * Multiply two values; the result is a new instance with a different (existing or generated) SI unit.
     * @param left FloatScalar.Abs&lt;?&gt;; the left operand
     * @param right FloatScalar.Abs&lt;?&gt;; the right operand
     * @return MutableFloatScalar.Abs&lt;SIUnit&gt;; the product of the two values
     */
    public static MutableFloatScalar.Abs<SIUnit> multiply(final FloatScalar.Abs<?> left, final FloatScalar.Abs<?> right)
    {
        SIUnit targetUnit =
                Unit.lookupOrCreateSIUnitWithSICoefficients(SICoefficients.multiply(left.getUnit().getSICoefficients(),
                        right.getUnit().getSICoefficients()).toString());
        return new MutableFloatScalar.Abs<SIUnit>(left.getValueSI() * right.getValueSI(), targetUnit);
    }

    /**
     * Multiply two values; the result is a new instance with a different (existing or generated) SI unit.
     * @param left FloatScalar.Rel&lt;?&gt;; the left operand
     * @param right FloatScalar.Rel&lt;?&gt;; the right operand
     * @return MutableFloatScalar.Rel&lt;SIUnit&gt;; the product of the two values
     */
    public static MutableFloatScalar.Rel<SIUnit> multiply(final FloatScalar.Rel<?> left, final FloatScalar.Rel<?> right)
    {
        SIUnit targetUnit =
                Unit.lookupOrCreateSIUnitWithSICoefficients(SICoefficients.multiply(left.getUnit().getSICoefficients(),
                        right.getUnit().getSICoefficients()).toString());
        return new MutableFloatScalar.Rel<SIUnit>(left.getValueSI() * right.getValueSI(), targetUnit);
    }

    /**
     * Divide two values; the result is a new instance with a different (existing or generated) SI unit.
     * @param left FloatScalar.Abs&lt;?&gt;; the left operand
     * @param right FloatScalar.Abs&lt;?&gt;; the right operand
     * @return MutableFloatScalar.Abs&lt;SIUnit&gt;; the ratio of the two values
     */
    public static MutableFloatScalar.Abs<SIUnit> divide(final FloatScalar.Abs<?> left, final FloatScalar.Abs<?> right)
    {
        SIUnit targetUnit =
                Unit.lookupOrCreateSIUnitWithSICoefficients(SICoefficients.divide(left.getUnit().getSICoefficients(),
                        right.getUnit().getSICoefficients()).toString());
        return new MutableFloatScalar.Abs<SIUnit>(left.getValueSI() / right.getValueSI(), targetUnit);
    }

    /**
     * Divide two values; the result is a new instance with a different (existing or generated) SI unit.
     * @param left FloatScalar.Rel&lt;?&gt;; the left operand
     * @param right FloatScalar.Rel&lt;?&gt;; the right operand
     * @return MutableFloatScalar.Rel&lt;SIUnit&gt;; the ratio of the two values
     */
    public static MutableFloatScalar.Rel<SIUnit> divide(final FloatScalar.Rel<?> left, final FloatScalar.Rel<?> right)
    {
        SIUnit targetUnit =
                Unit.lookupOrCreateSIUnitWithSICoefficients(SICoefficients.divide(left.getUnit().getSICoefficients(),
                        right.getUnit().getSICoefficients()).toString());
        return new MutableFloatScalar.Rel<SIUnit>(left.getValueSI() / right.getValueSI(), targetUnit);
    }

    /**********************************************************************************/
    /********************************** MATH METHODS **********************************/
    /**********************************************************************************/

    /** {@inheritDoc} */
    @Override
    public final void abs()
    {
        setValueSI(Math.abs(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void acos()
    {
        // TODO: dimensionless result (SIUnit.ONE).
        setValueSI((float) Math.acos(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void asin()
    {
        // TODO: dimensionless result (SIUnit.ONE).
        setValueSI((float) Math.asin(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void atan()
    {
        // TODO: dimensionless result (SIUnit.ONE).
        setValueSI((float) Math.atan(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void cbrt()
    {
        // TODO: dimension for all SI coefficients / 3.
        setValueSI((float) Math.cbrt(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void ceil()
    {
        setValueSI((float) Math.ceil(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void cos()
    {
        // TODO: dimensionless result (SIUnit.ONE).
        setValueSI((float) Math.cos(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void cosh()
    {
        // TODO: dimensionless result (SIUnit.ONE).
        setValueSI((float) Math.cosh(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void exp()
    {
        // TODO: dimensionless result (SIUnit.ONE).
        setValueSI((float) Math.exp(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void expm1()
    {
        // TODO: dimensionless result (SIUnit.ONE).
        setValueSI((float) Math.expm1(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void floor()
    {
        setValueSI((float) Math.floor(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void log()
    {
        // TODO: dimensionless result (SIUnit.ONE).
        setValueSI((float) Math.log(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void log10()
    {
        // TODO: dimensionless result (SIUnit.ONE).
        setValueSI((float) Math.log10(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void log1p()
    {
        // TODO: dimensionless result (SIUnit.ONE).
        setValueSI((float) Math.log1p(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void pow(final double x)
    {
        // TODO: SI unit with coefficients * x.
        setValueSI((float) Math.pow(getValueSI(), x));
    }

    /** {@inheritDoc} */
    @Override
    public final void rint()
    {
        setValueSI((float) Math.rint(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void round()
    {
        setValueSI(Math.round(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void signum()
    {
        // TODO: dimensionless result (SIUnit.ONE).
        setValueSI(Math.signum(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void sin()
    {
        // TODO: dimensionless result (SIUnit.ONE).
        setValueSI((float) Math.sin(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void sinh()
    {
        // TODO: dimensionless result (SIUnit.ONE).
        setValueSI((float) Math.sinh(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void sqrt()
    {
        // TODO: unit coefficients / 2.
        setValueSI((float) Math.sqrt(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void tan()
    {
        // TODO: dimensionless result (SIUnit.ONE).
        setValueSI((float) Math.tan(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void tanh()
    {
        // TODO: dimensionless result (SIUnit.ONE).
        setValueSI((float) Math.tanh(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void toDegrees()
    {
        setValueSI((float) Math.toDegrees(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void toRadians()
    {
        setValueSI((float) Math.toRadians(getValueSI()));
    }

    /** {@inheritDoc} */
    @Override
    public final void inv()
    {
        // TODO: negate all coefficients in the Unit.
        setValueSI(1.0f / getValueSI());
    }

    /** {@inheritDoc} */
    @Override
    public final void multiply(final float constant)
    {
        setValueSI(getValueSI() * constant);
    }

    /** {@inheritDoc} */
    @Override
    public final void divide(final float constant)
    {
        setValueSI(getValueSI() / constant);
    }

}
