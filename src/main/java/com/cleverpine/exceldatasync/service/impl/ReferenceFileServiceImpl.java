package com.cleverpine.exceldatasync.service.impl;


import com.cleverpine.exceldatasync.dto.ExcelFileDto;
import com.cleverpine.exceldatasync.dto.SimulationReferenceGlossaryDto;
import com.cleverpine.exceldatasync.util.ExportExcelHelper;
import lombok.RequiredArgsConstructor;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.data.domain.PageRequest;

import java.util.List;


@RequiredArgsConstructor
public class ReferenceFileServiceImpl {

    private final ExportExcelHelper exportExcelHelper;

    public ExcelFileDto generateExcelData(Long simulationId) {

        var workbook = new SXSSFWorkbook();
        PageRequest pageRequest = PageRequest.of(0, 1000);
//        var simulation = simulationService.getById(simulationId);
//        exportExcelHelper.fetchAllAndMapAndWrite(pageRequest, costFunctionService::getAll, workbook, CostFunctionDto.class);
//        exportExcelHelper.fetchAllAndMapAndWrite(pageRequest, customerReturnTimeService::getAll, workbook, CustomerReturnTimeDto.class);
//        exportExcelHelper.fetchAllAndMapByIdAndWrite(simulationId, pageRequest, disposetService::findDistinctDisposetsBySimulationId, workbook, DisposetDto.class);
//        exportExcelHelper.fetchAllAndMapByIdAndWrite(simulationId, pageRequest, disposetRemovalsService::getAllBySimulationId, workbook, DisposetRemovalDto.class);
//        exportExcelHelper.fetchAllAndMapByIdAndWrite(simulationId, pageRequest, disposetReplacementService::getAllBySimulationId, workbook, DisposetReplacementDto.class);
//        exportExcelHelper.fetchAllAndMapAndWrite(pageRequest, aircraftDataService::getAll, workbook, AircraftDataDto.class);
//        exportExcelHelper.fetchAllAndMapAndWrite(pageRequest, logisticsPlanDataService::getAll, workbook, LogisticsPlanDataDto.class);
//        exportExcelHelper.fetchAllAndMapAndWrite(pageRequest, customerDefaultLogisticsPlanDataService::getAll, workbook, CustomerDefaultLogisticsPlanDataDto.class);
//        exportExcelHelper.fetchAllAndMapAndWrite(pageRequest, planningCyclesService::getAll, workbook, PlanningCyclesDto.class);
//        exportExcelHelper.fetchAllAndMapAndWrite(pageRequest, transportCostService::getAll, workbook, TransportCostDto.class);
//        exportExcelHelper.fetchAllAndMapAndWrite(pageRequest, rankService::getAll, workbook, RankDto.class);
//        exportExcelHelper.fetchAllAndMapAndWrite(pageRequest, customerRegionService::getAll, workbook, CustomerRegionDto.class);
//        exportExcelHelper.fetchAllAndMapAndWrite(pageRequest, handoverLocationRegionService::getAll, workbook, HandoverLocationRegionDto.class);
//        exportExcelHelper.fetchAllAndMapAndWrite(pageRequest, operatorOutOfSupplyRegionService::getAll, workbook, OperatorOutOfSupplyRegionDto.class);
//        exportExcelHelper.fetchAllAndMapAndWrite(pageRequest, deviationFreeSupplyCustomerService::getAll, workbook, DeviationFreeSupplyCustomerDto.class);
//        exportExcelHelper.fetchAllAndMapAndWrite(pageRequest, warehouseNodeService::getAll, workbook, WarehouseNodesDto.class);
//        exportExcelHelper.fetchAllAndMapAndWrite(pageRequest, transportTimeBwWorkshopService::getAll, workbook, TransportTimeBwWorkshopsDto.class);
//        exportExcelHelper.fetchAllAndMapAndWrite(pageRequest, warehouseAircraftExclusionService::getAll, workbook, WarehouseAircraftExclusionsDto.class);
//        exportExcelHelper.fetchAllAndMapByIdAndWrite(simulationId, pageRequest, inhouseRepairShareService::getAllBySimulationId, workbook, InhouseRepairShareDto.class);
//        exportExcelHelper.fetchAllAndMapByIdAndWrite(simulationId, pageRequest, imroTimeService::getAllBySimulationId, workbook, ImroTimeDto.class);
//        exportExcelHelper.fetchAllAndMapByIdAndWrite(simulationId, pageRequest, externalRepairTimeService::getAllBySimulationId, workbook, ExternalRepairTimeDto.class);
//        exportExcelHelper.fetchAllAndMapByIdAndWrite(simulationId, pageRequest, resupplyTimeService::getAllBySimulationId, workbook, ResupplyTimeDto.class);
//        exportExcelHelper.fetchAllAndMapByIdAndWrite(simulationId, pageRequest, warehouseExclusionService::getAllBySimulationId, workbook, WarehouseExclusionDto.class);
//        exportExcelHelper.fetchAllAndMapByIdAndWrite(simulationId, pageRequest, minNumOfPartsInTheRegionService::getAllBySimulationId, workbook, MinNumOfPartsInTheRegionDto.class);
//        exportExcelHelper.fetchAllAndMapByIdAndWrite(simulationId, pageRequest, resupplyChainStructureService::getAllBySimulationId, workbook, ResupplyChainStructureDto.class);
//        exportExcelHelper.fetchAllAndMapByIdAndWrite(simulationId, pageRequest, customsCostService::getAllBySimulationId, workbook, CustomsCostDto.class);
//        exportExcelHelper.fetchAllAndMapByIdAndWrite(simulationId, pageRequest, workshopConstraintService::getAllBySimulationId, workbook, WorkshopConstraintDto.class);
//        exportExcelHelper.fetchAllAndMapByIdAndWrite(simulationId, pageRequest, removalService::findAllBySimulationId, workbook, RemovalsDto.class);
//        exportExcelHelper.fetchAllAndMapByIdAndWrite(simulationId, pageRequest, removalService::findAllBySimulationIdGroupedByDisposetNumber, removalMapper::removalsSumViewToRemovalsSumViewDto, workbook, RemovalsSumViewDto.class);
//        exportExcelHelper.writeDataToWorkbook(workbook, GLOSSARY_DTO_LIST, SimulationReferenceGlossaryDto.class);
//
//
        var filename = /*String.format(SIMULATION_REFERENCE_FILE_NAME, simulation.getName());*/ "";
        return exportExcelHelper.getExcelFileDto(workbook, filename);
    }

