# Project : 구르망(gourmand)
###                   By Team 모도리( 빈틈없이 아주 야무진 사람 ) 
<br>

![resized-image-Promo (1)](https://user-images.githubusercontent.com/73862452/108668344-67732480-751e-11eb-9450-584d4ede07f6.jpeg)

<br>

<table>
<tr>
<td>
<img src="src\frontend\public\img\theme\1.gif"/>
</td>
<td>
<img src="src\frontend\public\img\theme\2.gif"/>
</td>
</tr>
<tr>
<td>
<img src="src\frontend\public\img\theme\3.gif"/>
</td>
</tr>
</table>


<h3><b>😋 먹기 위해 산다!</b></h3><br>

전날 밤 잠들기 전부터 '내일 점심 뭐 먹지'를 고민하는,<br>
세상이 식사시간 중심으로 돌아가는 프로 직장인(like 현준🤓),<br><br>

오직 야식을 위해<br>
두 시간 넘게 러닝머신을 뛰고 온 프로 운동러(like 최지수🏃‍♀️),<br><br>

밤마다 사진첩 한가득 찍은 음식 사진 보며<br>
'아, 저거 맛있었지!'하다 잠드는,<br>
미각으로 온 세상을 기억하는 프로 기억러(like 장문희🤗),<br><br><br>

![resized-image-Promo](https://user-images.githubusercontent.com/73862452/108668669-e6685d00-751e-11eb-8d21-246b8f505e1f.jpeg)
<h3><b>세상은 넓고, 맛있는 음식은 많다!<b><br>
우리 모두 맛있는 것만 먹고 살 자격이 있습니다!!!!!</b></h3><br><br>

구르망 (Gourmand)은<br>
맛잘알들의 리뷰를 각자의 지도와 리스트로 관리하고,<br>
사용자의 입맛과 선호도를 분석해<br>
나아가 좋아할 것 같은 식당과 계정을 추천하는 식당 추천 서비스입니다.<br><br>

구르망을 통해 세상 모든 사람들이<br>
대충 때우는 한 끼가 아니라<br>
자기 입맛에 딱 맞는 만족스러운 식사로 더 행복한 매일을 보냈으면 좋겠습니다.<br><br>

   
## Why This Topic is Important<br><br>

### 1) Currently, <br>

<div>

![output-onlinepngtools (1)](https://user-images.githubusercontent.com/73862452/108665456-52939280-7518-11eb-94de-0c2d96b2db8d.png)
![output-onlinepngtools](https://user-images.githubusercontent.com/73862452/108665458-53c4bf80-7518-11eb-9553-dd689f9f29be.png)
####  <br>
:world_map: 맛집에 대한 수요는 갈수록 증가하고 있지만,
:earth_africa: 맛집 지도에 대한 검색 수요는 떨어지고 있습니다. 
<p></p>
<br>
출처:네이버 데이터랩 (https://datalab.naver.com/)

### 2) Why? 
    저희의 분석 결과 현재의 맛집 관련 서비스들의 개선점은 아래와 같습니다. 
    
    - 입맛과 취향은 서로 다른데 모두에게 획일화된 평가 기준, 가중치를 적용하고,
    - 정보의 가독성 부족 : 이미지, 영상 시대에 걸맞지 않게 맛집에 대한 정보들은 텍스트 중심으로 유통됩니다. 
    - 개별 장소 중심의 인터페이스로 인해 ‘리스트형 공유’가 불편하며,
    - 가본 곳, 가볼 곳의 구분 불가 : 현재 제공되는 맛집 관련 서비스들은 가본 곳과 가볼 곳이  
    - 누굴 믿지?: 광고, 서비스, 할인으로 인해 생긴 별점 인플레이션이 진짜 맛집을 가려내는 데 소음으로 작용합니다.

### 3) Problem Solving!
      구르망만의 차별점은 아래와 같습니다.
      
      1. 내가 정한 기준으로 음식점을 평가하고 나만의 맛집 리스트를 만들자
      2. 계정 중심으로 내가 만든 맛집리스트를 다른 사람들과 공유하자
      3. 내가 매긴 별점, 내가 만들고 좋아한 맛집 리스트를 분석해
			   내가 좋아할만한 맛집, 입맛을 가진  추천하자
      4. 방송맛집, 연예인 맛집 등을 개별 식당 단위가 아닌 지도 위 리스트로 보기 편하게 소개하자

## STRUCTURE 


<div>

![erd](https://user-images.githubusercontent.com/61037197/108632719-bdf74900-74b3-11eb-848a-3d3f3e18b5ef.PNG)
## MODELING    

### 메뉴트리
<div> 

![tree](https://user-images.githubusercontent.com/61037197/108651210-a300f500-7504-11eb-8c7e-5e32c1f15f5a.jpg)

## Service

#### :world_map: 나만의 맛집들을 지도 보여주기
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

## Issue <br>

### Backend  <br>

   1)지도 백엔드 서버에서 오류발생 <br>
   > ErrorCode <br>
   
  ```
   Error: ER_NOT_SUPPORTED_AUTH_MODE: Client does not support authentication protocol requested by server; consider upgrading MySQL client
  ```
   <br> 
   
   해결방법 <br> 
   ```
   mysql installer > server 옆에 reconfigure > Auth 설정가서 legacy 선택 > 완료
   <br>
   ```
   <br> 
   
   2) Entity & Table 1:1 매핑 관련<br>

Entity 클래스와 DTO 클래스를 분리하는 이유<br>

  ```
-View Layer와 DB Layer의 역할을 철저하게 분리하기 위해 사용합니다. 

-테이블과 매핑되는 Entity 클래스가 변경되면 여러 클래스에 영향을 끼치게 되는 반면, View와 통신하는 DTO 클래스(Request / Response 클래스)는 자주 변경되므로 분리해야 합니다.
```

:rotating_light:분리하지 않을시 문제점 <br>
```
1) 각 View 내에서 Domain Model의 getter만을 이용할때는 Domain Model 내에 Presentation을 위한 필드나 로직을 추가하게 되는데, 
이러한 방식이 모델링의 순수성을 깨고 Domain Model 객체를 망가뜨리게 됩니다.

2) Domain Model을 복잡하게 조합한 형태의 Presentation 요구사항들이 있기 때문에 Domain Model을 직접 사용하는 것은 어렵습니다.
즉 DTO는 Domain Model을 복사한 형태로, 다양한 Presentation Logic을 추가한 정도로 사용하며 Domain Model 객체는 Persistent만을 위해서 사용합니다.
```
참고 사이트 https://gmlwjd9405.github.io/2018/12/25/difference-dao-dto-entity.html
<br>

   3) 서버간의 데이터 송수신 오류발생 <br>

> 이슈 내용 <br>
```
javasript의 formdata 로 객체를 전송시 String으로 변환되어 [Object object] 로 전송되는 문제가 발생되었다.
```
>해결방법<br>
```
json 객체를 String 으로 변환해주는 JSON.stringfy() 함수를 사용해서 프론트앤드에서 백앤드 서버로 전송했다.
백앤드 Spring boot server에서는 해당 String을 jackson을 활용한 Object Mapping으로 객체로 변환하였다.
```


## MAP API

#### :fries: Naver Web Dynamic Map 
     1) 지도 생성 및 띄우기
     2) 지도에 마크 생성, 삭제 및 InfoWindow 추가


#### :ramen: Naver Geocoding
     1) 음식점명을 이용하여 음식 유형/카테고리를 불러옴

#### :sushi: KaKao Web Dynamic Map
     1) 검색을 통한 음식점 정보 찾기에 활용

