package org.opentrafficsim.road.network;

import java.util.HashMap;
import java.util.Map;

import org.djutils.immutablecollections.Immutable;
import org.djutils.immutablecollections.ImmutableHashMap;
import org.djutils.immutablecollections.ImmutableMap;
import org.opentrafficsim.core.compatibility.GTUCompatibility;
import org.opentrafficsim.core.gtu.GTUType;
import org.opentrafficsim.core.network.LongitudinalDirectionality;
import org.opentrafficsim.core.network.OTSNetwork;
import org.opentrafficsim.road.network.lane.LaneType;

/**
 * OTSRoadNetwork adds a number of methods to the Network class that are specific for roads, such as the LaneTypes. <br>
 * <br>
 * Copyright (c) 2003-2018 Delft University of Technology, Jaffalaan 5, 2628 BX Delft, the Netherlands. All rights reserved. See
 * for project information <a href="https://www.simulation.tudelft.nl/" target="_blank">www.simulation.tudelft.nl</a>. The
 * source code and binary code of this software is proprietary information of Delft University of Technology.
 * @author <a href="https://www.tudelft.nl/averbraeck" target="_blank">Alexander Verbraeck</a>
 */
public class OTSRoadNetwork extends OTSNetwork implements RoadNetwork
{
    /** */
    private static final long serialVersionUID = 1L;

    /** LaneTypes registered for this network. */
    private Map<String, LaneType> laneTypeMap = new HashMap<>();

    /**
     * Construction of an empty network.
     * @param id String; the network id.
     * @param addDefaultTypes add the default GTUTypes, LinkTypesand LaneTypes, or not
     */
    public OTSRoadNetwork(final String id, final boolean addDefaultTypes)
    {
        super(id, addDefaultTypes);
        if (addDefaultTypes)
        {
            addDefaultLaneTypes();
        }
    }

    /** {@inheritDoc} */
    @Override
    public void addDefaultLaneTypes()
    {
        GTUCompatibility<LaneType> noTrafficCompatibility = new GTUCompatibility<>((LaneType) null);
        new LaneType("NONE", noTrafficCompatibility, this);
        GTUCompatibility<LaneType> roadCompatibility = new GTUCompatibility<>((LaneType) null);
        roadCompatibility.addAllowedGTUType(getGtuType(GTUType.DEFAULTS.ROAD_USER), LongitudinalDirectionality.DIR_BOTH);
        LaneType twoWayLane = new LaneType("TWO_WAY_LANE", roadCompatibility, this);
        new LaneType("RURAL_ROAD", twoWayLane, new GTUCompatibility<>(roadCompatibility), this);
        new LaneType("URBAN_ROAD", twoWayLane, new GTUCompatibility<>(roadCompatibility), this);
        new LaneType("RESIDENTIAL_ROAD", twoWayLane, new GTUCompatibility<>(roadCompatibility), this);
        GTUCompatibility<LaneType> oneWayLaneCompatibility = new GTUCompatibility<>(roadCompatibility);
        oneWayLaneCompatibility.addAllowedGTUType(getGtuType(GTUType.DEFAULTS.ROAD_USER), LongitudinalDirectionality.DIR_PLUS);
        oneWayLaneCompatibility.addAllowedGTUType(getGtuType(GTUType.DEFAULTS.PEDESTRIAN), LongitudinalDirectionality.DIR_BOTH);
        new LaneType("ONE_WAY_LANE", oneWayLaneCompatibility, this);
        GTUCompatibility<LaneType> highwayLaneCompatibility = new GTUCompatibility<>(oneWayLaneCompatibility)
                .addAllowedGTUType(getGtuType(GTUType.DEFAULTS.PEDESTRIAN), LongitudinalDirectionality.DIR_NONE);
        new LaneType("FREEWAY", highwayLaneCompatibility, this);
        new LaneType("HIGHWAY", highwayLaneCompatibility, this);
        GTUCompatibility<LaneType> busLaneCompatibility = new GTUCompatibility<>(roadCompatibility);
        busLaneCompatibility.addAllowedGTUType(getGtuType(GTUType.DEFAULTS.BUS), LongitudinalDirectionality.DIR_BOTH);
        busLaneCompatibility.addAllowedGTUType(getGtuType(GTUType.DEFAULTS.ROAD_USER), LongitudinalDirectionality.DIR_NONE);
        new LaneType("BUS_LANE", busLaneCompatibility, this);
        GTUCompatibility<LaneType> mopedAndBicycleLaneCompatibility = new GTUCompatibility<>(roadCompatibility);
        mopedAndBicycleLaneCompatibility.addAllowedGTUType(getGtuType(GTUType.DEFAULTS.BICYCLE),
                LongitudinalDirectionality.DIR_BOTH);
        mopedAndBicycleLaneCompatibility.addAllowedGTUType(getGtuType(GTUType.DEFAULTS.ROAD_USER),
                LongitudinalDirectionality.DIR_NONE);
        new LaneType("MOPED_PATH", mopedAndBicycleLaneCompatibility, this);
        GTUCompatibility<LaneType> bicycleOnlyCompatibility = new GTUCompatibility<>(mopedAndBicycleLaneCompatibility);
        bicycleOnlyCompatibility.addAllowedGTUType(getGtuType(GTUType.DEFAULTS.MOPED), LongitudinalDirectionality.DIR_NONE);
        new LaneType("BICYCLE_PATH", bicycleOnlyCompatibility, this);
        GTUCompatibility<LaneType> pedestriansOnly = new GTUCompatibility<>(roadCompatibility);
        pedestriansOnly.addAllowedGTUType(getGtuType(GTUType.DEFAULTS.ROAD_USER), LongitudinalDirectionality.DIR_NONE);
        new LaneType("FOOTPATH", pedestriansOnly, this);
    }

    /** {@inheritDoc} */
    @Override
    public void addLaneType(final LaneType laneType)
    {
        this.laneTypeMap.put(laneType.getId(), laneType);
    }

    /** {@inheritDoc} */
    @Override
    public LaneType getLaneType(final String laneTypeId)
    {
        return this.laneTypeMap.get(laneTypeId);
    }

    /** {@inheritDoc} */
    @Override
    public LaneType getLaneType(final LaneType.DEFAULTS laneTypeEnum)
    {
        return this.laneTypeMap.get(laneTypeEnum.getId());
    }

    /** {@inheritDoc} */
    @Override
    public ImmutableMap<String, LaneType> getLaneTypes()
    {
        return new ImmutableHashMap<>(this.laneTypeMap, Immutable.WRAP);
    }

}