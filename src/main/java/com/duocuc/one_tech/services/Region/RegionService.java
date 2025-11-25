package com.duocuc.one_tech.services.Region;

import com.duocuc.one_tech.dto.region.RegionDTO;
import com.duocuc.one_tech.dto.region.RegionResponseDTO;

import java.util.List;

public interface RegionService {

    RegionResponseDTO createRegion(RegionDTO dto);

    List<RegionResponseDTO> findAll();

    RegionResponseDTO findById(Long id);

    RegionResponseDTO updateRegion(Long id, RegionDTO dto);

    void deleteRegion(Long id);

    RegionResponseDTO findByCode(String code);

    RegionResponseDTO findByName(String name);
}

