package com.cleverpine.exceldatasync.util;

import com.cleverpine.exceldatasync.dto.ExportExcelDto;
import com.cleverpine.exceldatasync.dto.sap.app.*;
import com.cleverpine.exceldatasync.dto.sap.app.optimization.header.*;
import com.cleverpine.exceldatasync.dto.sap.optimization.data.RemovalsDto;
import com.cleverpine.exceldatasync.dto.sap.optimization.data.RemovalsSumViewDto;
import com.cleverpine.exceldatasync.dto.sap.optimization.data.ResupplyTimeDto;
import com.cleverpine.exceldatasync.dto.sap.simulation.DisposetRemovalDto;
import com.cleverpine.exceldatasync.dto.sap.simulation.DisposetReplacementDto;
import com.cleverpine.exceldatasync.dto.sap.simulation.ExternalRepairTimeDto;
import com.cleverpine.exceldatasync.dto.sap.simulation.ImroTimeDto;
import com.cleverpine.exceldatasync.dto.sap.simulation.optimization.data.*;

import java.lang.invoke.MethodHandles;
import java.lang.invoke.VarHandle;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class ExcelVarHandleInitializer {

    /**
     * List of classes that correspond to the Excel Sheets that need to be imported/exported
     */
    public static final List<Class<? extends ExportExcelDto>> REFERENCE_FILE_SHEET_CLASSES = List.of(
            CostFunctionDto.class,
            CustomerReturnTimeDto.class,
            DisposetDto.class,
            DisposetRemovalDto.class,
            DisposetReplacementDto.class,
            AircraftDataDto.class,
            LogisticsPlanDataDto.class,
            CustomerDefaultLogisticsPlanDataDto.class,
            PlanningCyclesDto.class,
            TransportCostDto.class,
            RankDto.class,
            CustomerRegionDto.class,
            HandoverLocationRegionDto.class,
            OperatorOutOfSupplyRegionDto.class,
            DeviationFreeSupplyCustomerDto.class,
            WarehouseNodesDto.class,
            TransportTimeBwWorkshopsDto.class,
            WarehouseAircraftExclusionsDto.class,
            InhouseRepairShareDto.class,
            ImroTimeDto.class,
            ExternalRepairTimeDto.class,
            ResupplyTimeDto.class,
            WarehouseExclusionDto.class,
            MinNumOfPartsInTheRegionDto.class,
            ResupplyChainStructureDto.class,
            CustomsCostDto.class,
            WorkshopConstraintDto.class,
            RemovalsDto.class,
            RemovalsSumViewDto.class
    );

    /**
     * The declared fields of the Excel Sheet Dtos
     * We are saving them in a map instead of calling {@link Class#getDeclaredFields()} every time we need to get them, which is a costly operation.
     */
    private static final Map<Class<?>, Field[]> FIELDS_CACHE;

    /**
     * The VarHandles of the Excel Sheet Dtos
     */
    private static final Map<Class<?>, Map<String, VarHandle>> VAR_HANDLE_CACHE;

    static {
        FIELDS_CACHE = initializeFieldsCache();
        VAR_HANDLE_CACHE = initializeVarHandleCache();
    }

    private ExcelVarHandleInitializer() {
        //Empty and private -> you should not create instances of this class.
    }

    public static VarHandle getVarHandle(Class<? extends ExportExcelDto> declaringClass, String fieldName) {
        var classVarHandleMap = VAR_HANDLE_CACHE.get(declaringClass);
        if (classVarHandleMap == null) {
            throw new IllegalArgumentException("VarHandle not initialized for class: " + declaringClass.getName());
        }
        return classVarHandleMap.get(fieldName);
    }

    public static Field[] getDeclaredFields(Class<? extends ExportExcelDto> fieldValidationDtoClass) {
        return FIELDS_CACHE.get(fieldValidationDtoClass);
    }

    private static Map<Class<?>, Field[]> initializeFieldsCache() {
        return REFERENCE_FILE_SHEET_CLASSES.stream().collect(Collectors.toUnmodifiableMap(Function.identity(), Class::getDeclaredFields));
    }

    private static Map<Class<?>, Map<String, VarHandle>> initializeVarHandleCache() {
        return REFERENCE_FILE_SHEET_CLASSES.stream()
                .collect(Collectors.toUnmodifiableMap(
                        Function.identity(),
                        fieldValidationDtoClass -> Arrays.stream(getDeclaredFields(fieldValidationDtoClass))
                                .collect(Collectors.toUnmodifiableMap(
                                        Field::getName,
                                        field -> createVarHandleForField(fieldValidationDtoClass, field)))));
    }

    private static VarHandle createVarHandleForField(Class<?> declaringClass, Field field) {
        try {
            var lookup = MethodHandles.privateLookupIn(declaringClass, MethodHandles.lookup());
            return lookup.findVarHandle(declaringClass, field.getName(), field.getType());
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize VarHandle for " + declaringClass.getName() + "." + field.getName(), e);
        }
    }
}