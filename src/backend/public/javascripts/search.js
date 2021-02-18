// 목적어를 입력하고 검색 버튼을 눌렀을때 목적지를 지도위에 마커로 표시하는 기능 구현 
// 네이버에서는 주소를 통해서 좌표를 제공해주는 api를 제공
// 카카오에서는 키워드 검색을 통해서 목적지를 검색하는 api를 제공
// 카카오에서 제공하는 api를 통해서 목적지 검색을 구현하고자함

const mapOptions = {
  center: new naver.maps.LatLng(37.3595704, 127.105399),
  zoom: 10
};

var map = new naver.maps.Map('map', mapOptions);

var markerList = [];
var infowindowList = [];

for (var i in data) {
  var target = data[i];
  var latlng = new naver.maps.LatLng(target.lat, target.lng); //data의 모든 위도 경도 
  marker = new naver.maps.Marker({
    map : map,
    position : latlng, //위도 경도 위치 표시 
    icon : {
      content : "<div class='marker'></div>", // css 마커 적용
      anchor: new naver.maps.Point(12, 12),// 아이콘 중심 좌표 변경 with,height 반값으로 표기 //임의의 customized된 자표를 쓰기 위해 중심 좌표를 설정
      //현재 12, 12로 설정. 만약 가로 세로가 30 픽셀로 아이콘이 설정 된다 ==> 절반인 15라고 적어 주면 된다
    },
  });

  var content = `<div class='infowindow_wrap'>
      <div class='infowindow_title'>${target.title}</div>
      <div class='infowindow_content'>${target.content}</div>
      <div class='infowindow_date'>${target.date}</div>
    </div>`

    var infowindow = new naver.maps.InfoWindow({
      content : content,
      // 배경색이 투명한 색상으로 나옴
      backgroundColor : "#00ff0000", 
      borderColor : "#00ff0000",
      // anchorSize는 말풍선 형태로 꼬리가 달리게 되는것
      anchorSize : new naver.maps.Size(0,0)
      //꼬리표가 나오지 않도록 설정함
    })
    //클릭했을 때 몇번째 마커를 클릭했고, 몇번째 윈도우를 표시해줘야 되는지 List에 담기게 됨으로써 그것들을 바탕으로 구현하기 쉽게 해주는 코드
    markerList.push(marker);
    infowindowList.push(infowindow);
}
//클릭 이벤트 부여 과정
// 변수 i가 0부터 1까지 두번은 돌게 됩니다.
for (var i = 0, ii = markerList.length; i < ii; i++) {
  // 마커를 클릭하지 않고 지도를 클릭했을때 infowindow가 사라지는 코드
  // 맵을 클릭했을 때 함수가 호출되는 기능 구현
  naver.maps.Event.addListener(map,"click", ClickMap(i));
  //네이버 지도에 클릭 이벤트 설정
  //markerList i번째에는 우리가 누르게 되는 마커의 순서가 담기게 될 예정
  // 이후에 마커를 클릭했을때  getClickHandler(i)라는 함수를 실행시키는 이벤트를 실행하도록 하겠다고 설정하는 단계
  naver.maps.Event.addListener(markerList[i],"click", getClickHandler(i));
}

//맵을 클릭했을때 해당 marker가 열어 놓은 infowindow를 찾아서 닫아주는 함수를 호출
function ClickMap(i) {
  return function() {
    var infowindow = infowindowList[i];
    infowindow.close()
  }
}

function getClickHandler(i) {
  return function() {
    var marker = markerList[i];
    var infowindow = infowindowList[i];
    // 마커 윈도우를 지도위에 표시하는 코드
    // 마커를 클릭하면 infowindow가 나타나야하고 ,
    // infowindow가 띄어져 있는 상황에서는 marker를 클릭했을때 infowindow가 닫히는 기능 구현
    // 그렇기 때문에 infowindow가 지금 지도위에 표시가 되어있는지 표시 되어있지 않은지 알고 있어야함(if문법으로 구현)
    if (infowindow.getMap()) {
      infowindow.close();
    } else {
      infowindow.open(map, marker);
    }
  }
}
//현재 위치 여러번 클릭시 애니매이션 여러번 적용되는 문제 발생
//현재 위치 여러번 클릭해도 marker가 최초 1번 실행되게함 
//currentUse가 true일 때만 마커 생성
let currentUse = true;

// 현재위치 Box를 클릭했을때 trigger가 발생해서 안에 있는 코드들이 실행됨
$("#current").click(()=> {
  // alert("클릭 이벤트");
  //현재 위치를 지도 위에 표시히는 코드
  if ("geolocation" in navigator) {
    navigator.geolocation.getCurrentPosition(function (position) {
      const lat = position.coords.latitude;
      const lng = position.coords.longitude;
      const latlng = new naver.maps.LatLng(lat, lng);
      if (currentUse) {
        marker = new naver.maps.Marker({
        map: map,
        position: latlng,
        icon : {
          content : '<img class="pulse" draggable="false" unselectable="on" src="https://myfirstmap.s3.ap-northeast-2.amazonaws.com/circle.png">',
          anchor: new naver.maps.Point(11,11),
        }
      });
      currentUse = false;
      }

      //현재위치에 줌이 되고 현재 위치로 이동하는 코드
      // 줌 레벨 14, 이동하는 애니메이션은 주지 않겠다는 false
      map.setZoom(14, false);
      map.panTo(latlng);
    });
  } else {
    alert("위치정보 사용 불가능")
  }
});

//ps는 목적지 검색에 있어서 중요한 역할을 함
//카카오 API를 통해서 선언함
let ps2 = new kakao.maps.services.Places();
let search_arr = []

$("#search_input").on("keydown",function(e){
  //키보드로 엔터를 쳤을 때
  if(e.keyCode === 13) {
    //content에 검색한 내용(목적지)을 담음
    let content = $(this).val();
    // content를 통해서 api를 요청하게 되고 이에 대한 결과값이 placeSearchCB라는 함수를 통해서 처리됨
    ps2.keywordSearch(content,placeSearchCB)
  }
})

// 버튼을 클릭했을 때 검색 기능 적용
$("#search_button").on("click", function(e){
  let content = $("#search_input").val()
  ps2.keywordSearch(content,placeSearchCB);
})

// data에는 목적지를 바탕으로 검색한 결과가 나옴
// status는 api를 카카오서버를 이용하기 때문에 서버 상태를 받아옴
// pagination은 검색 결과가 얼마나 있는지 번호를 통해서 알 수 있게 됨
// 위도 : 127.05285401582 경도 : 37.5968011678013
function placeSearchCB(data, status, pagination) { 
  if (status === kakao.maps.services.Status.OK) {
    let target = data[0];
    const lat = target.y; //위도
    const lng = target.x; // 경도
    const latlng = new naver.maps.LatLng(lat, lng);
    marker = new naver.maps.Marker({
      position: latlng,
      map: map
    })
    // 한개의 목적지 마커가 지도위에 표시되어야하는데, 목적지를 검색할때마다 marker가 표시되는 에러 해결
    if (search_arr.length == 0) {
      search_arr.push(marker)
    } else {
      search_arr.push(marker)
      // 이전 검색된 목적지를 추출 
      let pre_marker = search_arr.splice(0, 1);
      //이전 검색된 목적지를 삭제
      pre_marker[0].setMap(null);
    }
    // 검색된 목적지에 줌 인(Zoom-in)을 하고자함
    map.setZoom(14, false);
    map.panTo(latlng);
  } else {
    alert("검색결과가 없습니다.")
  }
}

// 지도1 end
