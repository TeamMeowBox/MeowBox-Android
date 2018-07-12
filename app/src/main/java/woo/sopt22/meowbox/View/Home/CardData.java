package woo.sopt22.meowbox.View.Home;

public class CardData {
    int image;
    int tmp;

    public CardData(int image, int tmp) {
        this.image = image;
        this.tmp = tmp;
    }

    public int getTmp() {
        return tmp;
    }

    public void setTmp(int tmp) {
        this.tmp = tmp;
    }

    public int getImage(){
        return image;
    }

    public void setImage(int image){
        this.image = image;
    }
}
