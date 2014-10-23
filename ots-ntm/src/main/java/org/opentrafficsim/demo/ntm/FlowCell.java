package org.opentrafficsim.demo.ntm;

import java.awt.geom.Point2D;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.media.j3d.Bounds;

import org.opentrafficsim.core.unit.FrequencyUnit;
import org.opentrafficsim.core.unit.LengthUnit;
import org.opentrafficsim.core.value.vdouble.scalar.DoubleScalar;
import org.opentrafficsim.demo.ntm.Node.TrafficBehaviourType;
import org.opentrafficsim.demo.ntm.fundamentaldiagrams.NetworkFundamentalDiagram;

import com.vividsolutions.jts.geom.Geometry;

import nl.tudelft.simulation.dsol.animation.LocatableInterface;
import nl.tudelft.simulation.language.d3.DirectedPoint;

/**
 * <p>
 * Copyright (c) 2013-2014 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights
 * reserved. <br>
 * BSD-style license. See <a href="http://opentrafficsim.org/node/13">OpenTrafficSim License</a>.
 * <p>
 * @version 14 Oct 2014 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="http://Hansvanlint.weblog.tudelft.nl">Hans van Lint</a>
 * @author <a href="http://www.tudelft.nl/pknoppers">Peter Knoppers</a>
 * @author <a href="http://www.citg.tudelft.nl">Guus Tamminga</a>
 * @author <a href="http://www.citg.tudelft.nl">Yufei Yuan</a>
 */
public class FlowCell implements LocatableInterface
{
    /** link length in a length unit. */
    private DoubleScalar<LengthUnit> cellLength;

    /** link capacity in vehicles per hour. This is a mutable property (e.g., blockage). */
    private DoubleScalar<FrequencyUnit> capacity;

    
    /** */
    private CellBehaviour cellBehaviour;

    /**
     * @param cellLength
     * @param capacity
     * @param behaviourType
     */
    public FlowCell(final DoubleScalar<LengthUnit> cellLength, final DoubleScalar<FrequencyUnit> capacity,
            final TrafficBehaviourType behaviourType)
    {
        this.setCellLength(cellLength);
        this.setCapacity(capacity);
        if (behaviourType == TrafficBehaviourType.FLOW)
        {
            this.setCellBehaviour(new CellBehaviourFlow());
        }
    }

    /**
     * Retrieves car production from network fundamental diagram.
     * @param accumulatedCars number of cars in Cell
     * @param maxCapacity
     * @param param
     * @return carProduction
     */
    public final double retrieveCellTransmissionProduction(final double accumulatedCars, final double maxCapacity,
            final ParametersFundamentalDiagram param)
    {
        ArrayList<Point2D> xyPairs = new ArrayList<Point2D>();
        Point2D p = new Point2D.Double();
        p.setLocation(0, 0);
        xyPairs.add(p);
        p = new Point2D.Double();
        p.setLocation(param.getAccCritical1(), maxCapacity);
        xyPairs.add(p);
        p = new Point2D.Double();
        p.setLocation(param.getAccJam(), 0);
        xyPairs.add(p);
        double carProduction = NetworkFundamentalDiagram.PieceWiseLinear(xyPairs, accumulatedCars);
        return carProduction;
    }

    /** {@inheritDoc} */
    @Override
    public DirectedPoint getLocation() throws RemoteException
    {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    public Bounds getBounds() throws RemoteException
    {
        return null;
    }

    /**
     * @return cellLength.
     */
    public DoubleScalar<LengthUnit> getCellLength()
    {
        return this.cellLength;
    }

    /**
     * @param cellLength set cellLength.
     */
    public void setCellLength(DoubleScalar<LengthUnit> cellLength)
    {
        this.cellLength = cellLength;
    }

    /**
     * @return capacity.
     */
    public DoubleScalar<FrequencyUnit> getCapacity()
    {
        return this.capacity;
    }

    /**
     * @param capacity set capacity.
     */
    public void setCapacity(DoubleScalar<FrequencyUnit> capacity)
    {
        this.capacity = capacity;
    }

    /**
     * @return cellBehaviour.
     */
    public CellBehaviour getCellBehaviour()
    {
        return cellBehaviour;
    }

    /**
     * @param cellBehaviour set cellBehaviour.
     */
    public void setCellBehaviour(CellBehaviour cellBehaviour)
    {
        this.cellBehaviour = cellBehaviour;
    }


}
