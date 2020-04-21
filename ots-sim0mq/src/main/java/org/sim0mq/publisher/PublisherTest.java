package org.sim0mq.publisher;

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Frame;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.rmi.RemoteException;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.naming.NamingException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.xml.bind.JAXBException;
import javax.xml.parsers.ParserConfigurationException;

import org.djunits.unit.DurationUnit;
import org.djunits.unit.TimeUnit;
import org.djunits.value.vdouble.scalar.Duration;
import org.djunits.value.vdouble.scalar.Length;
import org.djunits.value.vdouble.scalar.Time;
import org.djutils.event.EventInterface;
import org.djutils.event.EventListenerInterface;
import org.djutils.immutablecollections.ImmutableMap;
import org.opentrafficsim.core.animation.gtu.colorer.DefaultSwitchableGTUColorer;
import org.opentrafficsim.core.dsol.AbstractOTSModel;
import org.opentrafficsim.core.dsol.OTSAnimator;
import org.opentrafficsim.core.dsol.OTSModelInterface;
import org.opentrafficsim.core.dsol.OTSSimulatorInterface;
import org.opentrafficsim.core.geometry.OTSGeometryException;
import org.opentrafficsim.core.gtu.GTUException;
import org.opentrafficsim.core.gtu.GTUType;
import org.opentrafficsim.core.network.NetworkException;
import org.opentrafficsim.core.network.OTSNetwork;
import org.opentrafficsim.core.object.InvisibleObjectInterface;
import org.opentrafficsim.draw.core.OTSDrawingException;
import org.opentrafficsim.draw.factory.DefaultAnimationFactory;
import org.opentrafficsim.road.network.OTSRoadNetwork;
import org.opentrafficsim.road.network.factory.xml.XmlParserException;
import org.opentrafficsim.road.network.factory.xml.parser.XmlNetworkLaneParser;
import org.opentrafficsim.road.network.lane.conflict.ConflictBuilder;
import org.opentrafficsim.road.network.lane.conflict.LaneCombinationList;
import org.opentrafficsim.swing.gui.OTSAnimationPanel;
import org.opentrafficsim.swing.gui.OTSSimulationApplication;
import org.opentrafficsim.swing.gui.OTSSwingApplication;
import org.opentrafficsim.trafficcontrol.TrafficControlException;
import org.opentrafficsim.trafficcontrol.trafcod.TrafCOD;
import org.xml.sax.SAXException;

import nl.tudelft.simulation.dsol.SimRuntimeException;
import nl.tudelft.simulation.dsol.swing.gui.TabbedContentPane;
import nl.tudelft.simulation.jstats.streams.MersenneTwister;
import nl.tudelft.simulation.jstats.streams.StreamInterface;
import nl.tudelft.simulation.language.DSOLException;

/**
 * Test code to see if the Publisher works.
 */
