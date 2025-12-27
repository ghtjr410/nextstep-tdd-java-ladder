# 1단계 - 스트림, 람다, Optional
***
## 코드 리뷰
> PR 링크:
> **[https://github.com/next-step/java-ladder/pull/2430](https://github.com/next-step/java-ladder/pull/2430)**

## 나의 학습 목표
- 람다 문법 학습
- Stream API 활용법 학습
- Optional 활용법 학습

## 람다(lambda)
### 람다와 클로저
람다는 익명 함수의 다른 표현이다. 즉, 함수는 함수인데 이름이 없는 경우를 의미한다.
### Collection의 모든 값을 출력
```java
// nextstep.fp.Lambda의 printAllOld method
List numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

for (int number : numbers) {
    System.out.println(number);
}
```

### 람다가 없던 시절
```java
List numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

numbers.forEach(new Consumer() {
    public void accept(Integer value) {
        System.out.println(value);
    }
});
```

### 람다를 활용하면…
```java
// nextstep.fp.Lambda의 printAllLambda method
List numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

numbers.forEach((Integer value) -> System.out.println(value));
numbers.forEach(value -> System.out.println(value)); // Type 추론이 가능해 Type 생략 가능
numbers.forEach(System.out::println); // :: 연산자 활용 가능
// = numbers.forEach(x -> System.out.println(x));
```

### 람다 문법
```
input arguments -> body
```

---

## 람다 실습 1 - 익명 클래스를 람다로 전환

다음 테스트 코드에서 MoveStrategy에 대한 익명 클래스로 구현하고 있는데 람다를 적용해 구현한다.
```java
// nextstep.fp.CarTest의 이동, 정지 method
public class CarTest {
    @Test
    public void 이동() {
        Car car = new Car("pobi", 0);
        Car actual = car.move(new MoveStrategy() {
            @Override
            public boolean isMovable() {
                return true;
            }
        });
        assertEquals(new Car("pobi", 1), actual);
    }

    @Test
    public void 정지() {
        Car car = new Car("pobi", 0);
        Car actual = car.move(new MoveStrategy() {
            @Override
            public boolean isMovable() {
                return false;
            }
        });
        assertEquals(new Car("pobi", 0), actual);
    }
}
```

- [x] 익명 클래스를 람다로 전환

---

## 람다 실습 2 - 람다를 활용해 중복 제거
```java
// nextstep.fp.Lambda의 sumAll method
List numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

public int sumAll(List numbers) {
    int total = 0;
    for (int number : numbers) {
        total += number;
    }
    return total;
}
```
```java
// nextstep.fp.Lambda의 sumAllEven method
List numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

public int sumAllEven(List numbers) {
    int total = 0;
    for (int number : numbers) {
        if (number % 2 == 0) {
            total += number;
        }
    }
    return total;
}
```

List에 담긴 값 중 3보다 큰 수만을 더해야 한다.
이 기능을 구현하려고 보니 앞의 요구사항 1,2와 많은 중복이 발생한다. 람다를 활용해 중복을 제거한다.

- [x] sumAll, sumAllEven, sumAllOverThree 중복 제거

### 힌트
- 변경되는 부분과 변경되지 않는 부분의 코드를 분리한다.
- 변경되는 부분을 인터페이스로 추출한다.
- 인터페이스에 대한 구현체를 익명 클래스(anonymous class)로 구현해 메소드의 인자로 전달한다.
```java
public interface Conditional {
    boolean test(Integer number);
}
```
```java
public int sumAll(List numbers, Conditional c) {
    // c.test(number)를 활용해 구현할 수 있다.
}
```

- 익명 클래스를 자바 8의 람다를 활용해 구현한다.

---


## 스트림(stream) - map, filter, reduce

Collection에 담긴 데이터를 처리하려면 Collection을 순회하면서 각 Element를 처리하는 것이 일반적이다. 앞으로는 순회하는 것을 잊고, Element 처리에만 집중하자.

### filter
요구사항은 파일 문자 중 길이가 12보다 큰 문자의 수를 구한다.
```java
// nextstep.fp.StreamStudy countWords method
String contents = new String(Files.readAllBytes(
  Paths.get("../ war-and-peace.txt")), StandardCharsets.UTF_8);
List words = Arrays.asList(contents.split("[\\P{L}]+"));

long count = 0;
for (String w : words) {
  if (w.length() > 12) count++;  
}
```

filter 활용해 구현
```java
String contents = new String(Files.readAllBytes(
  Paths.get("../alice.txt")), StandardCharsets.UTF_8);
List words = Arrays.asList(contents.split("[\\P{L}]+"));

long count = words.stream().filter(w -> w.length() > 12).count();
```

- [x] countWords를 Stream으로 변환

### map
List에 담긴 모든 숫자 값을 2배한 결과 List를 생성한다.
```java
// nextstep.fp.StreamStudy 클래스의 doubleNumbers method 참고
List numbers = Arrays.asList(1, 2, 3, 4, 5, 6);
List doubleNumbers =
  numbers.stream().map(x -> 2 * x).collect(Collectors.toList());
```

### reduce
List에 담긴 모든 숫자의 합을 구한다.
```java
// nextstep.fp.StreamStudy 클래스의 sumAll method 참고
List numbers = Arrays.asList(1, 2, 3, 4, 5, 6);

public int sumAll(List numbers) {
    return numbers.stream().reduce(0, (x, y) -> x + y);
}
```

---

## map, reduce, filter 실습 1

List에 담긴 모든 숫자 중 3보다 큰 숫자를 2배 한 후 모든 값의 합을 구한다.

- [x] sumOverThreeAndDouble 구현 (StreamStudyTest 통과)

---

## map, reduce, filter 실습 2

nextstep.fp.StreamStudy 클래스의 printLongestWordTop100() 메서드를 구현한다.

- [x] 단어의 길이가 12자를 초과하는 단어를 추출한다.
- [x] 12자가 넘는 단어 중 길이가 긴 순서로 100개의 단어를 추출한다.
- [x] 단어 중복을 허용하지 않는다. (서로 다른 단어 100개)
- [x] 추출한 100개의 단어를 출력한다. 모든 단어는 소문자로 출력해야 한다.

### 힌트
- `sorted()` - 길이가 긴 순서로 정렬
- `distinct()` - 중복 제거
- `limit()` - 100개 제한
- `String.toLowerCase()` - 소문자 변환

---

## Optional

### 요구사항 1 - Optional을 활용해 조건에 따른 반환

nextstep.optional.User의 ageIsInRange1() 메소드는 30살 이상, 45살 이하에 해당하는 User가 존재하는 경우 true를 반환하는 메소드이다.

같은 기능을 Optional을 활용해 ageIsInRange2() 메소드에 구현한다.

- [x] ageIsInRange2 구현 (UserTest 통과)

### 힌트
- `Optional.ofNullable(user)` 활용
- Optional의 `map()`, `filter()` 메소드 활용
- Optional의 `isPresent()` 메소드 활용

---

### 요구사항 2 - Optional에서 값을 반환

nextstep.optional.Users의 getUser() 메소드를 자바 8의 stream과 Optional을 활용해 구현한다.

- [x] getUser 구현 (UsersTest 통과)

### 힌트
- Optional의 `orElse()` 메소드 활용

---

### 요구사항 3 - Optional에서 exception 처리

nextstep.optional.ExpressionTest의 테스트가 통과하도록 Expression의 of 메소드를 구현한다.

- [x] Expression.of 구현 (ExpressionTest 통과)

### 힌트
- 자바의 enum 전체 값은 `values()` 메소드를 통해 배열로 접근 가능
- `Arrays.stream()`을 이용해 배열을 stream으로 생성
- `findFirst()` 메소드로 일치하는 값 하나 추출
- Optional의 `orElseThrow()` 메소드 활용

### 추가 미션 - Optional 체이닝으로 리팩터링

(사실은 깨진 테스트를 그냥 지나치지 못하는... 직업병이 있어서 미션을 추측해서 추가해봤습니다.)

`ComputerStore.getVersionOptional()` 메서드를 구현한다.

- [x] 중첩 if문을 Optional 체이닝으로 리팩터링
- [x] ComputerStoreTest 모든 테스트 통과

### 힌트
- `Optional.ofNullable()`로 시작
- `map()`을 체이닝하여 중첩 객체 접근
- `orElse()`로 기본값 반환