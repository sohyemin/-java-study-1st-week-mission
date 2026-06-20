# 1st Week Mission

## Mission 1 - Sliding Window를 이용한 LLM Context 관리

### 개요

LLM은 Context Window의 크기가 제한되어 있기 때문에 모든 대화 기록을 무한히 유지할 수 없다.

본 미션에서는 Queue와 Sliding Window 알고리즘을 이용하여, 토큰 수 제한을 초과하면 가장 오래된 메시지를 제거하는 방식으로 최근 대화만 유지하는 구조를 구현하였다.

---

## 기술 스택

* Java 21
* Queue
* LinkedList

---

## 프로젝트 구조

```text
slidingwindow

├── Message.java
├── SlidingWindowManager.java
├── NotSlidingWindow.java
└── SlidingWindowTest.java
```

---

## 동작 구조

```text
새 메시지 추가

↓

현재 토큰 수 증가

↓

토큰 제한 초과?

↓

YES

↓

가장 오래된 메시지 제거

↓

현재 토큰 수 갱신

↓

최근 대화만 유지
```

---

## Sliding Window 적용

```java
queue.add(message);
currentToken += message.getToken();

while (currentToken > maxToken && !queue.isEmpty()) {
    currentToken -= queue.remove().getToken();
}
```

현재 토큰 수를 별도로 유지하여 전체 메시지를 다시 계산하지 않고 O(N)으로 동작하도록 구현하였다.

---

## Sliding Window를 사용하지 않는 경우

```java
queue.add(message);

currentToken = queue.stream()
                    .mapToInt(Message::getToken)
                    .sum();
```

메시지가 추가될 때마다 전체 토큰 수를 다시 계산해야 하므로 비효율적이다.

---

## 시간 복잡도 비교

### NotSlidingWindow

```java
queue.stream()
     .mapToInt(Message::getToken)
     .sum();
```

* 매번 전체 Queue 순회
* 시간복잡도 : O(N²)

### SlidingWindow

```java
currentToken += message.getToken();
currentToken -= removedMessage.getToken();
```

* 변경된 값만 계산
* 시간복잡도 : O(N)

---

## 개선점

현재 토큰 수를 단순 정수값으로 관리하고 있다.

향후에는

* 실제 Tokenizer 적용
* OpenAI Token 계산 연동
* Chat History 관리 기능 추가
* AI Chat Server와 통합

등을 통해 실제 LLM Context 관리 기능으로 확장할 수 있다.

---

# Mission 2 - Wrapper 객체 오버헤드 프로파일링

## 개요

수백만 개의 AI Embedding 벡터를 저장한다고 가정하고,

* Primitive 배열 (`double[]`)
* Wrapper 객체 배열 (`Double[]`)

을 각각 생성하여 메모리 사용량과 생성 시간을 비교하였다.

---

## 기술 스택

* Java 21
* VisualVM

---

## 실험 코드

### Primitive

```java
double[] primitiveArray = new double[10_000_000];
```

### Wrapper

```java
Double[] wrapperArray = new Double[10_000_000];
```

---

## 측정 환경

VisualVM을 이용하여 Heap 사용량을 측정하였다.

---

## 실험 결과

| 항목       | double[] | Double[] |
| -------- | -------: | -------: |
| Heap 사용량 |   240 MB |   540 MB |
| 생성 시간    |    14 ms |    89 ms |

---

## 결과 분석

### double[]

실수 값이 연속된 메모리 공간에 저장된다.

```text
1.1 2.2 3.3 4.4 ...
```

* 메모리 사용량이 적음
* CPU Cache Hit율이 높음
* 생성 속도가 빠름

### Double[]

각 요소가 객체이며 참조를 통해 접근한다.

```text
reference

↓

Double Object

↓

실제 값
```

* Object Header 추가
* Heap 사용량 증가
* 참조 추적으로 인한 Cache Miss 증가 가능
* 생성 속도 감소

---

## CPU Cache 관점

Primitive 배열은 연속된 메모리에 저장되므로 CPU가 다음 데이터를 예측하여 가져오기 쉽다.

반면 Wrapper 배열은 객체가 Heap 곳곳에 분산될 수 있기 때문에 참조를 따라가며 값을 읽어야 한다.

따라서 AI Embedding처럼 대량의 실수 데이터를 다룰 경우 Primitive 배열이 메모리 효율과 성능 면에서 더 유리하다.

---

## 결론

동일한 데이터를 저장하더라도 Wrapper 객체를 사용할 경우

* Heap 사용량 증가
* 생성 시간 증가
* CPU Cache Miss 발생 가능성 증가

가 발생할 수 있다.

대량의 실수형 데이터를 처리하는 AI 시스템에서는 Primitive 배열을 사용하는 것이 메모리와 성능 측면에서 더욱 효율적이다.