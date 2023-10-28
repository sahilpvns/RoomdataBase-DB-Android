package com.tummoc.tummoc.network.model

import com.tummoc.tummoc.network.db.entity.CategoryInfo

class CategoryResponse {
    var status: Boolean? = null
    var message: String? = null
    var error: String? = null
    var categories: ArrayList<CategoryInfo>? = null

}

