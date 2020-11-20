package com.example.demo.mapper;

import com.example.demo.entity.Score;

public interface ScoreMapper {
    int insert(Score record);

    int insertSelective(Score record);
}