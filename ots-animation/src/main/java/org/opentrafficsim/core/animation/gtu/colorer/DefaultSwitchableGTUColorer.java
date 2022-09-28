package org.opentrafficsim.core.animation.gtu.colorer;

import java.io.Serializable;

import org.djunits.unit.SpeedUnit;
import org.djunits.value.vdouble.scalar.Acceleration;
import org.djunits.value.vdouble.scalar.Speed;

/**
 * A simple way to construct a SwitchableGTUColorer set up with the "standard" set of GTUColorers. <br>
 * Do not assume that the set of GTUColorers in the result will never change.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://opentrafficsim.org/docs/license.html">OpenTrafficSim License</a>.
 * <p>
 * @author <a href="https://github.com/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://tudelft.nl/staff/p.knoppers-1">Peter Knoppers</a>
 */
public class DefaultSwitchableGTUColorer extends SwitchableGTUColorer implements Serializable
{
    /** */
    private static final long serialVersionUID = 20150000L;

    /** The initial set of GTU colorers in the default list. */
    private static final GTUColorer[] COLORERS;

    static
    {
        COLORERS = new GTUColorer[3];
        COLORERS[0] = new IDGTUColorer();
        COLORERS[1] = new SpeedGTUColorer(new Speed(150, SpeedUnit.KM_PER_HOUR));
        COLORERS[2] = new AccelerationGTUColorer(Acceleration.instantiateSI(-6.0), Acceleration.instantiateSI(2));
    }

    /**
     * create a default list of GTU colorers.
     */
    public DefaultSwitchableGTUColorer()
    {
        super(0, DefaultSwitchableGTUColorer.COLORERS);
    }

}
