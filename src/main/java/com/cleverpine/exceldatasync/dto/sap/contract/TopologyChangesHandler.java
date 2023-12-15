package com.cleverpine.exceldatasync.dto.sap.contract;

public interface TopologyChangesHandler {
    Integer getLevelSource();

    String getDisposetNumber();

    String getPlanningCycle();

    String getRegionSource();

    String getNodeIdSource();
}
