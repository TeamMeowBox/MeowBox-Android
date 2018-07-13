package woo.sopt22.meowbox.View.MyPage.ProgressBar;

public class StateItemNumber extends BaseItem {

    private int number;

    // private Typeface typeface;

    public static abstract class Builder<T extends Builder<T>> extends BaseItem.Builder<T> {
        private int number;

        public T number(int number) {
            this.number = number;
            return self();
        }

        public StateItemNumber build() {
            return new StateItemNumber(this);
        }
    }

    private static class Builder2 extends Builder<Builder2> {
        @Override
        protected Builder2 self() {
            return this;
        }
    }

    public static Builder<?> builder() {
        return new Builder2();
    }


    protected StateItemNumber(Builder<?> builder) {
        super(builder);
        this.number = builder.number;
    }


    public int getNumber() {
        return number;
    }
}