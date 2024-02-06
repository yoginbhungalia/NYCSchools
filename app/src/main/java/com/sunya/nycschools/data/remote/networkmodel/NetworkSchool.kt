package com.sunya.nycschools.data.remote.networkmodel

import com.google.gson.annotations.SerializedName
import com.sunya.nycschools.data.model.School

data class NetworkSchool(
    val dbn: String,
    @SerializedName("school_name")
    val schoolName: String,
    @SerializedName("overview_paragraph")
    val overviewParagraph: String,
) {
    fun toSchool() = School(
        dbn,
        schoolName,
        overviewParagraph
    )
}