public class PublisherTest
{
    /**
     * Test code.
     * @param args String[]; the command line arguments (not used)
     * @throws IOException ...
     * @throws NamingException ...
     * @throws SimRuntimeException ...
     * @throws DSOLException ...
     * @throws OTSDrawingException ...
     */
    public static void main(final String[] args)
            throws IOException, SimRuntimeException, NamingException, DSOLException, OTSDrawingException
    {
        Duration warmupDuration = Duration.ZERO;
        Duration runDuration = new Duration(3600, DurationUnit.SECOND);
        Long seed = 123456L;
        String xml = new String(Files
                .readAllBytes(Paths.get("C:/Users/pknoppers/Java/ots-demo/src/main/resources/TrafCODDemo2/TrafCODDemo2.xml")));
        OTSAnimator animator = new OTSAnimator("OTS Animator");
        Sim0MQOTSModel model = new Sim0MQOTSModel(animator, "OTS model", "Remotely controlled OTS model", xml);
        Map<String, StreamInterface> map = new LinkedHashMap<>();
        map.put("generation", new MersenneTwister(seed));
        animator.initialize(Time.ZERO, warmupDuration, runDuration, model, map);
        OTSAnimationPanel animationPanel = new OTSAnimationPanel(model.getNetwork().getExtent(), new Dimension(1100, 1000),
                animator, model, OTSSwingApplication.DEFAULT_COLORER, model.getNetwork());
        DefaultAnimationFactory.animateXmlNetwork(model.getNetwork(), animator, new DefaultSwitchableGTUColorer());
        new OTSSimulationApplication<OTSModelInterface>(model, animationPanel);
        JFrame frame = (JFrame) animationPanel.getParent().getParent().getParent();
        frame.setExtendedState(Frame.NORMAL);
        frame.setSize(new Dimension(1100, 1000));
        frame.setBounds(0, 25, 1100, 1000);
        animator.setSpeedFactor(Double.MAX_VALUE, true);
        animator.setSpeedFactor(1000.0, true);

        ImmutableMap<String, InvisibleObjectInterface> invisibleObjectMap = model.getNetwork().getInvisibleObjectMap();
        for (InvisibleObjectInterface ioi : invisibleObjectMap.values())
        {
            if (ioi instanceof TrafCOD)
            {
                TrafCOD trafCOD = (TrafCOD) ioi;
                Container controllerDisplayPanel = trafCOD.getDisplayContainer();
                if (null != controllerDisplayPanel)
                {
                    JPanel wrapper = new JPanel(new BorderLayout());
                    wrapper.add(new JScrollPane(controllerDisplayPanel));
                    TabbedContentPane tabbedPane = animationPanel.getTabbedPane();
                    tabbedPane.addTab(tabbedPane.getTabCount() - 1, trafCOD.getId(), wrapper);
                }
            }
        }
        try
        {
            Thread.sleep(300);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        Publisher publisher = new Publisher(model.getNetwork());
        publisher.executeCommand("GTUs in network", SubscriptionHandler.Command.SUBSCRIBE_TO_ADD, null);
        System.out.println("Simulating up to 10 s");
        animator.runUpTo(new Time(30, TimeUnit.BASE_SECOND));
        while (animator.isRunning())
        {
            try
            {
                Thread.sleep(10);
            }
            catch (InterruptedException e)
            {
                e.printStackTrace();
            }
        }
        System.out.println("Simulator has stopped at time " + animator.getSimulatorTime());
        publisher.executeCommand("GTUs in network", SubscriptionHandler.Command.GET_CURRENT_POPULATION, null);
    }

}

/**
 * The Model.
 */
class Sim0MQOTSModel extends AbstractOTSModel implements EventListenerInterface
{
    /** */
    private static final long serialVersionUID = 20170419L;

    /** The network. */
    private OTSRoadNetwork network;

    /** The XML. */
    private final String xml;

    /**
     * @param simulator OTSSimulatorInterface; the simulator
     * @param shortName String; the model name
     * @param description String; the model description
     * @param xml String; the XML description of the simulation model
     */
    Sim0MQOTSModel(final OTSSimulatorInterface simulator, final String shortName, final String description, final String xml)
    {
        super(simulator, shortName, description);
        this.xml = xml;
    }

    /** {@inheritDoc} */
    @Override
    public void notify(final EventInterface event) throws RemoteException
    {
        System.err.println("Received event " + event);
    }

    /** {@inheritDoc} */
    @Override
    public void constructModel() throws SimRuntimeException
    {
        this.network = new OTSRoadNetwork(getShortName(), true);
        try
        {
            XmlNetworkLaneParser.build(new ByteArrayInputStream(this.xml.getBytes(StandardCharsets.UTF_8)), this.network,
                    getSimulator(), false);
            LaneCombinationList ignoreList = new LaneCombinationList();
            LaneCombinationList permittedList = new LaneCombinationList();
            ConflictBuilder.buildConflictsParallel(this.network, this.network.getGtuType(GTUType.DEFAULTS.VEHICLE),
                    getSimulator(), new ConflictBuilder.FixedWidthGenerator(Length.instantiateSI(2.0)), ignoreList,
                    permittedList);
        }
        catch (NetworkException | OTSGeometryException | JAXBException | URISyntaxException | XmlParserException | SAXException
                | ParserConfigurationException | GTUException | IOException | TrafficControlException exception)
        {
            exception.printStackTrace();
            // Abusing the SimRuntimeException to propagate the message to the main method (the problem could actually be a
            // parsing problem)
            throw new SimRuntimeException(exception);
        }
    }

    /** {@inheritDoc} */
    @Override
    public OTSNetwork getNetwork()
    {
        return this.network;
    }

    /** {@inheritDoc} */
    @Override
    public Serializable getSourceId()
    {
        return "Sim0MQOTSModel";
    }
}
