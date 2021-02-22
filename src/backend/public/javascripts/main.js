// 지도2 start
const mapOptions = {
    center: new naver.maps.LatLng(37.3595704, 127.105399),
    zoom: 10
};

const map = new naver.maps.Map("map", mapOptions);

// 리스트이름이 같은 가게를 모두 가져오기
// $.ajax({
//     url: "/location/resnum",
//     type: "GET",
// }).done((response) => {
//     //데이터가 없다면 리턴
//     if (response.message !== "success") return;
//     // 데이터가 있다면 data에 넣어줌
//     const data = response.data;
    
//     let ListName = [];
//     let res_num = [];

//     const getClickHandler = (i) => () => {
//         const marker = ListName[i];
//         const infowindow = infowindowList[i];
//         // 지도위에 마커가 표시되어 있으면 마커를 클릭했을 때 인포윈도우가 사라져야함
//         if ( infowindow.getMap()) {
//             infowindow.close();
//         } else {
//             // 닫혀있으면 그 마커 위에 인포윈도우 표시
//             infowindow.open(map, marker);
//         }
//     };

$.ajax({
    url: "/location",
    type: "GET",
}).done((response) => {
    //데이터가 없다면 리턴
    if (response.message !== "success") return;
    // 데이터가 있다면 data에 넣어줌
    const data = response.data;
    
    let markerList = [];
    let infowindowList = [];

    const getClickHandler = (i) => () => {
        const marker = markerList[i];
        const infowindow = infowindowList[i];
        // 지도위에 마커가 표시되어 있으면 마커를 클릭했을 때 인포윈도우가 사라져야함
        if ( infowindow.getMap()) {
            infowindow.close();
        } else {
            // 닫혀있으면 그 마커 위에 인포윈도우 표시
            infowindow.open(map, marker);
        }
    };
    // 위와 같은 포멧 코드
    // function getClickHandler(i) {
    //     return function() {

    //     }
    // }

    function removeMarker() {
        for (let i = 0; i < markerList.length; i++) {
            markerList[i].setMap(null);
        }
        
        markerList = [];
    }

    //data1에 있는 여러개의 마커를 동시에 표시
    for (let i in data) {
        const target = data[i];
        const latlng = new naver.maps.LatLng(target.x_value, target.y_value);

        let marker = new naver.maps.Marker({
            map: map,
            position: latlng,
            icon: {
                content: `<div class="marker"></div>`,
                anchor: new naver.maps.Point(7.5, 7.5),//마커의 넓이와 높이의 1/2로 설정
            }
        });
        // SELECT res_address, res_name, x_value, y_value
        //입력한 title과 address를 content에 저장
        const content = `
            <div class="infowindow_wrap">
                <div class="infowindow_title">${target.res_name}</div>
                <div class="infowindow_address">${target.res_address}</div>
            </div>`;
        
        const infowindow = new naver.maps.InfoWindow({
            content : content,
            backgroundColor: "#00ff0000",
            borderColor: "00ff0000",
            anchorSize: new naver.maps.Size(0, 0),
        });

        // 생성한 마커들을 markerList안에 push로 담아줍니다
        markerList.push(marker);
        infowindowList.push(infowindow);
    }

    

    //i를 파라미터로 받고 function을 리턴
    const getClickMap = (i) => () => {
        //인포윈도우 리스트에서 i번째를 가져옴
        const infowindow = infowindowList[i];
        infowindow.close();
    }

    // 각각의 i번째에 해당하는 마커를 클릭했을 때 getClickHandler라는 함수가 실행되게 하는 코드
    for (let i = 0, ii = markerList.length; i < ii; i++) {
        naver.maps.Event.addListener(markerList[i], "click", getClickHandler(i));
        //i번째 마커를 클릭했을 때 i번째 인포윈도우를 띄어주는 함수 필요

        //맵을 클릭했을 때 인포윈도우 close
        naver.maps.Event.addListener(map, "click", getClickMap(i));
    }

    // 세개로 분리하는 이유는 마커 모인 개수마다 다른 클러스터를 적용하기 위함
    //마커 개수 10개 이하는 cluster1 진행
    const cluster1 = {
        content: `<div class="cluster1"></div>`,
    };
    //마커 개수 10개 이상 100개 이하는 cluster2 진행
    const cluster2 = {
        content: `<div class="cluster2"></div>`,
    };
    //마커 개수 100개 이상일 때는 cluster3 진행
    const cluster3 = {
        content: `<div class="cluster3"></div>`,
    };

    // 클러스터링 기능 
    const markerClustering = new MarkerClustering({
        minClusterSize: 2,
        //12로 줌 레벨 최대로 설정
        maxZoom: 12,
        map: map,
        markers: markerList,
        // 클러스터를 클릭했을때 줌이 되면서 해당 마커들을 조금 더 잘 확인할 수 있도록 하는 기능(disableClickZoom: false)
        disableClickZoom: false,
        // 클러스터의 영역을 설정 ( gridSize가 작으면 작을 수록 좀더 세분화된 클러스터를 만들 수 있음)
        // (gridSize값이 크면 클수록 더 넓은 영역의 클러스터를 만들 수 있음- 평균값100, 마커 개수 작으면 20으로 주게됨)
        gridSize: 20,
        // 위에 만들었던 cluster1,cluster2,cluster3 활용
        icons: [cluster1, cluster2, cluster3],
        // indexGenerator는 숫자에 맞게 클러스터가 생성되게 함 
        // 예 ) 마커의 개수가 2개 이상 4개 이하일 때는 cluster1이 발생
        // 예 ) 마커의 개수가 5개 이상 9개 이하일 때는 cluster2가 발생
        // 예 ) 마커의 개수가 10개 이상일 때는 cluster3가 발생
        indexGenerator: [2, 5, 10 ],
        //stylingFunction을 통해서 단순하게 클러스터만 보여주는게 아닌 클러스터 안에 몇개의 마커가 있는지 시각적으로 표현하는 기능
        stylingFunction: (clusterMarker, count) => {
            $(clusterMarker.getElement()).find("div:first-child").text(count);
        },
    });
});