#### :hamburger: Naver Local Search
     1) 음식점명을 이용해 음식점 주소 찾기에 활용

## Setting Up <br> 

위의 파일을 다운받으신 후 서버를 실행할 수 있습니다. 
<br><br>

### :croissant: SpringBoot 실행 <br>

먼저 eclispe의 해당 파일을 열어 import 하신 후, <br>
파일의 java resources -> src/main/java -> io.gourmand -> Gourmand application을 spring boot app으로 실행시켜 줍니다.<br>
<br>


### :curry: Vue js 실행 <br>

1. 경로 지정 및 환경 설치 
<br> (개인마다 경로에 차이가 있을 수 있습니다. 해당 프로젝트의 경로를 확인한 후 frontend까지 지정해야합니다.)<br>
    <br> 
~~~cmd
 cd C:\Users\Playdata\Documents\GitHub\Gourmand_SpringBoot_Project\src\frontend
 npm install
 npm install –save chart.js vue-star-rating 
~~~

<br><br>

2. 설치 완료시 서버 실행 <br>
~~~cmd
npm run serve
~~~
<br>

 
<br>

### :rice_ball: Node Express 실행 <br>

1. node와 npm 버전 확인 
    <br> 
~~~cmd
 node -version 
 npm -version
~~~

<br><br>
2. 환경 설치 

