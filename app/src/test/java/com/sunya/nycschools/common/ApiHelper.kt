package com.sunya.nycschools.common

import com.sunya.nycschools.data.remote.networkmodel.NetworkSchool
import java.io.InputStreamReader

object ApiHelper {

    fun readJsonAsString(fileName: String): String {
        val inputStreamReader = InputStreamReader(ApiHelper::class.java.getResourceAsStream(fileName))
        val fileData: StringBuilder = StringBuilder()
        inputStreamReader.readLines().forEach {
            fileData.append(it)
        }
        return fileData.toString()
    }

    fun listOfNetworkSchools(): List<NetworkSchool> = listOf(
        NetworkSchool(
            "02M260",
            "Clinton School Writers & Artists, M.S. 260",
            "Students who are prepared for college must have an education that encourages them to take risks as they produce and perform. Our college preparatory curriculum develops writers and has built a tight-knit community. Our school develops students who can think analytically and write creatively. Our arts programming builds on our 25 years of experience in visual, performing arts and music on a middle school level. We partner with New Audience and the Whitney Museum as cultural partners. We are a International Baccalaureate (IB) candidate school that offers opportunities to take college courses at neighboring universities."
        ),
        NetworkSchool(
            "21K728",
            "Liberation Diploma Plus High School",
            "The mission of Liberation Diploma Plus High School, in partnership with CAMBA, is to develop the student academically, socially, and emotionally. We will equip students with the skills needed to evaluate their options so that they can make informed and appropriate choices and create personal goals for success. Our year-round model (trimesters plus summer school) provides students the opportunity to gain credits and attain required graduation competencies at an accelerated rate. Our partners offer all students career preparation and college exposure. Students have the opportunity to earn college credit(s). In addition to fulfilling New York City graduation requirements, students are required to complete a portfolio to receive a high school diploma."
        ),
    )

}