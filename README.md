# Project : 구르망(gourmand) 
#### - 빈틈없이 아주 야무진 사람
<br><br>

![logocopy](https://user-images.githubusercontent.com/61037197/108632311-8d161480-74b1-11eb-8a4a-f7ac3d26a2af.PNG)


먹기 위해 산다!!!!!

전날 밤 잠들기 전부터 '내일 점심 뭐 먹지'를 고민하는,
세상이 식사시간 중심으로 돌아가는 프로 직장인,

오직 야식을 위해
두 시간 넘게 러닝머신을 뛰고 온 프로 운동러,


밤마다 사진첩 한가득 찍은 음식 사진 보며
'아, 저거 맛있었지!'하다 잠드는,
미각으로 온 세상을 기억하는 프로 기억러,

세상은 넓고, 맛있는 식당은 많다!!
여러분은 맛있는 것만 먹고 살 자격이 있습니다!!!!!


구르망 (Gourmand)은
맛잘알들의 리뷰를 각자의 지도와 리스트로 관리하고,
사용자의 입맛과 선호도를 분석해
나아가 좋아할 것 같은 식당과 계정을 추천해줍니다.

구르망을 통해 세상 모든 사람들이 매일
대충 때우는 한 끼가 아니라
자기 입맛에 딱 맞는 만족스러운 식사로 더 행복해졌으면 좋겠습니다.



<p><p>
<br></br>   
## Why This Topic is Important<br><br>

### topic : 나만의 맛집 지도 <br>

<div>

![graph1](https://user-images.githubusercontent.com/61037197/108643287-52ca6880-74ed-11eb-8310-9532265d8f25.PNG)
#### 주제선정이유1 <br>
:taco: 맛집에 대한 수요는 갈수록 증가하고 있다!
<p><p></p>

![graph2](https://user-images.githubusercontent.com/61037197/108643291-552cc280-74ed-11eb-8621-f341e45f8125.PNG)

#### 주제선정이유2<br>
:pizza: 반면, 맛집 지도에 대한 검색 수요는 떨어지고 있다. 
<p></p>
<br>
출처:네이버 데이터랩 (https://datalab.naver.com/)

## STRUCTURE 


<div>

![erd](https://user-images.githubusercontent.com/61037197/108632719-bdf74900-74b3-11eb-848a-3d3f3e18b5ef.PNG)
  
## MODELING    

### 메뉴트리
<div> 

![tree](https://user-images.githubusercontent.com/61037197/108651210-a300f500-7504-11eb-8c7e-5e32c1f15f5a.jpg)



## Service 
<div>   



#### :world_map: 나만의 맛집을 지도를 통해 보여주기
    - 네이버/카카오 지도 연동
    - 지도 위에 위치 표시
    - 위치 클릭 시 정보 표시


#### :pretzel: 음식점 검색 및 추천<br>
    - 지도와 검색을 통해 확인 
    - 음식 유형별 검색 기능
    - 유명인 추천 맛집 보기
    - 음식 사진과 함께 리뷰 작성 및 평가하기


#### :computer: 로그인 및 회원가입<br>
    - 로그인시, 프로필 이미지 등록


#### :sun_with_face: 마이페이지 <br>
    - 내가 작성한 리뷰 확인
    - 맛집 등록 기록을 기반으로 취향 분석
    - 내가 등록한 맛집 리스트 확인


#### :100: 평가 작성<br>
    - 평가 기준을 바탕으로 리뷰 작성
    - 기준별 별점 점수를 시각화해서 보여주기
    - 기준별 별점 점수를 기반으로 총점 등록 가능


</br></p>

## Technologies Used
<br>
</br>

![techimage](https://user-images.githubusercontent.com/61037197/108664047-49ed8d00-7515-11eb-88d9-f530edb43261.jpg)

## Issue
<div>
1. 맛집페이지
   
   1) 
    - 
   2) 
    - 

2. 

    1) 
    2) 
    - 
    - 
    3)
    - 


## MAP API

#### :fries: Naver Web Dynamic Map 
     1) 지도 생성 및 띄우기
     2) 지도에 마크 생성, 삭제 및 InfoWindow 추가


#### :ramen: Naver Geocoding
     1) 음식점 명을 이용하여 음식 유형/카테고리를 불러옴

#### :sushi: KaKao Web Dynamic Map
     1) 검색을 통한 음식점 정보 찾기에 활용



## Setting Up <br> 

위의 파일을 다운받으신 후 서버를 실행할 수 있습니다. 
<br><br>

### :croissant: SpringBoot 실행 <br>

먼저 eclispe의 해당 파일을 열어 import 하신 후, <br>
파일의 java resources -> src/main/java -> io.gourmand ->Gourmand application을 spring boot app으로 실행시켜 줍니다.<br>
<br>


### :curry: Vue js 실행 <br>

