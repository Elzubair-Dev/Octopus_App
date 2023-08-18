package com.qco.octopusapp.viewPagers.data

import com.qco.octopusapp.R

data class PagerData(
    val image:Int,
    val explanation:Int
)

/**Light theme pager data**/
val pager = listOf(
    PagerData(
        R.drawable.pager_splash,
        R.string.octopus_main_title
    ),
    PagerData(
        R.drawable.pager_main,
        R.string.pager_two
    ),
    PagerData(
        R.drawable.pager_popup,
        R.string.pager_three
    ),
    PagerData(
        R.drawable.pager_drawer,
        R.string.pager_four
    )
)
/**Dark theme pager data**/
val darkPager = listOf(
    PagerData(
        R.drawable.splash_dm, R.string.octopus_main_title
    ),
    PagerData(
        R.drawable.main_dm, R.string.pager_two
    ),
    PagerData(
        R.drawable.popup_dm, R.string.pager_three
    ),
    PagerData(
        R.drawable.drawer_dm, R.string.pager_four
    )
)