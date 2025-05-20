package com.estsoft.demo.mock;

import static org.assertj.core.api.Assertions.assertThat;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.BDDMockito;
import org.mockito.Mockito;

public class UserCouponTest {
    @Test
    public void testUserCoupon() {
        User user = new User("id");
        assertThat(user.countTotalCoupon()).isEqualTo(0);

        ICoupon coupon = new DummyCoupon();
        user.addCoupon(coupon);

        assertThat(user.countTotalCoupon()).isEqualTo(1);
    }

    @Test
    public void testUserCouponWithMock() {
        User user = new User("area00");
        assertThat(user.countTotalCoupon()).isEqualTo(0); // 쿠폰 수령 전

        ICoupon coupon = Mockito.mock(ICoupon.class);
        BDDMockito.given(coupon.isValid())
            .willReturn(true);;

        user.addCoupon(coupon);
        assertThat(user.countTotalCoupon()).isEqualTo(1); // 쿠폰 수령 후 쿠폰수 검증
    }
}
