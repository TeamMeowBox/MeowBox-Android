package woo.sopt22.meowbox.View.MyPage.FAQ.models;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Genre extends ExpandableGroup {
    public Genre(String title, List items) {
        super(title, items);
    }
}