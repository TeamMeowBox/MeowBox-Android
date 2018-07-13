package woo.sopt22.meowbox.Model.Order;

public class OrderTest {
    String merchant_uid;
    String name;
    int amount;

    public OrderTest(String merchant_uid, String name, int amount) {
        this.merchant_uid = merchant_uid;
        this.name = name;
        this.amount = amount;
    }

    public String getMerchant_uid() {
        return merchant_uid;
    }

    public void setMerchant_uid(String merchant_uid) {
        this.merchant_uid = merchant_uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

}