~~~cmd
 npm install
 npm install -g nodemon
 npm install -g express
 npm install -g express-generator 
 npm install cores –save
 npm install express-session –save
 npm install axios –save 
 npm install -s cookie
 npm install cookie-parser
 npm install jsonwebtoken
 npm install jwt-decode
~~~

<br><br>
3. 서버 실행

~~~cmd
 npm start
~~~

<br>


## JWT(JSON Web Token)
JWT는 사용자 정보를 JSON 객체에 담아 이를 암호화하고 해싱 작업을 거쳐 문자열 토큰을 생성하는 기술
## JWT(JSON Web Token) 사용 이유
- 서버시스템측에서 더 이상 유저(USER)의 정보를 유지하지 않고, 유저가 회원 인증을 하게 될 때 토큰을 발급해줌으로서 유저가 자기 자신임을 인증 할 수 있게 해주기 위함입니다.<br>
- 토큰에는 유저의 정보(username, userID, user_num 등..)와 토큰의 유효기간을 담을 수 있어서 토큰의 유저 정보를 통해 서버에서 검증을 통해 유저(USER) 인증을 할 수 있습니다<br>
- 저희 프로젝트를 사용하시려면 백엔드 2개, 프론트엔드 1개의 서버가 필요합니다. 이처럼 다수의 서버를 사용할 때, 토큰을 사용함으로서, 서버를 확장하게 될 때에 매우 용이해지게 됩니다. <br>
- 즉, 서버 시스템이 분산되어있어도, 유저(USER)는 같은 토큰으로 서버에 요청을 하면 되고, 서버는 그저 그 토큰이 위조되지 않았는지만 검증을 하고 데이터베이스 조회도 할 필요 없이 바로 사용자임을 신뢰하고 처리할 수 있기 때문입니다.<br>
- 추가적으로, 토큰을 사용하면 로그인시에 페이스북, 구글과 같은 플랫폼에서 토큰을 발급해주면 토큰을 기반으로 권한을 공유하게 되어 유저가 서비스를 사용할 수 있게 됩니다.
<br>

```javascript

//routes 폴더에서 index.js에서 jsonwebtoken을 가져와서 사용하고 싶을때 사용한다
var jwt = require('jsonwebtoken');

//쿠키에서 accessToken 라는 쿠키이름을 가져와서 token 변수에 저장한다
const token = req.cookies['accessToken'];

//토큰과 jwtSecret을 통해 검증
tokenResult = jwt.verify(token, jwtSecret)

//get으로 index 페이지를 불러올 때 사용한다
router.get("/mymap", (req, res, rows) => {               
  res.render("index", { title: "Express" });
});

// 쿠키를 출력해서 확인해보고 싶을때 사용한다
console.log(req.cookies);
// 쿠키이름이 accessToken인 값을 token 변수에 넣고 jwt_decode를 통해 토큰을 디코딩한다
var token = req.cookies['accessToken'];
var decoded = jwt_decode(token);

//헤더에 있는 쿠키값을 가져와서 jwtSecret 비밀키와 검증을 해서 인증을 한다  
const token = req.headers.cookies.user
const tokenResult = jwt.verify(token, jwtSecret)
console.log(tokenResult)

// 헤더에 있는 토큰을 디코딩해서 출력한다 
var decodedHeader = jwt_decode(token, { header: true });
console.log(decodedHeader); 
//예시) 아래와 같은 형식으로 출력된다.
/* prints:
 * { typ: "JWT",
 * alg: "HS256" }
 */

//토큰을 디코딩해서 값을 확인한다
var token = "eyJhbGciOiJIUzI1NiJ9.eyJ1c2VyX251bSI6MSwidXNlcl9pZCI6ImVlIiwibmFtZSI6ImVlIn0.HEURm4BIXp6YogTzNPIAKu5tcHrFB9hQVhKKV157xnw";
var decoded = jwt-decode(token);
console.log(decoded);



```