1. 경로 지정 및 환경 설치 
<br> (개인마다 경로에 차이가 있을 수 있습니다. 해당 프로젝트의 경로를 확인한 후 frontend까지 지정해야합니다.)<br>
    <br> 
~~~cmd
 cd C:\Users\Playdata\Documents\GitHub\Gourmand_SpringBoot_Project\src\frontend
 npm install
 npm install –save chart.js vue-star-rating npm install -s cookie npm install cookie-parser
~~~

<br>

2. 설치 완료시 서버 실행 <br>
~~~cmd
npm run serve
~~~
<br>
<br>

### :rice_ball: Node express 실행 <br>

1. node와 npm 버전 확인 
    <br> 
~~~cmd
 node -version 
 npm -version
~~~

<br>
2. 환경 설치 

~~~cmd
 npm install -g nodemon
 npm install -g express
 npm install -g express-generator 
 npm install cores –save
 npm install express-session –save
 npm install axios –save 
~~~

<br>

3. 서버 실행

~~~cmd
 npm start
~~~

<br>


## Controller - APIs
<details>
<summary>Click to toggle contents of `code`</summary>
```
CODE!
```
</details>

<details>
<summary>Click to toggle contents of other `code`</summary>
```
MORE CODE!
```
</details><details>
<summary>login controller</summary>
signin         //로그인<br>
logout         //로그아웃
</details>

<details>
<summary>res controller</summary>
getResInfo      // 가게 정보를 담은 페이지<br>
getResThumbnail      // Thumbnail 정보를 담은 페이지<br>
getAllResThumbnail   // 거리별(default) 모든 Thumbnail<br>
getResThumbnailByCategory   // 카테고리별<br>
getResThumbnailByAvgStar   // 별점순   <br>
getResThumbnailByResName   // 이름 검색<br>
getResThumbnailByLocation   // 지역 검색<br>
getUserByRes      // 해당 가게를 리스트에 넣은 유저 반환<br>
getResOfList      // 해당 유저의 모든 리스트 맵으로 반환<br>
insetResToUser      // 가게 정보 저장<br>
createRes      // reslist 추가<br>
updateRes      // 가게 정보 수정<br>
deleteRes      // 가게 정보 삭제<br>
insetResToUser      // 가게 이미지만 추가   <br>
insertImgOfRes      // 가게 정보 수정<br>
</details>


   
<details>
<summary>rev controller</summary>
getReviewList      // 모든 리뷰 정보 조회<br>
getReview      // 하나의 리뷰 정보 조회<br>
createRev      // 리뷰 정보 저장<br>
updateRev      // 리뷰 수정<br>
deleteReview      // 리뷰 삭제<br>
deleteRevImg      // 리뷰 이미지 삭제<br>
returnAllRevOfRes      // 가게에 대한 리뷰 Thumbnail<br>
returnResStandard      // 가게에 대한 standard 정보<br>
returnAllOrderByTime   // 시간순으로 12개의 revThumbnail<br>
returnAllRevNumOfUser   // 회원의 모든리뷰 수<br>

<details>
<summary>user controller</summary>
createUser      // 회원 가입 <br>
updateUser      // 유저 정보 수정<br>
deleteUserStandardUser   // 회원 기준 삭제<br>
deleteUser      // 회원 삭제<br>
deleteUserImg      // 회원 이미지 삭제<br>
createUserStandard   // 회원 기준 저장<br>
returnUserResList      // 회원의 list 이름들 반환 (list에 저장용)<br>
getUserIdOfFamousUser   // 인기 많은 유저의 아이디와 닉네임<br>
getResOfList      // 내 리스트 불러오기<br>
getUserCountByList   // 내가 작성한 리스트 카운트 불러오는거<br>
getUserInfo      // 내 전체 정보 가져오기<br>
getResOfList      // 회원 리스트 불러오기<br>
getUserCountByList   // 리스트 카운트 불러오는거<br>
getAllOrderByUserNumNStar   // 유저 당 리뷰를 별점순으로 반환("/res/user/review/writeDate")나의 페이지<br>
getAllOrderByUserNumNDate// 유저 당 리뷰를 시간순으로 반환("/res/user/review/Star")나의 페이지<br>
getAllOrderByUserNumNStar   // 유저 당 리뷰를 별점순으로 반환("/res/user/review/writeDate/{id}")타인의 페이지<br>
getAllOrderByUserNumNDate// 유저 당 리뷰를 시간순으로 반환("/res/user/review/Star/{id}")타인의 페이지<br>
</details>
 <br>

## Reference 

<br></br>
 
## Appendix

#### :bento: 프로젝트 진행 과정 
https://drive.google.com/drive/folders/1-G8_Bw57G7msK3BAfzhLRIw_ZSRumXYm

#### :bagel: 프로젝트 관련 참고 내용 (팀원 장종욱 개인 블로그)
https://kowo1001.github.io/project/springboot-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%B0%B8%EA%B3%A0%EB%82%B4%EC%9A%A9/


