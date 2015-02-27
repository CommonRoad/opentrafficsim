package org.opentrafficsim.importexport.osm.output;

import java.awt.Color;
import java.io.IOException;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.SortedMap;
import java.util.TreeMap;

import javax.naming.NamingException;

import org.geotools.referencing.CRS;
import org.geotools.referencing.crs.DefaultGeocentricCRS;
import org.geotools.referencing.crs.DefaultGeographicCRS;
import org.opengis.referencing.FactoryException;
import org.opengis.referencing.crs.CoordinateReferenceSystem;
import org.opengis.referencing.operation.MathTransform;
import org.opengis.referencing.operation.TransformException;
import org.opentrafficsim.core.dsol.OTSAnimatorInterface;
import org.opentrafficsim.core.dsol.OTSDEVSSimulatorInterface;
import org.opentrafficsim.core.gtu.GTUType;
import org.opentrafficsim.core.network.LongitudinalDirectionality;
import org.opentrafficsim.core.network.NetworkException;
import org.opentrafficsim.core.network.animation.LaneAnimation;
import org.opentrafficsim.core.network.geotools.LinearGeometry;
import org.opentrafficsim.core.network.geotools.NodeGeotools;
import org.opentrafficsim.core.network.lane.CrossSectionLink;
import org.opentrafficsim.core.network.lane.Lane;
import org.opentrafficsim.core.network.lane.LaneType;
import org.opentrafficsim.core.network.lane.SinkLane;
import org.opentrafficsim.core.network.lane.SourceLane;
import org.opentrafficsim.core.unit.FrequencyUnit;
import org.opentrafficsim.core.unit.LengthUnit;
import org.opentrafficsim.core.value.vdouble.scalar.DoubleScalar;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;

/**
 * <p>
 * Copyright (c) 2013-2014 Delft University of Technology, PO Box 5, 2600 AA, Delft, the Netherlands. All rights
 * reserved. <br>
 * BSD-style license. See <a href="http://opentrafficsim.org/node/13">OpenTrafficSim License</a>.
 * <p>
 * @version 30.12.2014 <br>
 * @author <a href="http://www.tudelft.nl/pknoppers">Peter Knoppers</a>
 * @author <a>Moritz Bergmann</a>
 */
public final class Convert
{
    /** Do not instantiate this class. */
    private Convert()
    {
        // Cannot be instantiated.
    }
    
    /**
     * @param c WGS84 Coordinate
     * @return Geocentric Cartesian Coordinate
     * @throws FactoryException 
     * @throws TransformException 
     */
    public static Coordinate transform(final Coordinate c) throws FactoryException, TransformException
    {
        final CoordinateReferenceSystem wgs84 = DefaultGeographicCRS.WGS84;
        final CoordinateReferenceSystem cartesianCRS = DefaultGeocentricCRS.CARTESIAN;
        final MathTransform mathTransform; 
        try
        {
            mathTransform = CRS.findMathTransform(wgs84, cartesianCRS, false);
            double[] srcPt = {c.x, c.y};
            double[] dstPt = new double[mathTransform.getTargetDimensions()];

            mathTransform.transform(srcPt, 0, dstPt, 0, 1);
            Coordinate c2 = new Coordinate(dstPt[0], dstPt[1]);
            return c2;
        }
        catch (FactoryException e)
        {
            throw new FactoryException(e);
        }
        catch (TransformException exception)
        {
            throw new TransformException(exception.getMessage());
        }
    } 