// 행정구역 데이터 레이어 소개 및 표시하기
// const urlPrefix = "https://navermaps.github.io/maps.js/docs/data/region";
// const urlSuffix = ".json";

// //지역을 담는 배열 
// let regionGeoJson = [];
// // for문을 돌때 17개까지 요청을 해야되기 때문에 그것을 count해주는 변수를 만듬
// let loadCount = 0;

// const tooltip = $(
//     `<div style="position:absolute;z-index:1000;padding:5px 10px; background:white;border:1px solid black;font-size:14px;display:none;pointer-events:none;"></div>`
//     )

// tooltip.appendTo(map.getPanes().floatPane);

// naver.maps.Event.once(map, "init_stylemap", () => {
//     for (let i = 1; i < 18; i++) {
//         let keyword = i.toString();
//         //키워드가 한자리 수일 때 1
//         // 1이면 01로 생성, 2이면 02로 생성
//         if (keyword.length === 1) {
//             keyword = "0" + keyword; //keyword가 01,02,03 ~ 17까지 생성됨
//         }
//         $.ajax({
//             url : urlPrefix + keyword + urlSuffix, //url에 17번 요청이 들어감-> 17개에 대한 결과값이 regionGeoJson 빈 배열에 들어가게됨
//         }).done((geojson) => {
//             regionGeoJson.push(geojson);// 빈배열에 모두 다 찼을때는 startDataLayer함수를 통해서 정보를 바탕으로 해당 지도에 결과값을 띄우게됨
//             loadCount++;
//             //17개의 도를 다 돌았을때 startDataLayer 함수 실행
//             if (loadCount === 17){
//                 startDataLayer();
//             }
//         })
//     }
// })

// function startDataLayer() {
//     map.data.setStyle(feature => {
//         // 행정구역 도 17개 중 클릭을 하지 않았을때 아래 스타일 적용
//         const styleOptions = {
//             // fillColor : "#ff0000",
//             // fillOpacity: 0,
//             // strokeColor: "#ff0000",
//             // strokeWeight: 2, // 두꺼운 외곽선
//             // strokedOpacity: 0.4,
//         };
//         // 행정구역 도  17개 중 클릭했을때 아래 스타일 적용
//         if(feature.getProperty("focus")) {
//             // styleOptions.fillOpacity = 0
//             // styleOptions.fillColor = "#0f0";
//             // styleOptions.strokeColor = "#0f0";
//             // styleOptions.strokeWeight = 4;
//             // styleOptions.strokedOpacity = 1;
//         }

//         return styleOptions;
//     }); 
//     //결과값을 기반으로 도의 경계선을 표시
//     regionGeoJson.forEach((geojson) => {
//         map.data.addGeoJson(geojson);
//     });

//     map.data.addListener("click",(e) => {
//         let feature = e.feature;
//         // 클릭이 되어있지 않다면
//         if (feature.getProperty('focus') !== true) {
//             // 해당 도가 focus가 되어 있다는 것을 명시
//             feature.setProperty("focus", true);
//         } else {
//             feature.setProperty("focus", false);
//         }
//     });

//     // 도 17개 대상으로 마우스가 올라갔을때/벗어났을때 이벤트 적용
//     map.data.addListener("mouseover", (e) => {
//         let feature = e.feature;
//         //해당 도의 이름이 regionName에 담기게됨 
//         let regionName = feature.getProperty("areal");
//         tooltip.css({
//             display : "block",
//             left : e.offset.x, //x축 받아옴
//             top : e.offset.y,  //y축 받아옴
//         })
//         .text(regionName);
//         // mouseover된 구역의 스타일링을 재정의함
//         map.data.overrideStyle(feature, {
//             // fillOpacity : 0.6,
//             // strokeWeight : 4,
//             // strokedOpacity :1,
//         });
//     });
//     // 마우스가 도를 벗어났을때 스타일링 적용
//     map.data.addListener("mouseout", (e) => {
//         tooltip.hide().empty();
//         map.data.revertStyle();
//     });
// }






