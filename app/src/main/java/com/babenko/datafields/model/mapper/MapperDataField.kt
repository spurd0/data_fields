package com.babenko.datafields.model.mapper


import com.babenko.datafields.model.entity.DataField
import com.babenko.datafields.model.mapper.base.BaseMapper
import com.babenko.datafields.model.viewobject.DataFieldVo


class MapperDataField : BaseMapper<DataFieldVo, DataField>() {

    override fun mapTo(entity: DataField): DataFieldVo {
        return DataFieldVo(entity.id, entity.type, entity.placeholder, null)
    }
}