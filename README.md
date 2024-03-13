# dojun

볼링 점수판 시스템. 도전은 제가 알바한 카페가 있는 볼링장의 이름입니다.

## 요구 사항

- [x] 게임 실행 시, 참여 인원 수를 입력 받는다.
  - [x] 숫자가 아닌 입력은 다시 입력 받는다.
- [ ] 모든 입력 전, 변경 사항을 반영한 점수판을 출력한다.
  - [ ] 점수판에는 해당 Lane과 Frame 별 쓰러뜨린 핀 개수와, 합산한 점수를 표시한다.
- [x] 프레임마다 해당 Lane에게 쓰러뜨린 핀을 입력 받는다.
  - [x] 총 2회의 핀 입력을 받는다.
    - [x] 0부터 10 사이의 숫자가 아닌 입력은 다시 입력 받는다.
    - [x] 두 투구 포인트의 합이 10 이상이면 두 투구를 다시 입력 받는다.
  - [x] 첫 번째로 10개의 핀을 쓰러뜨린 경우, 만점이기 때문에 해당 프레임을 종료한다.
- [ ] 핀 입력마다 합산할 수 있는 점수를 계산한다.
  - [ ] STRIKE: 첫 입력에 10개의 핀을 쓰러뜨린 경우, 해당 프레임의 점수, 다음의 입력, 그 다음의 입력을 합산해 반영한다.
  - [ ] SPARE: 한 프레임의 두 투구 합이 10인 경우, 해당 프레임의 점수, 다음의 입력을 합산해 반영한다.
  - [ ] 아직 합산할수 없는 경우 공란을 출력한다.
- [x] 프레임은 총 10회 진행한다.
- [ ] 마지막 프레임의 점수판 출력 칸은 3칸이다.
  - [ ] 마지막 프레임에서 STRIKE의 경우, 2회의 추가 기회를 얻는다. 이는 마지막 프레임에 합산되는 보너스 점수다.
  - [ ] 마지막 프레임에서 SPARE의 경우, 1회의 추가 기회를 얻는다. 이는 마지막 프레임에 합산되는 보너스 점수다.
- [ ] 게임이 종료되면 전체 점수를 포함한 점수판을 출력하고 마친다.


## 기간

bowling1: 24. 3.11(월) 14:00 ~ 24. 3.14(수) 11:00<br>
bowling2: 24. 3.13(수) 24:00 ~ 24. 3.14(목) 21:00

## 제한 사항

- 외부 라이브러리 사용 금지!
- 되도록 혼자 힘으로 해보기