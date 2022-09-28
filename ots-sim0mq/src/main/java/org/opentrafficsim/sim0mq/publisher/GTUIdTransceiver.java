package org.opentrafficsim.sim0mq.publisher;

import org.djutils.immutablecollections.Immutable;
import org.djutils.immutablecollections.ImmutableLinkedHashSet;
import org.djutils.immutablecollections.ImmutableSet;
import org.opentrafficsim.core.gtu.GTU;
import org.opentrafficsim.core.network.OTSNetwork;

/**
 * Transceiver for GTU ids.
 * <p>
 * Copyright (c) 2020-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://opentrafficsim.org/docs/license.html">OpenTrafficSim License</a>.
 * </p>
 * @author <a href="https://github.com/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://tudelft.nl/staff/p.knoppers-1">Peter Knoppers</a>
 * @author <a href="https://dittlab.tudelft.nl">Wouter Schakel</a>
 */
public class GTUIdTransceiver extends AbstractIdTransceiver
{
    /**
     * Construct a GTUIdTransceiver.
     * @param network OTSNetwork; the OTS network
     */
    public GTUIdTransceiver(final OTSNetwork network)
    {
        super(network, "GTU id transceiver");
    }

    /** {@inheritDoc} */
    @Override
    ImmutableSet<GTU> getSet()
    {
        return new ImmutableLinkedHashSet<GTU>(getNetwork().getGTUs(), Immutable.WRAP);
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "GTUIdTransceiver [network=" + getNetwork().getId() + ", super=" + super.toString() + "]";
    }

}