    /**
     * This method converts an OSM link to an OTS link.
     * @param link OSM Link to be converted
     * @return OTS Link
     */
    public static CrossSectionLink<?, ?> convertLink(final org.opentrafficsim.importexport.osm.Link link)
    {
        NodeGeotools.STR start = convertNode(link.getStart());
        NodeGeotools.STR end = convertNode(link.getEnd());
        CrossSectionLink<?, ?> l2;
        if (link.getSplineList().isEmpty())
        {
            GeometryFactory factory = new GeometryFactory();
            Coordinate[] coordinates = new Coordinate[2];
            coordinates[0] = new Coordinate(start.getPoint().x, start.getPoint().y, 0);
            coordinates[1] = new Coordinate(end.getPoint().x, end.getPoint().y, 0);
            LineString lineString = factory.createLineString(coordinates);
            l2 =
                    new CrossSectionLink(link.getID(), start, end, new DoubleScalar.Rel<LengthUnit>(
                            lineString.getLength(), LengthUnit.METER));
            try
            {
                new LinearGeometry(l2, lineString, null);
            }
            catch (NetworkException exception)
            {
                throw new Error("Network exception in LinearGeometry");
            }
            
        }
        else
        {
            List<Coordinate> iC = new ArrayList<Coordinate>();
            for (org.opentrafficsim.importexport.osm.Node spline : link.getSplineList())
            {
                Coordinate coord = new Coordinate(spline.getLongitude(), spline.getLatitude());
                iC.add(coord);
            }
            Coordinate[] intermediateCoordinates = new Coordinate[iC.size()];
            iC.toArray(intermediateCoordinates);
            int coordinateCount = 2 + (null == intermediateCoordinates ? 0 : intermediateCoordinates.length);
            Coordinate[] coordinates = new Coordinate[coordinateCount];
            coordinates[0] = new Coordinate(start.getPoint().x, start.getPoint().y, 0);
            coordinates[coordinates.length - 1] = new Coordinate(end.getPoint().x, end.getPoint().y, 0);
            if (null != intermediateCoordinates)
            {
                for (int i = 0; i < intermediateCoordinates.length; i++)
                {
                    coordinates[i + 1] = new Coordinate(intermediateCoordinates[i]);
                }
            }
            GeometryFactory factory = new GeometryFactory();
            LineString lineString = factory.createLineString(coordinates);
            l2 =
                    new CrossSectionLink(link.getID(), start, end, new DoubleScalar.Rel<LengthUnit>(
                    lineString.getLength(), LengthUnit.METER));
            try
            {
                new LinearGeometry(l2, lineString, null);
            }
            catch (NetworkException exception)
            {
                throw new Error("Network exception in LinearGeometry");
            }
        }
        return l2;
    }

    /**
     * This method converts an OSM node to an OTS node.
     * @param node OSM Node to be converted
     * @return OTS Node
     */
    public static NodeGeotools.STR convertNode(final org.opentrafficsim.importexport.osm.Node node)
    {
        Coordinate coordWGS84;
        Coordinate coordGCC;
        if (node.contains("ele"))
        {
            try
            {
                coordWGS84 = new Coordinate(node.getLongitude(), node.getLatitude(), 
                        Double.parseDouble(node.getTag("ele").getValue()));
                try
                {
                    coordGCC = Convert.transform(coordWGS84);
                    NodeGeotools.STR n2 = new NodeGeotools.STR(Objects.toString(node.getID()), coordGCC);
                    return n2;
                }
                catch (FactoryException exception)
                {
                    exception.printStackTrace();
                }
                catch (TransformException exception)
                {
                    exception.printStackTrace();
                }
            }
            catch (NumberFormatException exception)
            {
                exception.printStackTrace();
            }
            catch (IOException exception)
            {
                exception.printStackTrace();
            }
        }
        else
        {
            coordWGS84 = new Coordinate(node.getLongitude(), node.getLatitude(), 0D);
            try
            {
                coordGCC = Convert.transform(coordWGS84);
                NodeGeotools.STR n2 = new NodeGeotools.STR(Objects.toString(node.getID()), coordGCC);
                return n2;
            }
            catch (FactoryException exception)
            {
                exception.printStackTrace();
            }
            catch (TransformException exception)
            {
                exception.printStackTrace();
            }
        }
        return null;
    }
    
