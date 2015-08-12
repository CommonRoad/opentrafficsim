package nl.grontmij.smarttraffic.lane;

import java.awt.geom.Rectangle2D;
import java.awt.geom.Rectangle2D.Double;
import java.io.IOException;
import java.net.URL;
import java.rmi.RemoteException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.HashMap;

import javax.naming.NamingException;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.xml.parsers.ParserConfigurationException;

import nl.tudelft.simulation.dsol.SimRuntimeException;
import nl.tudelft.simulation.dsol.simulators.SimulatorInterface;
import nl.tudelft.simulation.language.io.URLResource;

import org.opentrafficsim.core.car.LaneBasedIndividualCar;
import org.opentrafficsim.core.dsol.OTSDEVSSimulatorInterface;
import org.opentrafficsim.core.dsol.OTSModelInterface;
import org.opentrafficsim.core.dsol.OTSSimTimeDouble;
import org.opentrafficsim.core.geometry.OTSGeometryException;
import org.opentrafficsim.core.gtu.GTUException;
import org.opentrafficsim.core.gtu.GTUType;
import org.opentrafficsim.core.gtu.animation.GTUColorer;
import org.opentrafficsim.core.gtu.following.GTUFollowingModel;
import org.opentrafficsim.core.gtu.following.IDMPlus;
import org.opentrafficsim.core.gtu.lane.changing.AbstractLaneChangeModel;
import org.opentrafficsim.core.gtu.lane.changing.Egoistic;
import org.opentrafficsim.core.network.NetworkException;
import org.opentrafficsim.core.network.OTSNetwork;
import org.opentrafficsim.core.network.factory.xml.XmlNetworkLaneParser;
import org.opentrafficsim.core.network.lane.AbstractSensor;
import org.opentrafficsim.core.network.lane.Lane;
import org.opentrafficsim.core.unit.SpeedUnit;
import org.opentrafficsim.core.unit.TimeUnit;
import org.opentrafficsim.core.value.vdouble.scalar.DoubleScalar;
import org.opentrafficsim.core.value.vdouble.scalar.DoubleScalar.Abs;
import org.opentrafficsim.core.value.vdouble.scalar.DoubleScalar.Rel;
import org.opentrafficsim.simulationengine.AbstractWrappableSimulation;
import org.opentrafficsim.simulationengine.properties.AbstractProperty;
import org.xml.sax.SAXException;

/**
 * <p>
 * @version Oct 17, 2014 <br>
 *          ======= $LastChangedDate$, @version $Revision$, by $Author:
 *          rabma $, initial version ct 17, 2014 <br>
 *          =======
 * @version $Revision$, $LastChangedDate$, by $Author$, initial
 *          version Oct 17, 2014 <br>
 *          >>>>>>> .r1123 >>>>>>> .r1113
 * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
 * @author <a href="http://www.citg.tudelft.nl">Guus Tamminga</a>
 */

public class GTM extends AbstractWrappableSimulation
{
    /**
     * Main program.
     * @param args String[]; the command line arguments (not used)
     * @throws SimRuntimeException should never happen
     * @throws RemoteException on communications failure
     */

    public static Instant startTimeSimulation;

    public static HashMap<String, StopLineLane> mapSignalGroupToStopLineAtJunction = new HashMap<String, StopLineLane>();

