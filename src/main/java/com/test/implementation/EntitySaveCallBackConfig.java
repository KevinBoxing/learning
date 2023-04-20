package com.test.implementation;

import cn.hutool.core.util.IdUtil;
import cn.hutool.core.util.StrUtil;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.mapping.OutboundRow;
import org.springframework.data.r2dbc.mapping.event.BeforeSaveCallback;
import org.springframework.data.relational.core.sql.SqlIdentifier;
import org.springframework.r2dbc.core.Parameter;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

/**
 * Description: entity保存前回调 <br>
 * Date: 2022-9-7 14:45<br>
 *
 * @author moon
 * @since 1.0.0
 */
@Component
public class EntitySaveCallBackConfig implements BeforeSaveCallback<FluxBaseEntity> {

    /**
     *  保存前回调方法
     * @param entity 待保存实体
     * @param outboundRow 数据库字段及类型
     * @param sqlIdentifier 数据库表名
     * @return Publisher<com.test.implementation.FluxBaseEntity>
     */
    @Override
    public Publisher<FluxBaseEntity> onBeforeSave(FluxBaseEntity entity, OutboundRow outboundRow, SqlIdentifier sqlIdentifier) {
        if (StrUtil.isBlank(entity.getId())) {
            String simpleUUID = IdUtil.simpleUUID();
            // 回填主键(前端回显时用)
            entity.setId(simpleUUID);
            // 数据库新增主键
            outboundRow.put("pk_id",Parameter.from(simpleUUID));
        }
        return Mono.just(entity);
    }
}
