# PocketMonster Java console version

### Summary
1. 선택지를 통해 진행
2. 전투, 아이템, 센터 구현
3. 이어하기 가능

### Code
1. 페이지 추가
> * Page 추상 클래스를 상속받아 추상 메소드를 구현
> ```java
> public abstract PageType getType();
> public abstract void printAction();
> ```
> * PageType Enum은 PageManager가 Command패턴으로 페이지들을 관리하기 위한 타입으로 <br/>
>   페이지 추가 시 그에 따른 Enum값 추가 필요
> * printAction()에 출력할 내용을 설정
