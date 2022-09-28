package org.opentrafficsim.draw.core;

/**
 * OTSDrawingException .
 * <p>
 * Copyright (c) 2013-2022 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="https://opentrafficsim.org/docs/license.html">OpenTrafficSim License</a>.
 * </p>
 * @author <a href="https://github.com/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class OTSDrawingException extends Exception
{
    /** */
    private static final long serialVersionUID = 1L;

    /** */
    public OTSDrawingException()
    {
        //
    }

    /**
     * @param message String; the error message
     */
    public OTSDrawingException(final String message)
    {
        super(message);
    }

    /**
     * @param cause Throwable; the cause of the exception to be included
     */
    public OTSDrawingException(final Throwable cause)
    {
        super(cause);
    }

    /**
     * @param message String; the error message
     * @param cause Throwable; the cause of the exception to be included
     */
    public OTSDrawingException(final String message, final Throwable cause)
    {
        super(message, cause);
    }
}
