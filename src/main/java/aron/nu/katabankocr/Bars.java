package aron.nu.katabankocr;

import java.util.Objects;

class Bars {
    boolean top;
    boolean upLeft;
    boolean middle;
    boolean upRight;
    boolean downLeft;
    boolean bottom;
    boolean downRight;

    Bars(boolean top, boolean upLeft, boolean middle, boolean upRight, boolean downLeft,
            boolean bottom, boolean downRight) {
        this.top = top;
        this.upLeft = upLeft;
        this.middle = middle;
        this.upRight = upRight;
        this.downLeft = downLeft;
        this.bottom = bottom;
        this.downRight = downRight;
    }

    @Override
    public int hashCode() {
        return Objects.hash(bottom, downLeft, downRight, middle, top, upLeft, upRight);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() == obj.getClass()) {
            Bars o = (Bars) obj;
            return Objects.equals(bottom, o.bottom) && Objects.equals(downLeft, o.downLeft)
                    && Objects.equals(downRight, o.downRight) && Objects.equals(middle, o.middle)
                    && Objects.equals(top, o.top) && Objects.equals(upLeft, o.upLeft)
                    && Objects.equals(upRight, o.upRight);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Bars [top=" + top + ", upLeft=" + upLeft + ", middle=" + middle + ", upRight=" + upRight + ", downLeft="
                + downLeft + ", bottom=" + bottom + ", downRight=" + downRight + "]";
    }
}
