package woo.sopt22.meowbox.View.MyPage.Setting;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class ExpandableListDataPump {
    public static HashMap<String, List<String>> getData() {
        HashMap<String, List<String>> expandableListDetail = new HashMap<String, List<String>>();

        List<String> cricket = new ArrayList<String>();
        cricket.add("India");
        cricket.add("Pakistan");
        cricket.add("Australia");
        cricket.add("England");
        cricket.add("South Africa");


        List<String> notice1 = new ArrayList<String>();
        notice1.add("어린 시절부터 지금까지 읽은 많은 책 중에 저는 <어린왕자>라는 동화를 가장 사랑합니다.\n" +
                "서두에 어른을 위한 동화라고 명시한 것처럼 이 책은 어렸을 때보다 지금에 더 가슴에 와 닿는 듯합니다.\n" +
                "그중 여우가 어린왕자에게 길들임을 설명하며 \"오후 4시에 네가 온다면 나는 오후 3시부터 행복해지기 시작할거야.\"라고 말하는 장면이 있습니다.\n" +
                "\n" +
                "오늘처럼 가을빛이 묻은 바람에 가을을 기다리듯이 누군가 사랑하는 사람을 만나는 날은 눈을 떠서부터 준비하는 과정, 그를 만나러 가는 순간까지 행복합니다.\n" +
                "여러분에게 출근길이 그렇다면 얼마나 행복하겠습니까?\n" +
                "여러분이 무언가 설레는 것 하나쯤은 간직하고 살아가기를 바랍니다.");

        expandableListDetail.put("CRICKET TEAMS", cricket);
        expandableListDetail.put("미유박스 주문관련 공지입니다.", notice2);
        expandableListDetail.put("드디어 미유박스가 출시되었습니다", notice1);
        return expandableListDetail;
    }
}