package com.cleverpine.exceldatasync.dto.sap.simulation;

import com.cleverpine.exceldatasync.dto.sap.contract.ProjectIdHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdIntegrationPosDto implements ProjectIdHandler { //281

    private String contractNumber; //bomno
    private String contractItem; //bomitem
    private String matnr;
    private String disposetNumber; //mrpgr
    private BigDecimal noAccess; // (20, 9)
    private String nodeId; //nid
    private Integer rst;
    private String kostl;
    private String status;
    private String rmaRegion;
    private Long projectId;
    private Long simulationId;
}
