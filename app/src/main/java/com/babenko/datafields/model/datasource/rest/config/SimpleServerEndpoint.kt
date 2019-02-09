package com.babenko.datafields.model.datasource.rest.config

import com.babenko.datafields.model.datasource.rest.constant.RestUrls


class SimpleServerEndpoint(
    host: String = RestUrls.HOST,
    path: String? = RestUrls.PATH,
    api: String? = RestUrls.API
) : BaseServerEndpoint(host, path, api)