## Controller - APIs

<details>
<summary>login controller</summary>
signin       		  //로그인 		<br>("/auth/login")<br><br>
logout       		  //로그아웃		<br>("/auth/logout")
</details>

<details>
<summary>res controller</summary>
getResInfo 	  	  // 가게 정보를 담은 페이지			<br>("/res/{id}/resinfo")<br><br>
getResThumbnail     	  // Thumbnail 정보를 담은 페이지		<br>("/res/{id}/thumbnail")<br><br>
getAllResThumbnail  	  // 거리별(default) 모든 Thumbnail		<br>("/res/thumbnail/{xValue},{yValue}/{limit}")<br><br>
getResThumbnailByCategory   // 카테고리별				<br>("/res/thumbnail/category/{category}/{xValue},{yValue}")<br><br>
getResThumbnailByAvgStar    // 별점순 				<br>("/res/thumbnail/avgstar/{limit}/{xValue},{yValue}") <br><br>
getResThumbnailByResName   // 이름 검색				<br>("/res/thumbnail/search/{name}/{xValue},{yValue}/{limit}")<br><br>
getResThumbnailByLocation    // 지역 검색				<br>("/res/thumbnail/region/{region}/{limit}")<br><br>
getUserByRes    		   // 해당 가게를 리스트에 넣은 유저 반환	<br>("/res/{id}/user")<br><br>
getResOfList    		   // 해당 유저의 모든 리스트 맵으로 반환	<br>("/res/user/{id}/list")<br><br>
insetResToUser   	  	   // reslist 추가				<br>("/res/user/insert")<br><br>
createRes     		   // 가게 정보 저장			<br>("/res/regi")<br><br>
updateRes  		   // 가게 정보 수정			<br>("res/{id}/update")<br><br>
deleteRes    		   // 가게 정보 삭제			<br>("res/{id}/delete")<br><br>
insertImgOfRes  		   // 가게 이미지만 추가			<br>("/res/{id}/update/img")  <br><br>   		  
</details>


<details>
<summary>rev controller</summary>
getReviewList      		// 모든 리뷰 정보 조회			<br>("/rev/{id}/revsinfo")<br><br>
getReview      		// 하나의 리뷰 정보 조회			<br>("/rev/{revNum}/revinfo")<br><br>
createRev     		 // 리뷰 정보 저장				<br>("/rev/regi")<br><br>
updateRev     		 // 리뷰 수정				<br>("/rev/{revNum}/update")<br><br>
deleteReview     		 // 리뷰 삭제				<br>("/rev/{res}/deleteReview/{num}")<br><br>
deleteRevImg     		 // 리뷰 이미지 삭제			<br>("/rev/delete/img")<br><br>
returnAllRevOfRes    	  // 가게에 대한 리뷰 Thumbnail		<br>("/rev/res/{id}")<br><br>
returnResStandard     	 // 가게에 대한 standard 정보		<br>("/rev/res/{id}/standard")<br><br>
returnAllOrderByTime  	 // 시간순으로 12개의 revThumbnail		<br>("/rev/main/nologin")<br><br>
returnAllRevNumOfUser  	 // 회원의 모든리뷰 수			<br>("/rev/user/cnt")<br><br>
</details>

