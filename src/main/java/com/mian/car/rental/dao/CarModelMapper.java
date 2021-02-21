package com.mian.car.rental.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mian.car.rental.po.CarModel;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarModelMapper extends BaseMapper<CarModel> {

    @Select("<script>" +
            "select m.id, m.name, m.price_per_hour, m.in_stock, m.created_at from car_model m  where m.id in (select distinct c.car_model_id from car c where c.id not in " +
            "   <foreach item='item' index='index' collection='carIds' open='(' separator=',' close=')'>" +
            "       #{item}" +
            "   </foreach> " +
            ")" +
            "</script>")
    List<CarModel> getCarModelFilteredByCarIds(@Param("carIds") List<String> carIds);

}
