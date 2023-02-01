package org.opentrafficsim.road.network.lane.object.detector;

import java.io.Serializable;

import nl.tudelft.simulation.dsol.animation.Locatable;

/**
 * Tagging interface for detectors (enables switching on/off animation of all detector types at once).
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://opentrafficsim.org/docs/license.html">OpenTrafficSim License</a>.
 * <p>
 * @author <a href="https://github.com/averbraeck">Alexander Verbraeck</a>
 * @author <a href="https://tudelft.nl/staff/p.knoppers-1">Peter Knoppers</a>
 * @author <a href="https://dittlab.tudelft.nl">Wouter Schakel</a>
 */
public interface DetectorAnimationToggle extends Serializable, Locatable
{
    // Nothing here
}