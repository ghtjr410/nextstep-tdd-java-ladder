# 2단계 - 사다리(생성)
***
## 코드 리뷰
> PR 링크:
> **[https://github.com/next-step/java-ladder/pull/2431](https://github.com/next-step/java-ladder/pull/2431)**

## 기능 요구사항
- 사다리 게임에 참여하는 사람에 이름을 최대5글자까지 부여할 수 있다. 사다리를 출력할 때 사람 이름도 같이 출력한다.
- 사람 이름은 쉼표(,)를 기준으로 구분한다.
- 사람 이름을 5자 기준으로 출력하기 때문에 사다리 폭도 넓어져야 한다.
- 사다리 타기가 정상적으로 동작하려면 라인이 겹치지 않도록 해야 한다.
- |-----|-----| 모양과 같이 가로 라인이 겹치는 경우 어느 방향으로 이동할지 결정할 수 없다.

## 프로그래밍 요구사항
- **자바 8의 스트림과 람다를 적용해 프로그래밍한다.**
- **규칙 6: 모든 엔티티를 작게 유지한다.**

### 실행 결과
- 위 요구사항에 따라 4명의 사람을 위한 5개 높이 사다리를 만들 경우, 프로그램을 실행한 결과는 다음과 같다.
```text
참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)
pobi,honux,crong,jk

최대 사다리 높이는 몇 개인가요?
5

실행결과

pobi  honux crong   jk
    |-----|     |-----|
    |     |-----|     |
    |-----|     |     |
    |     |-----|     |
    |-----|     |-----|
```

### 힌트
- 2차원 배열을 ArrayList, Generic을 적용해 구현하면 ArrayList<ArrayList<Boolean>>와 같이 이해하기 어려운 코드가 추가된다.
- 사다리 게임에서 한 라인의 좌표 값을 가지는 객체를 추가해 구현해 본다.
```java
public class Line {
    private List<Boolean> points = new ArrayList<>();

    public Line (int countOfPerson) {
        // 라인의 좌표 값에 선이 있는지 유무를 판단하는 로직 추가
    }

    [...]
}
```
- 위와 같이 Line 객체를 추가하면 ArrayList<ArrayList<Boolean>> 코드를 ArrayList<Line>과 같이 구현하는 것이 가능해 진다.

## PR 전 점검
**[체크리스트 확인하기](checklist.md)**

## 구현 기능 목록

### 참가자 이름
- [x] 이름 (Name)
    - [x] 문자열로 생성
    - [x] null 또는 빈 값일 시 예외 발생
    - [x] 5자 초과 시 예외 발생
    - [x] 6자 고정 출력용 문자열 반환

- [x] 이름 목록 (Names)
    - [x] 쉼표 구분 문자열로 생성
    - [x] 이름 목록으로 생성
    - [x] null일 시 예외 발생
    - [x] 2명 미만일 시 예외 발생
    - [x] 참가자 수 반환
    - [x] 출력용 문자열 반환

### 사다리 높이
- [x] 높이 (Height)
    - [x] 정수로 생성
    - [x] 1 미만일 시 예외 발생

### 사다리 라인
- [x] 라인 (Line)
    - [x] Boolean 목록으로 생성
    - [x] 가변인자로 생성
    - [x] null 또는 빈 값일 시 예외 발생
    - [x] 연속된 true 존재 시 예외 발생
    - [x] 출력용 문자열 반환

- [x] 라인 목록 (Lines)
    - [x] Line 목록으로 생성
    - [x] Height, 참가자 수, LineGenerator로 생성
    - [x] 출력용 문자열 반환

### 사다리 생성
- [x] 라인 생성기 인터페이스 (LineGenerator)
    - [x] Line 생성 메서드 정의

- [x] Boolean 생성기 인터페이스 (BooleanGenerator)
    - [x] boolean 생성 메서드 정의

- [x] 랜덤 라인 생성기 (RandomLineGenerator)
    - [x] LineGenerator 구현
    - [x] BooleanGenerator 주입 가능
    - [x] 기본 생성 시 Random 사용
    - [x] 연속 true 방지 로직 적용

### 사다리
- [x] 사다리 (Ladder)
    - [x] Names, Lines로 생성
    - [x] 출력용 문자열 반환
