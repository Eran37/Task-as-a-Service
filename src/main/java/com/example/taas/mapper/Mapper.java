package com.example.taas.mapper;

// Tiger api -> java 5 ->
// interface that casting two types

import java.util.List;

public interface Mapper <DAO, DTO> {

    DAO toDao(DTO dto);
    DTO toDto(DAO dao);

    List<DAO> toDaoList(List<DTO> dtoList);
    List<DTO> toDtoList(List<DAO> daoList);

}