    /**
     * @param osmlink 
     * @return HashMap of the lane structure
     */
    private static HashMap<Double, LaneType<String>> makeStructure(final org.opentrafficsim.importexport.osm.Link osmlink)
    {
        SortedMap<Integer, LaneType<String>> structure = new TreeMap<Integer, LaneType<String>>();
        HashMap<Double, LaneType<String>> structurewithOffset = new HashMap<Double, LaneType<String>>();
        int forwards = osmlink.getForwardLanes();
        int backwards = osmlink.getLanes() - osmlink.getForwardLanes();
        LaneType<String> lt;
        
        for (org.opentrafficsim.importexport.osm.Tag t : osmlink.getTags())
        {
            if (t.getKey().equals("highway")
                    && (t.getValue().equals("primary") || t.getValue().equals("secondary")
                    || t.getValue().equals("tertiary") || t.getValue().equals("residential")
                    || t.getValue().equals("trunk") || t.getValue().equals("motorway") 
                    || t.getValue().equals("service") || t.getValue().equals("unclassified")
                    || t.getValue().equals("motorway_link") || t.getValue().equals("primary_link")
                    || t.getValue().equals("secondary_link") || t.getValue().equals("tertiary_link")
                    || t.getValue().equals("trunk_link") || t.getValue().equals("road")))
            {
                lt = makeLaneType(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.car);
                for (int i = 0 - backwards; i < forwards; i++)
                {
                    structure.put(i, lt);
                }
            }
        }
        for (org.opentrafficsim.importexport.osm.Tag t : osmlink.getTags())
        {
            if (t.getKey().equals("cycleway"))
            {
                lt = makeLaneType(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.bike);
                switch (t.getValue())
                {
                    case "lane":        forwards++;
                                        backwards++;
                                        structure.put(0 - backwards, lt);
                                        structure.put(forwards - 1, lt);
                                        break;
                    case "track":       forwards++;
                                        backwards++;
                                        structure.put(0 - backwards, lt);
                                        structure.put(forwards - 1, lt);
                                        break;
                    case "shared_lane": 
                        List<GTUType<String>> types = new ArrayList<GTUType<String>>();
                        types.add(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.bike);
                        types.add(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.car);
                                        lt = makeLaneType(types);
                                        structure.put(0 - backwards, lt);
                                        structure.put(forwards - 1, lt);
                                        break;
                    default:            break;
                }
            }
        }
        for (org.opentrafficsim.importexport.osm.Tag t : osmlink.getTags())
        {
            if (t.getKey().equals("sidewalk"))
            {
                lt = makeLaneType(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.pedestrian);
                switch (t.getValue())
                {
                    case "both":    forwards++;
                                    backwards++;
                                    structure.put(0 - backwards, lt);
                                    structure.put(forwards - 1, lt);
                                    break;
                    case "left":    backwards++;
                                    structure.put(0 - backwards, lt);
                                    break;
                    case "right":   forwards++;
                                    structure.put(forwards - 1, lt);
                                    break;
                    default:        break;
                }
            }
        }
        for (org.opentrafficsim.importexport.osm.Tag t : osmlink.getTags())
        {
            if (t.getKey().equals("highway") && (t.getValue().equals("cycleway") 
                    || t.getValue().equals("footway") || t.getValue().equals("pedestrian") 
                    || t.getValue().equals("steps")))
            {
                if (t.getValue().equals("footway") || t.getValue().equals("pedestrian") 
                    || t.getValue().equals("steps"))
                {
                    lt = makeLaneType(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.pedestrian);
                    for (int i = 0 - backwards; i < forwards; i++)
                    {
                        structure.put(i, lt);
                    }
                }
                if (t.getValue().equals("cycleway"))
                {
                    lt = makeLaneType(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.bike);
                    for (int i = 0 - backwards; i < forwards; i++)
                    {
                        structure.put(i, lt);
                    }
                }
            }
        }
        
        for (org.opentrafficsim.importexport.osm.Tag t : osmlink.getTags())
        {
            if (t.getKey().equals("highway") &&  t.getValue().equals("path"))
            {
                List<GTUType<String>> types = new ArrayList<GTUType<String>>();
                for (org.opentrafficsim.importexport.osm.Tag t2 : osmlink.getTags())
                {
                    if (t2.getKey().equals("bicycle"))
                    {
                        types.add(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.bike);
                    }
                    if (t2.getKey().equals("foot"))
                    {
                        types.add(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.pedestrian);
                    }
                }
                if (!types.isEmpty())
                {    
                    lt = makeLaneType(types);
                    structure.put(forwards - 1, lt);
                }
            }
        }
        
        structurewithOffset = calculateOffsets(structure, osmlink, forwards, backwards);
        return structurewithOffset;
    }
    
