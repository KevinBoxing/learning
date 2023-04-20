package com.test.implementation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.relational.core.mapping.Table;

import java.time.LocalDate;


/**
 * Description:  <br>
 * Date: 2022-9-6 10:11<br>
 *
 * @author moon
 * @since 1.0.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table("web_flux_demo") // 标志数据表名
public class WebFluxDemoEntity extends FluxBaseEntity {
    private String username;
    private String password;
    private Integer age;
    /**
     * 注意使用 LocalDate 不能使用util下date
     */
    private LocalDate birth;
}