<details>
<summary>user controller</summary>
createUser    		  // 회원 가입<br>("/user/regi")<br><br>
updateUser    		  // 유저 정보 수정<br>("/user/update")<br><br>
deleteUserStandardUser 	  // 회원 기준 삭제<br>("/user/userstandard")<br><br>
deleteUser     		  // 회원 삭제<br>("/user")<br><br>
deleteUserImg   		  // 회원 이미지 삭제<br>("/user/img")<br><br>
createUserStandard  	  // 회원 기준 저장<br>("/user/regiNewStandard")<br><br>
returnUserResList     	  // 회원의 list 이름들 반환 (list에 저장용)<br>("/user/reslist/")<br><br>
getUserIdOfFamousUser 	  // 인기 많은 유저의 아이디와 닉네임<br>("/user/popular")<br><br>
getResOfList    		  // 내 리스트 불러오기<br>("/user/list")<br><br>
getUserCountByList   	  // 내가 작성한 리스트 카운트 불러오는거<br>("/user/count/list")<br><br>
getUserInfo     		  // 내 전체 정보 가져오기<br>("/user/info")("/user/count/list/{id}")<br><br>
getResOfList    		  // 회원 리스트 불러오기<br>("/user/list/{id}")<br><br>
getUserCountByList  	  // 리스트 카운트 불러오기<br>("/user/count/list/{id}")<br><br>
getFoodTypeByReview	  // 선호 food_type 갯수로 내림 차순<br>("/user/{userNum}/userAnalysis/foodType")<br><br>
getAllOrderByUserNumNStar   // 유저 당 리뷰를 별점순으로 반환<br>("/res/user/review/writeDate")나의 페이지<br><br>
getAllOrderByUserNumNDate  // 유저 당 리뷰를 시간순으로 반환<br>("/res/user/review/Star")나의 페이지<br><br>
getAllOrderByUserNumNStar   // 유저 당 리뷰를 별점순으로 반환<br>("/res/user/review/writeDate/{id}")타인의 페이지<br><br>
getAllOrderByUserNumNDate  // 유저 당 리뷰를 시간순으로 반환<br>("/res/user/review/Star/{id}")타인의 페이지<br><br>
</details>

## Reference 

#### :bento: 프로젝트 진행 과정 
https://drive.google.com/drive/folders/1-G8_Bw57G7msK3BAfzhLRIw_ZSRumXYm

#### :bagel: 프로젝트 관련 참고 내용 1.
https://kowo1001.github.io/project/springboot-%ED%94%84%EB%A1%9C%EC%A0%9D%ED%8A%B8-%EC%B0%B8%EA%B3%A0%EB%82%B4%EC%9A%A9/
#### :bagel: 프로젝트 관련 참고 내용 2.
https://ta-ye.github.io/study/#Vue.js

### :bagel: 로고
https://www.flaticon.com/


## Contributors

<div>
&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;<a href="https://github.com/Ta-Ye"><img src="https://avatars.githubusercontent.com/u/72329183?s=64&v=4" width="40"/></a>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;<a href="https://github.com/kowo1001"><img src="https://avatars.githubusercontent.com/u/37354978?s=64&v=4" width="40"/></a>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;<a href="https://github.com/YeonjiKim0316"><img src="https://avatars3.githubusercontent.com/u/73862452?s=460&u=6091225c2e241fcef51c99e69c772b845aa03073&v=4" width="40" /></a> &#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;<a href="https://github.com/YUNHYE-0107"><img src="https://avatars.githubusercontent.com/u/73386460?s=64&v=4" width="40"/></a>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;<a href="https://github.com/jmlee0415"><img src="https://avatars.githubusercontent.com/u/61037197?s=64&v=4" width="40"/></a>&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;&#160;<a href="https://github.com/minyeong-park"><img src="https://avatars.githubusercontent.com/u/74331913?s=64&v=4" width="40"/></a><br>
<a href="https://github.com/Ta-Ye">Ta-Ye(최태열)</a>&#160;
<a href="https://github.com/kowo1001">kowo1001(장종욱)</a> <a href="https://github.com/YeonjiKim0316">YeonjiKim(김연지)</a> <a href="https://github.com/YUNHYE-0107">YUNHYE-0107(조윤혜)</a>&#160;
<a href="https://github.com/jmlee0415">jmlee0415(이정민)</a>&#160;<a href="https://github.com/minyeong-park">minyeong-park(박민영)</a>

