package com.cleverpine.exceldatasync.dto.sap.simulation;

import com.cleverpine.exceldatasync.dto.sap.contract.ProjectIdHandler;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RmaCustomizingDto implements ProjectIdHandler { //000

    private String function;
    private String paraname;
    private String zkey;
    private String paravalue;
    private Boolean active;
    private String description;
    private Long projectId;
    private Long simulationId;
}
