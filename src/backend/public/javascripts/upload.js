const mapContainer = document.getElementById("map");
const mapOption = {
    center : new daum.maps.LatLng(37.554477, 126.970419),
    // 지도 확대 레빌 3 
    level : 3,
};
// 지도를 띄우기
let map = new daum.maps.Map(mapContainer, mapOption);

// 인포윈도우 zindex:1로 설정함으로써 지도보다 위에 띄어질수 있게함
let infowindow = new daum.maps.InfoWindow({
    zIndex: 1,
});

// 몇번째 마커가 들어가는지 빈 배열로 markerList 설정
let markerList = [];

let ps = new daum.maps.services.Places();

searchPlaces();

// searchPlaces는 키워드를 받고 이를 검색하는 함수
function searchPlaces() {
    // id가 keyword인 값을 keyword 변수에 넣음
    let keyword = $("#keyword").val();
    // keywordSearch 함수는 keyword를 바탕으로 검색을 하고 검색결과를 placesSearchCB 함수를 통해서 콜백해줌
    ps.keywordSearch(keyword, placesSearchCB);
}

// 결과값이 data로 나오고, 결과값을 반환하기 위한 서버 상태를 status에 넣음
function placesSearchCB(data, status) {
    if ( status === daum.maps.services.Status.OK ) {
        displayPlaces(data);
    }else if ( status === daum.maps.services.Status.ZERO_RESULT) {
        alert("검색 결과가 존재하지 않습니다")
        return;
    }else if (status === daum.maps.services.Status.ERROR) {
        alert("검색 결과중 오류가 발생했습니다")
        return;
    }   
}

// 검색된 데이터값을 통해서 리스트 형태의 뷰를 만들고 결과값을 마커로 표시하는 기능
function displayPlaces(data) {
    let listEL = document.getElementById("placesList");
    // 키워드를 검색했을때 해당 영역을 보여주는 기능 (마커영역 계산->영역들이 모두 표시됨)
    let bounds = new daum.maps.LatLngBounds();
    
    //기존에 있던 리스트,마커들을 제거
    removeAllChildNodes(listEL);
    removeMarker();

    
    for (let i = 0; i < data.length; i++) {
        let lat = data[i].y;
        let lng = data[i].x;
        let address_name = data[i]["address_name"];
        let place_name = data[i]["place_name"];

        const placePosition = new daum.maps.LatLng(lat, lng);
        bounds.extend(placePosition);

        let marker = new daum.maps.Marker({
            position: placePosition,
        });

        marker.setMap(map);
        markerList.push(marker);

        //결과값을 리스트 형태로 만듬
        const el = document.createElement("div");
        const itemStr = `
            <div class = "info">
                <div class = "info_title">
                    ${place_name}
                </div>
                <span>${address_name}</span>
                <button></button>
            </div>
            `;

            el.innerHTML = itemStr;
            el.className = "item";

            //마커를 클릭했을 때 인포윈도우가 생성되어야 하고 맴을 클릭했을 때는 인포윈도우가 닫아야함
            // 결과값을 클릭했을때도 인포윈도우가 열리고 닫혀야함
            daum.maps.event.addListener(marker, "click", function() {
                displayInfowindow(marker, place_name, address_name, lat, lng);
            });

            daum.maps.event.addListener(map, "click", function (){
                infowindow.close();
            });

            el.onclick = function() {
                displayInfowindow(marker, place_name, address_name, lat, lng);
            };

            // 검색 결과가 들어가야할 자리인 listEl에 넣어줌
            listEL.appendChild(el);
    }
    // map이 bounds 영역으로 이동하게끔 만듬
    map.setBounds(bounds);
}

function displayInfowindow(marker, title, address, lat, lng) {
    let content = `
        <div style="padding:25px;">
            ${title}<br>
            ${address}<br>
            <button onclick="onSubmit('${title}','${address}',${lat},${lng});">등록</button>
        </div>
        `;
        //인포윈도우를 보여줄 때 그 위치로 이동 
        map.panTo(marker.getPosition());
        //작성된 content를 넣어줌
        infowindow.setContent(content);
        infowindow.open(map, marker);
}

function onSubmit(title, address, lat, lng) {
    $.ajax({
        url: "/location",
        data: {title, address, lat, lng},
        type: "POST", 
    })
    .done((response) => {
        console.log("데이터 요청 성공");
        alert("성공");
    })
    .fail((error) => {
        console.log("데이터 요청 실패");
        alert("실패");
    });
}

// placesList 안에 있는 노드, 태그들을 제거함
function removeAllChildNodes(el) {
    while(el.hasChildNodes()) {
        el.removeChild(el.lastChild);
    }
}

//검색을 할때마다 초기화를 해서 이전에 검색된 정보를 초기화 해주는 기능(이전 검색된 마커 삭제)
//removeMarker은 마커들을 모두 제거하는 함수
function removeMarker() {
    for (let i = 0; i < markerList.length; i++) {
        markerList[i].setMap(null);
    }
    markerList = [];
}


// 검색창에 키워드를 입력하고 검색하기 눌렀을때 결과값이 리스트로 나오고 마커를 지도위에 표시함

//등록 버튼을 클릭했을때 해당 주소, 장소명, 위도, 경도 등이 MongoDB로 저장될 수 있도록 하는 함수 


//MongoDB에 저장되어 있는 위치정보 데이터를 메인페이지에 띄우는 기능