    public static final List<SimulationReferenceGlossaryDto> GLOSSARY_DTO_LIST = List.of(
            new SimulationReferenceGlossaryDto("ZMDLP_101_RMA_721", "Kostenfunktion", "Hier sind die relevanten Kostenfunktionen aufgelistet"),
            new SimulationReferenceGlossaryDto("ZMDLP_204_REF", "Kundenrücklieferzeit", "Hier sind aller Kundenrücklieferzeiten (KRÜTZ) aufgelistet"),
            new SimulationReferenceGlossaryDto("ZMDLP_300_RMA_730", "Disposet Stammdaten", "Alle Stammdaten zu den ausgewählten Disposets"),
            new SimulationReferenceGlossaryDto("ZMDLP_302_REF", "MxBR-Werte", "Hier werden alle MxBR-Werte für die ausgewählten Disposets angezeigt"),
            new SimulationReferenceGlossaryDto("ZMDLP_307_REF", "GemBev-Informationen", "Hier werden ale Disposets aufgelistet, die GemBev sind"),
            new SimulationReferenceGlossaryDto("ZMDLP_ME_111_REF", "Flugzeugtypen", "Hier werden alle Flugzeug-Serien und die dazugehörigen Flugzeug-Typen aufgelistet"),
            new SimulationReferenceGlossaryDto("ZMDLP_ME_421_RMA_727", "Logstikplandaten", "Logistikzeiten zwischen Lagerstandorten und Übergabelokationen"),
            new SimulationReferenceGlossaryDto("ZMDLP_ME_423_RMA_728", "Logistikplandaten Kundendefault", "Default-Logistikzeiten zwischen Lagerstandorten und den Kunden"),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_103_724", "Planungskreisläufe", "Auflist der Planungskreisläufe DEVY und DEVN"),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_105_722", "Transportkosten", "Gemittelte Transportkosten zwischen den Regionen"),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_106_723", "Lagerort Rang", "Ranking der Lagerstandorte inklusive Lagerzusatzkosten"),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_130_REF", "Kundenregion", "Zuordnung der Kunden zu einer Region"),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_140_REF", "Region Übergabe-Lokation", "Zuordnung der Übergabelokationen zu einer Region"),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_150_REF", "Kundenwunsch nach regionaler Versorgung", "Hier wird hinterlegt, welcher Kunde auf welcher Flotte eine regionale Versorgung wünscht."),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_151_REF", "Kunden mit Abweichungsfreier Versorgung", "Hier sind Kunden und Kundenflotten aufgelistet, die eine DEVN-Versorgung wünschen"),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_160_726", "Topologische Knoten", "Auflistung der Lagerorte inklusive PLC"),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_165_725", "Logistikzeit zwischen Knoten", "Transportzeiten zwischen den Lagerorten"),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_170_729", "Lagerausschluss für Kunden", "Hier werden alle Lagerausschlüsse für die einzelnen Kunden aufgelistet"),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_303EF_471", "Eigenfertigungszeit", "Hier wird die Eigenfertigungszeit pro Disposet aufgeführt."),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_303FF_472", "Fremdfertigungszeit", "Hier wird die Eigenfertigungszeit pro Disposet und Rwgionengruppe aufgeführt."),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_303VIE_732", "Anteil Eigenfertigung", "Hier wird der Anteil der Eigenfertigung pro Disposet und Regionengruppe angezeigt"),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_305_731", "RST-Zeit", "Hier werden alles Disposets aufgelistet, die einen Lagerausschulss haben"),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_310_733", "Lagerausschluss für Disposets", "Hier sind die relevanten Kostenfunktionen aufgelistet"),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_311_734", "Bestandsvorgaben", "Bestandsvorgaben pro Disposet und Region"),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_340_740", "Topologie zum Disposet", "Hier wird aufgelistet, welcher Hauptlagerort den Disposets zugordnet ist, und welche untergeordnenten Versorgungslager in Frage kommen"),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_390_735", "Constraints", "Hier werden die jeweiligen Contraims aufgeführt"),
            new SimulationReferenceGlossaryDto("ZMDLP_RMA_900_750", "Wechsel / Bedarfe", "Hier werden alle Wechsel der Kunden aufgeführt.")
    );

}
