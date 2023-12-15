package com.cleverpine.exceldatasync.dto.sap.simulation;

import com.cleverpine.exceldatasync.dto.sap.contract.ProjectIdHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProdIntegrationHeadDto implements ProjectIdHandler { //280

    private String contractNumber; //bomno
    private String contractType; //bomtype
    private String materialCycle;
    private String prodtype;
    private String status;
    private OffsetDateTime validFrom;
    private OffsetDateTime validUntil;
    private String vlContractNumber; //vlBomno
    private String aircraftContractNumber; //acBomno
    private String engContractNumber; //engBomno
    private Long projectId;
    private Long simulationId;
}
