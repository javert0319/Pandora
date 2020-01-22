package com.caesarlib.userinfo

import com.caesarlib.res_tools.CaesarStringDealTool
import com.caesarlib.res_tools.ToolsGroble

object UserDeal {
    fun UserIsNull(str: String): String {
        return if (CaesarStringDealTool.StringIsNull(str)) {
            ToolsGroble.appContext.getString(R.string.res_tools_nouser)
        } else str
    }
}
