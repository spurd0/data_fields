package com.babenko.datafields.model.mapper


import com.babenko.datafields.model.entity.DataField
import com.babenko.datafields.model.mapper.base.BaseMapper
import com.babenko.datafields.model.viewobject.DataFieldsVo


class MapperDataField : BaseMapper<DataFieldsVo.DataFieldVo, DataField>() {

    override fun mapTo(entity: DataField): DataFieldsVo.DataFieldVo {
        return DataFieldsVo.DataFieldVo(entity.id, entity.type, entity.placeholder, null)
    }
}