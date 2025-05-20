package com.estsoft.demo.mock;

import java.util.ArrayList;
import java.util.List;
import lombok.Getter;

@Getter
public class User {

    private String id;
    private List<ICoupon> coupons;

    public User(String id) {
        this.id = id;
        this.coupons = new ArrayList<>();
    }

    public int countTotalCoupon() {
        return coupons.size();
    }

    public void addCoupon(ICoupon coupon) {
        if (coupon.isValid()) {
            coupons.add(coupon);
        }
    }
}
