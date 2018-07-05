package woo.sopt22.meowbox.View.MyPage.ProgressBar;

import woo.sopt22.meowbox.View.MyPage.ProgressBar.StateProgressBar;
import woo.sopt22.meowbox.View.MyPage.ProgressBar.StateItem;

public interface OnStateItemClickListener {

    void onStateItemClick(StateProgressBar stateProgressBar, StateItem stateItem, int stateNumber, boolean isCurrentState);

}