    // standaard methode om de simulatie (DSOL) te starten
    // Ga naar de methode constructModel voor de inhoud
    public static void main(final String[] args) throws RemoteException, SimRuntimeException
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    GTM xmlModel = new GTM();
                    xmlModel.buildSimulator(new ArrayList<AbstractProperty<?>>(), null, true);
                }
                catch (RemoteException | SimRuntimeException | NamingException exception)
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
        return "TestXMLModel";
    }

    /** {@inheritDoc} */
    @Override
    public final String description()
    {
        return "TestXMLModel";
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
        return new GTMModel(colorer);
    }

    /** {@inheritDoc} */
    @Override
    protected final Rectangle2D.Double makeAnimationRectangle()
    {
        return new Rectangle2D.Double(-100, 5500, 600, 300);
    }

    /**
     * Model to test the XML parser.
     * <p>
     * Copyright (c) 2013-2015 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights reserved. <br>
     * BSD-style license. See <a href="http://opentrafficsim.org/node/13">OpenTrafficSim License</a>.
     * <p>
     * @version $Revision$, $LastChangedDate$, by $Author$,
     *          initial version Jun 27, 2015 <br>
     * @author <a href="http://www.tbm.tudelft.nl/averbraeck">Alexander Verbraeck</a>
     * @author <a href="http://www.citg.tudelft.nl">Guus Tamminga</a>
     */

    class GTMModel implements OTSModelInterface
    {
        /** */
        private static final long serialVersionUID = 20141121L;

        /** the simulator. */
        private OTSDEVSSimulatorInterface simulator;

        /** The blocking car. */
        private HashMap<Lane<?, ?>, LaneBasedIndividualCar<Integer>> blockMap =
            new HashMap<Lane<?, ?>, LaneBasedIndividualCar<Integer>>();

        /** Type of all GTUs. */
        private GTUType<String> gtuType = GTUType.makeGTUType("CAR");

        /** The lane change model. */
        private AbstractLaneChangeModel laneChangeModel = new Egoistic();

        /** the car following model, e.g. IDM Plus for cars. */
        private GTUFollowingModel gtuFollowingModel = new IDMPlus();

        DoubleScalar.Abs<SpeedUnit> initialSpeed;

        /**
         * @param gtuColorer the GTUColorer to use.
         */
        public GTMModel(final GTUColorer gtuColorer)
        {
            super();
            this.gtuColorer = gtuColorer;
        }

        /** the gtuColorer. */
        private final GTUColorer gtuColorer;

        /** {@inheritDoc} */
        @Override
        public final void constructModel(
            final SimulatorInterface<DoubleScalar.Abs<TimeUnit>, DoubleScalar.Rel<TimeUnit>, OTSSimTimeDouble> pSimulator)
            throws SimRuntimeException, RemoteException
        {
            this.simulator = (OTSDEVSSimulatorInterface) pSimulator;

            // base directory (relative to user dir)
            String dirBase = System.getProperty("user.dir") + "/src/main/resources/";

            // geef hier de file met het netwerk
            URL url = URLResource.getResource(dirBase + "network.xml");

            // Bouw het netwerk
            XmlNetworkLaneParser nlp = new XmlNetworkLaneParser(this.simulator);
            OTSNetwork<?, ?, ?> network = null;
            try
            {
                network = nlp.build(url);
            }
            catch (NetworkException | ParserConfigurationException | SAXException | IOException | NamingException
                | GTUException | OTSGeometryException exception1)
            {
                exception1.printStackTrace();
            }

            // read the configuration files for VLOG (detector/signalgroup: both
            // index and name
            String dirConfigVri = "configVRI/";
            String dirLoggings = "VRI-loggings/";
            // het nummer van de N201 wordt gebruikt in de bestanden
            String wegNummer = "201";
            // Geef de numers van de VRI's
            // String[] vriNummer = { "225", "231", "234", "239", "245", "249",
            // "291", "297", "302", "308", "311", "314" };
            String[] vriNummer = {"225"};

            // in de configVriList worden de vri configuraties opgeslagen
            // De ConfigVri bevat de detectoren (index, naam) en de
            // signaalgroepen (index, naam)
            HashMap<String, ConfigVri> configVriList = null;
            try
            {
                configVriList = ReadVLog.readVlogConfigFiles(dirConfigVri, dirBase, wegNummer, vriNummer);
            }
            catch (IOException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }

            // read and define detectors from the network
            // in mapSensors staan alle detectoren (met de naam als zoeksleutel
            // (key))
            HashMap<String, AbstractSensor> mapSensor = new HashMap<String, AbstractSensor>();
            // Vervolgens worden de verschillende typen ook nog in aparte
            // HashMaps opgeslagen
            HashMap<String, GenerateSensor> mapSensorGenerateCars = new HashMap<String, GenerateSensor>();
            HashMap<String, KillSensor> mapSensorKillCars = new HashMap<String, KillSensor>();
            HashMap<String, CheckSensor> mapSensorCheckCars = new HashMap<String, CheckSensor>();
            // alle detectoren uit het netwerk worden verzameld
            try
            {
                ReadNetworkData.readDetectors(this.simulator, network, configVriList, mapSensor, mapSensorGenerateCars,
                    mapSensorKillCars, mapSensorCheckCars);
            }
            catch (NetworkException | NamingException e2)
            {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }

            // read the historical (at a later stage streaming) VLOG data
            // start met inlezen files vanaf tijdstip ....
            // nu wordt er nog data van alleen 1 juni ingelezen
            int year = 2015;
            int month = 6;
            int day = 1;
            long hour = 6;
            int minute = 0;
            int second = 0;
            int tenth = 0;
            Instant timeVLog =
                Instant.parse(String.format("%04d-%02d-%02dT%02d:%02d:%02d.%02dZ", year, month, day, hour, minute, second,
                    tenth));
            startTimeSimulation = timeVLog;
            ZoneOffset offset = ZoneOffset.of("-00:00");
            LocalDateTime ldt = LocalDateTime.ofInstant(timeVLog, offset);
            ldt = LocalDateTime.ofInstant(timeVLog, offset);
            // read the vlog data with both detector and signalgroup data
            // van alle detectoren worden nu de pulsen toegevoegd (tijdstip en
            // waarde detectie/signaal)
            // deze worden opgeslagen in de mapSensor, maar tegelijkertijd ook
            // in de mappen mapSensorGenerateCars, mapSensorKillCars en
            // mapSensorCheckCars (omdat daar een verwijzing naar dezelfde objecten is
            //
            try
            {
                ReadVLog.readVlogFiles(mapSensor, configVriList, timeVLog, dirBase + dirLoggings, wegNummer, vriNummer);
            }
            catch (IOException e2)
            {
                // TODO Auto-generated catch block
                e2.printStackTrace();
            }

            // connect the detector pulses to the simulator and generate Cars
            // Module that provides actions if a pulse from a detector is
            // activated
            @SuppressWarnings("unchecked")
            // define the type of cars
            GTUType<String> gtuType = GTUType.makeGTUType("CAR");
            // Parameters en variabelen om te tunen!!!!
            // creeren van een voertuig als een detector "af" gaat (waarde wordt nul)
            int generateCar = 0;
            double lengthCar = 4.5; // lengte voertuig

            /*-
            try
            {
                ScheduleGenerateCars generateCars =
                    new ScheduleGenerateCars(gtuType, gtuFollowingModel, laneChangeModel, gtuColorer, simulator,
                        mapSensorGenerateCars, generateCar, lengthCar);
            }
            catch (NetworkException e1)
            {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
             */

            try
            {
                ScheduleTrafficLightsStates scheduleTrafficLightStates =
                    new ScheduleTrafficLightsStates(simulator, mapSensorGenerateCars, GTM.mapSignalGroupToStopLineAtJunction);
            }
            catch (NetworkException | GTUException | NamingException e)
            {
                e.printStackTrace();
            }

            // - Compare the (INTERMEDIATE) pulse to vehicles in the simulation
            //
            // - if no car is matched: Generate a car
            // - if matched: reposition that car, and perhaps other cars
            // - de range om te zoeken naar voertuigen:
            // ------de eerste waarde is de afstand in meters stroomOPwaarts van het voertuig
            // ------de tweede waarde is de afstand in meters stroomAFwaarts van het voertuig
            
            /*-
            java.lang.Double[] range = new java.lang.Double[]{50.0, 50.0};
            try
            {
                ScheduleCheckPulses scheduleCheckPulses = new ScheduleCheckPulses(simulator, mapSensorCheckCars, range);
            }
            catch (NetworkException | GTUException | NamingException e)
            {
                e.printStackTrace();
            }
    */
            
            // - Kill a car (EXIT)
            // connect to the sensorKill

            // module that detects a car in the simulation passing a
            // detector and provide actions:
            // - if the car is too far downstream, and not matched by a
            // pulse: we assume it is not matched --> Action: delete the
            // car.
            // -

            // module for logging information
            // car: time and distance traveled since started
            // detector: log all cars that pass (ID and time)
            // road: time and ID for every car entered and exited

            // learning algorithms:
            // if the cars are traveling faster/slower than the pulses:
            // decrease/increase the maximum speed

        }

        /** {@inheritDoc} */
        @Override
        public SimulatorInterface<Abs<TimeUnit>, Rel<TimeUnit>, OTSSimTimeDouble> getSimulator() throws RemoteException
        {
            return this.simulator;
        }

    }

}
