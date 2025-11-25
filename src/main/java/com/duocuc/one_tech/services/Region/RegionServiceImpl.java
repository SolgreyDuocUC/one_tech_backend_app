package com.duocuc.one_tech.services.Region;

import com.duocuc.one_tech.dto.region.RegionDTO;
import com.duocuc.one_tech.dto.region.RegionResponseDTO;
import com.duocuc.one_tech.models.Region;
import com.duocuc.one_tech.repositories.RegionRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RegionServiceImpl implements RegionService {

    private final RegionRepository regionRepository;

    private RegionResponseDTO toDTO(Region region) {
        RegionResponseDTO dto = new RegionResponseDTO();
        dto.setIdRegion(region.getIdRegion());
        dto.setCode(region.getCode());
        dto.setName(region.getName());
        return dto;
    }

    private Region toEntity(RegionDTO dto) {
        Region region = new Region();
        region.setCode(dto.getCode());
        region.setName(dto.getName());
        return region;
    }

    @Override
    @Transactional
    public RegionResponseDTO createRegion(RegionDTO dto) {
        Region region = toEntity(dto);
        Region saved = regionRepository.save(region);
        return toDTO(saved);
    }

    @Override
    public List<RegionResponseDTO> findAll() {
        return regionRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    @Override
    public RegionResponseDTO findById(Long id) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Región no encontrada"));
        return toDTO(region);
    }

    @Override
    @Transactional
    public RegionResponseDTO updateRegion(Long id, RegionDTO dto) {
        Region region = regionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Región no encontrada"));

        region.setCode(dto.getCode());
        region.setName(dto.getName());

        return toDTO(regionRepository.save(region));
    }

    @Override
    @Transactional
    public void deleteRegion(Long id) {
        if (!regionRepository.existsById(id)) {
            throw new RuntimeException("La región no existe");
        }
        regionRepository.deleteById(id);
    }

    @Override
    public RegionResponseDTO findByCode(String code) {
        Region region = regionRepository.findByCode(code)
                .orElseThrow(() -> new RuntimeException("Código no encontrado"));
        return toDTO(region);
    }

    @Override
    public RegionResponseDTO findByName(String name) {
        Region region = regionRepository.findByName(name)
                .orElseThrow(() -> new RuntimeException("Nombre no encontrado"));
        return toDTO(region);
    }
}

