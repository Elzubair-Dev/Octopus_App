package com.qco.octopusapp.visualization.dialogs.dialogsComposables.pagerDialog.data

import com.qco.octopusapp.R

data class PagerDialogData(
    val image : Int,
    val explanation : Int
    )

/**TPagerList stands for Text to Binary Pager List**/
val TPagerList = listOf(
    PagerDialogData(
        R.drawable.about_b,
        R.string.t_pager_one
    ),
    PagerDialogData(
        R.drawable.eighte_two,
        R.string.t_pager_two
    ),
    PagerDialogData(
        R.drawable.fourte_two,
        R.string.t_pager_three
    ),
    PagerDialogData(
        R.drawable.twenty_two,
        R.string.t_pager_four
    ),
    PagerDialogData(
        R.drawable.ten_two,
        R.string.t_pager_five
    ),
    PagerDialogData(
        R.drawable.five_two,
        R.string.t_pager_six
    ),
    PagerDialogData(
        R.drawable.two_two,
        R.string.t_pager_seven
    ),
    PagerDialogData(
        R.drawable.one_two,
        R.string.t_pager_eight
    )
)

/**BPagerList stands for Binary to Text Pager List**/
val BPagerList = listOf(
    PagerDialogData(
        R.drawable.about_t,R.string.b2t_about
    ),
    PagerDialogData(
        R.drawable.index0,R.string.b2t_index0
    ),
    PagerDialogData(
        R.drawable.index1,R.string.b2t_index1
    ),
    PagerDialogData(
        R.drawable.index2,R.string.b2t_index2
    ),
    PagerDialogData(
        R.drawable.index3,R.string.b2t_index3
    ),
    PagerDialogData(
        R.drawable.index4,R.string.b2t_index4
    ),
    PagerDialogData(
        R.drawable.index5,R.string.b2t_index5
    ),
    PagerDialogData(
        R.drawable.index6,R.string.b2t_index6
    ),
    PagerDialogData(
        R.drawable.index7,R.string.b2t_index7
    )

)