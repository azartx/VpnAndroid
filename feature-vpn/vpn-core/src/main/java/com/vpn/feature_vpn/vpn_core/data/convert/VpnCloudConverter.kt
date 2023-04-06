package com.vpn.feature_vpn.vpn_core.data.convert

import com.vpn.feature_vpn.vpn_core.data.model.DataResponse
import com.vpn.feature_vpn.vpn_core_api.model.Data
import retrofit2.Converter

class VpnCloudConverter : Converter<DataResponse, Data> {

    override fun convert(from: DataResponse): Data =
        Data(time = from.time)
}
