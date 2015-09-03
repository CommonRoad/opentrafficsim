/**
 * 
 */
package nl.grontmij.smarttraffic.lane;

import java.rmi.RemoteException;
import java.util.HashMap;

import javax.naming.NamingException;

import org.opentrafficsim.core.dsol.OTSSimulatorInterface;
import org.opentrafficsim.core.gtu.RelativePosition;
import org.opentrafficsim.core.gtu.lane.LaneBasedGTU;
import org.opentrafficsim.core.network.lane.AbstractSensor;
import org.opentrafficsim.core.network.lane.Lane;

/**
 * @author p070518
 */
public class GenerateSensor extends AbstractSensor
{
    /** */
    private static final long serialVersionUID = 20141231L;

    private HashMap<Time.Abs, Integer> statusByTime = new HashMap<Time.Abs, Integer>();

    /**
     * @param lane
     * @param longitudinalPositionFromEnd
     * @param nameSensor
     * @param nameJunction
     */
    public GenerateSensor(Lane lane, Length.Rel longitudinalPositionFromEnd, final RelativePosition.TYPE front,
        String nameSensor, final OTSSimulatorInterface simulator)
    {
        super(lane, longitudinalPositionFromEnd, front, nameSensor, simulator);
        try
        {
            new GenerateSensorAnimation(this, simulator);
        }
        catch (RemoteException | NamingException exception)
        {
            exception.printStackTrace();
        }
    }

    public HashMap<Time.Abs, Integer> getStatusByTime()
    {
        return statusByTime;
    }

    public void setStatusByTime(HashMap<Time.Abs, Integer> statusByTime)
    {
        this.statusByTime = statusByTime;
    }

    public void addStatusByTime(Time.Abs timeNow, Integer status)
    {
        this.statusByTime.put(timeNow, status);
    }

    /**
     * {@inheritDoc} <br>
     * For this method, we assume that the right sensor triggered this method. In this case the sensor that indicates the front
     * of the GTU. The code triggering the sensor therefore has to do the checking for sensor type.
     */
    @Override
    public void trigger(final LaneBasedGTU gtu)
    {
        // no action needed
    }

    /** {@inheritDoc} */
    @Override
    public String toString()
    {
        return "GenerateSensor [getLane()=" + this.getLane() + ", getLongitudinalPosition()="
            + this.getLongitudinalPosition() + ", getPositionType()=" + this.getPositionType() + "]";
    }

}
