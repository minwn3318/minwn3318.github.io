---
title: biginteger
date: 2026-04-27 10:00:00 +0900
categories: [algorithm, codingtest]
tags: [codingtest]
---

# BigInteger
## BigInteger란?
- 정수의 크기가 기존 변수들 보다 훨씬 넘어설 때 사용하는 정수변수 타입이다

기존의 변수들은 int와 long을 살펴보면, 10진수로 계산했을 때,

- int = 10의 8승
- long = 10의 16승

이다

그러나 코딩 테스트를 하다보면 이보다 훨씬 큰 수를 처리해야 할 때가 있다

 > 만약 이걸 무시하고 기존 변수 타입을 사용하면, **NumberFormat**이라는 런타임 에러가 발생하니 주의하다 
 
---
## 활용 방법
- new BigInteger() : 변수 선언
- mod, divide, minus, plus,... : 기존 사칙연산이 적용되지 않기에 메서드가 존재한다. 이것들로 사칙연산을 수행해야한다