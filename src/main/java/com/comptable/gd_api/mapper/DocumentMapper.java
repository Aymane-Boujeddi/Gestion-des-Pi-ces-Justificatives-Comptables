package com.comptable.gd_api.mapper;


import com.comptable.gd_api.dto.request.DocumentRequestDTO;
import com.comptable.gd_api.dto.response.DocumentResponseDTO;
import com.comptable.gd_api.entity.Document;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface DocumentMapper {

    Document toEntity(DocumentRequestDTO documentRequestDTO);

    DocumentResponseDTO toResponse(Document document);
}
