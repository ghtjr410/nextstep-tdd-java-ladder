# 4단계 - 사다리(리팩터링)
***
## 코드리뷰
> PR 링크:
> **[https://github.com/next-step/java-ladder/pull/2436](https://github.com/next-step/java-ladder/pull/2436)**

## 기능 요구사항
- 기능 요구사항 3단계와 같다.

### 실행 결과
- 위 요구사항에 따라 4명의 사람을 위한 5개 높이 사다리를 만들 경우, 프로그램을 실행한 결과는 다음과 같다.
```text
참여할 사람 이름을 입력하세요. (이름은 쉼표(,)로 구분하세요)
pobi,honux,crong,jk

실행 결과를 입력하세요. (결과는 쉼표(,)로 구분하세요)
꽝,5000,꽝,3000

최대 사다리 높이는 몇 개인가요?
5

사다리 결과

pobi  honux crong   jk
    |-----|     |-----|
    |     |-----|     |
    |-----|     |     |
    |     |-----|     |
    |-----|     |-----|
꽝    5000  꽝    3000

결과를 보고 싶은 사람은?
pobi

실행 결과
꽝

결과를 보고 싶은 사람은?
all

실행 결과
pobi : 꽝
honux : 3000
crong : 꽝
jk : 5000
```

## 리팩터링 요구사항
#### In -> Out 방식으로 TDD를 도전해 본다.
#### 책임 주도 설계(인터페이스) 기반으로 구현해 본다.

## PR 전 점검
**[체크리스트 확인하기](checklist.md)**

## 리팩터링 목록

### 1. Point 클래스 추출 (핵심)
- `Line`의 이동 책임을 `Point`로 분리
- `List<Boolean>` → `List<Point>`로 구조 변경
- 연속 가로선 검증이 `Point` 생성자의 `(left && right)` 검증으로 대체

### 2. Tell, Don't Ask 적용
- `Point::right` getter 사용 → `Point::toSegment()` 책임 위임
- 상태를 꺼내서 판단하지 않고 객체에게 행위 요청

### 3. Line 부생성자 가독성 개선
- 복잡한 변환 로직을 `toPoints()`, `createPoint()` 헬퍼 메서드로 분리
- 부생성자는 테스트 편의성 유지

### 4. Compact Constructor 적용
- `Names`, `Prizes` 생성자를 compact constructor로 변경
- 불필요한 `this.values = ` 제거

### 5. Application 들여쓰기 규칙 준수
- `printResults()` 메서드 추출
- 2단계 들여쓰기 → 1단계로 개선

### 6. 코드 일관성 정리
- `this.` 사용 통일 (생략으로)
- 테스트도 책임 이동에 맞춰 `LineTest` → `PointTest`로 이동

### 7. MatchingResult 도입
- 위치 매핑(`Map<Integer, Integer>`)과 이름/상품 매핑 분리
- `LadderGame` 삭제, `Ladder.play()` → `MatchingResult` → `LadderResult` 흐름으로 변경

### 8. Point에 index 추가
- `Point(boolean left, boolean right)` → `Point(int index, Direction direction)`
- `move(int position)` → `move()` 파라미터 제거
- Point가 자기 위치를 알아서 계산

### 9. Direction 분리
- `Point`에서 방향 책임 분리
- `Direction(boolean left, boolean current)` - 이동 방향 캡슐화
- 규칙 7 준수 (인스턴스 변수 2개 이하)

### 10. 체이닝 방식 Point 생성
- `Direction.first()`, `next(generator)`, `last()` 메서드 추가
- `Point.first()`, `next(generator)`, `last()` 메서드 추가
- 연속 true 방지 로직을 `Direction.next(generator)` 내부로 이동
- 외부 생성 → 객체가 다음 객체를 생성하는 방식으로 변경