    /**
     * @param structure 
     * @param osmlink 
     * @param forwards 
     * @param backwards 
     * @return HashMap containing the lane structure with offsets.
     */
    private static HashMap<Double, LaneType<String>> calculateOffsets(
            final SortedMap<Integer, LaneType<String>> structure,
            final org.opentrafficsim.importexport.osm.Link osmlink,
            final Integer forwards, final Integer backwards)
    {
        HashMap<Double, LaneType<String>> structurewithOffset = new HashMap<Double, LaneType<String>>();
        LaneType<String> lt;
        Double width = 3.05D;
        //boolean widthOverride = false;
        
        for (org.opentrafficsim.importexport.osm.Tag t : osmlink.getTags())
        {
            if (t.getKey().equals("width"))
            {
                String w = t.getValue().replace(",", ".");
                width = Double.parseDouble(w) / osmlink.getLanes();
                //widthOverride = true;
            }
        }
        double currentOffset = 0.0D;
        if (structure.lastKey() >= 0)
        {
            for (int i = 0; i < forwards; i++)
            {
                lt = structure.get(i);
                if (lt.isCompatible(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.car))
                {
                    structurewithOffset.put(currentOffset, lt);
                    currentOffset += width;
                }
                else if (lt.isCompatible(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.bike))
                {
                    structurewithOffset.put(currentOffset, lt);
                    currentOffset += 0.8D;
                }
                else if (lt.isCompatible(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.pedestrian))
                {
                    structurewithOffset.put(currentOffset, lt);
                    currentOffset += 0.95D;
                }
            }
        }
        if (structure.firstKey() < 0)
        {    
            currentOffset = 0.0D;
            for (int i = -1; i >= (0 - backwards); i--)
            {
                lt = structure.get(i);
                LaneType<String> lt2 = structure.get(i + 1);
                if (lt2.isCompatible(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.car))
                {
                    currentOffset -= width;
                    structurewithOffset.put(currentOffset, lt);
                }
                else if (lt2.isCompatible(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.bike))
                {
                    currentOffset -= 0.8D;
                    structurewithOffset.put(currentOffset, lt);
                }
                else if (lt2.isCompatible(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.pedestrian))
                {
                    currentOffset -= 0.95D;
                    structurewithOffset.put(currentOffset, lt);
                }
            }
        }
        return structurewithOffset;
    }

