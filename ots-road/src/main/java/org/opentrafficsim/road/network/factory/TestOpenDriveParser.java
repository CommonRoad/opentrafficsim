package org.opentrafficsim.road.network.factory;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.net.SocketException;
import java.net.URL;
import java.rmi.RemoteException;
import java.util.ArrayList;

import javax.naming.NamingException;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;

import nl.javel.gisbeans.io.esri.CoordinateTransform;
import nl.tudelft.simulation.dsol.SimRuntimeException;
import nl.tudelft.simulation.dsol.animation.D2.GisRenderable2D;
import nl.tudelft.simulation.dsol.simulators.SimulatorInterface;
import nl.tudelft.simulation.jstats.distributions.DistConstant;
import nl.tudelft.simulation.jstats.distributions.DistExponential;
import nl.tudelft.simulation.jstats.distributions.DistTriangular;
import nl.tudelft.simulation.jstats.distributions.DistUniform;
import nl.tudelft.simulation.jstats.streams.MersenneTwister;
import nl.tudelft.simulation.jstats.streams.StreamInterface;
import nl.tudelft.simulation.language.io.URLResource;

import org.djunits.unit.AccelerationUnit;
import org.djunits.unit.LengthUnit;
import org.djunits.unit.SpeedUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.value.vdouble.scalar.Acceleration;
import org.djunits.value.vdouble.scalar.DoubleScalar;
import org.djunits.value.vdouble.scalar.Length;
import org.djunits.value.vdouble.scalar.Speed;
import org.djunits.value.vdouble.scalar.Time;
import org.opentrafficsim.core.dsol.OTSDEVSSimulatorInterface;
import org.opentrafficsim.core.dsol.OTSModelInterface;
import org.opentrafficsim.core.dsol.OTSSimTimeDouble;
import org.opentrafficsim.core.geometry.OTSGeometryException;
import org.opentrafficsim.core.gtu.GTUDirectionality;
import org.opentrafficsim.core.gtu.GTUException;
import org.opentrafficsim.core.gtu.GTUType;
import org.opentrafficsim.core.gtu.animation.AccelerationGTUColorer;
import org.opentrafficsim.core.gtu.animation.GTUColorer;
import org.opentrafficsim.core.gtu.animation.IDGTUColorer;
import org.opentrafficsim.core.gtu.animation.SwitchableGTUColorer;
import org.opentrafficsim.core.gtu.animation.VelocityGTUColorer;
import org.opentrafficsim.core.network.Link;
import org.opentrafficsim.core.network.NetworkException;
import org.opentrafficsim.core.network.Node;
import org.opentrafficsim.core.network.OTSNetwork;
import org.opentrafficsim.core.network.route.CompleteRoute;
import org.opentrafficsim.core.units.distributions.ContinuousDistDoubleScalar;
import org.opentrafficsim.road.car.LaneBasedIndividualCar;
import org.opentrafficsim.road.gtu.generator.GTUGeneratorIndividual;
import org.opentrafficsim.road.gtu.lane.driver.LaneBasedDrivingCharacteristics;
import org.opentrafficsim.road.gtu.lane.perception.LanePerception;
import org.opentrafficsim.road.gtu.lane.tactical.following.IDMPlus;
import org.opentrafficsim.road.gtu.lane.tactical.lanechange.Altruistic;
import org.opentrafficsim.road.gtu.strategical.LaneBasedStrategicalPlanner;
import org.opentrafficsim.road.gtu.strategical.route.LaneBasedStrategicalRoutePlanner;
import org.opentrafficsim.road.network.factory.opendrive.GeneratorAnimation;
import org.opentrafficsim.road.network.factory.opendrive.OpenDriveNetworkLaneParser;
import org.opentrafficsim.road.network.factory.opendrive.communicationRTI.ReceiverThread;
import org.opentrafficsim.road.network.lane.CrossSectionElement;
import org.opentrafficsim.road.network.lane.CrossSectionLink;
import org.opentrafficsim.road.network.lane.Lane;
import org.opentrafficsim.road.network.lane.NoTrafficLane;
import org.opentrafficsim.road.network.lane.Sensor;
import org.opentrafficsim.road.network.lane.SinkSensor;
import org.opentrafficsim.simulationengine.AbstractWrappableAnimation;
import org.opentrafficsim.simulationengine.properties.AbstractProperty;
import org.xml.sax.SAXException;

