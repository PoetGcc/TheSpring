package com.gcc.thespring.utils

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonParser

/**
 * @author guocc
 * @date 2020/8/22
 * 考虑全局使用一个 Gson 对象
 */
class GsonUtils private constructor() {
    companion object {
        private val sGson: Gson = GsonBuilder().serializeNulls().disableHtmlEscaping().create()

        @JvmStatic
        fun <T> fromJson(json: String, tClass: Class<T>): T {
            return sGson.fromJson(json, tClass)
        }

        @JvmStatic
        fun toJson(any: Any): String {
            return sGson.toJson(any)
        }

        @JvmStatic
        fun formatJson(json: String): String {
            val je = JsonParser.parseString(json)
            return GsonBuilder().setPrettyPrinting().create().toJson(je)
        }
    }

    init {
        throw AssertionError("No instances.")
    }
}
