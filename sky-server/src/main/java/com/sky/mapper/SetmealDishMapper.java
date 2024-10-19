package com.sky.mapper;

import com.sky.entity.SetmealDish;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface SetmealDishMapper {
    /**
     * 根据菜品ID查套餐id
     */
    List<Long> getSetmealIdsByDishId(List<Long> dishIds);

    void insertBatch(List<SetmealDish> setmealDishes);
    @Select("select * from setmeal_dish where setmeal_id=#{setmeal}")
    List<SetmealDish> getBySetmealId(Long setmealId);
}
