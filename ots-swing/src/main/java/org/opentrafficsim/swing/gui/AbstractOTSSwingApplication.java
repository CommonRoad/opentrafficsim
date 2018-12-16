package org.opentrafficsim.swing.gui;

import java.awt.Frame;

import javax.swing.JFrame;
import javax.swing.JPanel;

import org.opentrafficsim.core.dsol.OTSModelInterface;

/**
 * Wrap a DSOL simulation model, or any (descendant of a) JPanel in a JFrame (wrap it in a window). The window will be
 * maximized.
 * <p>
 * Copyright (c) 2013-2018 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://opentrafficsim.org/docs/license.html">OpenTrafficSim License</a>.
 * <p>
 * $LastChangedDate: 2018-09-19 13:55:45 +0200 (Wed, 19 Sep 2018) $, @version $Revision: 4006 $, by $Author: averbraeck $,
 * initial version 16 dec. 2014 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="http://www.tudelft.nl/pknoppers">Peter Knoppers</a>
 */
public abstract class AbstractOTSSwingApplication extends JFrame
{
    /** */
    private static final long serialVersionUID = 20141216L;

    /**
     * Wrap a JPanel in a JFrame.
     * @param title String; title for the JFrame
     * @param panel JPanel; the JPanel that will become the contentPane of the JFrame
     */
    public AbstractOTSSwingApplication(final String title, final JPanel panel)
    {
        super();
        setTitle(title);
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    /**
     * Wrap a WrappableSimulation in a JFrame.
     * @param model OTSModelInterface; the model that will be shown in the JFrame
     * @param panel JPanel; this should be the JPanel of the simulation
     */
    public AbstractOTSSwingApplication(final OTSModelInterface model, final JPanel panel)
    {
        super();
        setTitle(model.getShortName());
        setContentPane(panel);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setExtendedState(Frame.MAXIMIZED_BOTH);
        setVisible(true);
    }

    /** {@inheritDoc} */
    @Override
    public final String toString()
    {
        return "SimulatorFrame []";
    }
}