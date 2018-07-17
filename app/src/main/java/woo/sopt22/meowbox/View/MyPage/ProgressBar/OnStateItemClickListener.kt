package woo.sopt22.meowbox.View.MyPage.ProgressBar

import woo.sopt22.meowbox.View.MyPage.ProgressBar.StateProgressBar
import woo.sopt22.meowbox.View.MyPage.ProgressBar.StateItem

interface OnStateItemClickListener {

    fun onStateItemClick(stateProgressBar: StateProgressBar, stateItem: StateItem, stateNumber: Int, isCurrentState: Boolean)

}