package org.opentrafficsim.core.dsol;

import java.io.Serializable;

/**
 * AbstractOTSSimulationApplication is a class that can be extended by a simulation application that runs from the command line.
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://opentrafficsim.org/docs/license.html">OpenTrafficSim License</a>.
 * </p>
 * @author <a href="https://github.com/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public abstract class AbstractOTSSimulationApplication implements Serializable
{
    /** */
    private static final long serialVersionUID = 20141216L;

    /** the model. */
    private final OTSModelInterface model;

    /**
     * The application, which is model aware.
     * @param model OTSModelInterface; the model that will be shown in the JFrame
     */
    public AbstractOTSSimulationApplication(final OTSModelInterface model)
    {
        this.model = model;
    }

    /**
     * @return model
     */
    public final OTSModelInterface getModel()
    {
        return this.model;
    }
}
