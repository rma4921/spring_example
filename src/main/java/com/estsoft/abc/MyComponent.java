package com.estsoft.abc;

import org.springframework.stereotype.Component;

// 빈 추가가 안됨.
// 컴포넌트 스캔 범위가 아님 -> MyConfiguration에 작성한 내용에 따라 스캔됨.
@Component
public class MyComponent {
}
