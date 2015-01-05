package org.opentrafficsim.simulationengine;

import java.awt.Dimension;
import java.awt.geom.Rectangle2D;
import java.rmi.RemoteException;

import nl.tudelft.simulation.dsol.SimRuntimeException;
import nl.tudelft.simulation.dsol.animation.D2.AnimationPanel;
import nl.tudelft.simulation.dsol.experiment.ReplicationMode;
import nl.tudelft.simulation.dsol.gui.swing.DSOLPanel;
import nl.tudelft.simulation.dsol.simulators.DEVSSimulator;
import nl.tudelft.simulation.dsol.simulators.SimulatorInterface;
import nl.tudelft.simulation.event.Event;

import org.opentrafficsim.core.dsol.OTSDEVSAnimator;
import org.opentrafficsim.core.dsol.OTSDEVSSimulator;
import org.opentrafficsim.core.dsol.OTSModelInterface;
import org.opentrafficsim.core.dsol.OTSReplication;
import org.opentrafficsim.core.dsol.OTSSimTimeDouble;
import org.opentrafficsim.core.unit.TimeUnit;
import org.opentrafficsim.core.value.vdouble.scalar.DoubleScalar;

/**
 * <p>
 * Copyright (c) 2013-2014 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://opentrafficsim.org/node/13">OpenTrafficSim License</a>.
 * <p>
 * @version 12 nov. 2014 <br>
 * @author <a href="http://www.tudelft.nl/pknoppers">Peter Knoppers</a>
 */
public class SimpleSimulator
{
    /** Counter for replication. */
    private int lastReplication = 0;

    /** The JPanel that contains the simulator controls, a status bar and a JTabbedPane with switchable sub panels. */
    private final DSOLPanel<DoubleScalar.Abs<TimeUnit>, DoubleScalar.Rel<TimeUnit>, OTSSimTimeDouble> panel;

    /** The simulator engine. */
    private final DEVSSimulator<DoubleScalar.Abs<TimeUnit>, DoubleScalar.Rel<TimeUnit>, OTSSimTimeDouble> simulator;

    /**
     * Create a simulation engine without animation; the easy way.
     * @param startTime OTSSimTimeDouble; the start time of the simulation
     * @param warmupPeriod DoubleScalar.Rel&lt;TimeUnit&gt;; the warm up period of the simulation (use new
     *            DoubleScalar.Rel&ltTimeUnit&gt;(0, TimeUnit.SECOND) if you don't know what this is)
     * @param runLength DoubleScalar.Rel&lt;TimeUnit&gt;; the duration of the simulation
     * @param model OTSModelInterface; the simulation to execute
     * @throws RemoteException on communications failure
     * @throws SimRuntimeException on ???
     */
    public SimpleSimulator(final OTSSimTimeDouble startTime, final DoubleScalar.Rel<TimeUnit> warmupPeriod,
        final DoubleScalar.Rel<TimeUnit> runLength, final OTSModelInterface model) throws RemoteException,
        SimRuntimeException
    {
        this.simulator = new OTSDEVSSimulator();
        this.simulator.initialize(new OTSReplication("rep" + ++this.lastReplication, startTime, warmupPeriod, runLength,
            model), ReplicationMode.TERMINATING);
        this.panel =
            new DSOLPanel<DoubleScalar.Abs<TimeUnit>, DoubleScalar.Rel<TimeUnit>, OTSSimTimeDouble>(model, this.simulator);
    }

    /**
     * Create a simulation engine with animation; the easy way.
     * @param startTime OTSSimTimeDouble; the start time of the simulation
     * @param warmupPeriod DoubleScalar.Rel&lt;TimeUnit&gt;; the warm up period of the simulation (use new
     *            DoubleScalar.Rel&ltTimeUnit&gt;(0, TimeUnit.SECOND) if you don't know what this is)
     * @param runLength DoubleScalar.Rel&lt;TimeUnit&gt;; the duration of the simulation
     * @param model OTSModelInterface; the simulation to execute
     * @param extent Rectangle2D; bottom left corner, length and width of the area (world) to animate.
     * @throws RemoteException on communications failure
     * @throws SimRuntimeException on ???
     */
    public SimpleSimulator(final OTSSimTimeDouble startTime, final DoubleScalar.Rel<TimeUnit> warmupPeriod,
        final DoubleScalar.Rel<TimeUnit> runLength, final OTSModelInterface model, final Rectangle2D extent)
        throws RemoteException, SimRuntimeException
    {
        this.simulator = new OTSDEVSAnimator();
        this.simulator.initialize(new OTSReplication("rep" + ++this.lastReplication, startTime, warmupPeriod, runLength,
            model), ReplicationMode.TERMINATING);
        this.panel =
            new DSOLPanel<DoubleScalar.Abs<TimeUnit>, DoubleScalar.Rel<TimeUnit>, OTSSimTimeDouble>(model, this.simulator);
        Dimension size = new Dimension(1024, 768);
        AnimationPanel animationPanel = new AnimationPanel(extent, size, this.simulator);
        this.panel.getTabbedPane().addTab(0, "animation", animationPanel);
        animationPanel.notify(new Event(SimulatorInterface.START_REPLICATION_EVENT, this.simulator, null));
    }

    /**
     * To use in a Swing application add the DSOLPanel to a JFrame.
     * @return the simulation panel (extends JPanel).
     */
    public final DSOLPanel<DoubleScalar.Abs<TimeUnit>, DoubleScalar.Rel<TimeUnit>, OTSSimTimeDouble> getPanel()
    {
        return this.panel;
    }

    /**
     * Access to the simulator is needed to create simulated objects.
     * @return simulator.
     */
    public final DEVSSimulator<DoubleScalar.Abs<TimeUnit>, DoubleScalar.Rel<TimeUnit>, OTSSimTimeDouble> getSimulator()
    {
        return this.simulator;
    }

}
