package com.sky.mapper;

import com.github.pagehelper.Page;
import com.sky.dto.GoodsSalesDTO;
import com.sky.dto.OrdersPageQueryDTO;
import com.sky.entity.Orders;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import javax.annotation.ManagedBean;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Mapper
public interface OrderMapper {
    void insert(Orders orders);

    /**
     * 根据订单号查询订单
     * @param orderNumber
     */
    @Select("select * from orders where number = #{orderNumber}")
    Orders getByNumber(String orderNumber);

    /**
     * 修改订单信息
     * @param orders
     */
    void update(Orders orders);

    /**
     * 分页条件查询并按下单时间排序
     * @param ordersPageQueryDTO
     */
    Page<Orders> pageQuery(OrdersPageQueryDTO ordersPageQueryDTO);
    /**
     * 根据id查询订单
     * @param id
     */
    @Select("select * from orders where id=#{id}")
    Orders getById(Long id);
    @Select("select * from orders where status=#{status} and order_time<#{time}")
    List<Orders> getByStatusAndOrderTimeLT(Integer status, LocalDateTime time);

    @Select("select count(id) from orders where status = #{status}")
    Integer countStatus(Integer status);

    Double sumByMap(Map map);

    /**
     * 根据动态条件统计订单数量
     * @param map
     * @return
     */

    Integer countByMap(Map map);

    List<GoodsSalesDTO> getSalesTop10(LocalDateTime begin, LocalDateTime end);
}