/**
 * <p>
 * Copyright (c) 2013-2015 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
 * BSD-style license. See <a href="http://opentrafficsim.org/docs/license.html">OpenTrafficSim License</a>.
 * <p>
 * $LastChangedDate: 2015-08-05 15:55:21 +0200 (Wed, 05 Aug 2015) $, @version $Revision: 1199 $, by $Author: averbraeck $,
 * initial version Oct 17, 2014 <br>
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 */
public class TestOpenDriveParser extends AbstractWrappableAnimation
{
    /**
     * Main program.
     * @param args String[]; the command line arguments (not used)
     * @throws SimRuntimeException should never happen
     */
    public static void main(final String[] args) throws SimRuntimeException
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    TestOpenDriveParser xmlModel = new TestOpenDriveParser();
                    // 1 hour simulation run for testing
                    xmlModel.buildAnimator(new Time.Abs(0.0, TimeUnit.SECOND), new Time.Rel(0.0, TimeUnit.SECOND),
                        new Time.Rel(60.0, TimeUnit.MINUTE), new ArrayList<AbstractProperty<?>>(), null, true);
                }
                catch (SimRuntimeException | NamingException exception)
                {
                    exception.printStackTrace();
                }
            }
        });
    }

    /** {@inheritDoc} */
    @Override
    public final String shortName()
    {
        return "TestOpenDriveModel";
    }

    /** {@inheritDoc} */
    @Override
    public final String description()
    {
        return "TestOpenDriveModel";
    }

    /** {@inheritDoc} */
    @Override
    public final void stopTimersThreads()
    {
        super.stopTimersThreads();
    }

    /** {@inheritDoc} */
    @Override
    protected final JPanel makeCharts()
    {
        return null;
    }

    /** {@inheritDoc} */
    @Override
    protected final OTSModelInterface makeModel(final GTUColorer colorer)
    {
        return new TestOpenDriveModel();
    }

    /** {@inheritDoc} */
    @Override
    protected final Rectangle2D.Double makeAnimationRectangle()
    {
        return new Rectangle2D.Double(-1000, -1000, 2000, 2000);
    }

    /**
     * Model to test the XML parser.
     * <p>
     * Copyright (c) 2013-2015 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. <br>
     * All rights reserved. BSD-style license. See <a href="http://opentrafficsim.org/docs/license.html">OpenTrafficSim
     * License</a>.
     * <p>
     * $LastChangedDate: 2015-08-05 15:55:21 +0200 (Wed, 05 Aug 2015) $, @version $Revision: 1199 $, by $Author: averbraeck $,
     * initial version un 27, 2015 <br>
     * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
     * @author <a href="http://www.tudelft.nl/pknoppers">Peter Knoppers</a>
     */
    class TestOpenDriveModel implements OTSModelInterface
    {
        /** */
        private static final long serialVersionUID = 20150811L;

        /** the simulator. */
        private OTSDEVSSimulatorInterface simulator;

        /** {@inheritDoc} */
        @Override
        public final
            void
            constructModel(
                final SimulatorInterface<DoubleScalar.Abs<TimeUnit>, DoubleScalar.Rel<TimeUnit>, OTSSimTimeDouble> pSimulator)
                throws SimRuntimeException
        {
            this.simulator = (OTSDEVSSimulatorInterface) pSimulator;

            URL gisURL = URLResource.getResource("/gis/map.xml");
            System.err.println("GIS-map file: " + gisURL.toString());

            // TODO parse these from the xodr-file.
            double latCenter = 37.40897623275873, lonCenter = -122.0246091728831;
            CoordinateTransform latLonToXY = new CoordinateTransformLonLatToXY(lonCenter, latCenter);
            new GisRenderable2D(this.simulator, gisURL, latLonToXY);

            // URL url = URLResource.getResource("/OpenDrive.xodr");
            URL url = URLResource.getResource("/testod.xodr");
            this.simulator.setPauseOnError(false);
            OpenDriveNetworkLaneParser nlp = new OpenDriveNetworkLaneParser(this.simulator);
            OTSNetwork network = null;
            try
            {
                network = nlp.build(url);
            }
            catch (NetworkException | ParserConfigurationException | SAXException | IOException | NamingException
                | GTUException | OTSGeometryException exception)
            {
                exception.printStackTrace();
            }

            // Make a GTU Type
            GTUType carType = GTUType.makeGTUType("Car");
            
            //new ReceiverThread(this.simulator).run();
            
            try
            {
                new Thread(new ReceiverThread(this.simulator, carType)).start();
            } catch (SocketException exception1)
            {
                exception1.printStackTrace();
            }

            // stream
            StreamInterface stream = new MersenneTwister(1);
            Length.Rel M25 = new Length.Rel(25.0, LengthUnit.METER);
            Length.Rel M0 = new Length.Rel(0.0, LengthUnit.METER);

            // distributions
            ContinuousDistDoubleScalar.Rel<Speed, SpeedUnit> initialSpeedDist =
                new ContinuousDistDoubleScalar.Rel<>(new DistConstant(stream, 0.0), SpeedUnit.SI);
            ContinuousDistDoubleScalar.Rel<Time.Rel, TimeUnit> iatDist =
                new ContinuousDistDoubleScalar.Rel<>(new DistExponential(stream, 30.0), TimeUnit.SECOND);
            ContinuousDistDoubleScalar.Rel<Length.Rel, LengthUnit> lengthDist =
                new ContinuousDistDoubleScalar.Rel<>(new DistUniform(stream, 4.0, 5.0), LengthUnit.METER);
            ContinuousDistDoubleScalar.Rel<Length.Rel, LengthUnit> widthDist =
                new ContinuousDistDoubleScalar.Rel<>(new DistConstant(stream, 2.0), LengthUnit.METER);
            ContinuousDistDoubleScalar.Rel<Speed, SpeedUnit> maxSpeedDist =
                new ContinuousDistDoubleScalar.Rel<>(new DistTriangular(stream, 30.0, 35.0, 40.0),
                    SpeedUnit.MILE_PER_HOUR);

            // default colorer

            // put some generators and sinks on the outer edges of the network
            for (Link link : network.getLinkMap().values())
            {
                CrossSectionLink csLink = (CrossSectionLink) link;
                // look if start node is isolated
                if (link.getStartNode().getLinks().size() == 1) // only ourselves...
                {
                    // put generators and sinks 25 m from the edge of the link
                    for (CrossSectionElement cse : csLink.getCrossSectionElementList())
                    {
                        if (cse instanceof Lane && !(cse instanceof NoTrafficLane))
                        {
                            Lane lane = (Lane) cse;
                            if (Integer.parseInt(lane.getId()) < 0)
                            {
                                // make a generator
                                Time.Abs startTime = new Time.Abs(0.0, TimeUnit.SI);
                                Time.Abs endTime = new Time.Abs(Double.MAX_VALUE, TimeUnit.SI);
                                Length.Rel position = lane.getLength().lt(M25) ? M0 : M25;
                                String id = lane.getParentLink().getId() + "." + lane.getId();
                                LaneBasedDrivingCharacteristics drivingCharacteristics =
                                    new LaneBasedDrivingCharacteristics(new IDMPlus(), new Altruistic());
                                LaneBasedStrategicalPlanner strategicalPlanner =
                                    new LaneBasedStrategicalRoutePlanner(drivingCharacteristics);
                                LanePerception perception = new LanePerception(null); // TODO
//                                new GTUGeneratorIndividual(id, this.simulator, carType, LaneBasedIndividualCar.class,
//                                    initialSpeedDist, iatDist, lengthDist, widthDist, maxSpeedDist, Integer.MAX_VALUE,
//                                    startTime, endTime, lane, position, GTUDirectionality.DIR_PLUS,
//                                    makeSwitchableGTUColorer(), strategicalPlanner, perception);
                                try
                                {
                                    new GeneratorAnimation(lane, position, this.simulator);
                                }
                                catch (RemoteException | NamingException | NetworkException exception)
                                {
                                    exception.printStackTrace();
                                }
                            }
                            else
                            {
                                // make a sink
                                Length.Rel position = lane.getLength().lt(M25) ? M0 : M25;
                                Sensor sensor = new SinkSensor(lane, position, this.simulator);
                                try
                                {
                                    lane.addSensor(sensor, GTUType.ALL);
                                }
                                catch (NetworkException exception)
                                {
                                    exception.printStackTrace();
                                }
                            }
                        }
                    }
                }
                else if (link.getEndNode().getLinks().size() == 1) // only ourselves...
                {
                    // put generators and sinks 25 m from the edge of the link
                    for (CrossSectionElement cse : csLink.getCrossSectionElementList())
                    {
                        if (cse instanceof Lane && !(cse instanceof NoTrafficLane))
                        {
                            Lane lane = (Lane) cse;
                            if (Integer.parseInt(lane.getId()) > 0)
                            {
                                // make a generator
                                Time.Abs startTime = new Time.Abs(0.0, TimeUnit.SI);
                                Time.Abs endTime = new Time.Abs(Double.MAX_VALUE, TimeUnit.SI);
                                Length.Rel position =
                                    lane.getLength().lt(M25) ? lane.getLength() : lane.getLength().minus(M25);
                                String id = lane.getParentLink().getId() + "." + lane.getId();
                                LaneBasedDrivingCharacteristics drivingCharacteristics =
                                    new LaneBasedDrivingCharacteristics(new IDMPlus(), new Altruistic());
                                LaneBasedStrategicalPlanner strategicalPlanner =
                                    new LaneBasedStrategicalRoutePlanner(drivingCharacteristics);
                                LanePerception perception = new LanePerception(null); // TODO
//                                new GTUGeneratorIndividual(id, this.simulator, carType, LaneBasedIndividualCar.class,
//                                    initialSpeedDist, iatDist, lengthDist, widthDist, maxSpeedDist, Integer.MAX_VALUE,
//                                    startTime, endTime, lane, position, GTUDirectionality.DIR_MINUS,
//                                    makeSwitchableGTUColorer(), strategicalPlanner, perception);
                                try
                                {
                                    new GeneratorAnimation(lane, position, this.simulator);
                                }
                                catch (RemoteException | NamingException | NetworkException exception)
                                {
                                    exception.printStackTrace();
                                }
                            }
                            else
                            {
                                // make a sink
                                Length.Rel position =
                                    lane.getLength().lt(M25) ? lane.getLength() : lane.getLength().minus(M25);
                                Sensor sensor = new SinkSensor(lane, position, this.simulator);
                                try
                                {
                                    lane.addSensor(sensor, GTUType.ALL);
                                }
                                catch (NetworkException exception)
                                {
                                    exception.printStackTrace();
                                }
                            }
                        }
                    }
                }
            }

            CrossSectionLink link = (CrossSectionLink) network.getLink("3766053");
            for (CrossSectionElement cse : link.getCrossSectionElementList())
            {
                if (cse instanceof Lane)
                {
                    Lane lane = (Lane) cse;
                    System.out.println("Lane " + lane + " - offset=" + lane.getDesignLineOffsetAtEnd()
                        + " - nextlanes(ALL) = " + lane.nextLanes(GTUType.ALL) + " - nextlanes(CarType) = "
                        + lane.nextLanes(carType));
                }
            }

            // test the shortest path method
            Node nodeFrom = network.getLink("3766052").getEndNode();
            Node nodeTo = network.getLink("3766035.1").getStartNode();
            CompleteRoute cr;
            try
            {
                cr = network.getShortestRouteBetween(GTUType.ALL, nodeFrom, nodeTo);
                System.out.println(cr.toString().replaceAll("to OTSNode", "\ntoOTSNode").replaceAll(", OTSNode",
                    ", \nOTSNode"));
            }
            catch (NetworkException exception)
            {
                exception.printStackTrace();
            }
        }

        /** {@inheritDoc} */
        @Override
        public SimulatorInterface<DoubleScalar.Abs<TimeUnit>, DoubleScalar.Rel<TimeUnit>, OTSSimTimeDouble>
            getSimulator()

        {
            return this.simulator;
        }

        /**
         * @return a GTUColorer
         */
        private GTUColorer makeSwitchableGTUColorer()
        {
            GTUColorer[] gtuColorers =
                new GTUColorer[]{
                    new IDGTUColorer(),
                    new VelocityGTUColorer(new Speed(100.0, SpeedUnit.KM_PER_HOUR)),
                    new AccelerationGTUColorer(new Acceleration(1.0, AccelerationUnit.METER_PER_SECOND_2),
                        new Acceleration(1.0, AccelerationUnit.METER_PER_SECOND_2))};
            return new SwitchableGTUColorer(0, gtuColorers);
        }
    }

}
