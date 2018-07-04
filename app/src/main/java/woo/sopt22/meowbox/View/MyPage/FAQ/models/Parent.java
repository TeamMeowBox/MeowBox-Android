package woo.sopt22.meowbox.View.MyPage.FAQ.models;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class Parent extends ExpandableGroup {
    public Parent(String title, List items) {
        super(title, items);
    }
}