    /**
     * This method creates lanes out of an OSM link LaneTypes are not jet extensive and can be further increased through
     * Tags provided by OSM. The standard lane width of 3.05 is an estimation based on the Wuropean width limitation for
     * vehicles (2.55m) + 25cm each side.
     * @param osmlink Link; the OSM link to make lanes for
     * @param simulator 
     * @return Lanes
     * @throws NetworkException 
     * @throws NamingException 
     * @throws RemoteException 
     */
    public static List<Lane> makeLanes(
            final org.opentrafficsim.importexport.osm.Link osmlink, final OTSDEVSSimulatorInterface simulator)
            throws NetworkException, RemoteException, NamingException
    {
        CrossSectionLink<?, ?> otslink = convertLink(osmlink);
        List<Lane> lanes = new ArrayList<Lane>();
        LaneType<String> lt = null;
        Lane result = null;
        Color standard = Color.LIGHT_GRAY;
        Color color = Color.LIGHT_GRAY;
        boolean widthOverride = false; /* In case the OSM link provides a width the standard width will be overridden */
        HashMap<Double, LaneType<String>> structure = makeStructure(osmlink);
        
        DoubleScalar.Rel<LengthUnit> width = new DoubleScalar.Rel<LengthUnit>(3.05, LengthUnit.METER); /** estimation */
        for (org.opentrafficsim.importexport.osm.Tag t : osmlink.getTags())
        {
            if (t.getKey().equals("width"))
            {
                String w = t.getValue().replace(",", ".");
                width = new DoubleScalar.Rel<LengthUnit>(Double.parseDouble(w) 
                        / osmlink.getLanes(), LengthUnit.METER);
                widthOverride = true;
            }
        }
        
        DoubleScalar.Abs<FrequencyUnit> f2000 =
                new DoubleScalar.Abs<FrequencyUnit>(2000.0, FrequencyUnit.PER_HOUR); /** temporary */
        Iterator<Double> iter = structure.keySet().iterator();
        while (iter.hasNext())
        {
            Double i = iter.next();
            lt = structure.get(i);
            if (!widthOverride)
            {
                if (lt.isCompatible(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.car))
                {
                    width = new DoubleScalar.Rel<LengthUnit>(3.05, LengthUnit.METER);
                    standard = Color.LIGHT_GRAY;
                }
                else if (lt.isCompatible(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.bike))
                {
                    width = new DoubleScalar.Rel<LengthUnit>(0.8, LengthUnit.METER);
                    standard = Color.ORANGE;
                }
                else if (lt.isCompatible(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.pedestrian))
                {
                    width = new DoubleScalar.Rel<LengthUnit>(0.95, LengthUnit.METER);
                    standard = Color.GREEN;
                }
            }
            else
            {
                if (lt.isCompatible(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.car))
                {
                    standard = Color.LIGHT_GRAY;
                }
                else if (lt.isCompatible(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.bike))
                {
                    standard = Color.ORANGE;
                }
                else if (lt.isCompatible(org.opentrafficsim.importexport.osm.PredefinedGTUTypes.pedestrian))
                {
                    standard = Color.GREEN;
                }
            }
            Double offSet = i;
            DoubleScalar.Rel<LengthUnit> latPos = new DoubleScalar.Rel<LengthUnit>(offSet, LengthUnit.METER);
            if (osmlink.hasTag("hasPreceding") && i >= 0)
            {
                color = Color.RED;
                result = new SinkLane(otslink, latPos, width, lt, LongitudinalDirectionality.FORWARD);
            }
            else if (osmlink.hasTag("hasPreceding") && i < 0)
            {
                color = Color.BLUE;
                result = new SourceLane(otslink, latPos, width, lt, LongitudinalDirectionality.BACKWARD);
            }
            else if (osmlink.hasTag("hasFollowing") && i >= 0)
            {
                color = Color.BLUE;
                result = new SourceLane(otslink, latPos, width, lt, LongitudinalDirectionality.FORWARD);
            }
            else if (osmlink.hasTag("hasFollowing") && i < 0)
            {
                color = Color.RED;
                result = new SinkLane(otslink, latPos, width, lt, LongitudinalDirectionality.BACKWARD);
            }
            else
            {
                color = standard;
                result = new Lane(otslink, latPos, latPos, width, width, lt, LongitudinalDirectionality.FORWARD, f2000);
            }
            animateLane(result, simulator, color);
            lanes.add(result);
        }
        return lanes;
    }
    
    /**
     * Animates Lane.
     * @param l 
     * @param simulator 
     * @param color 
     * @throws RemoteException 
     * @throws NamingException 
     */
    private static void animateLane(final Lane l, final OTSDEVSSimulatorInterface simulator, final Color color) 
            throws RemoteException, NamingException
    {
        if (simulator instanceof OTSAnimatorInterface)
        {
            new LaneAnimation(l, simulator, color);
        }
    }

    /**
     * This method creates a LaneType which supports all GTUTypes that have been specified in the GTUType List "GTUs".
     * @param gtuTypes List&lt;GTUType&lt;String&gt;&gt;; list of GTUTypes
     * @return LaneType permeable for all of the specific GTUTypes
     */
    public static LaneType<String> makeLaneType(final List<GTUType<String>> gtuTypes)
    {
        String iD = "";
        for (GTUType<String> gtu : gtuTypes)
        {
            iD.concat(gtu.getId());
        }
        LaneType<String> lt = new LaneType<String>(iD);
        for (GTUType<String> gtu : gtuTypes)
        {
            lt.addPermeability(gtu);
        }
        return lt;
    }

    /**
     * This method creates a LaneType which supports the specified GTUType.
     * @param gtuType GTUType; the type of GTU that can travel on the new LaneType
     * @return LaneType
     */
    public static LaneType<String> makeLaneType(final GTUType<String> gtuType)
    {
        String iD = gtuType.getId();
        LaneType<String> lt = new LaneType<String>(iD);
        lt.addPermeability(gtuType);
        return lt;
    }
}
