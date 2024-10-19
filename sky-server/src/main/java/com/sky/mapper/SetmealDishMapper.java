package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品ID查套餐id
     */
    List<Long> getSetmealIdsByDishId(List<Long> dishIds);

    void insertBatch(List<SetmealDish> setmealDishes);
}
