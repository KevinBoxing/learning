package com.test.implementation;

import cn.hutool.core.util.StrUtil;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.domain.Persistable;
import org.springframework.data.relational.core.mapping.Column;

import java.io.Serializable;

/**
 * Description: 基类 实现 Persistable 接口,并实现 isNew 方法,<br>
 * Date: 2022-9-7 14:47<br>
 *
 * @author moon
 * @since 1.0.0
 */
@Data
public class FluxBaseEntity implements Persistable<String>,Serializable {

    /**
     * 主键
     */
    @Id
    @Column("pk_id") // 表内字段名字
    private String id;

    /**
     * 在实体新怎和修改方法时会走该方法,来判断需要新增还是修改
     * @return bool
     */
    @Override
    public boolean isNew() {
        return StrUtil.isBlank(this.id);
    }
}
